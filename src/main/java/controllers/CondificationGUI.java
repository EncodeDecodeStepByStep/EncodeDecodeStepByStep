package controllers;

import codifications.Codification;
import codifications.CodificationMapper;
import utils.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CondificationGUI extends JFrame implements ActionListener{
    private JPanel mainPanel;
    private JTextField codificacaoDesejadaString;
    private JComboBox codificationBox;
    private JButton encodeButton;
    private JButton decodeButton;
    private JTextArea trabalhoFeitoPorTextArea;
    private JList list1;
    private JTextField obsParaDecodeOTextField;
    private JProgressBar progressBar1;
    private JSpinner goulombDivisor;
    private JComboBox redunduncyBox;

    private JFileChooser chooser;
    private File file;

    private Codification codification;

    public CondificationGUI(String title) throws HeadlessException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.chooser = new JFileChooser();
        encodeButton.addActionListener(this);
        decodeButton.addActionListener(this);
    }

    public static void main(String[] args) {
        JFrame frame = new CondificationGUI("Encoder/Decoder - Teoria da Computação GA");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();

        if (e.getSource() == encodeButton){
            this.codification = CodificationMapper.getCodificationByStringName(String.valueOf(this.codificationBox.getSelectedItem()), (Integer) goulombDivisor.getValue());
            System.out.println("codificando");
            try {
                codification.encode(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("codificado");
        } else {
            System.out.println("decodificando");
            try {
                this.codification = CodificationMapper.getCodificationByStringBits(new Reader(file).readCabecalho());
                this.codification.decode(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("decodificado");
        }

    }
}
