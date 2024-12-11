package Prova_A2;

public class Usuario {
    private String rg;
    private String senha;
    private String email;
    private String bairro;

    public Usuario(String rg, String senha, String email, String bairro) {
        this.rg = rg;
        this.senha = senha;
        this.email = email;
        this.bairro = bairro;
    }

    public String getRg() {
        return rg;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public String getBairroResidencia() {
        return bairro;
    }

    @Override
    public String toString() {
        return "RG: " + rg + ", Email: " + email + ", Bairro: " + bairro;
    }
}
