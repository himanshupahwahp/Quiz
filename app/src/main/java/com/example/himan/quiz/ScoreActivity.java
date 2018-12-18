package com.example.himan.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener, ScoreActivityInterface {

    TextView scoreText;
    TextView comment;
    private QuizActivity quizActivity;
    private Button playAgainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreText = (TextView) findViewById(R.id.scoreText);
        comment = (TextView) findViewById(R.id.comment);
        playAgainButton = (Button) findViewById(R.id.playAgain);
        setScore();
        playAgainButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    public void setScore()
    {
        quizActivity = new QuizActivity();
        scoreText.setText("Score: " + quizActivity.getScore() + "/5");
        if (quizActivity.getScore() == 0 || quizActivity.getScore() == 1 || quizActivity.getScore() == 2)
        {
            comment.setText("Please Try Again!");
        }
        else if(quizActivity.getScore() == 3)
        {
            comment.setText("Good Job");
        }
        else if(quizActivity.getScore() == 4)
        {
            comment.setText("Excellent Work!");
        }
        else if(quizActivity.getScore() == 5)
        {
            comment.setText("You are a genius!");
        }

    }

}
