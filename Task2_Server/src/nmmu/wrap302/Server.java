package nmmu.wrap302;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    OXOGame game;
    ServerSocket server;
    Socket connection;
    DataOutputStream output;
    DataInputStream input;
    boolean gameFinished = false;
    boolean playing = true;

    Scanner scanner = new Scanner(System.in);

    public Server() {
        runServer();
    }

    public static void main(String[] args) {
        new Server();
    }

    private void runServer() {

        try {
            System.out.println("SERVER: Server IP " + InetAddress.getLocalHost().getHostAddress());
            System.out.println("Enter a port number (must be a number)");
            int portNum = Integer.parseInt(scanner.nextLine());
            server = new ServerSocket(portNum, 1);
            System.out.println("SERVER: Server started " + InetAddress.getLocalHost().getHostAddress());


            while (true) {

                connection = server.accept();
                System.out.println("SERVER: Connection received from: " + connection.getInetAddress().getHostName());

                input = new DataInputStream(connection.getInputStream());
                output = new DataOutputStream(connection.getOutputStream());
                System.out.println("SERVER: Got I/O streams");

                game = new OXOGame();
                game.clientPlayer = new Player(10, connection);
                game.serverPlayer = new Player(0, null);
                game.initialiseCurPlayer();
                game.startGame();

                while (playing) {
                    gameFinished = false;
                    doCurrentPlayersTurn();
                }

                if (!playing) {
                    System.out.println("SERVER: Transmission complete. Closing socket.");
                    connection.close();
                    input.close();
                    output.close();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doCurrentPlayersTurn() throws IOException {
        if (game.curPlayer.equals(game.clientPlayer)) {
            output.writeUTF(game.gameBoard.toString());
            output.writeUTF("It is your turn. Make your move :) \nEnter position number of where you would like to place your X");
            System.out.println("Other player's turn");
            String clientInput = input.readUTF();
            while (!validateInput(clientInput)) {
                output.writeUTF("Incorrect value, please select a number seen on the board");
                clientInput = input.readUTF();
            }
            game.gameBoard.updateValue(Integer.parseInt(clientInput), 10);
            output.writeUTF("Your move was successful");

        } else {
            game.gameBoard.displayBoard();
            output.writeUTF("Other player's turn");
            System.out.println("It is your turn. Make your move :)");
            System.out.println("Enter position number of where you would like to place your O");
            String valueEntered = scanner.nextLine();
            while (!validateInput(valueEntered)) {
                System.out.println("Incorrect value, please select a number seen on the board");
                valueEntered = scanner.nextLine();
            }
            game.gameBoard.updateValue(Integer.parseInt(valueEntered), 0);
            System.out.println("Your move was successful");

        }
        if (game.isGameFinished()||game.checkForWinnerDuringGame()) {
            gameFinished = true;
        }
        if (gameFinished) {
            game.finishGame();
            output.writeUTF("Game is finished.\n" +
                    "Scores:\n" +
                    "Server (O) number of wins: " + game.oWins + "\n" +
                    "Client (X) number of wins: " + game.xWins + "\n" +
                    "Draws: " + game.draws + "\n");
            output.writeUTF("Would you like to play again? y/n");

            System.out.println("Would you like to play again? y/n");
            String serverReply = scanner.nextLine();



            String clientReply = input.readUTF();

            if ((clientReply.equals("Y") || clientReply.equals("y")) && (serverReply.equals("Y") || serverReply.equals("y"))) {
                playing = true;
                game.startGame();
                output.writeUTF("Yay, We playing another game");
                System.out.println("Yay, We playing another game");
            } else {
                playing = false;
                output.writeUTF("We will play another game next time");
                System.out.println("We will play another game next time.");
            }
        }
        else if (game.curPlayer.equals(game.clientPlayer)) {
            game.curPlayer = game.serverPlayer;
        } else game.curPlayer = game.clientPlayer;

    }

    public boolean validateInput(String str) {
        int num;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (num == 0 || num == 10)
            return false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (num == game.gameBoard.board[i][j])
                    return true;
            }
        }
        return false;
    }
}
