package eci.arsw.covidanalyzer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;

/**
 * ---------------------------------------------------------------------------------------------------------------------------
 * ---------------------------------------------------------------------------------------------------------------------------
 * 													CovidAggregateService
 * ---------------------------------------------------------------------------------------------------------------------------
 * 
 * ---------------------------------------------------------------------------------------------------------------------------
 * @author Santiago Buitrago
 * ---------------------------------------------------------------------------------------------------------------------------
 */
 
@Service("CovidAggregateService")
public class CovidAggregateService implements ICovidAggregateService {
	
	
	private CacheInterface<Result> cache;
	private Map<UUID, Integer> pruebas;
	
	public CovidAggregateService() {
		cache = new Cache<Result>();
		pruebas = new HashMap<UUID, Integer>();
	}

	@Override
	public boolean aggregateResult(Result result, ResultType type) {
		boolean added = true;
		try{
			result.resultType = type;
			cache.addElement(result);
			pruebas.put(result.id, (pruebas.containsKey(result.id)) ? pruebas.get(result.id)+1: 1);
		} catch (Exception e) {
			added = false;
		}
		return added;
	}

	@Override
	public List<Result> getResult(ResultType type) {
		List<Result> resultArr = new ArrayList<Result>();
		for (Result r: cache.getAll()) {
			if (r.resultType.equals(type)) resultArr.add(r);
		}
		return resultArr;
	}

	@Override
	public Result getIndivudualPersonResult(UUID id, ResultType type) {
		Result res = null;
		for (Result r: cache.getAll()) {
			if (r.id.equals(id)) {
				res = r;
				break;
			}
		}
		
		return res;
	}

	@Override
	public void upsertPersonWithMultipleTests(UUID id, ResultType type) {
		
	}
}