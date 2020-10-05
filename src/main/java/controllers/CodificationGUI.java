package controllers;

import codifications.Codification;
import codifications.CodificationMapper;
import utils.ErrorWriter;
import utils.Reader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CodificationGUI extends JFrame implements ActionListener {
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
    private JLabel goulombKLabel;

    private JFileChooser chooser;
    private File file;

    private Codification codification;

    public CodificationGUI(String title) throws HeadlessException {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.chooser = new JFileChooser();
        encodeButton.addActionListener(this);
        decodeButton.addActionListener(this);
        goulombDivisor.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if(Integer.parseInt(goulombDivisor.getValue().toString())<2){
                    goulombDivisor.setValue(2);
                }
            }
        });
        codificationBox.addActionListener(this);
        goulombKLabel.setVisible(false);
        goulombDivisor.setVisible(false);
        goulombDivisor.setValue(2);
    }

    public static void main(String[] args) {
        JFrame frame = new CodificationGUI("Encoder/Decoder - Teoria da Computação GA");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==codificationBox){
            if(codificationBox.getSelectedItem().toString()=="Goulomb"){
                goulombKLabel.setVisible(true);
                goulombDivisor.setVisible(true);
            }else{
                goulombKLabel.setVisible(false);
                goulombDivisor.setVisible(false);
            }

        }else{
            new Thread(new Runnable() {
                public void run() {
                    chooser.showOpenDialog(null);
                    file = chooser.getSelectedFile();
                    chooser.setSelectedFile(null);

                    progressBar1.setValue(0);
                    ErrorWriter.setFile(file.getParent(), file.getName());

                    if (e.getSource() == encodeButton) {
                        codification = CodificationMapper.getCodificationByStringName(String.valueOf(codificationBox.getSelectedItem()), (Integer) goulombDivisor.getValue());
                        System.out.println("codificando");
                        try {
                            codification.encode(file, progressBar1);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        System.out.println("codificado");
                    } else {
                        System.out.println("decodificando");
                        try {
                            codification = CodificationMapper.getCodificationByStringBits(new Reader(file, null).readCabecalho());
                            codification.decode(file, progressBar1);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        System.out.println("decodificado");
                    }
                }
            }).start();
        }
    }
}
