package tn.esprit.androidproject;

import  androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import tn.esprit.androidproject.databinding.ActivityCertifBinding;

public class CertifActivity extends AppCompatActivity {
ArrayList<QuestionModel> list =new ArrayList<>();
    private int count=0;
    private int position=0;
    private int score=0;
    CountDownTimer timer;
    ActivityCertifBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCertifBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        //getSupportActionBar().hide();
        restTimer();
        timer.start();
        String setName= getIntent().getStringExtra("set");
        if(setName.equals("SET-1")){
            serOne();
        }
        else if(setName.equals("SET-2")){
            setTwo();

        }
        for (int i=0;i<3;i++){

            binding.optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkAnsawer((Button) view);
                }
            });
        }
        playAnimation(binding.question,0,list.get(position).getQuestion());
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                if(timer !=null){
                    timer.cancel();
                }
                timer.start();

              binding.btnNext. setEnabled(false);
              binding.btnNext.setAlpha((float) 0.3);
              enableOption(true);
              position ++;
              if(position==list.size()){
                  Intent intent= new Intent(CertifActivity.this, ScoreActivity.class);
                  intent.putExtra("score",score);
                  intent.putExtra("total",list.size());
                  startActivity(intent);
                  finish();
                  return;
              }
              count=0;
              playAnimation(binding.question,0,list.get(position).getQuestion());
            }
        });


    }

    private void restTimer() {
   timer = new CountDownTimer(30000, 1000) {
       @Override
       public void onTick(long l) {
           binding.timer.setText(String.valueOf(l/1000));
       }

       @Override
       public void onFinish() {
           Dialog dialog = new Dialog(CertifActivity.this);
           dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
           dialog.setCancelable(false);
           dialog.setContentView(R.layout.timeout_dialog);
           dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent= new Intent(CertifActivity.this,SetsActivity.class);
                   startActivity(intent);
                   finish();

               }
           });
           dialog.show();
       }
   };

    }

    private void playAnimation(View view, int value, String data) {
         view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
                     @Override
                     public void onAnimationStart(@NonNull Animator animator) {
                         if(value ==0 && count<3){
                             String option ="";
                             if(count==0){
                                 option=list.get(position).getOptionA();
                             }else if(count==1){
                                 option =list.get(position).getOptionB();
                             }else if(count==2){
                                 option =list.get(position).getOptionC();
                             }
                             playAnimation(binding.optionContainer.getChildAt(count),0,option);
                             count++;
                         }
                     }

                     @Override
                     public void onAnimationEnd(@NonNull Animator animator) {
                         if(value==0)
                         {
                             try {
                                 ((TextView) view).setText(data);
                                 binding.totalQuestion.setText(position+1+"/"+list.size());

                             } catch (Exception e) {
                                 ((Button)view).setText(data);

                              }
                             view.setTag(data);
                             playAnimation(view,1,data);

                         }

                     }

                     @Override
                     public void onAnimationCancel(@NonNull Animator animator) {

                     }

                     @Override
                     public void onAnimationRepeat(@NonNull Animator animator) {

                     }
                 });
    }

    private void enableOption(boolean enable)
    {
        for(int i=0;i<3;i++){
            binding.optionContainer.getChildAt(i).setEnabled(enable);
            if(enable){
                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.btn_opt);
            }
        }


    }

    private void checkAnsawer(Button selectedOption) {
        if(timer!=null){
            timer.cancel();
        }
        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);
        if(selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){
            score ++;
            selectedOption.setBackgroundResource(R.drawable.right_answ);
        }
        else {
            selectedOption.setBackgroundResource(R.drawable.btnwrong);
            Button correctOption =(Button) binding.optionContainer.findViewWithTag(list.get(position).getCorrectAnswer());
         correctOption.setBackgroundResource(R.drawable.right_answ);
        }
    }

    private void setTwo() {
        list.add(new QuestionModel("CLR signifie _______?", "Common Local Runtime","Common Language Runtime","Common Language Realtime","Common Language Runtime"));
        list.add(new QuestionModel("Quels sont les types de JIT?", "Pre-JIT","Econo-JT","Tout les réponses sont vrais","Tout les réponses sont vrais"));
        list.add(new QuestionModel("Garbage collector (GC) comprend _______ générations.", "Trois","Un","Deux","Trois"));
    }

    private void serOne() {
        list.add(new QuestionModel("CLR signifie _______?", "Common Local Runtime","Common Language Runtime","Common Language Realtime","Common Language Runtime"));
        list.add(new QuestionModel("Quels sont les types de JIT?", "Pre-JIT","Econo-JT","Tout les réponses sont vrais","Tout les réponses sont vrais"));
        list.add(new QuestionModel("Garbage collector (GC) comprend _______ générations.", "Trois","Un","Deux","Trois"));

    }
}