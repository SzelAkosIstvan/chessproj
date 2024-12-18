package Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Kiralyno implements SakkBabuk{
    private static final long serialVersionUID = 1L;
    private ImageIcon characterIcon;
    private int color;

    public Kiralyno(int color){
        this.color = color;
        if(color==0) {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/queen.png");
        } else {
            characterIcon = new ImageIcon("/Users/akosistvanszel/Documents/Java/projekt/src/img/wqueen.png");
        }
    }

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        return (fromX == toX || fromY == toY || Math.abs(fromX - toX) == Math.abs(fromY - toY));
    }

    @Override
    public void move(int fromX, int fromY, int toX, int toY) {
        if (isValidMove(fromX, fromY, toX, toY)) {
            System.out.println("A Vásáros lép: (" + fromX + "," + fromY + ") -> (" + toX + "," + toY + ")");
        } else {
            System.out.println("Érvénytelen lépés a Vásáros számára.");
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
        addDirectionalMoves(x, y, board, possibleMoves, 1, 0);  // Függőleges (lefelé)
        addDirectionalMoves(x, y, board, possibleMoves, -1, 0); // Függőleges (felfelé)
        addDirectionalMoves(x, y, board, possibleMoves, 0, 1);  // Vízszintes (jobbra)
        addDirectionalMoves(x, y, board, possibleMoves, 0, -1); // Vízszintes (balra)
        addDirectionalMoves(x, y, board, possibleMoves, 1, 1);  // Függőleges (lefelé)
        addDirectionalMoves(x, y, board, possibleMoves, -1, -1); // Függőleges (felfelé)
        addDirectionalMoves(x, y, board, possibleMoves, -1, 1);  // Vízszintes (jobbra)
        addDirectionalMoves(x, y, board, possibleMoves, 1, -1); // Vízszintes (balra)
        return possibleMoves;
    }
    private void addDirectionalMoves(int x, int y, SakkBabuk[][] board, ArrayList<int[]> moves, int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        while (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
            moves.add(new int[]{newX, newY});
            newX += dx;
            newY += dy;
        }
    }
}
