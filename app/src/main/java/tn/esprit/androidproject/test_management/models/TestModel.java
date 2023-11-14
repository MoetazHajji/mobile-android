package tn.esprit.androidproject.test_management.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "test")
public class TestModel {

    @PrimaryKey(autoGenerate = true)
    private int idTest;
    @ColumnInfo(name ="test_name")
    private String testName;
    @ColumnInfo(name ="test_date")
    private Date testDate;

    @ColumnInfo(name ="test_image")
    private byte[] quizImage;





    public byte[] getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(byte[] quizImage) {
        this.quizImage = quizImage;
    }

    @Ignore
    public TestModel(int idTest, String testName, Date testDate, byte[] quizImage) {
        this.idTest = idTest;
        this.testName = testName;
        this.testDate = testDate;
        this.quizImage = quizImage;
    }


    @Ignore
    public TestModel(String testName, Date testDate) {
        this.testName = testName;
        this.testDate = testDate;
    }

    public TestModel(int idTest, String testName, Date testDate) {
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

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }
}
