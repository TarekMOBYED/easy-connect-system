package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Schichtleiter implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private final List<Mitarbeiter> arbeiterListe = new ArrayList<>();

    public Schichtleiter() {
    }

    public Schichtleiter(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addMitarbeiter(Mitarbeiter m) {
        if (m != null && !arbeiterListe.contains(m)) {
            arbeiterListe.add(m);
        }
    }

    public void removeMitarbeiter(Mitarbeiter m) {
        arbeiterListe.remove(m);
    }

    public List<Mitarbeiter> getArbeiterListe() {
        return Collections.unmodifiableList(arbeiterListe);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}