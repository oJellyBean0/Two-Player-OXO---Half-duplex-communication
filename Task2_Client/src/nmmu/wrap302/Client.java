package nmmu.wrap302;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    Socket client;
    DataInputStream input;
    DataOutputStream output;
    boolean playing = true;

    public Client() {
        runClientSide();
    }

    public static void main(String[] args) {
        new Client();
    }

    public void runClientSide() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Client side is started");
            System.out.println("Enter server IP address (just press ENTER if server is run on current pc");
            String ipAddress = scanner.nextLine();
            if (ipAddress.length() == 0)
                ipAddress = String.valueOf(InetAddress.getLocalHost().getHostAddress());
            System.out.println("Enter a port number (must be a number)");
            int portNum = Integer.parseInt(scanner.nextLine());
            connectToServer(ipAddress, portNum);

            getIOStreams();
            playing = true;
            while (playing) {
                sendAndReceiveHistData();
            }
            closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToServer(String ipAddress, int portNum) throws IOException {
        client = new Socket(ipAddress, portNum);
        System.out.println("Connected to: " + ipAddress);
    }

    public void getIOStreams() throws IOException {
        input = new DataInputStream(client.getInputStream());
        output = new DataOutputStream(client.getOutputStream());
        System.out.println("Got I/O Streams");
    }

    public void closeConnection() throws IOException {
        System.out.println("Transmission complete. Closing connection.");
        client.close();
        input.close();
        output.close();
    }

    public void sendAndReceiveHistData() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String boardString = input.readUTF();
        if (boardString.contains("|")) {
            String messageForClient = input.readUTF();
            System.out.println(boardString);
            System.out.println(messageForClient);
            //System.out.println("Enter position number of where you would like to place your X");

            String valueEntered = scanner.nextLine();
            output.writeUTF(valueEntered);
            System.out.println(input.readUTF());


        } else if (boardString.contains("Incorrect value")) {
            String messageForClient = input.readUTF();
            System.out.println(boardString);
            System.out.println(messageForClient);

            String valueEntered = scanner.nextLine();
            output.writeUTF(valueEntered);
            System.out.println(input.readUTF());
        } else if (boardString.contains("Other player's turn")) {
            System.out.println(boardString);
        } else if (boardString.contains("Game is finished")) {
            System.out.println(boardString);
            String anotherGame = input.readUTF();
            System.out.println(anotherGame);
            String reply = scanner.nextLine();
            output.writeUTF(reply);
            String anotherGameDecision = input.readUTF();
            System.out.println(anotherGameDecision);
            playing = anotherGameDecision.contains("Yay");
        }
    }


}
