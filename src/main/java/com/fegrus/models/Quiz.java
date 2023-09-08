package com.fegrus.models;


import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Row;


public class Quiz {

  private long id;
  private String name;
  private String description;
  private long numQuests;



  private static Quiz from(Row row){
    return new Quiz(row.getLong("_id"),row.getString("Name"),row.getString("Description"),row.getLong("num_quests"));
  }

  public static Multi<Quiz> findAll(MySQLPool client) {
    return client.query("select * from quiz").execute().onItem().transformToMulti(set -> Multi.createFrom().iterable(set)
            .onItem().transform(Quiz::from));
  }

  public Quiz(long id, String name, String description, long numQuests) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.numQuests = numQuests;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getNumQuests() {
    return numQuests;
  }

  public void setNumQuests(long numQuests) {
    this.numQuests = numQuests;
  }

}
