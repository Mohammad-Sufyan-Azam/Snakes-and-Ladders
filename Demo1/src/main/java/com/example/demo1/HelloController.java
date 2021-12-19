package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button roll;
    @FXML
    private ImageView diceImage;
    @FXML
    private Button no;

    private Dice d;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        String s = welcomeText.getText();
        System.out.println(s);
    }
    @FXML
    protected void RollDice() {
        roll.setDisable(true);
        d = new Dice(diceImage, roll);
        Thread thread = new Thread(d);
        thread.start();
    }

    @FXML
    protected void printNo() {
        System.out.println("Final No: "+d.getDiceNo());
    }
}