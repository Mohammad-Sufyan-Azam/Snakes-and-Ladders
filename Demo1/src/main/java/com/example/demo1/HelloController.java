package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class HelloController {
    @FXML
    public ImageView player1;
    @FXML
    public ImageView player2;
    public GridPane grid;
    public Button back;
    @FXML
    private Label welcomeText;
    @FXML
    private Button roll;
    @FXML
    private ImageView diceImage;

    private Dice d = new Dice(diceImage, roll);
    private Board b = new Board();
    private Players p;
    private boolean playersInitialized = false;
    private boolean playingComputer = true;


    @FXML
    protected void backButtonClicked() throws IOException {
        System.out.println("Data Saved!");

        // Main method of EndBoxController Class
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("End-Box.fxml"));
        Stage finishBox = new Stage();
        finishBox.initStyle(StageStyle.UNDECORATED);
        finishBox.initModality(Modality.APPLICATION_MODAL); // Block input events
        Scene scene = new Scene(fxmlLoader.load(), 272, 167);
        finishBox.setTitle("Close");
        finishBox.setScene(scene);
        finishBox.showAndWait();
    }

    protected int RollDice() throws InterruptedException {
        Random random = new Random();
        int dice = random.nextInt(6)+1;
        //System.out.println("Dice gives: "+dice);
        return dice;
    }

    @FXML
    private void movePlayers() throws Exception {
        int i=1;
        System.out.println("In the movePlayers function: "+playingComputer);
        if (!playersInitialized)
        {
            if (playingComputer)
                p = new Players(2, player1, player2, grid, roll, "Player1", "Computer");
            else
                p = new Players(2, player1, player2, grid, roll, "Player1", "Player2");

            playersInitialized = true;
        }
        while(playingComputer && i <= 1)
        {
            int die = RollDice();
            p.move2Players(die);

            String win = p.getWinner();
            String lose = p.getLoser();
            if (!win.equals("null"))
            {
                roll.setDisable(true);
                p.updateWinnerScore(win);
                showResult();

                // Main method of ResultBoxController class
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Result-Box.fxml"));
                Stage Box = new Stage();
                Box.initStyle(StageStyle.UNDECORATED);
                Box.initModality(Modality.APPLICATION_MODAL); // Block input events
                Scene scene = new Scene(fxmlLoader.load(), 288, 220);
                Box.setTitle("Result");
                Box.setScene(scene);
                Box.showAndWait();
            }
            i+=1;
        }
    }

    private void showResult() {
        int[] arr = p.getWinnerScore();
        System.out.println(p.getWinner()+"'s lost matches are: "+arr[0]);
        System.out.println(p.getWinner()+"'s won matches are: "+arr[1]);
    }

    public void ComputerPlaying(boolean b) {
        System.out.println("In the computer function: "+b);
        this.playingComputer = b;
    }
}
