package com.survey.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.survey.domain.Answer;

@RepositoryRestResource
public interface AnswerRepository extends CrudRepository<Answer, Integer> {

}
