package tn.esprit.androidproject.test_management;

import java.util.List;

public class TestModel {

    private int idTest;
    private String testName;
    private String testDate;
    private List<String> quizs;

    private byte[] quizImage;

    public List<String> getQuizs() {
        return quizs;
    }

    public void setQuizs(List<String> quizs) {
        this.quizs = quizs;
    }


    public byte[] getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(byte[] quizImage) {
        this.quizImage = quizImage;
    }

    public TestModel(int idTest, String testName, String testDate, byte[] quizImage) {
        this.idTest = idTest;
        this.testName = testName;
        this.testDate = testDate;
        this.quizImage = quizImage;
    }

    public TestModel(int idTest, String testName, String testDate, List<String> quizs) {
        this.idTest = idTest;
        this.testName = testName;
        this.testDate = testDate;
        this.quizs = quizs;
    }
    public TestModel(String testName, String testDate) {
        this.testName = testName;
        this.testDate = testDate;
    }

    public TestModel(int idTest, String testName, String testDate) {
        this.idTest = idTest;
        this.testName = testName;
        this.testDate = testDate;
    }

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
}
