package Game;

import javax.swing.*;

public class MainTestFrame extends JFrame {
    public MainTestFrame() {
        setTitle("Sakk Tábla");
        setSize(400, 400);  // Ablak mérete
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hozzunk létre egy ChessboardPanel objektumot
        Table chessboardPanel = new Table();

        // Tábla hozzáadása az ablakhoz
        this.add(chessboardPanel);

        // Ablak bezárása előtt a tartalom igazítása
        pack();
        setLocationRelativeTo(null);  // Ablak középre helyezése
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainTestFrame().setVisible(true));
    }
}
