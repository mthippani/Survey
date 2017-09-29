package com.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.survey.domain.Question;
import com.survey.repository.QuestionRepository;

/**
 * Created with IntelliJ IDEA.
 * User: Mahesh
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuestionController {

    @Autowired
    protected QuestionRepository questionRepository;

    /**
     * Get all the questions
     * @return
     */
    @RequestMapping(value="/questions")
    public Iterable<Question> questions() {
        return questionRepository.findAll();
    }


    /**
     * Get the Question object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/question/{id}")
    public Question get(@PathVariable("id") Integer id) {
        return questionRepository.findOne(id);
    }


    /**
     * Delete the question object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {

        try {
            questionRepository.delete(id);
            return String.format("Question [%s] successfully deleted", id);
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Question [%s]", e.getMessage());
        }
    }

    /**
     * Created the new question
     * @param question
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/question/", method = RequestMethod.POST)
    public ResponseEntity<?> createQuestion(@RequestBody Question question, UriComponentsBuilder ucBuilder) {
        try{
            questionRepository.save(question);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/question/{id}").buildAndExpand(question.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Error occurred while creating question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the question based on the given details
     * @param id
     * @param question
     * @return
     */
    @RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateQuestion (@PathVariable("id") Integer id, @RequestBody Question question) {
        try{
            Question questionInstance=questionRepository.findOne(id);
            if (questionInstance == null) {
                return new ResponseEntity("Failed", HttpStatus.NOT_FOUND);
            }
            questionInstance.setQuestion(question.getQuestion());
            questionInstance.setSurvey(question.getSurvey());
            questionInstance.setAnswers(question.getAnswers());
            questionRepository.save(questionInstance);
            return new ResponseEntity<Question>(questionInstance, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Error occurred while updating question", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
