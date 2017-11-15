package nmmu.wrap302;

public class Board {

    //layout of board
    //logic of game
    int[][] board;
    int boardSize = 3;

    public Board() {
        board = new int[boardSize][boardSize];
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int value = (((i - 1) * 3) + j);
                board[i - 1][j - 1] = value;
            }
        }
    }

    public int checkDiagonalForO() {
        int counter = 0;
        if (board[0][0] == 0 && board[1][1] == 0 && board[2][2] == 0) {
            counter++;
        }
        if (board[0][2] == 0 && board[1][1] == 0 && board[2][0] == 0) {
            counter++;
        }

        return counter;
    }

    public int checkVerticalForO() {
        int counter = 0;
        if (board[0][0] == 0 && board[1][0] == 0 && board[2][0] == 0) {
            counter++;
        }
        if (board[0][1] == 0 && board[1][1] == 0 && board[2][1] == 0) {
            counter++;
        }
        if (board[0][2] == 0 && board[1][2] == 0 && board[2][2] == 0) {
            counter++;
        }
        return counter;

    }

    public int checkHorizontalForO() {
        int counter = 0;
        if (board[0][0] == 0 && board[0][1] == 0 && board[0][2] == 0) {
            counter++;
        }
        if (board[1][0] == 0 && board[1][1] == 0 && board[1][2] == 0) {
            counter++;
        }
        if (board[2][0] == 0 && board[2][1] == 0 && board[2][2] == 0) {
            counter++;
        }

        return counter;

    }

    public int checkDiagonalForX() {
        int counter = 0;
        if (board[0][0] == 10 && board[1][1] == 10 && board[2][2] == 10) {
            counter++;
        }
        if (board[0][2] == 10 && board[1][1] == 10 && board[2][0] == 10) {
            counter++;
        }

        return counter;
    }

    public int checkVerticalForX() {
        int counter = 0;
        if (board[0][0] == 10 && board[1][0] == 10 && board[2][0] == 10) {
            counter++;
        }
        if (board[0][1] == 10 && board[1][1] == 10 && board[2][1] == 10) {
            counter++;
        }
        if (board[0][2] == 10 && board[1][2] == 10 && board[2][2] == 10) {
            counter++;
        }
        return counter;

    }

    public int checkHorizontalForX() {
        int counter = 0;
        if (board[0][0] == 10 && board[0][1] == 10 && board[0][2] == 10) {
            counter++;
        }
        if (board[1][0] == 10 && board[1][1] == 10 && board[1][2] == 10) {
            counter++;
        }
        if (board[2][0] == 10 && board[2][1] == 10 && board[2][2] == 10) {
            counter++;
        }

        return counter;

    }


    public void displayBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String valueToPrint;
                int boardValue = board[i][j];
                if (boardValue == 0)
                    valueToPrint = "O";
                else if (boardValue == 10)
                    valueToPrint = "X";
                else valueToPrint = String.valueOf(boardValue);


                if (j == 2 && i != 2) {
                    System.out.println(valueToPrint);
                    System.out.println("-+-+-");
                } else if (j == 2) {
                    System.out.println(valueToPrint);
                } else {
                    System.out.print(valueToPrint + "|");
                }
            }

        }
    }

    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String valueToPrint;
                int boardValue = board[i][j];
                if (boardValue == 0)
                    valueToPrint = "O";
                else if (boardValue == 10)
                    valueToPrint = "X";
                else valueToPrint = String.valueOf(boardValue);


                if (j == 2 && i != 2) {
                    boardString.append(valueToPrint).append("\n");
                    boardString.append("-+-+-\n");
                } else if (j == 2) {
                    boardString.append(valueToPrint).append("\n");
                } else {
                    boardString.append(valueToPrint).append("|");
                }
            }

        }
        return boardString.toString();
    }

    public void updateValue(int pos, int player) {
        for (int i = 1; i <= 3; i++)
            for (int j = 1; j <= 3; j++)
                if (board[i - 1][j - 1] == pos) board[i - 1][j - 1] = player;
    }
}
