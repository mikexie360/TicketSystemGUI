package com.example.backend.entity;

import javax.persistence.*;

@Entity
@Table(name="Ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="category")
    private String category;
    @Column(name="report")
    private String report;
    @Column(name="stepsToReproduce")
    private String stepsToReproduce;
//    @Column(name="dateSubmitted")
//    private String dateSubmitted;
//    @Column(name="dateResolved")
//    private String dateResolved;
//    @Column(name="submittedBy")
//    private String submittedBy;
//    @Column(name="resolvedBy")
//    private String resolvedBy;
    @Column(name="isResolved")
    private String isResolved;

    public Ticket() {
    }

    public Ticket(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getStepsToReproduce() {
        return stepsToReproduce;
    }

    public void setStepsToReproduce(String stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }
//
//    public String getDateSubmitted() {
//        return dateSubmitted;
//    }
//
//    public void setDateSubmitted(String dateSubmitted) {
//        this.dateSubmitted = dateSubmitted;
//    }
//
//    public String getDateResolved() {
//        return dateResolved;
//    }
//
//    public void setDateResolved(String dateResolved) {
//        this.dateResolved = dateResolved;
//    }

    public String getIsResolved() {
        return isResolved;
    }

    public void setIsResolved(String isResolved) {
        this.isResolved = isResolved;
    }
//
//    public String getSubmittedBy() {
//        return submittedBy;
//    }
//
//    public void setSubmittedBy(String submittedBy) {
//        this.submittedBy = submittedBy;
//    }
//
//    public String getResolvedBy() {
//        return resolvedBy;
//    }
//
//    public void setResolvedBy(String resolvedBy) {
//        this.resolvedBy = resolvedBy;
//    }
}
