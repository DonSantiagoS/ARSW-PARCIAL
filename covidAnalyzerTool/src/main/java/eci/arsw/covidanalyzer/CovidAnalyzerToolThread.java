package eci.arsw.covidanalyzer;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CovidAnalyzerToolThread
 * ---------------------------------------------------------------------------------------------------------------------------
 * 
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * ---------------------------------------------------------------------------------------------------------------------------
 */
public class CovidAnalyzerToolThread extends Thread{
	
	private List<File> archivos;
	private TestReader testReader;
	
	public CovidAnalyzerToolThread(List<File> archivos){
		this.archivos=archivos;
		testReader= new TestReader();
	}
	
	@Override
	public void run() {
        for(File archivo : archivos)
        {
            List<Result> tests = testReader.readResultsFromFile(archivo);
            for(Result result : tests)
            {
                synchronized(CovidAnalyzerTool.monitor){
                    if(CovidAnalyzerTool.pause){
                        try{
                            CovidAnalyzerTool.monitor.wait();
                        }catch (InterruptedException e){
                            e.printStackTrace();}
                    }
                }
                CovidAnalyzerTool.resultAnalyzer.addResult(result);
            }
            CovidAnalyzerTool.amountOfFilesProcessed.incrementAndGet();
        }
    }

    public synchronized void continuar(){
        CovidAnalyzerTool.monitor.notifyAll();
    }
	
}
