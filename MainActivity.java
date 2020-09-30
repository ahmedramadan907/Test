package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final String SCORE_KEY = "score";
    public final String QUESTION_KEY = "QuestionNum";

    public boolean answer = true;

    public QuizModel[] quizModels = {
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false)

    };

    public int QuestionNumber ;
    public int count ;
    public int Question ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button btnTrue = findViewById(R.id.btnTrue);
        final Button btnFalse = findViewById(R.id.btnFalse);
       // final Button btnReset = findViewById(R.id.btnReset);
       // final Button btnExit = findViewById(R.id.btnExit);
        final TextView txtQuizState = findViewById(R.id.txtQuizState);
        final TextView txtQuestions = findViewById(R.id.txtQuestions);
        final ProgressBar progressBar = findViewById(R.id.progressBar);


        txtQuestions.setText(quizModels[QuestionNumber].getmQuestion());
        txtQuizState.setText(String.valueOf(count));
       // btnReset.setVisibility(View.INVISIBLE);
        //btnExit.setVisibility(View.INVISIBLE);

        if(savedInstanceState != null){

            count = savedInstanceState.getInt(SCORE_KEY);
            QuestionNumber = savedInstanceState.getInt(QUESTION_KEY);

            txtQuizState.setText(String.valueOf(count));
            Question = quizModels[QuestionNumber].getmQuestion();

            txtQuestions.setText(Question);

        }

        else{

            count = 0;
            QuestionNumber = 0 ;
        }


        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuestionNumber <= 9) {
                    answer = false;
                    boolean RealAnswer = quizModels[QuestionNumber].getmAnswer();
                     Question = quizModels[QuestionNumber].getmQuestion();

                    if (answer == RealAnswer) {

                        count++;
                        Toast.makeText(MainActivity.this, " Great! ", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(MainActivity.this, "Not good! ", Toast.LENGTH_SHORT).show();
                    }
                    txtQuizState.setText(String.valueOf(count));
                    txtQuestions.setText(Question);
                    QuestionNumber++;
                    progressBar.incrementProgressBy(10);
                } else {
                    txtQuestions.setText("Thanks for using my App ! ");
                    txtQuizState.setText("Your Score is : " + count);
                    btnFalse.setVisibility(View.GONE);
                    btnTrue.setVisibility(View.GONE);
                   // btnReset.setVisibility(View.VISIBLE);
                   // btnExit.setVisibility(View.VISIBLE);
                    AlertDialog();
                }
            }

        });

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (QuestionNumber <= 9) {

                    answer = true;
                    boolean RealAnswer = quizModels[QuestionNumber].getmAnswer();
                     Question = quizModels[QuestionNumber].getmQuestion();

                    if (answer == RealAnswer) {
                        Toast.makeText(MainActivity.this, "Great! ", Toast.LENGTH_SHORT).show();
                        count++;
                    } else {

                        Toast.makeText(MainActivity.this, "Not good! ", Toast.LENGTH_SHORT).show();

                    }

                    txtQuizState.setText(String.valueOf(count));
                    txtQuestions.setText(Question);
                    QuestionNumber++;
                    progressBar.incrementProgressBy(10);
                } else {
                    txtQuestions.setText("Thanks for using my App ! ");
                    txtQuizState.setText("Your Score is : " + count);
                    btnTrue.setVisibility(View.GONE);
                    btnFalse.setVisibility(View.GONE);
                   // btnReset.setVisibility(View.VISIBLE);
                   // btnExit.setVisibility(View.VISIBLE);
                    AlertDialog();
                }
            }
        });

       /* btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuestionNumber = 0;
                count = 0;
                txtQuestions.setText(R.string.q1);
                txtQuizState.setText(String.valueOf(count));
                btnTrue.setVisibility(View.VISIBLE);
                btnFalse.setVisibility(View.VISIBLE);
                btnReset.setVisibility(View.INVISIBLE);
                btnExit.setVisibility(View.INVISIBLE);
                progressBar.setProgress(0);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });*/




    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY , count);
        outState.putInt(QUESTION_KEY , QuestionNumber);
    }


    private void AlertDialog(){

        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("--------");
        alert.setMessage("Your score is ");
        alert.setPositiveButton("finish the quiz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("reset", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.show();
    }


}
