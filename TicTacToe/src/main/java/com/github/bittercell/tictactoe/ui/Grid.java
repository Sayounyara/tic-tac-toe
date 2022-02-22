package com.github.bittercell.tictactoe.ui;


import com.github.bittercell.tictactoe.logic.GameState;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Grid {

    private enum Turn {
        X, O
    }

    private final GridPane boardGrid;
    private final Button[][] boardSpaces;
    private int turnCount;
    public Turn turn;
    public GameState state;

    public Grid() {
        boardGrid = new GridPane();
        boardSpaces = new Button[3][3];
        boardGrid.setAlignment(Pos.CENTER);
        turn = Turn.X;
        state = GameState.ONGOING;

        for (int column = 0; column < boardSpaces.length; column++) {
            for (int row = 0; row < boardSpaces.length; row++) {
                boardSpaces[row][column] = (new Button(" "));
                boardSpaces[row][column].setFont(Font.font("Monospaced", 40));
                boardGrid.add(boardSpaces[row][column], row, column);
            }
        }

        boardGrid.setHgap(10);
        boardGrid.setVgap(10);
    }

    public GridPane getGrid() {
        return this.boardGrid;
    }

    public Button getBoardSpace(int row, int column) {
        return boardSpaces[row][column];
    }

    public boolean placeMove(int row, int column) {
        if (turnCount == 9) {
            return false;
        }
        boardSpaces[row][column].setText(this.turn.toString());
        turnCount++;
        if (!gameEnd()) {
            nextTurn();
        }
        return true;
    }

    private boolean gameEnd() {
        if (turnCount == 9) { // if it gets to this point the game has drawn
            state = GameState.DRAW;
            return true;
        }
        if (checkForWin()) {
            if (turn == Turn.X) { // if it is currently X's turn
                state = GameState.X_WIN;
            } else { // if it is currently O's turn
                System.out.println("This is just to test this catching statement -- turn = " + this.turn); // hopefully this catches only Turn.O
                state = GameState.O_WIN;
            }
            return true;
        }
        // if everything fails then the game would still be continuing (unless I messed up real good)
        state = GameState.ONGOING;
        return false;
    }

    public String getTurn() {
        return this.turn.toString();
    }

    public String getStateAsRaw() {
        return this.state.toString();
    }

    public String getStateAsInfo() {
        return this.state.getStateAsInfo();
    }

    public int getStateAsInt() {
        return state.getState();
    }

    private void nextTurn() {
        if (turnCount % 2 == 0) {
            turn = Turn.X;
        } else {
            turn = Turn.O;
        }
    }

    private boolean checkForWin() {
        String player = turn.toString();
        if (checkForHorizontalWin(player)) {
            return true;
        } else if (checkForVerticalWin(player)) {
            return true;
        } else return checkForDiagonalWin(player);
    }

    private boolean checkForHorizontalWin(String player) {
        for (int row = 0; row < boardSpaces.length; row++) { // technically I would think this would search for vertical index wins but I don't like how this index works
            //doin the same thing horizontal was but with rotation :3
            boolean left = boardSpaces[0][row].getText().equals(player);
            boolean center = boardSpaces[1][row].getText().equals(player);
            boolean right = boardSpaces[2][row].getText().equals(player);
            if (compareAll(left, center, right)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForVerticalWin(String player) { // I don't like you 2d index; you are the bad type of qwerky
        for (Button[] boardSpace : boardSpaces) { // same with vertical
            // In here test for if [row][0, 1, 2] all equal player
            boolean top = boardSpace[0].getText().equals(player);
            boolean center = boardSpace[1].getText().equals(player);
            boolean bottom = boardSpace[2].getText().equals(player);
            if (compareAll(top, center, bottom)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForDiagonalWin(String player) {
        // This one is more manual than the other two
        if (compareAll(boardSpaces[0][0].getText().equals(player), boardSpaces[1][1].getText().equals(player), boardSpaces[2][2].getText().equals(player))) {
            return true;
        } else {
            return compareAll(boardSpaces[2][0].getText().equals(player), boardSpaces[1][1].getText().equals(player), boardSpaces[0][2].getText().equals(player));
        }
    }

    private boolean compareAll(boolean a, boolean b, boolean c) {
        return a && b && c; // look at this -- so nice and neat :33,,, didn't take me 4 tries at all hehehe
    }
}
