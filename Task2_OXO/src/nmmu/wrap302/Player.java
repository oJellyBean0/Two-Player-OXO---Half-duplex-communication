package nmmu.wrap302;

import java.net.Socket;

public class Player {
    //request client to move then you going to go from the
    //client and server are linked from player

    //0 = O Server and 10 = X Client
    int symbol;
    Socket connection;

    public Player(int symbol, Socket connection) {
        this.symbol = symbol;
        this.connection = connection;//Note: Connection will be null for the server
    }
}
