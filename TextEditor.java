import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener {
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu;
    private JMenuItem newFile, openFile, saveFile, saveAndSubmit, closeFile, cutText, copyText, pasteText;

// Demooo Git

    public TextEditor() {
        setTitle("Text Editor");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        textArea = new JTextArea();
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        saveAndSubmit = new JMenuItem("Save and Submit");
        closeFile = new JMenuItem("Close");
        cutText = new JMenuItem("Cut");
        copyText = new JMenuItem("Copy");
        pasteText = new JMenuItem("Paste");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        saveAndSubmit.addActionListener(this);
        closeFile.addActionListener(this);
        cutText.addActionListener(this);
        copyText.addActionListener(this);
        pasteText.addActionListener(this);

        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAndSubmit);
        fileMenu.add(closeFile);
        editMenu.add(cutText);
        editMenu.add(copyText);
        editMenu.add(pasteText);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);
        add(textArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            textArea.setText("");
        } else if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

            int option = fileChooser.showOpenDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));
                    textArea.setText("");
                    String line;
                    while ((line = br.readLine()) != null) {
                        textArea.append(line + "\n");
                    }
                    br.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if (e.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files (*.txt)", "txt"));

            int option = fileChooser.showSaveDialog(this);

            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                    bw.write(textArea.getText());
                    bw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if (e.getSource() == saveAndSubmit) {
            // You can implement the submit logic here
            // For example, send the text to a server or perform some other action
        } else if (e.getSource() == closeFile) {
            System.exit(0);
        } else if (e.getSource() == cutText) {
            textArea.cut();
        } else if (e.getSource() == copyText) {
            textArea.copy();
        } else if (e.getSource() == pasteText) {
            textArea.paste();
        }
    }  

    public static void main(String[] args) {
        new TextEditor();
    }
}
