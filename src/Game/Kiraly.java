package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Kiraly implements SakkBabuk{
    private static final long serialVersionUID = 1L;
    private ImageIcon characterIcon;
    private int color;

    public Kiraly(int color){
        this.color = color;
        if(color==0) {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/king.png");
        } else {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/wking.png");
        }
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        return Math.abs(fromX - toX) <= 1 && Math.abs(fromY - toY) <= 1;
    }

    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (isValidMove(fromX, fromY, toX, toY)) {
            System.out.println("A Király lép: (" + fromX + "," + fromY + ") -> (" + toX + "," + toY + ")");
        } else {
            System.out.println("Érvénytelen lépés a Király számára.");
        }
    }

    @Override
    public void draw(Graphics g, int squareSize, int x, int y) {
        int pixelX = x * squareSize+15;
        int pixelY = y * squareSize+15;
        g.setColor(Color.RED);
        ImageIcon resizedIcon = new ImageIcon(characterIcon.getImage().getScaledInstance(squareSize-30, squareSize-30, Image.SCALE_SMOOTH));
        g.drawImage(resizedIcon.getImage(), pixelX, pixelY, null);
    }

    @Override
    public ArrayList<int[]> getAllPossibleMoves(int x, int y, SakkBabuk[][] board) {
        ArrayList<int[]> possibleMoves = new ArrayList<>();
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX<8 && newX>=0 && newY<8 && newY>=0 &&board[newX][newY] == null) {
                possibleMoves.add(new int[]{newX, newY});
            }
        }
        return possibleMoves;
    }
}
