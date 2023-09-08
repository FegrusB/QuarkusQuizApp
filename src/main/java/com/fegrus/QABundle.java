package com.fegrus;

import com.fegrus.models.Answer;
import com.fegrus.models.Question;
import io.vertx.mutiny.mysqlclient.MySQLPool;

import java.util.List;

public class QABundle {


    private final Question question;
    private final List<Answer> answers;

    public QABundle(Question question, MySQLPool client){

        this.question = question;
        this.answers = Answer.findAllByQuestionID(question.getId(),client);

    }

    @SuppressWarnings("unused")
    public Question getQuestion() {
        return question;
    }

    @SuppressWarnings("unused")
    public List<Answer> getAnswers() {
        return answers;
    }



}
