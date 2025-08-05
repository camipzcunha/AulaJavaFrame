package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    private JTextField nomeField;
    private JTextArea comentarioArea;
    private JCheckBox interesseAWS;
    private JRadioButton simRadio;
    private JRadioButton naoRadio;
    private JButton enviarButton;

    public Main() {
        criarJanela();
    }

    private void criarJanela() {
        JFrame frame = new JFrame("Formulário de Contato");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.setBackground(new Color(240, 240, 245));

        // Título
        JLabel titulo = new JLabel("Formulário de Contato");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(new Color(50, 50, 100));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(20));

        // Nome
        JLabel nomeLabel = new JLabel("Nome:");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nomeField = new JTextField(20);
        nomeField.setMaximumSize(new Dimension(300, 25));
        nomeField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 150)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        painel.add(nomeLabel);
        painel.add(nomeField);
        painel.add(Box.createVerticalStrut(10));

        // Comentário
        JLabel comentarioLabel = new JLabel("Comentário:");
        comentarioLabel.setFont(new Font("Arial", Font.BOLD, 12));
        comentarioArea = new JTextArea(4, 20);
        comentarioArea.setLineWrap(true);
        comentarioArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JScrollPane scroll = new JScrollPane(comentarioArea);
        scroll.setMaximumSize(new Dimension(300, 80));
        scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 150)));
        painel.add(comentarioLabel);
        painel.add(scroll);
        painel.add(Box.createVerticalStrut(10));

        // AWS checkbox
        interesseAWS = new JCheckBox("Tem interesse em AWS?");
        interesseAWS.setBackground(new Color(240, 240, 245));
        painel.add(interesseAWS);
        painel.add(Box.createVerticalStrut(10));

        // Radio buttons
        JLabel pergunta = new JLabel("Já trabalha com cloud?");
        pergunta.setFont(new Font("Arial", Font.BOLD, 12));
        painel.add(pergunta);

        simRadio = new JRadioButton("Sim");
        naoRadio = new JRadioButton("Não");
        simRadio.setBackground(new Color(240, 240, 245));
        naoRadio.setBackground(new Color(240, 240, 245));

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(simRadio);
        grupo.add(naoRadio);

        JPanel radioPanel = new JPanel();
        radioPanel.setBackground(new Color(240, 240, 245));
        radioPanel.add(simRadio);
        radioPanel.add(naoRadio);
        painel.add(radioPanel);
        painel.add(Box.createVerticalStrut(20));

        // Botão enviar
        enviarButton = new JButton("Enviar");
        enviarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        enviarButton.setBackground(new Color(70, 130, 180));
        enviarButton.setForeground(Color.WHITE);
        enviarButton.setFont(new Font("Arial", Font.BOLD, 14));
        enviarButton.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        enviarButton.setFocusPainted(false);
        painel.add(enviarButton);

        // Eventos
        enviarButton.addActionListener(e -> enviarFormulario());

        enviarButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                enviarButton.setText("Envie meus dados!");
            }
            public void mouseExited(MouseEvent e) {
                enviarButton.setText("Enviar");
            }
        });

        frame.add(painel);
        frame.setVisible(true);
    }

    private void enviarFormulario() {
        String nome = nomeField.getText();
        String comentario = comentarioArea.getText();
        boolean aws = interesseAWS.isSelected();
        String cloud = "";

        if (simRadio.isSelected()) {
            cloud = "Sim";
        } else if (naoRadio.isSelected()) {
            cloud = "Não";
        } else {
            cloud = "Não respondeu";
        }

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o nome!");
            return;
        }

        // Mostrar dados
        String dados = "Nome: " + nome + "\n" +
                "Comentário: " + comentario + "\n" +
                "AWS: " + (aws ? "Sim" : "Não") + "\n" +
                "Cloud: " + cloud;

        JOptionPane.showMessageDialog(null, dados, "Dados enviados", JOptionPane.INFORMATION_MESSAGE);

        // Console (mantendo original)
        System.out.println("Nome: " + nome + ", Comentário: " + comentario);
    }

    public static void main(String[] args) {
        new Main();
    }
}