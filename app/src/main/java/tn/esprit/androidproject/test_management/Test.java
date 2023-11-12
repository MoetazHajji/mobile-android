package tn.esprit.androidproject.test_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import android.app.AlertDialog;

import tn.esprit.androidproject.R;

public class Test extends AppCompatActivity implements View.OnClickListener {

    TextView questionTextView;
    Button ans1, ans2, ans3, ans4;
    Button submitBtn, cancelBtn;

    int score=0;
    int totalQuestion = QuestionAnswer.questions.length;
    int currentQuestionIndex = 0;
    String selectedAnswer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        questionTextView = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        cancelBtn = findViewById(R.id.cancel);

        submitBtn = findViewById(R.id.submit_btn);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);

        submitBtn.setOnClickListener(this );
        cancelBtn.setOnClickListener(this);

        questionTextView.setText("Total questions : "+totalQuestion);

        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.cancel) {
            finish(); // This will close the activity
        }
        ans1.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans4.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;





        if(clickedButton.getId()==R.id.submit_btn){

            if (selectedAnswer != null) {
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();}
            else {
                new AlertDialog.Builder(this)
                        .setTitle("Please choose an answer")
                        .setMessage("Score is " + score + " out of " + totalQuestion)
                        .setPositiveButton("retry", (dialogInterface, i) -> restartQuiz())
                        .setCancelable(false)
                        .show();
            }


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }



    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.questions[currentQuestionIndex]);
        ans1.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ans2.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ans3.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ans4.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

    }

    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}