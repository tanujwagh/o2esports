package com.spool.types;

import java.io.Serializable;

/**
 * Created by Korrin on 4/4/2017.
 */
public class QAAnswer implements Serializable{
    private int questionFor;
    private String answer;
    private int bid;
    private double multiplicationFactor;

    public int getQuestionFor() {
        return questionFor;
    }

    public void setQuestionFor(int questionFor) {
        this.questionFor = questionFor;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public double getMultiplicationFactor() {
        return multiplicationFactor;
    }

    public void setMultiplicationFactor(double multiplicationFactor) {
        this.multiplicationFactor = multiplicationFactor;
    }

    public static QAAnswer getDefault() {
        QAAnswer qaAnswer = new QAAnswer();
        qaAnswer.setMultiplicationFactor(1);
        qaAnswer.setQuestionFor(QAType.Oddtime);
        qaAnswer.setAnswer("NoAnswer");
        qaAnswer.setBid(0);
        return qaAnswer;
    }
}
