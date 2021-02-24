package eci.arsw.covidanalyzer;

import eci.arsw.covidanalyzer.model.Result;
import eci.arsw.covidanalyzer.model.ResultType;
import eci.arsw.covidanalyzer.service.ICovidAggregateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static eci.arsw.covidanalyzer.model.ResultType.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class CovidAggregateController {
    ICovidAggregateService covidAggregateService;

    @Autowired
    @Qualifier("ICovidAggregateService")
    ICovidAggregateService iCovidAggregateService;

    //TODO: Implemente todos los metodos POST que hacen falta.
    /**
     * POST
     * */
    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        try {
            iCovidAggregateService.aggregateResult(result, TRUE_POSITIVE);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        try {
            iCovidAggregateService.aggregateResult(result, TRUE_NEGATIVE);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        try {
            iCovidAggregateService.aggregateResult(result, FALSE_POSITIVE);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.POST)
    public ResponseEntity addTruePositiveResult(Result result) {
        try {
            iCovidAggregateService.aggregateResult(result, ResultType.FALSE_NEGATIVE);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * GET
     * */
    //TODO: Implemente todos los metodos GET que hacen falta.

    @RequestMapping(value = "/covid/result/true-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        try {
            return new ResponseEntity<>(iCovidAggregateService.getResult(TRUE_POSITIVE), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/true-negative", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        try {
            return new ResponseEntity<>(iCovidAggregateService.getResult(TRUE_NEGATIVE), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/false-positive", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        try {
            return new ResponseEntity<>(iCovidAggregateService.getResult(FALSE_POSITIVE), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/covid/result/false-negative", method = RequestMethod.GET)
    public ResponseEntity getTruePositiveResult() {
        try {
            return new ResponseEntity<>(iCovidAggregateService.getResult(FALSE_NEGATIVE), HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return new ResponseEntity<>("ERROR 500", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * PUT
     * */

    @RequestMapping(value = "/covid/result/persona/{id}", method = RequestMethod.PUT)
    public ResponseEntity savePersonaWithMultipleTests() {
        try{
            iCovidAggregateService.upsertPersonWithMultipleTests(result);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return null;
    }


    
}