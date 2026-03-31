package dao;

import model.Mitarbeiter;
import model.Zustand;
import util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAOImpl implements MitarbeiterDAO {

    @Override
    public int insert(Mitarbeiter m) throws SQLException {
        String sql = "INSERT INTO mitarbeiter (name, zustand, beschaeftigtBeiFirma, message) VALUES (?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getZustand().getLabel());
            ps.setString(3, m.getBeschaeftigtBeiFirma());
            ps.setString(4, m.getMessage());

            int result = ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setId(rs.getInt(1));
                }
            }

            return result;
        }
    }

    @Override
    public int update(Mitarbeiter m) throws SQLException {
        String sql = "UPDATE mitarbeiter SET name = ?, zustand = ?, beschaeftigtBeiFirma = ?, message = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getZustand().getLabel());
            ps.setString(3, m.getBeschaeftigtBeiFirma());
            ps.setString(4, m.getMessage());
            ps.setInt(5, m.getId());

            return ps.executeUpdate();
        }
    }

    @Override
    public int delete(int id) throws SQLException {
        String sql = "UPDATE mitarbeiter SET zustand = ? WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Zustand.INAKTIV_GELOESCHT.getLabel());
            ps.setInt(2, id);

            return ps.executeUpdate();
        }
    }

    @Override
    public Mitarbeiter get(int id) throws SQLException {
        String sql = "SELECT * FROM mitarbeiter WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }

        return null;
    }

    @Override
    public List<Mitarbeiter> getAll() throws SQLException {
        String sql = "SELECT * FROM mitarbeiter WHERE zustand <> ? ORDER BY id";

        List<Mitarbeiter> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, Zustand.INAKTIV_GELOESCHT.getLabel());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }

        return list;
    }

    private Mitarbeiter mapRow(ResultSet rs) throws SQLException {
        return new Mitarbeiter(
                rs.getInt("id"),
                rs.getString("name"),
                Zustand.fromLabel(rs.getString("zustand")),
                rs.getString("beschaeftigtBeiFirma"),
                rs.getString("message")
        );
    }
}