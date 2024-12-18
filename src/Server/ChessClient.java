package Server;

import Game.Table;

import java.io.*;
import java.net.*;

public class ChessClient {
    private Table table;
    public ChessClient(Socket socket) {
        try (//Socket socket = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             ObjectInputStream tablereader = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Csatlakozva a szerverhez!");
            Table receivedTable = (Table) tablereader.readObject();

            // Fogadjuk a szerver első üzenetét
            String serverMessage = in.readLine();
            System.out.println("Enemy player name: " + serverMessage);
            //Main.setEnemyName(serverMessage);

            String clientMessage = "Guest2";
            out.println(clientMessage);


            serverMessage = in.readLine();
            System.out.println(serverMessage);
            if(serverMessage.equals("1")){
                serverMessage = in.readLine();
                System.out.println(serverMessage);
                System.out.println("Az elso lepest a szerver tette meeeeg");
                //szerver lepes es babu torles
            }
//            for(int i=0;i<8;i++){
//                clientMessage = "kliens";
//                out.println(clientMessage);
//                serverMessage = in.readLine();
//                System.out.println(serverMessage);
//                //enemy lepes es babu torles
//            }
            while (true)
            {
                clientMessage = "kliens";
                out.println(clientMessage);//kliens lepese
                if(serverMessage.equals("win")){
                    System.out.println("enemy won the match");
                    clientMessage = "lose";
                    out.println(clientMessage);
                    break;
                }
                if(serverMessage.equals("lose")){
                    System.out.println("You won the match");
                    clientMessage = "win";
                    out.println(clientMessage);
                    break;
                }

                //ha nyert akkor itt is break es kuld egy win uzenetet

                serverMessage = in.readLine();
                System.out.println(serverMessage);//szerver lepese
            }

        } catch (IOException e) {
            System.err.println("Kommunikációs hiba: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//    public static void main(String[] args) {
//        new ChessClient();
//    }
}
