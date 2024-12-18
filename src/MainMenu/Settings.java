package MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class Settings extends JPanel {
    private int volume = 50;       // Default value
    private int tableStyleID = 0;  // Default value
    private String playerName = "";
    private String name=null;
    private JSlider volumeSlider;
    private JLabel volumeLabel;
    private JTextField nameField;
    private JComboBox<String> selectTableStyle;

    public Settings(JFrame parentFrame) {
        // Set layout
        setLayout(null);
        setBackground(new Color(240, 240, 240)); // Light gray background for a clean look

        // Title label
        JLabel titleLabel = new JLabel("Settings Menu");
        titleLabel.setBounds(200, 20, 200, 30); // Centered at the top
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel);

        // Name input field
        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setBounds(100, 80, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(nameLabel);

        String randomName = "guest" + generateRandomNumber(1000, 9999);
        nameField = new JTextField(randomName);
        nameField.setBounds(250, 80, 200, 30); // x, y, width, height
        add(nameField);

        // Volume slider
        JLabel sliderLabel = new JLabel("Adjust Volume:");
        sliderLabel.setBounds(100, 140, 150, 30);
        sliderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(sliderLabel);

        volumeSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, volume);
        volumeSlider.setBounds(250, 140, 200, 40); // Positioned horizontally
        volumeSlider.setMajorTickSpacing(20);
        volumeSlider.setMinorTickSpacing(5);
        volumeSlider.setPaintTicks(true);
        volumeSlider.setPaintLabels(true);
        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setVolume(volumeSlider.getValue());
                volumeLabel.setText("Volume: " + getVolume());
            }
        });
        add(volumeSlider);

        // Volume label
        volumeLabel = new JLabel("Volume: " + volume);
        volumeLabel.setBounds(460, 140, 100, 30); // Displayed beside the slider
        volumeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(volumeLabel);

        // Table style combo box
        JLabel tableStyleLabel = new JLabel("Select Table Style:");
        tableStyleLabel.setBounds(100, 200, 150, 30);
        tableStyleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(tableStyleLabel);

        String[] choices = {"1. Black-White", "2. Gold-White", "3. Future"};
        selectTableStyle = new JComboBox<>(choices);
        selectTableStyle.setBounds(250, 200, 200, 30);
        selectTableStyle.setSelectedIndex(tableStyleID);
        selectTableStyle.setEditable(false);
        selectTableStyle.setBackground(Color.WHITE);
        selectTableStyle.setForeground(Color.BLACK);
        selectTableStyle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectTableStyle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setTableStyleID(selectTableStyle.getSelectedIndex());
                System.out.println("Table style set to: " + getTableStyleID());
            }
        });
        add(selectTableStyle);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(250, 270, 100, 30); // Positioned below all components
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = nameField.getText();
                int userVolume = volumeSlider.getValue();
                int userTableStyle = selectTableStyle.getSelectedIndex();

                saveToFile("/Users/akosistvanszel/Documents/Java/projekt/src/config.properties", userName, userVolume, userTableStyle);

                parentFrame.getContentPane().remove(Settings.this);
                parentFrame.add(new MainMenu(parentFrame), BorderLayout.CENTER);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
        });
        add(saveButton);
        loadFromFile("/Users/akosistvanszel/Documents/Java/projekt/src/config.properties");
    }
    public void loadFromFile(String filePath) {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader(filePath)) {
            properties.load(reader);

            // Adattagok frissítése a fájl tartalmából
            volume = Integer.parseInt(properties.getProperty("volume", String.valueOf(volume)));
            tableStyleID = Integer.parseInt(properties.getProperty("tableStyleID", String.valueOf(tableStyleID)));
            playerName = properties.getProperty("playerName", playerName);

            System.out.println("Adatok sikeresen betöltve a fájlból.");
        } catch (IOException | NumberFormatException e) {
            System.err.println("Hiba történt a fájl beolvasása közben: " + e.getMessage());
        }
    }
    private void saveToFile(String filePath, String userName, int userVolume, int userTableStyle) {
        Properties properties = new Properties();
        properties.setProperty("name", userName);
        properties.setProperty("volume", String.valueOf(userVolume));
        properties.setProperty("tableStyleID", String.valueOf(userTableStyle));

        try (FileWriter writer = new FileWriter(filePath)) {
            properties.store(writer, "User Settings");
            System.out.println("Settings successfully saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving settings: " + e.getMessage());
        }
    }
    private String generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNumber);
    }

    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getTableStyleID() {
        return tableStyleID;
    }
    public void setTableStyleID(int tableStyleID) {
        this.tableStyleID = tableStyleID;
    }
    public String getName()
    {
        return name;
    }
}