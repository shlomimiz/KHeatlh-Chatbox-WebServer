package controllers;
import Entities.Graph;
import modules.GraphLoader;
import Entities.UsersSessions;
import play.mvc.*;

/**
 * This controller contains actions to handle HTTP requests
 */

public class HomeController extends Controller {

    public Result getUserAnswer(String username) {

        if(UsersSessions.getInstance().getActiveSessions().get(username) == null) {

            return badRequest("Unknown user");
        }

        String answer = request().body().asText().replace(",","").replace("\"","");

        Graph graph =  GraphLoader.getGraph();

        int vertex = UsersSessions.getInstance().getActiveSessions().get(username);

        vertex = graph.getEdges().get(vertex).getOrDefault(answer.trim(), 0);

        UsersSessions.getInstance().getActiveSessions().put(username, vertex);

        return ok();
    }

    public Result getNextQuestion(String username) {

        Integer vertex = UsersSessions.getInstance().getActiveSessions().putIfAbsent(username,1);

        vertex = vertex == null ? 1 : vertex;

        String ret = "";

        Graph graph =  GraphLoader.getGraph();

        ret += graph.getVertices().get(vertex).getQuestion();

        if(graph.getVertices().get(vertex).isMulti()) {

            ret += "Please type all that apply from the following with commas (i.e: A,B,C) \n ";
        }

        else {

            ret += "Please type only one of the following answers: \n ";

        }

        ret += graph.getVertices().get(vertex).getOptions();

        return ok(ret);
    }

    public Result alive(){

        return ok("Server is alive port 9000!");
    }

}
