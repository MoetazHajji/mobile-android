package tn.esprit.androidproject.ProfileUser;

import java.util.Date;
import java.util.List;

public class PositionModel {
    private int id;
    private String title;
    private String companyName;
    private String location;
    private Date startDate;
    private Date endDate;
    private String industry;
    private String description;
    private List<String> skills;

    public PositionModel() {
    }

    public PositionModel(int id, String title, String companyName, String location, Date startDate, Date endDate, String industry, String description, List<String> skills) {
        this.id = id;
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.industry = industry;
        this.description = description;
        this.skills = skills;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getIndustry() {
        return industry;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
