/**
 * The Game Engine interfaces with the GUI and contains the game logic.
 */
public class GameEngine {

    /**
     * String representation of the X player. Used to track where Xs have been played. 
     */
    private String playerX = "X"; 

    /**
     * String representation of the O player. Used to track where Os have been played. 
     */
    private String playerO = "O"; 

    /**
     * Tracks the current player. Used to update text in the GUI and to declare a winner. 
     */
    private String currentPlayer; 

    /**
     * A standard 3x3 tic tac toe grid constructed with a 2D String array
     * [0,0] [0,1] [0,2]
     * [1,0] [1,1] [1,2]
     * [2,0] [2,1] [2,2]
     */
    private String[][] gameBoard; 
    
    /**
     * Game Engine Constructor. 
     * Sets the current player to X. 
     * Initializes the gameBoard array and fills it with empty strings. 
     */
    public GameEngine() {
        currentPlayer = playerX; 
        gameBoard = new String[3][3]; 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = ""; 
            }
        }
    }

    /**
     * Checks if there is a winner. 
     * @return true if any row, column or diagonal contains 3 of the same players tokens, false if not
     */
    public boolean isWinner() {
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2] && !gameBoard[1][1].equals("") || // diagonal TL-BR
            gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0] && !gameBoard[1][1].equals("") || // diagonal TR-BL
            gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2] && !gameBoard[1][1].equals("") || // middle row
            gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1] && !gameBoard[1][1].equals("") || // middle column
            gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2] && !gameBoard[0][0].equals("") || // top row
            gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0] && !gameBoard[0][0].equals("") || // left column
            gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2] && !gameBoard[2][2].equals("") || // bottom row
            gameBoard[0][2] == gameBoard[1][2] && gameBoard[0][2] == gameBoard[2][2] && !gameBoard[2][2].equals("")) { // right column
            return true;  
        }
        return false; 
    }

    /**
     * Gets the currentPlayer.
     * @return currentPlayer.
     */
    public String getCurrentPlayer() {
        return currentPlayer; 
    }

    /**
     * Gets the game board for the GUI
     * ## Currently the GUI sets the buttons and then sets the back end game board
     * ## It may be better if this were reversed. (This is not currently used.)
     * @return 3x3 String array representing the game board
     */
    public String[][] getGameBoard() {
        return gameBoard; 
    }

    /**
     * Tracks where tokens are placed on the board. 
     * @param row the row to place the token.
     * @param col teh column to place the token. 
     * @param player the X or O value to be placed on the board. 
     * @throws IndexOutOfBoundsException if row or col is any value other than 0, 1, 2
     */
    public void setToken(int row, int col, String player) {
        if (0 > row || 0 > col || row > 2 || col > 2) throw new IndexOutOfBoundsException("This is not a valid board location"); 
        gameBoard[row][col] = player; 
    }

    /**
     * Switch currentPlayer. 
     */
    public void switchPlayer() {
        if (currentPlayer == playerX) {
            currentPlayer = playerO; 
        } else {
            currentPlayer = playerX; 
        }
    }
}