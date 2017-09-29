package com.survey.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Narasimha
 */
@Entity
@Table(name="questions")
 public class Question {

    @Id
    @SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")
    private int id;

    @Column	private String question;

    @OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true, fetch = FetchType.LAZY)
    private java.util.List<Answer> answers;

    @ManyToOne
    @JoinColumn(name ="SURVEY_ID")
    private Survey survey;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_QUESTION_ID", referencedColumnName = "ID")
    private Question parent;

    @OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true, fetch = FetchType.LAZY )
    @JoinColumn(name = "PARENT_QUESTION_ID")
    private List<Question> childrens;

    public Question(){  }

    public Question(int id, String question, Survey survey )
    {
        super();
        this.id = id;
        this.question = question;
        this.survey = survey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Question getParent() {
        return parent;
    }

    public void setParent(Question parent) {
        this.parent = parent;
    }

    public List<Question> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Question> childrens) {
        this.childrens = childrens;
    }
}

