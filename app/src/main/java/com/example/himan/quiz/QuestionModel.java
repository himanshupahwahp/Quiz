package com.example.himan.quiz;

 class Question {

        private String question;

        private Option[] options;

        Question(String question, Option[] options){
            this.question = question;
            this.options = options;

        }

        public void setQuestion(String ques)
        {
            this.question = ques;
        }

        public String getQuestion()
        {
            return question;
        }

        public void setOptions(Option[] options)
        {
            this.options = options;
        }

        public Option[] getOptions()
        {
            return options;
        }
    }

    class Option {

        private String text;

        private Boolean correct;

        Option(String text, Boolean correct){
            this.text = text;
            this.correct = correct;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public String getText()
        {
            return text;
        }
        public void setCorrect(Boolean value)
        {
            this.correct = value;
        }

        public Boolean getCorrect()
        {
            return correct;
        }

}
