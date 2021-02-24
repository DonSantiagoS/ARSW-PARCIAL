package eci.arsw.covidanalyzer.model;

import java.util.UUID;

public class Result {
	public UUID id;
    public ResultType resultType;
    public String firstName;
    public String lastName;
    public String gender;
    public String email;
    public String birthString;
    public String testString;
    public boolean result;
    public double testSpecifity;
    @Override
    public String toString() {
    	return id.toString()+" "+firstName+" "+lastName;
    }
}
