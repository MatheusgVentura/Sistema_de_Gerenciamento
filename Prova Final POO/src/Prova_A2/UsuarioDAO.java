package Prova_A2;

import java.sql.*;

public class UsuarioDAO {

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (rg, senha, email, bairro_residencia) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getRg());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getBairroResidencia());
            stmt.executeUpdate();
        }
    }

    public boolean autenticar(String rg, String senha) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuario WHERE rg = ? AND senha = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rg);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    public boolean excluirUsuario(String rg) throws SQLException {
        String sql = "DELETE FROM usuario WHERE rg = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rg);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
