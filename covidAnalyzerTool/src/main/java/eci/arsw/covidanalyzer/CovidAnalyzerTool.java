package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool {
	
    public static ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    public static AtomicInteger amountOfFilesProcessed;
	public static Object monitor;
	public static boolean pause;
	private static ArrayList<CovidAnalyzerToolThread> hilos;
	private int numeroHilos;

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
		numeroHilos=5;
		hilos= new ArrayList<CovidAnalyzerToolThread>();
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
		int inicio=0;
		int fin=numeroHilos;
		int repartidos=amountOfFilesTotal/numeroHilos;
		for (int i=0;i<numeroHilos;i++){
			if((i+1==numeroHilos)&&fin<amountOfFilesTotal){
				fin=amountOfFilesTotal;
			}
			CovidAnalyzerToolThread nuevoHilo= new CovidAnalyzerToolThread(resultFiles.subList(inicio,fin));
			inicio=fin;
			fin= fin + repartidos;
			nuevoHilo.start();
			hilos.add(nuevoHilo);
		}
		
		
        for (File resultFile : resultFiles) {
            List<Result> results = testReader.readResultsFromFile(resultFile);
            for (Result result : results) {
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();
        }
    }

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
		covidAnalyzerTool.processResultData();
		System.out.println("Presione Enter si desea pausar y ver resultados parciales");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            if (line.contains("exit")){
				if (!pause==false){
					pause=true;
				}
				else if (pause==true){
					pause=false;
				}
				for (CovidAnalyzerToolThread hilosCovid : hilos){
					hilosCovid.continuar();
				}
				break;
			}
            String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
            Set<Result> positivePeople = covidAnalyzerTool.getPositivePeople();
            String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
            message = String.format(message, covidAnalyzerTool.amountOfFilesProcessed.get(), covidAnalyzerTool.amountOfFilesTotal, positivePeople.size(), affectedPeople);
            System.out.println(message);
        }
    }
}