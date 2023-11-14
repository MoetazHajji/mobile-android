package tn.esprit.androidproject.test_management.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.esprit.androidproject.test_management.models.TestModel;

@Dao
public interface TestDao {

    @Query("SELECT * FROM TEST ORDER BY IDTEST")
    List<TestModel> loadAllTests();

    @Insert
    void insertTest(TestModel test);

    @Update
    void updateTest(TestModel test);

    @Delete
    void delete(TestModel test);

    @Query("SELECT * FROM TEST WHERE idTest = :idTest")
    TestModel loadPersonById(int idTest);

}
