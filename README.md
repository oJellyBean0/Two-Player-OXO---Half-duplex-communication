# Two-Player-OXO---Half-duplex-communication

## Objectives:

```
 Threads
 Half-duplex communication
```

## Two-Player OXO

Noughts and crosses^1 is a simple game, similar to SOS that you implemented previously. For this
task, you are to write a networked version of the game where two people can play against one
another. The game is to be written in Java, as a **_console_** application, i.e. no Android required... unless
you want too.

The board can be represented simply using text only, e.g.:

1|2|O
-+-+-
4|X|O
-+-+-
X|8|9

where the numbers are places where the current player can place their mark, and “X” and “O” are
the player’s marks that have been placed already.

You will need to write two console applications, namely the game-server and the game-client. The
game-server will initially ask the player running it for the port number to be used. The game-client
will ask the player running it for the address and port number of the game-server.

The game-server is responsible for maintaining the state of the game, deciding whose turn it is, what
valid moves are and whether the game is over or not. The game-client, on the other hand, is a
“dumb” terminal and simply receives the game state information from the server, displays it to the
user, accepts input and sends the commands to the game-server (which will process the command
and update the board). Therefore the game-client should be very simple, with the majority of the
logic residing in the game-server.

The player running the game-server will be “O”, while the player running the game-client will be “X”.
Once a game has started, the game-server can decide who starts the first round.

Play will alternate between “O” and “X” with players placing their mark in an empty place on the
grid. Play will end when there is a draw or a winner.

Once a game has ended, each player will be asked if they want to play another game or not. If both
want to play, then a new game starts and the player who did not start the previous round will start
the next one. If either player does not wish to play further, then both the server and client should
shut down.

The server should keep score of the number of wins for “O”, number of wins for “X” and the number
of draws. This information should be displayed to both players at the end of a game.
