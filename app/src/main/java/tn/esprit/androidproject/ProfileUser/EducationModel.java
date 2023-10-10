package tn.esprit.androidproject.ProfileUser;

import java.util.Date;
import java.util.List;

public class EducationModel {
    private int id;
    private String school;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private String grade;
    private String activities;
    private String description;
    private List<String> skills;

    public EducationModel() {
    }

    public EducationModel(int id, String school, String degree, String fieldOfStudy, Date startDate, Date endDate, String grade, String activities, String description, List<String> skills) {
        this.id = id;
        this.school = school;
        this.degree = degree;
        this.fieldOfStudy = fieldOfStudy;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
        this.activities = activities;
        this.description = description;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public String getDegree() {
        return degree;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getGrade() {
        return grade;
    }

    public String getActivities() {
        return activities;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
