package eci.arsw.covidanalyzer.service;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service("ICovidAggregateServiceStub")
public class ICovidAggregateServiceStub implements iCovidAggregateService{
	
	
	
    /**
     * Add a new result into the specified result type storage.
     *
     * @param result
     * @param type
     * @return
     */
	 @Override
    public boolean aggregateResult(Result result, ResultType type){
		
	}

    /**
     * Get all the results for the specified result type.
     *
     * @param type
     * @return
     */
	 @Override
    public boolean getResult(ResultType type){
		
	}

    /**
     * 
     * @param id
     * @param type
     */
	@Override
    public void upsertPersonWithMultipleTests(UUID id, ResultType type){
		boolean found = false;
		for (Result result:results){
			if (result.equals())
		}
	}


}
