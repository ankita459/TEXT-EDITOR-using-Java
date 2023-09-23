import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.print.PrinterException;

public class TextEditor {
    public static void main(String[] args) {
        JTextArea textArea = new JTextArea();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);

        JMenuItem closeItem = new JMenuItem("Close");
        fileMenu.add(closeItem);

        JMenuItem printItem = new JMenuItem("Print");
        fileMenu.add(printItem);

        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem cutItem = new JMenuItem("Cut");
        editMenu.add(cutItem);

        JMenuItem copyItem = new JMenuItem("Copy");
        editMenu.add(copyItem);

        JMenuItem pasteItem = new JMenuItem("Paste");
        editMenu.add(pasteItem);

        frame.add(textArea);

        // Add functionality to menu items
        openItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                textArea.read(new FileReader(selectedFile.getAbsolutePath()), null);
            } catch (IOException ioException) {
                System.out.println("File not found");
            }
        }
    }
});

        saveItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter(fileToSave));
                out.write(textArea.getText());
                out.close();
            } catch (IOException ex) {
                System.out.println("Error saving file");
            }
        }
    }
});

        closeItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Ask the user to save their work before closing
        int option = JOptionPane.showConfirmDialog(frame, "Do you want to save your changes?");
        if (option == JOptionPane.YES_OPTION) {
            // Save the file
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(fileToSave));
                    out.write(textArea.getText());
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Error saving file");
                }
            }
        }
        // Clear the text area
        textArea.setText("");
    }
});

        printItem.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            boolean complete = textArea.print();
            if (complete) {
                JOptionPane.showMessageDialog(null, "Done printing");
            } else {
                JOptionPane.showMessageDialog(null, "Printing cancelled");
            }
        } catch (PrinterException pe) {
            JOptionPane.showMessageDialog(null, "Error: Print failed");
        }
    }
});

        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });
    }
}