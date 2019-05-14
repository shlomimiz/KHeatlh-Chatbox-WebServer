package controllers;
import Entities.Graph;
import modules.GraphLoader;
import Entities.UsersSessions;
import play.mvc.*;

/**
 * This controller contains actions to handle HTTP requests
 */

public class HomeController extends Controller {

    // On-demand references to Singleton Objects, will be allocated upon an HTTP session.

    private UsersSessions usersSessions = UsersSessions.getInstance();

    private Graph graph = GraphLoader.getInstance();

    public Result getUserAnswer(String username) {

        if(usersSessions.getActiveSessions().get(username) == null) {

            return badRequest("Unknown user");
        }

        String answer = request().body().asText().replace(",","").replace("\"","");

        int vertex = usersSessions.getActiveSessions().get(username);

        vertex = graph.getEdges().get(vertex).getOrDefault(answer, 0);

        if (vertex == 0) {

            usersSessions.getActiveSessions().remove(username);

        } else {

            usersSessions.getActiveSessions().put(username, vertex);
        }


        return ok();
    }

    public Result getNextQuestion(String username) {

        Integer vertex = usersSessions.getActiveSessions().putIfAbsent(username,1);

        vertex = vertex == null ? 1 : vertex;

        String ret = "";

        ret += graph.getVertices().get(vertex).getQuestion();

        ret += graph.getVertices().get(vertex).isMulti() ?
                "Please type all that apply from the following with commas (i.e: A,B,C) \n"
                : "Please type only one of the following answers: \n ";

        ret += graph.getVertices().get(vertex).getOptions();

        return ok(ret);
    }

    public Result alive(){

        return ok("Server is alive port 9000!");
    }

}
