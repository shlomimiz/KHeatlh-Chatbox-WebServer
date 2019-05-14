package Entities;

import java.util.List;

public class Vertex {

    public String text;
    public boolean multi;
    public List<String> options;

    public Vertex(String text, boolean multi, List<String> options){
        this.text = text;
        this.multi = multi;
        this.options = options;
    }

    public String getQuestion() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isMulti(){ return multi;}

}
