package com.survey.controller;

import com.survey.domain.Answer;
import com.survey.domain.Question;
import com.survey.repository.AnswerRepository;
import com.survey.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Narasimha
 */
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnswerController {

    @Autowired
    protected AnswerRepository answerRepository;

    @Autowired
    protected QuestionRepository questionRepository;

    /**
     * Get all the Answers
     * @return
     */
    @RequestMapping(value="/answers")
    public Iterable<Answer> Answers() {
        return answerRepository.findAll();
    }


    /**
     * Get the Answer object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/answer/{id}")
    public Answer get(@PathVariable("id") Integer id) {
        return answerRepository.findOne(id);
    }


    /**
     * Delete the Answer object for given id
     * @param id
     * @return
     */
    @RequestMapping(value = "/answer/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {

        try {
            answerRepository.delete(id);
            return String.format("Answer [%s] successfully deleted", id);
        } catch (Exception e) {
            return String.format("A problem occurred when deleting Answer [%s]", e.getMessage());
        }
    }

    /**
     * Created the new Answer
     * @param answer
     * @param ucBuilder
     * @return
     */
    @RequestMapping(value = "/answer/", method = RequestMethod.POST)
    public ResponseEntity<?> createAnswer(@RequestBody Answer answer, UriComponentsBuilder ucBuilder) {
        try{
            if(answer.getQuestion()!=null){
                Question question=questionRepository.findOne(answer.getQuestion().getId());
                if (question != null) {
                    answer.setQuestion(question);
                }
                answerRepository.save(answer);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/Answer/{id}").buildAndExpand(answer.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("Error occurred while creating answer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update the Answer based on the given details
     * @param id
     * @param answer
     * @return
     */
    @RequestMapping(value = "/answer/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAnswer(@PathVariable("id") Integer id, @RequestBody Answer answer) {
        try{
            Answer answerInstance=answerRepository.findOne(id);
            if(answer.getQuestion()!=null){
                Question question=questionRepository.findOne(answerInstance.getQuestion().getId());
                if (question != null) {
                    answerInstance.setQuestion(question);
                }
            }
            if (answerInstance == null) {
                return new ResponseEntity("Failed", HttpStatus.NOT_FOUND);
            }
            answerInstance.setAnswer(answer.getAnswer());
            answerRepository.save(answerInstance);
            return new ResponseEntity<Answer>(answerInstance, HttpStatus.OK);
        }

        catch (Exception e) {
            return new ResponseEntity<String>("Error occurred while updating Answer", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}