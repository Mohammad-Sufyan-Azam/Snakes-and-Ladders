package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class InitialScene1 {

    public Button Computer;
    public Button Human;
    public Pane mainScene;
    HelloController h = new HelloController();


    public void PlayWithComputer(ActionEvent actionEvent) throws IOException {
        System.out.println("Playing with computer");
        VBox v = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        h.ComputerPlaying(true);
        mainScene.getChildren().setAll(v);
    }

    public void PlayWithHuman(ActionEvent actionEvent) throws IOException {
        System.out.println("Playing with human");
        VBox v = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        h.ComputerPlaying(false);
        mainScene.getChildren().setAll(v);
    }
}
