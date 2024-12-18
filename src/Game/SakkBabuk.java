package Game;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public interface SakkBabuk extends Serializable {
    boolean isValidMove(int x, int y, int newX, int newY);
    void move(int x, int y, int newX, int newY);
    void draw(Graphics g, int squareSize, int x, int y);
    ArrayList<int []> getAllPossibleMoves(int x, int y, SakkBabuk[][] board);
}
