package com.github.bittercell.tictactoe.logic;

public enum GameState {
    ONGOING (0),
    X_WIN   (1),
    O_WIN   (2),
    DRAW    (3);

    private int state;

    private GameState(int value) {
        this.state = value;
    }

    public int getState() {
        return this.state;
    }

    public String getStateAsInfo() {
        switch (this.state) {
            case 0: break;
            case 1: return "X wins!";
            case 2: return "O wins!";
            case 3: return "Draw!";
        }

        return "This shouldn't print...";
    }
}
