package Modul9;

import java.awt.*;
import java.io.*;
import javax.swing.*;

public class AplikasiFileIO extends JFrame {
    JTextArea textArea;
    JButton btnOpentxt, btnSavetxt, btnSaveBinary, btnLoadBinary, btnWhoBinary, btnAppend;
    JFileChooser fileChooser;
    UserConfig userConfig;

    public AplikasiFileIO() {
        setTitle("Aplikasi File IO");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 14));
        fileChooser = new JFileChooser();

        JPanel btnPanel = new JPanel();
        btnOpentxt = new JButton("Open .txt");
        btnSavetxt = new JButton("Save .txt");
        btnAppend = new JButton("Append to .txt");
        btnSaveBinary = new JButton("Save Binary");
        btnLoadBinary = new JButton("Load Binary");
        btnWhoBinary = new JButton("Who Binary");

        btnPanel.add(btnOpentxt);
        btnPanel.add(btnSavetxt);
        btnPanel.add(btnAppend);
        btnPanel.add(btnSaveBinary);
        btnPanel.add(btnLoadBinary);

        add(btnWhoBinary, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        btnOpentxt.addActionListener(e -> openTextFile());
        btnSavetxt.addActionListener(e -> saveTextFile());
        btnAppend.addActionListener(e -> appendToFile());
        btnSaveBinary.addActionListener(e -> saveBinaryFile());
        btnLoadBinary.addActionListener(e -> loadBinaryFile());
        btnWhoBinary.addActionListener(e -> whoBinary());

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveLastSession();
            }
        });
    }

    void openTextFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = null;

            try {
                reader = new BufferedReader(new FileReader(file));
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                JOptionPane.showMessageDialog(this, "File berhasil dibuka.");

            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "File tidak ditemukan: " + ex.getMessage());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error membaca file: " + ex.getMessage());
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error menutup file: " + ex.getMessage());
                }
            }
        }
    }

    void saveTextFile() {
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "File berhasil disimpan.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error menyimpan file: " + ex.getMessage());
            }
        }
    }

    void saveBinaryFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Modul9/config.bin"))) {
            userConfig = new UserConfig();
            String username = System.getProperty("user.name");
            int fontSize = textArea.getFont().getSize();
            userConfig.setUsername(username);
            userConfig.setFontsize(fontSize);
            oos.writeObject(userConfig);

            JOptionPane.showMessageDialog(this,
                    "Object " + userConfig.getUsername() + " berhasil disimpan ke config.bin");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error menyimpan bin: " + e.getMessage());
        }
    }

    void loadBinaryFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Modul9/config.bin"))) {
            userConfig = (UserConfig) ois.readObject();
            int fontSize = userConfig.getFontsize();
            textArea.setFont(new Font("Monospaced", Font.PLAIN, fontSize));
            JOptionPane.showMessageDialog(this,
                    "Object " + userConfig.getUsername() + " berhasil dimuat dari config.bin");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Kelas UserConfig tidak ditemukan.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File config.bin tidak ditemukan.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error memuat bin: " + e.getMessage());
        }
    }

    void appendToFile() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.newLine();
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Text berhasil ditambahkan ke file.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error menulis ke file: " + e.getMessage());
            }
        }
    }

    void loadLastSession() {
        File file = new File("Modul9/last_note.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea.append(line + "\n");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error memuat sesi terakhir: " + e.getMessage());
            }
        } else {
            System.out.println("No previous session found.");
        }
    }

    void saveLastSession() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Modul9/last_note.txt"))) {
            writer.write(textArea.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error menyimpan sesi terakhir: " + e.getMessage());
        }
    }

    void whoBinary() {
        if (userConfig != null) {
            String info = "Username: " + userConfig.getUsername() + "\nFont Size: " + userConfig.getFontsize();
            JOptionPane.showMessageDialog(this, info, "User Config Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No user config loaded.", "User Config Info",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiFileIO app = new AplikasiFileIO();
            app.setVisible(true);
            app.loadLastSession();
        });
    }
}
