/*
 * Integrantes do grupo:
 * - Gabriel [Seu Sobrenome]
 * - [Nome do colega 1]
 * - [Nome do colega 2]
 * - [Nome do colega 3]
 *
 * Tema: Cadastro de Alunos
 * Campos: Nome, Idade, Curso, Matrícula
 */

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrudMenuCsv {
    private static final String filePath = "registros.csv";

    public static void main(String[] args) {
        while (true) {
            String[] options = {"Adicionar", "Listar", "Atualizar", "Remover", "Sair"};
            int choice = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Menu CRUD",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> adicionarRegistro();
                case 1 -> listarRegistros();
                case 2 -> atualizarRegistro();
                case 3 -> removerRegistro();
                case 4 -> {
                    JOptionPane.showMessageDialog(null, "Encerrando o programa.");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    private static void adicionarRegistro() {
        String nome = JOptionPane.showInputDialog("Digite o nome:");
        String idade = JOptionPane.showInputDialog("Digite a idade:");
        String curso = JOptionPane.showInputDialog("Digite o curso:");
        String matricula = JOptionPane.showInputDialog("Digite a matrícula:");

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(nome).append(",")
                  .append(idade).append(",")
                  .append(curso).append(",")
                  .append(matricula).append("\n");
            JOptionPane.showMessageDialog(null, "Registro adicionado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo.");
            e.printStackTrace();
        }
    }

    private static void listarRegistros() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder registros = new StringBuilder("Registros:\n");
            String linha;
            while ((linha = reader.readLine()) != null) {
                registros.append(linha).append("\n");
            }
            JOptionPane.showMessageDialog(null, registros.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
    }

    private static void atualizarRegistro() {
        List<String> registros = lerRegistros();
        if (registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog("Digite o nome do aluno a ser atualizado:");
        boolean encontrado = false;
        for (int i = 0; i < registros.size(); i++) {
            String[] dados = registros.get(i).split(",");
            if (dados[0].equalsIgnoreCase(nomeBusca)) {
                String novoNome = JOptionPane.showInputDialog("Novo nome:", dados[0]);
                String novaIdade = JOptionPane.showInputDialog("Nova idade:", dados[1]);
                String novoCurso = JOptionPane.showInputDialog("Novo curso:", dados[2]);
                String novaMatricula = JOptionPane.showInputDialog("Nova matrícula:", dados[3]);
                registros.set(i, novoNome + "," + novaIdade + "," + novoCurso + "," + novaMatricula);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            escreverRegistros(registros);
            JOptionPane.showMessageDialog(null, "Registro atualizado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static void removerRegistro() {
        List<String> registros = lerRegistros();
        if (registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado.");
            return;
        }

        String nomeBusca = JOptionPane.showInputDialog("Digite o nome do aluno a ser removido:");
        boolean encontrado = registros.removeIf(registro -> registro.split(",")[0].equalsIgnoreCase(nomeBusca));

        if (encontrado) {
            escreverRegistros(registros);
            JOptionPane.showMessageDialog(null, "Registro removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Registro não encontrado.");
        }
    }

    private static List<String> lerRegistros() {
        List<String> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                registros.add(linha);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler o arquivo.");
            e.printStackTrace();
        }
        return registros;
    }

    private static void escreverRegistros(List<String> registros) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String registro : registros) {
                writer.append(registro).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever no arquivo.");
            e.printStackTrace();
        }
    }
}
