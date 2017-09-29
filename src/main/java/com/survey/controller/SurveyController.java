package com.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.survey.domain.Survey;
import com.survey.repository.SurveyRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Mahesh
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SurveyController {

    @Autowired
    protected SurveyRepository surveyRepository;

    /**
     * Get all the surveys
     * @return
     */
    @RequestMapping(value="/surveys")
    public Iterable<Survey> surveys() {
        return surveyRepository.findAll();
    }


    /**
     * Get the survey object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/survey/{id}")
    public Survey get(@PathVariable("id") Integer id) {
        return surveyRepository.findOne(id);
    }


    /**
     * Delete the survey object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/survey/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {

        try {
            surveyRepository.delete(id);
            return String.format("Survey [%s] successfully deleted", id);
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Survey [%s]", e.getMessage());
        }
    }

    /**
     * Created the new survey
     * @param survey
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/survey/", method = RequestMethod.POST)
    public ResponseEntity<?> createSurvey(@RequestBody Survey survey, UriComponentsBuilder ucBuilder) {
        try{
            surveyRepository.save(survey);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/com/survey/{id}").buildAndExpand(survey.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Error occured while creating survey", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the survey based on the given details
     * @param id
     * @param survey
     * @return
     */
    @RequestMapping(value = "survey/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSurvey(@PathVariable("id") Integer id, @RequestBody Survey survey) {
        try{
            Survey surveyInstance=surveyRepository.findOne(id);
            if (surveyInstance == null) {
                return new ResponseEntity("Failed", HttpStatus.NOT_FOUND);
            }
            surveyInstance.setName(survey.getName());
            surveyInstance.setStartDate(survey.getStartDate());
            surveyInstance.setEndDate(survey.getEndDate());
            surveyInstance.setStatus(survey.getStatus());
            surveyRepository.save(surveyInstance);
            return new ResponseEntity<Survey>(surveyInstance, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Error occured while updating survey", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
