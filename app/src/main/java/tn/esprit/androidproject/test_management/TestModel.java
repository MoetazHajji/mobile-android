package tn.esprit.androidproject.test_management;

public class TestModel {

    private int idTest;
    private String testName;
    private String testDate;

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
