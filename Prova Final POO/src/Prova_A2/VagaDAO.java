package Prova_A2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VagaDAO {

    public void cadastrarVaga(Vaga vaga) throws SQLException {
        String sql = "INSERT INTO vaga (empresa, cargo, descricao, requisitos, usuario_rg) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vaga.getEmpresa());
            stmt.setString(2, vaga.getCargo());
            stmt.setString(3, vaga.getDescricao());
            stmt.setString(4, vaga.getRequisitos());
            stmt.setString(5, vaga.getUsuarioRG());
            stmt.executeUpdate();
        }
    }

    public List<Vaga> listarVagasPorUsuario(String usuarioRG) throws SQLException {
        String sql = "SELECT * FROM vaga WHERE usuario_rg = ?";
        List<Vaga> vagas = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuarioRG);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vaga vaga = new Vaga(
                        rs.getString("empresa"),
                        rs.getString("cargo"),
                        rs.getString("descricao"),
                        rs.getString("requisitos"),
                        rs.getString("usuario_rg")
                );
                vagas.add(vaga);
            }
        }
        return vagas;
    }

    public boolean excluirVaga(int id) throws SQLException {
        String sql = "DELETE FROM vaga WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
