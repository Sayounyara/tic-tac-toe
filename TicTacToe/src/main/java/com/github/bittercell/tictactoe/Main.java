package com.github.bittercell.tictactoe;

import com.github.bittercell.tictactoe.ui.Grid;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        Label info = new Label("Turn: X");
        Grid boardSpaces = new Grid();

        info.setFont(Font.font("Monospaced", 40));

        layout.setTop(info);
        layout.setCenter(boardSpaces.getGrid()); // Change to setBottom() to add new game feature in setCenter()
        layout.setPadding(new Insets(10));

        // Main logic
        for (int column = 0; column < 3; column++) {
            for (int row = 0; row < 3; row++) {
                int finalRow = row;
                int finalCol = column;
                boardSpaces.getBoardSpace(row, column).setOnAction((event) -> {
                    if (!boardSpaces.placeMove(finalRow, finalCol)) {
                        System.out.println("Game has ended! (" + boardSpaces.getStateAsRaw() + ")");

                    } else {
                        info.setText("Turn: " + boardSpaces.getTurn());
                    }
                    if (boardSpaces.getStateAsInt() != 0) {
                        info.setText(boardSpaces.getStateAsInfo());
                    }
                });
            }
        }

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setTitle("Tic-Tac-Toe (Refactored)");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
