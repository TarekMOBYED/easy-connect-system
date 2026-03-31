package model;

import java.io.Serializable;

public class Arbeitgeber implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String firmenname;

    public Arbeitgeber() {
    }

    public Arbeitgeber(int id, String firmenname) {
        this.id = id;
        this.firmenname = firmenname;
    }

    public int getId() {
        return id;
    }

    public String getFirmenname() {
        return firmenname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirmenname(String firmenname) {
        this.firmenname = firmenname;
    }

    @Override
    public String toString() {
        return "Arbeitgeber [id=" + id + ", firmenname=" + firmenname + "]";
    }
}