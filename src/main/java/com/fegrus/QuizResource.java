package com.fegrus;

import com.fegrus.models.Question;
import com.fegrus.models.Quiz;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("/quiz")
public class QuizResource {

    @Inject
    io.vertx.mutiny.mysqlclient.MySQLPool client;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Quiz> getAll(){
        return Quiz.findAll(client);
    }

    @GET
    @Path("/QA/{quizId}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<QABundle> getQuizDetails(@PathParam("quizId") long quizId){
        return Question.getQABundle(quizId,client);
    }
}
