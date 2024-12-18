package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Lo implements SakkBabuk{
    private static final long serialVersionUID = 1L;
    private ImageIcon characterIcon;
    private int color;

    public Lo(int color){
        this.color = color;
        if(color==0) {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/horse.png");
        } else {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/whorse.png");
        }
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (isValidMove(fromX, fromY, toX, toY)) {
            System.out.println("A Ló lép: (" + fromX + "," + fromY + ") -> (" + toX + "," + toY + ")");
        } else {
            System.out.println("Érvénytelen lépés a Ló számára.");
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
        int[][] moves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                possibleMoves.add(new int[]{newX, newY});
            }
        }
        return possibleMoves;
    }
}
