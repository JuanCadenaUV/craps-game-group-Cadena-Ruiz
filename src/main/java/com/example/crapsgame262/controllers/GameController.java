package com.example.crapsgame262.controllers;

import com.example.crapsgame262.models.AlertBox;
import com.example.crapsgame262.models.Game;
import com.example.crapsgame262.models.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {

    private Player currentPlayer;

    private final Game game = new Game();

    @FXML
    private ImageView dice1ImageView;

    @FXML
    private ImageView dice2ImageView;

    @FXML
    private Label nicknameLabel;

    @FXML
    private Label rollLabel;

    @FXML
    private Label pointLabel;

    @FXML
    private Label gamesWonLabel;

    @FXML
    private Label gamesLostLabel;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        nicknameLabel.setText(this.currentPlayer.getNickname());
    }

    @FXML
    void onMouseClickedHelpButton(MouseEvent event) {
        new AlertBox().showAlertBox(
                "Craps Game - Ayuda",
                "Cómo se juega Craps",
                "Primer tiro:\n"
                        + "  • Si sacas 7 u 11, ganas.\n"
                        + "  • Si sacas 2, 3 o 12 (\"Craps\"), pierdes.\n"
                        + "  • Con cualquier otro número (4, 5, 6, 8, 9, 10) se establece el \"punto\".\n\n"
                        + "Si hay punto, sigue lanzando:\n"
                        + "  • Si sacas el punto otra vez antes de un 7, ganas.\n"
                        + "  • Si sacas 7 antes del punto, pierdes.\n\n"
                        + "Pulsa \"Lanzar\" para tirar los dados. El tiro, el punto y los juegos "
                        + "ganados y perdidos se muestran en Resultados.");
    }

    @FXML
    void onMouseClickedDropButton(MouseEvent event) {
        game.roll();

        dice1ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                game.getDice1().getImagePath()
                        )
                )
        );

        dice2ImageView.setImage(
                new Image(
                        getClass().getResourceAsStream(
                                game.getDice2().getImagePath()
                        )
                )
        );

        rollLabel.setText(String.valueOf(game.getLastRoll()));
        pointLabel.setText(game.getPoint() == 0 ? "-" : String.valueOf(game.getPoint()));
        gamesWonLabel.setText(String.valueOf(game.getGamesWon()));
        gamesLostLabel.setText(String.valueOf(game.getGamesLost()));
    }
}
