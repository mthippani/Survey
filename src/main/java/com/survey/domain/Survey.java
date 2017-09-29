package com.survey.domain;

/**
 * @author Narasimha
 */


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Survey")
public class Survey
{
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private Date startDate;
    @Column
    private Date endDate;
    @Column
    private String status;

    public Survey()
    {
    }

    public Survey(int id, String name, Date startDate, Date endDate, String status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}

