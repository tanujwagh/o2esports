package com.spool.types;

import java.io.Serializable;

/**
 * Created by Korrin on 4/4/2017.
 */
public class QAAnswers implements Serializable{
    private QAAnswer answer1;
    private QAAnswer answer2;
    private QAAnswer answer3;
    private QAAnswer answer4;

    public QAAnswer getAnswer1() {
        return answer1;
    }

    public void setAnswer1(QAAnswer answer1) {
        this.answer1 = answer1;
    }

    public QAAnswer getAnswer2() {
        return answer2;
    }

    public void setAnswer2(QAAnswer answer2) {
        this.answer2 = answer2;
    }

    public QAAnswer getAnswer3() {
        return answer3;
    }

    public void setAnswer3(QAAnswer answer3) {
        this.answer3 = answer3;
    }

    public QAAnswer getAnswer4() {
        return answer4;
    }

    public void setAnswer4(QAAnswer answer4) {
        this.answer4 = answer4;
    }
}
