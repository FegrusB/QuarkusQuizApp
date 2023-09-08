package com.fegrus.models;


import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.List;

public class Answer {

  private long id;
  private long questionId;
  private String answerText;
  private boolean correct;

  public static List<Answer> findAllByQuestionID(long id, MySQLPool client) {
    return client.preparedQuery("SELECT * FROM answer WHERE question_id = ?").execute(Tuple.of(id)).onItem().transformToMulti(set -> Multi.createFrom().iterable(set)
            .onItem().transform(Answer::from)).collect().asList().await().indefinitely();
  }

  private static Answer from(Row row){
    return new Answer(row.getLong("_id"),row.getLong("question_id"),row.getString("answer_text"),row.getBoolean("correct"));
  }

  public Answer(long id, long questionId, String answerText, Boolean correct) {
    this.id = id;
    this.questionId = questionId;
    this.answerText = answerText;
    this.correct = correct;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getQuestionId() {
    return questionId;
  }

  public void setQuestionId(long questionId) {
    this.questionId = questionId;
  }


  public String getAnswerText() {
    return answerText;
  }

  public void setAnswerText(String answerText) {
    this.answerText = answerText;
  }


  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

}
