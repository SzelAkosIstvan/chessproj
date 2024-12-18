package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Paraszt implements SakkBabuk {
    private static final long serialVersionUID = 1L;
    private ImageIcon characterIcon;
    private int color;//1 vagy 0 attol fuggoen,hogy ki kezd

    public Paraszt(int color){
        this.color = color;
        if(color==0) {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/pawn.png");
        } else {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/wpawn.png");
        }
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        return (fromX == toX && toY == fromY + 1);
    }

    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (isValidMove(fromX, fromY, toX, toY)) {
            System.out.println("A Paraszt lép: (" + fromX + "," + fromY + ") -> (" + toX + "," + toY + ")");
        } else {
            System.out.println("Érvénytelen lépés a Paraszt számára.");
        }
    }

    @Override
    public void draw(Graphics g, int squareSize, int x, int y) {
        int pixelX = x * squareSize + 15;
        int pixelY = y * squareSize + 15;
        g.setColor(Color.RED);
        ImageIcon resizedIcon = new ImageIcon(characterIcon.getImage().getScaledInstance(squareSize - 30, squareSize - 30, Image.SCALE_SMOOTH));
        g.drawImage(resizedIcon.getImage(), pixelX, pixelY, null);
    }

    @Override
    public ArrayList<int[]> getAllPossibleMoves(int x, int y, SakkBabuk[][] board) {
        ArrayList<int[]> allPossibleMoves = new ArrayList<>();
        if (board[x-1][y] == null) {
            allPossibleMoves.add(new int[]{x-1, y});
            return allPossibleMoves;
        }
        return allPossibleMoves;
    }
}
