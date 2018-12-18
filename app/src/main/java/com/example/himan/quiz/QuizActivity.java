package com.example.himan.quiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener, QuizActivityInterface {
    private TextView questionText;
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private Button previousButton;
    private Button next;
    private TextView questionNumLabel;
    private TextView scoreNumLabel;
    private int[][] previousOptionsSelected = new int[10][2];
    private ArrayList<Question> questions =  new ArrayList<Question>(10);
    private ArrayList<Question> shuffleQuestions =  new ArrayList<Question>(5);
    private int currentQuestion = 0;
    private int answeredQuestions = 0;
    private static int  score = 0;
    private Boolean quizEnded = false;
    private Boolean optionSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questionText = (TextView) findViewById(R.id.questionText);
        option1 = (Button) findViewById(R.id.option1);
        option2 = (Button) findViewById(R.id.option2);
        option3 = (Button) findViewById(R.id.option3);
        option4 = (Button) findViewById(R.id.option4);
        previousButton = (Button) findViewById(R.id.previousButton);
        next = (Button) findViewById(R.id.next);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        previousButton.setOnClickListener(this);
        next.setOnClickListener(this);
        questionNumLabel = (TextView) findViewById(R.id.questionNum);
        scoreNumLabel = (TextView) findViewById(R.id.scoreNumLabel);
        loadQuestions();
        showQuestion(currentQuestion);
        score = 0;
        hidePrevious();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.next:

                if (optionSelected == true || currentQuestion < answeredQuestions) {
                if (currentQuestion < shuffleQuestions.size()-6) {
                    currentQuestion += 1;
                    showQuestion(currentQuestion);
                } else {
                    endQuiz();
                }
                resetButton();
                if(currentQuestion == 1)
                {
                  previousButton.setVisibility(View.VISIBLE);
                }
                if (currentQuestion < answeredQuestions)
                {
                    setPreviousOptions();
                    disableButton();
                }
                else {
                    enableButon();
                }

                updateQuestionNumLabel();
                optionSelected = false;
                }
                else {
                    AlertDialog alertDialog = new AlertDialog.Builder(QuizActivity.this).create();
                    alertDialog.setTitle("No Option Selected");
                    alertDialog.setMessage("Please select any option");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
                break;

                case R.id.previousButton:

                    if (currentQuestion == 1)
                    {
                        hidePrevious();
                    }
                    resetButton();
                    currentQuestion = currentQuestion - 1;
                    showQuestion(currentQuestion);
                    setPreviousOptions();
                    disableButton();
                    updateQuestionNumLabel();

                break;

            case R.id.option1:
                previousOptionsSelected[currentQuestion][0] = 0;
                selectAnswer(0, view);
            break;

            case R.id.option2:

                previousOptionsSelected[currentQuestion][0] = 1;
                selectAnswer(1, view);

            break;

            case R.id.option3:

                previousOptionsSelected[currentQuestion][0] = 2;
                selectAnswer(2, view);

            break;

            case R.id.option4:

                previousOptionsSelected[currentQuestion][0] = 3;
                selectAnswer(3, view);
            break;

        }
    }

    public void hidePrevious()
    {
    previousButton.setVisibility(View.INVISIBLE);
    }
    public void updateQuestionNumLabel()
    {
        questionNumLabel.setText("Question: " + (currentQuestion+1) + "/5");
    }

    public void updateScoreNumLabel()
    {
        scoreNumLabel.setText("Score: " + score + "/5");
    }

    public void resetButton()
    {
        option1.setBackgroundColor(Color.CYAN);
        option2.setBackgroundColor(Color.CYAN);
        option3.setBackgroundColor(Color.CYAN);
        option4.setBackgroundColor(Color.CYAN);
    }

    public void endQuiz()
    {
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
    }

    public int getScore()
    {
        return score;
    }



    public void loadQuestions() {
        Question question1 = new Question(
                "What is the value of 16 * 16?",
                new Option[]{
        new Option("256", true),
        new Option("356", false),
        new Option("645", false),
        new Option("345", false)
                }
                );

        Question question2 = new Question(
                 "What is radius of circle?",
                new Option[]{
                        new Option("2(3.14)(R)", true),
                        new Option("4(3.14)(R)", false),
                        new Option("5(3.14)(R)", false),
                        new Option("5(7)(R)", false)
                }

        );

        Question question3 = new Question(
                "What is the area of rectangle?",
                new Option[]{new Option("L + B", false),
        new Option("L * B", true),
        new Option("L - B", false),
        new Option("None", false)
            }

        );

        Question question4 = new Question(
                "What is the perimeter of rectangle?",
                new Option[]{ new Option("2(l+b)", true),
        new Option("l+b", false),
        new Option("l*b", false),
        new Option("l/b", false)
            }

        );

        Question question5 = new Question(
                "What is the 25 * 25?",
                new Option[]{
                        new Option("1525", false),
        new Option("625",true),
        new Option("567", false),
        new Option("123",false)
            }

        );

        Question question6 = new Question(
                "What is sq. root of 625?",
                new Option[]{
                        new Option("35",false),
        new Option("25", true),
        new Option("112", false),
        new Option("876", false)
            }

        );

        Question question7 = new Question(
                "What is the area of circle?",
                new Option[]{
                        new Option("(3.14)(R)(R)", true),
        new Option("(4)(R)(R)", false),
        new Option("(6)(R)(R)", false),
        new Option("(7)(R)(R)", false)
            }
        );

        Question question8 = new Question(
                "What is the value of 78 * 67?",
                new Option[]{
                        new Option("5226", true),
        new Option("124", false),
        new Option("6225", false),
        new Option("5444", false)
            }
        );

        Question question9 = new Question(
                "What is the value of 9 * 94 + 123?",
                new Option[]{
                       new Option("969", true),
        new Option("10969", false),
        new Option("189", false),
        new Option("190", false)
            }
        );

        Question question10 = new Question(
                "What is the area of square?",
                new Option[]{
                        new Option("(side)(side)", true),
        new Option("(side)", false),
        new Option("3(side)", false),
        new Option("2(side)", false)
            }
        );

        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        shuffle();
    }



    public void showQuestion(int questionId) {
        Question selectedQuestion = shuffleQuestions.get(questionId);
        questionText.setText(selectedQuestion.getQuestion());
        Option[] options = selectedQuestion.getOptions();
        option1.setText(options[0].getText());
        option2.setText(options[1].getText());
        option3.setText(options[2].getText());
        option4.setText(options[3].getText());
    }



    public void selectAnswer(int optionId, View view){
        disableButton();
        optionSelected = true;
        answeredQuestions += 1;
        Option[] options = shuffleQuestions.get(currentQuestion).getOptions();
        Option answer = options[optionId];
        int count = 0;
        if (true == answer.getCorrect()) {
            score += 1;
            updateScoreNumLabel();
            view.setBackgroundColor(Color.GREEN);
        }else {
            view.setBackgroundColor(Color.RED);
        }
        for (Option ans: shuffleQuestions.get(currentQuestion).getOptions())
        {
            if (ans.getCorrect() == true)
            {
                previousOptionsSelected[currentQuestion][1] = count;
                if (count == 0){
                option1.setBackgroundColor(Color.GREEN);
            }
                else if (count == 1){
                option2.setBackgroundColor(Color.GREEN);
            }
                else if (count == 2){
                option3.setBackgroundColor(Color.GREEN);
            }
                else if (count == 3){
                option4.setBackgroundColor(Color.GREEN);
            }
            }
            count = count + 1;
        }
    }

    public void disableButton() {
        option1.setClickable(false);
        option2.setClickable(false);
        option3.setClickable(false);
        option4.setClickable(false);
    }

    public void enableButon()
    {
        option1.setClickable(true);
        option2.setClickable(true);
        option3.setClickable(true);
        option4.setClickable(true);
    }

    public void setPreviousOptions()
    {
        if(previousOptionsSelected[currentQuestion][0] == 0){      // setting red color for previous wrong answers
        option1.setBackgroundColor(Color.RED);
    }
        else if(previousOptionsSelected[currentQuestion][0] == 1){
        option2.setBackgroundColor(Color.RED);
    }
        else if(previousOptionsSelected[currentQuestion][0] == 2){
        option3.setBackgroundColor(Color.RED);
    }
        else if(previousOptionsSelected[currentQuestion][0] == 3){
        option4.setBackgroundColor(Color.RED);
    }


        for (Option answer: shuffleQuestions.get(currentQuestion).getOptions())
        {
            if (answer.getCorrect() == true)    //setting green color for previous right answerss
            {

                if(previousOptionsSelected[currentQuestion][1] == 0){
                option1.setBackgroundColor(Color.GREEN);
            }
                else if(previousOptionsSelected[currentQuestion][1] == 1){
                option2.setBackgroundColor(Color.GREEN);
            }
                else if(previousOptionsSelected[currentQuestion][1] == 2){
                option3.setBackgroundColor(Color.GREEN);
            }
                else if(previousOptionsSelected[currentQuestion][1] == 3){
                option4.setBackgroundColor(Color.GREEN);
            }
            }
        }

    }

    public void shuffle()
    {
        for (int i = 0; i < 10; i++)
        {
            int randomNum = 0;
            randomNum = new Random().nextInt(questions.size());
            shuffleQuestions.add(questions.get(randomNum));
            questions.remove(randomNum);
        }


    }
}


