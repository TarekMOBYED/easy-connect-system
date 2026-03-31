package app;

import dao.MitarbeiterDAO;
import dao.MitarbeiterDAOImpl;
import model.Mitarbeiter;
import model.Zustand;
import util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TestApp {

    private static String currentUserRole = "";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Willkommen zum Easy Connect System ===");

            if (!login(sc)) {
                System.out.println("Login fehlgeschlagen. Programm wird beendet.");
                return;
            }

            MitarbeiterDAO dao = new MitarbeiterDAOImpl();
            int wahl;

            do {
                showMenu();
                wahl = readInt(sc, "Ihre Wahl: ");

                try {
                    switch (wahl) {
                        case 1:
                            createMitarbeiter(sc, dao);
                            break;
                        case 2:
                            markUrlaub(sc, dao);
                            break;
                        case 3:
                            sendMessage(sc, dao);
                            break;
                        case 4:
                            showAllMitarbeiter(dao);
                            break;
                        case 5:
                            deleteMitarbeiter(sc, dao);
                            break;
                        case 6:
                            System.out.println("Auf Wiedersehen!");
                            break;
                        default:
                            System.out.println("Ungültige Auswahl.");
                    }
                } catch (SQLException e) {
                    System.out.println("Datenbankfehler: " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.out.println("Eingabefehler: " + e.getMessage());
                }

            } while (wahl != 6);
        }
    }

    private static void showMenu() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Angemeldet als: " + currentUserRole);
        System.out.println("1. Neuen Mitarbeiter anlegen (nur Admin)");
        System.out.println("2. Urlaub anmelden");
        System.out.println("3. Nachricht senden");
        System.out.println("4. Mitarbeiter anzeigen");
        System.out.println("5. Mitarbeiter entfernen (nur Admin)");
        System.out.println("6. Beenden");
    }

    private static boolean login(Scanner sc) {
        System.out.print("Benutzername: ");
        String user = sc.nextLine().trim();

        System.out.print("Passwort: ");
        String pass = sc.nextLine().trim();

        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    currentUserRole = rs.getString("role");
                    System.out.println("Login erfolgreich! Rolle: " + currentUserRole);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Login: " + e.getMessage());
        }

        return false;
    }

    private static void createMitarbeiter(Scanner sc, MitarbeiterDAO dao) throws SQLException {
        if (!isAdmin()) {
            System.out.println("Zugriff verweigert: Nur Admins dürfen Mitarbeiter anlegen.");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Firma: ");
        String firma = sc.nextLine().trim();

        Mitarbeiter m = new Mitarbeiter(name, Zustand.AKTIV, firma, "");
        dao.insert(m);

        System.out.println("Mitarbeiter erfolgreich angelegt. Neue ID: " + m.getId());
    }

    private static void markUrlaub(Scanner sc, MitarbeiterDAO dao) throws SQLException {
        int id = readInt(sc, "Mitarbeiter-ID: ");
        Mitarbeiter m = dao.get(id);

        if (m == null) {
            System.out.println("Mitarbeiter nicht gefunden.");
            return;
        }

        m.setZustand(Zustand.IN_URLAUB);
        dao.update(m);
        System.out.println("Status wurde auf 'In Urlaub' gesetzt.");
    }

    private static void sendMessage(Scanner sc, MitarbeiterDAO dao) throws SQLException {
        int id = readInt(sc, "Mitarbeiter-ID: ");
        Mitarbeiter m = dao.get(id);

        if (m == null) {
            System.out.println("Mitarbeiter nicht gefunden.");
            return;
        }

        System.out.print("Nachricht: ");
        String msg = sc.nextLine().trim();

        m.setMessage(msg);
        dao.update(m);

        System.out.println("Nachricht wurde gespeichert.");
    }

    private static void showAllMitarbeiter(MitarbeiterDAO dao) throws SQLException {
        List<Mitarbeiter> list = dao.getAll();

        if (list.isEmpty()) {
            System.out.println("Keine aktiven Mitarbeiter vorhanden.");
            return;
        }

        System.out.println("\n=== Mitarbeiterliste ===");
        for (Mitarbeiter m : list) {
            System.out.println(m);
        }
    }

    private static void deleteMitarbeiter(Scanner sc, MitarbeiterDAO dao) throws SQLException {
        if (!isAdmin()) {
            System.out.println("Zugriff verweigert: Nur Admins dürfen Mitarbeiter entfernen.");
            return;
        }

        int id = readInt(sc, "Mitarbeiter-ID zum Entfernen: ");
        int result = dao.delete(id);

        if (result > 0) {
            System.out.println("Mitarbeiter wurde soft gelöscht.");
        } else {
            System.out.println("Kein Mitarbeiter mit dieser ID gefunden.");
        }
    }

    private static boolean isAdmin() {
        return "Admin".equalsIgnoreCase(currentUserRole);
    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Bitte eine gültige Zahl eingeben.");
            }
        }
    }
}