import MainMenu.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main extends JFrame {
    private int volume;
    private int tableStyleID;
    private static String playerName;
    public Main() {
        setTitle("Sakk");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        loadMainMenu();
        //add(new BackgroundPanel("/Users/akosistvanszel/Documents/Java/projekt/src/img/backgroundMain.jpg"));
        loadFromFile("/Users/akosistvanszel/Documents/Java/projekt/src/config.properties");
        setVisible(true);
    }
    public static void main(String[] args) {
        new Main();
    }
    private void loadMainMenu() {
        BackgroundPanel backgroundPanel = new BackgroundPanel("/Users/akosistvanszel/Documents/Java/projekt/src/img/backgroundMain.jpg");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(new MainMenu(this), BorderLayout.CENTER);
        setContentPane(backgroundPanel);
        revalidate();
        repaint();
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
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                System.out.println("Nem sikerült betölteni a képet: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
    public static String getPlayerName() {
        return playerName;
    }
}
