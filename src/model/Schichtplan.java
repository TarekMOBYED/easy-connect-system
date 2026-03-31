package model;

import java.io.Serializable;

public class Schichtplan implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String tag;

    public Schichtplan() {
    }

    public Schichtplan(int id, String tag) {
        this.id = id;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Schichtplan [id=" + id + ", tag=" + tag + "]";
    }
}