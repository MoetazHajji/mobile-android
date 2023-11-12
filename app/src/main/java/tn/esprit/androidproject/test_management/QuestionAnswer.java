package tn.esprit.androidproject.test_management;

public class QuestionAnswer {

    public static String questions[]={
        "Choose the appropriate data type for this field: isSwimmer",
        "Choose the appropriate data type for this value: 5.5",
        "An object could be"
    };

    public static String choices[][] = {
        {"boolean", "int", "String", "Date"},
        {"String", "decimal", "boolean", "int"},
        {"an algorithm", "a data container", "a program", "anything"},
    };

    public static String correctAnswers[] = {
        "boolean",
        "double",
        "anything"
    };
}
