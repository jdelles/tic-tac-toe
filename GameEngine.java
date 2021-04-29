/**
 * The Game Engine interfaces with the GUI and contains the game logic 
 * for the Tic Tac Toe game
 */
public class GameEngine {

    private GameEngine instance; 

    private char playerX = 'X'; 

    private char playerO = 'O'; 

    private char currentPlayer; 

    /**
     * A standard 3x3 tic tac toe grid 
     * [0,0] [0,1] [0,2]
     * [1,0] [1,1] [1,2]
     * [2,0] [2,1] [2,2]
     */
    private char[][] gameBoard; 
    
    /**
     * Game Engine Constructor
     */
    public GameEngine() {
        newGameEngine(); 
    }

    /**
     * restricts the GameEngine to a singular instance
     */
    private void newGameEngine() {
        if (null == instance) {
            instance = new GameEngine(); 
            currentPlayer = playerX; 
            gameBoard = new char[3][3]; 
        }
    }

    /**
     * Checks if the game is over
     * @return true if the game is over, false if it is not
     */
    public boolean isGameOver() {
        if ((gameBoard[0][0] == gameBoard[1][1] && gameBoard[0][0] == gameBoard[2][2]) || 
            (gameBoard[0][2] == gameBoard[1][1] && gameBoard[0][2] == gameBoard[2][0]) || 
            (gameBoard[1][0] == gameBoard[1][1] && gameBoard[1][0] == gameBoard[1][2]) || 
            (gameBoard[0][1] == gameBoard[1][1] && gameBoard[0][1] == gameBoard[2][1]) ||
            (gameBoard[0][0] == gameBoard[0][1] && gameBoard[0][0] == gameBoard[0][2]) || 
            (gameBoard[0][0] == gameBoard[1][0] && gameBoard[0][0] == gameBoard[2][0]) ||
            (gameBoard[2][0] == gameBoard[2][1] && gameBoard[2][0] == gameBoard[2][2]) || 
            (gameBoard[2][0] == gameBoard[1][0] && gameBoard[2][0] == gameBoard[0][0])) {
            return true;  
        }
        return false; 
    }

    /**
     * Gets the currentPlayer
     * @return currentPlayer
     */
    public char getCurrentPlayer() {
        return currentPlayer; 
    }

    /**
     * Gets the game board for the GUI
     * @return 3x3 String array representing the game board
     */
    public char[][] getGameBoard() {
        return gameBoard; 
    }

    /**
     * Place a token on the board
     */
    public void placeToken(int x, int y) {
        if (' ' != gameBoard[x][y]) throw new IllegalArgumentException("This space is already taken."); 
        gameBoard[x][y] = currentPlayer; 
    }

    /**
     * Switch player
     */
    public void switchPlayer() {
        if (currentPlayer == playerX) {
            currentPlayer = playerO; 
        } else {
            currentPlayer = playerX; 
        }
    }
}
