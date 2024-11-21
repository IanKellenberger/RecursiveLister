import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class RecursiveLister {
    private JFrame frame;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private JButton startButton;
    private JButton quitButton;
    private JLabel titleLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                RecursiveLister window = new RecursiveLister();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public RecursiveLister() {
        frame = new JFrame("Recursive Lister");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        titleLabel = new JLabel("Select a Directory to List Files", SwingConstants.CENTER);
        frame.getContentPane().add(titleLabel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chooseDirectory();
            }
        });
        panel.add(startButton);

        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(quitButton);
    }

    // Method to choose a directory
    private void chooseDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = chooser.showOpenDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = chooser.getSelectedFile();
            textArea.setText(""); // Clear the text area
            listFiles(selectedDirectory, "");
        }
    }

    // Recursive method to list files in directory and subdirectories
    private void listFiles(File directory, String indent) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        textArea.append(indent + "Directory: " + file.getName() + "\n");
                        listFiles(file, indent + "  ");  // Recurse into sub-directory
                    } else {
                        textArea.append(indent + "File: " + file.getName() + "\n");
                    }
                }
            }
        }
    }
}
