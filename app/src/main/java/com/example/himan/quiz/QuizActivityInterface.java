package com.example.himan.quiz;

import android.view.View;
import android.widget.TextView;

public interface QuizActivityInterface {

        public void updateQuestionNumLabel();

        public void updateScoreNumLabel();

        public void resetButton();

        public void hidePrevious();

        public void endQuiz();

        public int getScore();

        public void loadQuestions();

        public void showQuestion(int questionId);

        public void selectAnswer(int optionId , View view);

        public void disableButton();

        public void enableButon();

        public void setPreviousOptions();

        public void shuffle();
    }



