package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndBoxController {
    @FXML
    private Button Back;
    @FXML
    private Button MainMenu;

    @FXML
    private void BackButtonClicked() {
        System.out.println("In BackButtonClicked function");
        Stage curr = (Stage)Back.getScene().getWindow();
        curr.close();
    }
    @FXML
    private void MainMenuButtonClicked() {
        System.out.println("In MainMenuButtonClicked function");
    }
}

// For progress bar
/*
import java.net.URL;

class DoWork extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(i + 1);
            updateProgress(i + 1, 10);
            Thread.sleep(500);
            if (isCancelled()) {
                return i;
            }
        }
        return 10;
    }
    @Override
    public boolean cancel(boolean may InterruptIfRunning) {
        updateMessage("Cancelled!");
        return super.cancel(mayInterruptIfRunning);
    }
    @Override
    protected void updateProgress (double workDone, double max) {
        updateMessage("progress!" + workDone);
        super.updateProgress(workDone, max);
    }
}

public class FirstController implements Initializable {
    @FXML
    ProgressBar bar;
    @FXML
    Progress Indicator indicator;
    @FXML
    Label status;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DoWork task = new DoWork();
        bar.progressProperty().bind(task.progress Property());
        indicator.progress Property().bind(task.progress Property());
        status.textProperty().bind(task.messageProperty());
    }
    new Thread(task).start();
}
 */