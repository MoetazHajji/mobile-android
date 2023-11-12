package tn.esprit.androidproject.test_management.services;

import java.util.Arrays;
import java.util.List;

import tn.esprit.androidproject.test_management.TestModel;

public class TestService {

    public static List<TestModel> testModelList = Arrays.asList( new TestModel(1, "Semaine 1", "quiz 1"),
        new TestModel(2, "Semaine 2","tesvsfvdvtModelList 2",Arrays.asList("quiz 1") ),
       new TestModel(3, "Semaine 3", "testsdvsModelList 2", Arrays.asList("quiz 1")),
       new TestModel(4, "Semaine 4", "testdvsModelList 1",Arrays.asList("quiz 1") ),
       new TestModel(5, "Semaine 5", "testdsvsModelList 1",Arrays.asList( "quiz 1")),
       new TestModel(6, "Semaine 6", "testsdvModelList 1",Arrays.asList("quiz 1") ),
       new TestModel(7, "Semaine 7","testMsvdodelList 1",Arrays.asList("quiz 1") )
);
}
