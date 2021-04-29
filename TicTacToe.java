import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TicTacToe GUI. Creates a game board with interactive buttons that allow
 * players to play the game TicTacToe. Uses the GameEngine class to track
 * current player and winning conditions. When a game ends, the user is prompted
 * to choose to play again or exit. 
 */
public class TicTacToe extends JFrame {

    /**
     * The game name.
     */
    private static final String GAME_TITLE = "Tic Tac Toe"; 

    /** 
     * Exit Option .
     */
    public static final Integer EXIT_OPTION = 1;
    
    /**
     * Clickable buttons that comprise the game board.
     */
    private JButton[][] gameBoard; 

    /**
     * The instruction text field at the bottom of the game. Indicates who's turn it is. 
     */
    private JLabel text; 

    /**
     * A turn counter. Used to determine when the game is over. 
     */
    private int turn; 

    /**
     * An instance of the game engine to determine when there is a winner
     * ## Need to decide how much more functionality can be abstracted into
     * ## this class. 
     */
    private GameEngine instance; 

    /**
     * The main method - calls TicTacToe() to launch the GUI.
     * @param args a String array of command line arguments - not used.
     */
    public static void main(String[] args) {
        new TicTacToe(); 
    }

    /**
     * Constructs the GUI. Initializes the game engine, turn counter, and draws the board.
     */
    private TicTacToe() {

        gameBoard = new JButton[3][3]; 
        instance = new GameEngine(); 
        turn = 0; 

        // app window
        Container c = getContentPane();
        setSize(800, 800); 
        setLocation(50, 50); 
        setTitle(GAME_TITLE); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLayout(new BorderLayout());

        // game section
        JPanel center = new JPanel(); 
        center.setLayout(new GridLayout(3, 3));
        drawBoard(); 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                center.add(gameBoard[i][j]); 
            }
        }

        // instruction section
        JPanel south = new JPanel(); 
        south.setLayout(new FlowLayout()); 
        text = new JLabel(); 
        south.add(text); 
        text.setText("Place your " + instance.getCurrentPlayer()); 

        c.add(center, BorderLayout.CENTER);  
        c.add(south, BorderLayout.SOUTH); 
        setVisible(true);  
    }

    /**
     * Marks each player choice on the game board and passes the information
     * to the GameEngine. Exits via endTurn().
     */
    private void updateBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                instance.setToken(i, j, gameBoard[i][j].getText()); 
                if (gameBoard[i][j].getText().equals("X")) {
                    gameBoard[i][j].setBackground(Color.BLUE);
                } else if (gameBoard[i][j].getText().equals("O")) {
                    gameBoard[i][j].setBackground(Color.RED);
                }
            }
        }
        endTurn(); 
    }

    /**
     * Draws the game board by constructing 9 JButtons in a 3x3 grid with 
     * line borders. Sets each button's action listener and event to handle
     * player clicks by updating the button text and calling updateBoard().
     */
    private void drawBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = new JButton(); 
                gameBoard[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
                gameBoard[i][j].setBackground(Color.GRAY);
                gameBoard[i][j].setOpaque(true); 
                gameBoard[i][j].addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton toUpdate = (JButton) e.getSource();

                        // if square has already been taken
                        if (toUpdate.getText().equals("X") || toUpdate.getText().equals("O")) {
                            errorMessage(); 
                            return; 
                        }
                        
                        toUpdate.setText(instance.getCurrentPlayer()); 
                        updateBoard(); 
                    }

                });
            }
        }
    }

    /**
     * Increments the turn counter and if the turn is greater than four, checks
     * to see if there is a winner. If there is a winner or if the turn counter
     * is 9, passes a message to gameOver() otherwise switches players and 
     * the corresponding instruction text label. 
     */
    private void endTurn() {
        turn++; 
        if (turn > 4 && instance.isWinner()) {
            gameOver(instance.getCurrentPlayer() + " wins!"); 
        } else if (turn > 8) {
            gameOver("It's a draw!"); 
        } else {
            instance.switchPlayer();
            text.setText("Place your " + instance.getCurrentPlayer()); 
        }
    }

    /**
     * Ran when the game is over either via win or draw. The results parameter
     * is used to display the winner or the draw message. The players are presented
     * with a popup containing the win or draw message and the options to exit or
     * play a new game. If they choose to play a new game the gameboard is 
     * reset via resetGame(). 
     * @param results a String declaring the winner or that the game ended in a draw.
     */
    private void gameOver(String results) {
        Object[] options = {"New Game", "Exit"};
        Object choice = JOptionPane.showOptionDialog(null, results,
        "Game Over", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (choice.equals(EXIT_OPTION)) {
            System.exit(0);
        } else {
            resetGame();
        }
    }

    private void errorMessage() {
        Object[] options = {"Ok"};
        JOptionPane.showOptionDialog(null, "Cannot play here.",
        "Error Message", JOptionPane.DEFAULT_OPTION,
        JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
    }

    /**
     * Resets the game board back to gray backgrounds and removes all X and Os 
     * from the button and from the GameEngine's array. Sets the player back to X
     * and updates the corresponding instruction text label. 
     */
    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j].setBackground(Color.GRAY);
                gameBoard[i][j].setText(""); 
            }
        }
        turn = 0; 
        instance = new GameEngine(); 
        text.setText("Place your " + instance.getCurrentPlayer()); 
    }
}