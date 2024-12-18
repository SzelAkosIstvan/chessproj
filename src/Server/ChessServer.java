package Server;

import Game.Table;

import java.io.*;
import java.net.*;
import java.util.Random;

public class ChessServer {
    private Table table;
    public ChessServer(Table sharedTable) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Szerver elindult, várakozik a kliensre...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 ObjectOutputStream tableshare = new ObjectOutputStream(socket.getOutputStream())) {

                System.out.println("Kliens csatlakozott!");
                String enemy;
                //Table sharedTable = new Table();
                tableshare.writeObject(sharedTable);
                tableshare.flush();

                String serverMessage = "Guest1";
                out.println(serverMessage);//elkuldi a nevet

                String clientMessage = in.readLine();//kliens neve
                System.out.println("Enemy player name: " + clientMessage);
                enemy=clientMessage;


                Random random = new Random();
                int randomNumber = random.nextInt(2);
                System.out.println(randomNumber);
                if (randomNumber == 0) {
                    System.out.println(enemy+" kezd");
                    clientMessage= String.valueOf(randomNumber);
                    out.println(clientMessage);
                } else {
                    System.out.println(serverMessage+" kezd");
                    clientMessage= String.valueOf(randomNumber);
                    out.println(clientMessage);
                    serverMessage="szerver";
                    out.println(serverMessage);
                }
//                for(int i=0;i<8;i++){//while(true)
//                    clientMessage = in.readLine();
//                    System.out.println(clientMessage);
//                    serverMessage="szerver";
//                    out.println(serverMessage);
//                    //enemy lepes es babu torles
//                }
                while (true)
                {
                    clientMessage = in.readLine();
                    System.out.println(clientMessage);
                    if (clientMessage.equals("win")) {
                        System.out.println("enemy won the match");
                        serverMessage = "lose";
                        out.println(serverMessage);
                        break;
                    }
                    if(clientMessage.equals("lose")){
                        System.out.println("You won the match");
                        serverMessage = "win";
                        out.println(serverMessage);
                        break;
                    }
//                    serverMessage=table.getLastMove();
                    serverMessage="win";
                    out.println(serverMessage);//szerver uj lepese


                    //ha nyert akkor itt is break es kuld egy win uzenetet
                }

            } catch (IOException e) {
                System.err.println("Kommunikációs hiba: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Hiba a szerver elindításakor: " + e.getMessage());
        }
    }
//    public static void main(String[] args) {
//        new ChessServer();
//    }
}
