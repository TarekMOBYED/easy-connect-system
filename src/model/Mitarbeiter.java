package model;

import java.io.Serializable;
import java.util.Objects;

public class Mitarbeiter implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private Zustand zustand;
    private String beschaeftigtBeiFirma;
    private String message;

    public Mitarbeiter() {
    }

    public Mitarbeiter(int id, String name, Zustand zustand, String beschaeftigtBeiFirma, String message) {
        setId(id);
        setName(name);
        setZustand(zustand);
        setBeschaeftigtBeiFirma(beschaeftigtBeiFirma);
        setMessage(message);
    }

    public Mitarbeiter(String name, Zustand zustand, String beschaeftigtBeiFirma, String message) {
        this(0, name, zustand, beschaeftigtBeiFirma, message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID darf nicht negativ sein.");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }
        this.name = name.trim();
    }

    public Zustand getZustand() {
        return zustand;
    }

    public void setZustand(Zustand zustand) {
        if (zustand == null) {
            throw new IllegalArgumentException("Zustand darf nicht null sein.");
        }
        this.zustand = zustand;
    }

    public String getBeschaeftigtBeiFirma() {
        return beschaeftigtBeiFirma;
    }

    public void setBeschaeftigtBeiFirma(String beschaeftigtBeiFirma) {
        if (beschaeftigtBeiFirma == null || beschaeftigtBeiFirma.trim().isEmpty()) {
            throw new IllegalArgumentException("Firma darf nicht leer sein.");
        }
        this.beschaeftigtBeiFirma = beschaeftigtBeiFirma.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = (message == null) ? "" : message.trim();
    }

    @Override
    public String toString() {
    	String msg = (message == null || message.trim().isEmpty()) ? "---" : message;
    	return String.format(
                "[ID: %d] Name: %-15s | Status: %-18s | Firma: %-15s | Nachricht: %s",
                id, name, zustand.getLabel(), beschaeftigtBeiFirma, msg
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mitarbeiter)) return false;
        Mitarbeiter that = (Mitarbeiter) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}