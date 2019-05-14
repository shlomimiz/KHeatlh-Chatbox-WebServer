package modules;
import Entities.Graph;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Singleton
public class GraphLoader implements Runnable  {

    private static Graph instance = null;

    private Gson g;

    private static Future GraphLoadingProcess;

    public static Graph getInstance() {

        while(!GraphLoadingProcess.isDone()) {}

        return instance;
    }

    @Inject
    public GraphLoader()  {

        GraphLoadingProcess =  Executors.newSingleThreadExecutor().submit(this);
    }

    @Override
    public void run() {

        g = new Gson();

        try (JsonReader reader = new JsonReader(new FileReader("conf/graph.json"))) {

            instance = g.fromJson(reader, Graph.class);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}