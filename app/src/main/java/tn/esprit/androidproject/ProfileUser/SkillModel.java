package tn.esprit.androidproject.ProfileUser;

public class SkillModel {
    private int id;
    private String skillName;

    public SkillModel() {
    }

    public SkillModel(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
