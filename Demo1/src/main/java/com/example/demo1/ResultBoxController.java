package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultBoxController {
    public Label matchesWon;
    public Label matchesLost;

    private int lost = 0;
    private int won = 0;

    public Button MainMenu;
    public Button Back;

    public void MainMenuButtonClicked(ActionEvent actionEvent) {
        /*System.out.println("Matches lost: "+lost);
        System.out.println("Matches won: "+won);*/
        System.out.println("Main Menu Button Clicked");

    }

    public void BackButtonClicked() {
        System.out.println("Back Button Clicked");
        Stage current = (Stage)Back.getScene().getWindow();
        current.close();
    }

    public void setScore(int lost, int won) {
        this.lost = lost;
        this.won = won;
    }

    public void setName(String win, String lose) {
        System.out.println("In setName function");
        matchesWon.setText(win);
        matchesLost.setText(lose);
    }
}
