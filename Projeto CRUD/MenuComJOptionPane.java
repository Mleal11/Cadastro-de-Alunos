import javax.swing.JOptionPane;

public class MenuComJOptionPane {
    public static void main(String[] args) {
        String[] opcoes = {"Opção 1", "Opção 2", "Opção 3", "Sair"};
        boolean continuar = true;

        while (continuar) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma opção:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0: // Opção 1
                    JOptionPane.showMessageDialog(null, "Você escolheu a Opção 1!");
                    break;
                case 1: // Opção 2
                    JOptionPane.showMessageDialog(null, "Você escolheu a Opção 2!");
                    break;
                case 2: // Opção 3
                    JOptionPane.showMessageDialog(null, "Você escolheu a Opção 3!");
                    break;
                case 3: // Sair
                    JOptionPane.showMessageDialog(null, "Saindo do programa...");
                    continuar = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Nenhuma opção válida foi selecionada.");
                    continuar = false;
                    break;
            }
        }
    }
}