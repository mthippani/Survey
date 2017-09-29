package com.survey.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.survey.domain.Survey;

@RepositoryRestResource
public interface SurveyRepository extends CrudRepository<Survey, Integer> {

}
