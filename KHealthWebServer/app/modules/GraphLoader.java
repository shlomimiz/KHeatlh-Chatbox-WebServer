package modules;
import Entities.Graph;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.*;
import java.util.concurrent.Executors;

@Singleton
public class GraphLoader implements Runnable  {

    private static Graph graph;
    private Gson g;

    @Inject
    public GraphLoader()  {

        Executors.newSingleThreadExecutor().submit(this);
    }

    @Override
    public void run() {

        g = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader("conf/graph.json"))) {

            graph = g.fromJson(reader, Graph.class);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Graph getGraph() {
        return graph;
    }
}