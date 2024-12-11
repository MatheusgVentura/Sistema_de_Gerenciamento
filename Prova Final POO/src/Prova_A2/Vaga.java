package Prova_A2;

class Vaga {
    private String empresa;
    private String cargo;
    private String descricao;
    private String requisitos;
    private String usuarioRg; // Associado ao RG do usuário

    public Vaga(String empresa, String cargo, String descricao, String requisitos, String usuarioRg) {
        this.empresa = empresa;
        this.cargo = cargo;
        this.descricao = descricao;
        this.requisitos = requisitos;
        this.usuarioRg = usuarioRg;
    }

    public Vaga(String empresa, String cargo, String descricao, String requisitos) {
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public String getUsuarioRG() {
        return this.usuarioRg;
    }

    @Override
    public String toString() {
        return "Empresa: " + empresa + ", Cargo: " + cargo + ", Descrição: " + descricao + ", Requisitos: " + requisitos;
    }
}