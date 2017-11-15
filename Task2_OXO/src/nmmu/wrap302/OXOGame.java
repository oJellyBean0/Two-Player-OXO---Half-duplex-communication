package nmmu.wrap302;

public class OXOGame {
    public Player clientPlayer;
    public Player serverPlayer;

    public Player curPlayer;
    public Board gameBoard;
    int oWins, xWins, draws;

    public OXOGame() {
        oWins = 0;
        xWins = 0;
        draws = 0;
    }

    public void initialiseCurPlayer() {
        curPlayer = serverPlayer;
    }

    public void startGame() {
        gameBoard = new Board();
        selectStartingPlayer();
    }

    public void selectStartingPlayer() {
        if (curPlayer.equals(clientPlayer)) {
            curPlayer = serverPlayer;
        } else curPlayer = clientPlayer;
    }


    public boolean isGameFinished() {
        for (int i = 0; i < gameBoard.boardSize; i++)
            for (int j = 0; j < gameBoard.boardSize; j++)
                if (gameBoard.board[i][j] != 0 && gameBoard.board[i][j] != 10) return false;
        return true;
    }

    public boolean checkForWinner(){
        int countForX = 0, countForO = 0;
        int winner;

        //0 = O Server and 10 = X Client
        if (curPlayer.equals(clientPlayer)) {
            countForX += gameBoard.checkVerticalForX();
            countForX += gameBoard.checkHorizontalForX();
            countForX += gameBoard.checkDiagonalForX();
        } else {
            countForO += gameBoard.checkVerticalForO();
            countForO += gameBoard.checkHorizontalForO();
            countForO += gameBoard.checkDiagonalForO();
        }

        if (countForO>0||countForX>0)
            return true;
        return false;
    }

    public boolean checkForWinnerDuringGame(){
        return checkForWinner();
    }

    private int getWinner() {
        int countForX = 0, countForO = 0;
        int winner;
        countForO += gameBoard.checkVerticalForO();
        countForO += gameBoard.checkHorizontalForO();
        countForO += gameBoard.checkDiagonalForO();

        countForX += gameBoard.checkVerticalForX();
        countForX += gameBoard.checkHorizontalForX();
        countForX += gameBoard.checkDiagonalForX();

        winner = findWinner(countForO, countForX);
        return winner;
    }

    private void displayScores() {
        System.out.println("Server (O) number of wins: " + oWins);
        System.out.println("Client (X) number of wins: " + xWins);
        System.out.println("Draws: " + draws);
    }

    public void finishGame() {
        int winner = getWinner();
        incrementFinalScore(winner);
        displayScores();
    }

    private void incrementFinalScore(int winner) {
        if (winner == 0)
            oWins++;
        else if (winner == 10)
            xWins++;
        else draws++;
    }

    private int findWinner(int countForO, int countForX) {
        if (countForO == countForX)
            //for draw
            return -1;
        else if (countForO > countForX)
            return 0;
        else return 10;
    }

}
