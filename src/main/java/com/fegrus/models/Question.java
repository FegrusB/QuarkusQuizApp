package com.fegrus.models;


import com.fegrus.QABundle;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Question {

  private long id;
  private long quizId;
  private String questionText;

  private static List<Question> findAllByQuizID(long id,MySQLPool client) {
    return client.preparedQuery("SELECT * FROM question WHERE quiz_id = ?").execute(Tuple.of(id)).onItem().transformToMulti(set -> Multi.createFrom().iterable(set)
            .onItem().transform(Question::from)).collect().asList().await().indefinitely();
  }

  public static ArrayList<QABundle> getQABundle(long id, MySQLPool client){
    ArrayList<Question> questionList = new ArrayList<>( Question.findAllByQuizID(id,client));
    ArrayList<QABundle> bundleList = new ArrayList<>();
    for (Question q: questionList){
      bundleList.add(new QABundle(q,client));
    }
    return bundleList;
  }

  private static Question from(Row row){
    return new Question(row.getLong("_id"),row.getLong("quiz_id"),row.getString("question_text"));
  }

  public Question(long id, long quizId, String questionText) {
    this.id = id;
    this.quizId = quizId;
    this.questionText = questionText;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getQuizId() {
    return quizId;
  }

  public void setQuizId(long quizId) {
    this.quizId = quizId;
  }


  public String getQuestionText() {
    return questionText;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

}
