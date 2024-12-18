package MainMenu;

import Game.Table;
import Server.ChessClient;
import Server.ChessServer;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class StartGame {
    public void startGame(JFrame parentFrame) {
        System.out.println("Starting game...");
        try {
            // Megpróbálunk csatlakozni a szerverhez
            Socket socket = new Socket("localhost", 12345);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Table sharedTable = (Table) in.readObject();
            createGameFrame(sharedTable);
            new ChessClient(socket);
        } catch (IOException e) {
            // Ha nem létezik még szerver, akkor indítunk egyet
            try {
                //createGameFrame();
                Table sharedTable = new Table();
                ChessServer server = new ChessServer(sharedTable);
                createGameFrame(sharedTable);
                Thread.sleep(1000); // Várakozás, hogy a szerver biztosan elinduljon
                new Thread(new Runnable() {
                    public void run() {
                        Socket socket = null;
                        try {
                            socket = new Socket("localhost", 12345);
//                            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
//                            Table sharedTable = (Table) in.readObject();
//                            createGameFrame(sharedTable);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                        //System.out.println("hasznalom a sakktablat");
                    }
                });
            } catch (InterruptedException ex) {
                System.err.println("Nem sikerült elindítani a szervert vagy csatlakozni: " + ex.getMessage());
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        if (parentFrame != null) {
//            parentFrame.dispose(); // Close and release resources of the parent frame
//        }

    }

    private void createGameFrame(Table chessboardPanel) {
        JFrame gameFrame = new JFrame("Chess Game");
//        Table chessboardPanel = new Table();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().add(chessboardPanel);
        gameFrame.setSize(800, 830);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
