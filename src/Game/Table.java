package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.io.Serializable;

public class Table extends JPanel implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final int BOARD_SIZE = 8; // 8x8 tábla
    private SakkBabuk[][] board; // A tábla bábúi
    private int highlightedRow = -1;
    private int highlightedCol = -1;
    private ArrayList<int[]> possibilities;


    private String lastMoveFrom, lastMoveTo;

    public Table() {
        possibilities = new ArrayList<>();
        this.setPreferredSize(new Dimension(400, 400));
        board = new SakkBabuk[BOARD_SIZE][BOARD_SIZE];

        // Bábuk elhelyezése
// Fehér gyalogok
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][1] = new Paraszt(1); // Fehér gyalogok
        }

// Fekete gyalogok
        for (int i = 0; i < BOARD_SIZE; i++) {
            board[i][6] = new Paraszt(0); // Fekete gyalogok
        }

// Fehér tisztek (1. sor)
        board[0][0] = new Bastya(1);   // Fehér bástya
        board[1][0] = new Lo(1);       // Fehér ló
        board[2][0] = new Futo(1);     // Fehér futó
        board[3][0] = new Kiralyno(1); // Fehér királynő
        board[4][0] = new Kiraly(1);   // Fehér király
        board[5][0] = new Futo(1);     // Fehér futó
        board[6][0] = new Lo(1);       // Fehér ló
        board[7][0] = new Bastya(1);   // Fehér bástya

// Fekete tisztek (8. sor)
        board[0][7] = new Bastya(0);   // Fekete bástya
        board[1][7] = new Lo(0);       // Fekete ló
        board[2][7] = new Futo(0);     // Fekete futó
        board[3][7] = new Kiralyno(0); // Fekete királynő
        board[4][7] = new Kiraly(0);   // Fekete király
        board[5][7] = new Futo(0);     // Fekete futó
        board[6][7] = new Lo(0);       // Fekete ló
        board[7][7] = new Bastya(0);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int squareSize = getWidth() / BOARD_SIZE;
        boolean isWhite = true;

        // Táblázat rajzolása
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (row == highlightedRow && col == highlightedCol) {
                    g.setColor(new Color(255, 198, 52)); // Kijelölt mező színe
                } else if (possibilities != null && isInPossibilities(row, col)) {
                    g.setColor(Color.GREEN);
                } else if (isWhite) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.DARK_GRAY);
                }
                g.fillRect(col * squareSize, row * squareSize, squareSize, squareSize);

                // Bábuk kirajzolása
                if (board[col][row] != null) {
                    board[col][row].draw(g, squareSize, col, row);
                }

                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }
    }

    private boolean isInPossibilities(int row, int col) {
        for (int[] pos : possibilities) {
            if (pos[0] == row && pos[1] == col) {
                return true;
            }
        }
        return false;
    }

    private void handleMouseClick(MouseEvent e) {
        int squareSize = getWidth() / BOARD_SIZE;
        int col = e.getX() / squareSize;
        int row = e.getY() / squareSize;

        // Ellenőrizd, hogy a kattintás a táblán belül történt-e
        if (col >= 0 && col < BOARD_SIZE && row >= 0 && row < BOARD_SIZE) {
            // Ha a kattintott mező egy lehetőség (zöld mező)
            if (isInPossibilities(row, col)) {
                // Rakd át a kijelölt bábut az új helyre
                board[col][row] = board[highlightedCol][highlightedRow];
                board[highlightedCol][highlightedRow] = null; // Eredeti helyet ürítsd ki

                lastMoveFrom = highlightedCol+","+highlightedRow;
                lastMoveTo = col+","+row;

                // Töröld a kiemelést és a lehetőségeket
                highlightedRow = -1;
                highlightedCol = -1;
                possibilities.clear();

                repaint(); // Újrarajzolás a frissített állapottal
            }
            // Ha egy másik mezőt kattintottak meg, frissítsd a kijelölt mezőt és a lehetőségeket
            else if ((highlightedCol == -1 && highlightedRow == -1) || (highlightedCol != col || highlightedRow != row)) {
                SakkBabuk clickedPiece = board[col][row];
                System.out.println("Kattintott mező: oszlop=" + col + ", sor=" + row);

                if (clickedPiece != null) {
                    System.out.println("Bábú: " + clickedPiece.getClass().getSimpleName());
                    if (possibilities != null) {
                        possibilities.clear();
                    }
                    possibilities = clickedPiece.getAllPossibleMoves(row, col, board);
                } else {
                    System.out.println("Nincs bábú a mezőn.");
                    possibilities.clear();
                }

                // A kijelölt mező frissítése
                highlightedRow = row;
                highlightedCol = col;
                repaint(); // Újrarajzolás
            }
            // Ha újra ugyanazt a mezőt kattintották meg, töröld a kiemelést
            else if (highlightedCol != -1 && highlightedRow != -1) {
                highlightedRow = -1;
                highlightedCol = -1;
                if (possibilities != null) {
                    possibilities.clear();
                }
                repaint();
            }
        }
    }

    public String getLastMove() {
        if(lastMoveFrom == null || lastMoveTo == null) {
            return "null";
        }
        System.out.println(lastMoveFrom+"->"+lastMoveTo);
        return lastMoveFrom+"->"+lastMoveTo;
    }
    public void setLastMoves() {
        lastMoveFrom = null;
        lastMoveTo = null;
    }
}

