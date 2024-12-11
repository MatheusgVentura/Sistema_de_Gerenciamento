package Prova_A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Usuario> usuarios = new ArrayList<>();
    static List<Vaga> vagas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Sistema de Gerenciamento de Vagas de Emprego");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Login");
            System.out.println("3. Cadastrar Vaga");
            System.out.println("4. Listar Vagas do Usuário");
            System.out.println("5. Excluir Usuário");
            System.out.println("6. Excluir Vaga");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir caractere de nova linha

            switch (opcao) {
                case 1:
                    cadastrarUsuario(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    cadastrarVaga(scanner);
                    break;
                case 4:
                    listarVagas(scanner);
                    break;
                case 5:
                    excluirUsuario(scanner);
                    break;
                case 6:
                    excluirVaga(scanner);
                    break;
                case 7:
                    System.out.println("\nSaindo do sistema...");
                    System.exit(0); // Encerra o programa
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.\n");
            }
        }
    }

    // Método para cadastrar o usuário
    public static void cadastrarUsuario(Scanner scanner) {
        System.out.println("Digite o RG do usuário: ");
        String rg = scanner.nextLine();
        if (!validarRG(rg)) {
            System.out.println("\nRG inválido! O RG deve conter apenas números e ter 10 caracteres.\n");
            return;
        }

        System.out.println("Digite a senha do usuário: ");
        String senha = scanner.nextLine();
        if (!validarSenha(senha)) {
            System.out.println("\nSenha inválida! A senha deve ter pelo menos 6 caracteres.\n");
            return;
        }

        System.out.println("Digite o email do usuário: ");
        String email = scanner.nextLine();
        if (!validarEmail(email)) {
            System.out.println("\nEmail inválido! O email não tem o formato correto.\n");
            return;
        }

        System.out.println("Digite o bairro de residência do usuário: ");
        String bairro = scanner.nextLine();

        // Verifica se o RG já existe
        for (Usuario usuario : usuarios) {
            if (usuario.getRg().equals(rg)) {
                System.out.println("\nRG já cadastrado. Tente um RG diferente.\n");
                return;
            }
        }

        Usuario usuario = new Usuario(rg, senha, email, bairro);
        usuarios.add(usuario);
        System.out.println("\nUsuário cadastrado com sucesso!\n");
    }

    // Método para realizar login
    public static void login(Scanner scanner) {
        System.out.println("Digite o RG do usuário para login: ");
        String rg = scanner.nextLine();
        System.out.println("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        // Verifica se o usuário existe e se a senha está correta
        for (Usuario usuario : usuarios) {
            if (usuario.getRg().equals(rg) && usuario.getSenha().equals(senha)) {
                System.out.println("\nLogin realizado com sucesso para o RG: " + rg + "\n");
                return;
            }
        }

        System.out.println("\nCredenciais inválidas. Tente novamente.\n");
    }

    // Método para cadastrar vaga
    public static void cadastrarVaga(Scanner scanner) {
        System.out.println("Digite o nome da empresa: ");
        String empresa = scanner.nextLine();
        System.out.println("Digite o cargo: ");
        String cargo = scanner.nextLine();
        System.out.println("Digite a descrição da vaga: ");
        String descricao = scanner.nextLine();
        System.out.println("Digite os requisitos da vaga: ");
        String requisitos = scanner.nextLine();
        System.out.println("Digite o RG do usuário associado à vaga: ");
        String usuarioRg = scanner.nextLine();

        // Verifica se o usuário existe
        boolean usuarioExistente = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getRg().equals(usuarioRg)) {
                usuarioExistente = true;
                break;
            }
        }

        if (!usuarioExistente) {
            System.out.println("\nRG do usuário não encontrado. A vaga não pode ser cadastrada.\n");
            return;
        }

        Vaga vaga = new Vaga(empresa, cargo, descricao, requisitos, usuarioRg);
        vagas.add(vaga);
        System.out.println("\nVaga cadastrada com sucesso!\n");
    }

    // Método para listar vagas do usuário
    public static void listarVagas(Scanner scanner) {
        System.out.println("Digite o RG do usuário para listar as vagas: ");
        String rg = scanner.nextLine();

        // Filtra e lista as vagas associadas ao RG do usuário
        boolean encontrouVaga = false;
        for (Vaga vaga : vagas) {
            if (vaga.getUsuarioRG().equals(rg)) {
                System.out.println(vaga);
                encontrouVaga = true;
            }
        }

        if (!encontrouVaga) {
            System.out.println("\nNão foram encontradas vagas para o RG: " + rg + "\n");
        }
    }

    // Método para excluir um usuário
    public static void excluirUsuario(Scanner scanner) {
        System.out.println("Digite o RG do usuário que deseja excluir: ");
        String rg = scanner.nextLine();

        boolean usuarioRemovido = usuarios.removeIf(usuario -> usuario.getRg().equals(rg));
        if (usuarioRemovido) {
            vagas.removeIf(vaga -> vaga.getUsuarioRG().equals(rg));
            System.out.println("\nUsuário e suas vagas associadas foram excluídos com sucesso!\n");
        } else {
            System.out.println("\nUsuário não encontrado.\n");
        }
    }

    // Método para excluir uma vaga
    public static void excluirVaga(Scanner scanner) {
        System.out.println("Digite o nome da empresa da vaga que deseja excluir: ");
        String empresa = scanner.nextLine();
        System.out.println("Digite o cargo da vaga que deseja excluir: ");
        String cargo = scanner.nextLine();

        boolean vagaRemovida = vagas.removeIf(vaga -> vaga.getEmpresa().equals(empresa) && vaga.getCargo().equals(cargo));
        if (vagaRemovida) {
            System.out.println("\nVaga excluída com sucesso!\n");
        } else {
            System.out.println("\nVaga não encontrada.\n");
        }
    }

    // Método para validar o RG
    public static boolean validarRG(String rg) {
        return rg.matches("\\d{10}");
    }

    // Método para validar a senha
    public static boolean validarSenha(String senha) {
        return senha.length() >= 6;
    }

    // Método para validar o email
    public static boolean validarEmail(String email) {
        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }
}
