package model;

public enum Zustand {
    AKTIV("Aktiv"),
    IN_URLAUB("In Urlaub"),
    INAKTIV_GELOESCHT("Inaktiv/Gelöscht");

    private final String label;

    Zustand(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static Zustand fromLabel(String label) {
        for (Zustand z : values()) {
            if (z.label.equalsIgnoreCase(label)) {
                return z;
            }
        }
        throw new IllegalArgumentException("Unbekannter Zustand: " + label);
    }
}