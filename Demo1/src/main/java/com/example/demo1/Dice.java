package com.example.demo1;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.Random;

public class Dice implements Runnable {

    int diceNo = 0;
    private ImageView diceImage;
    private Button roll;

    public Dice(ImageView diceImage, Button roll) {
        this.diceImage = diceImage;
        this.roll = roll;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                diceNo = (random.nextInt(6) + 1);
                String location = "src/main/resources/com/example/demo1/dice/dice" + diceNo + ".png";
                File file = new File(location);
                diceImage.setImage(new Image(file.toURI().toString()));

                Thread.sleep(80);

            }
            roll.setDisable(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setDiceNo(diceNo);
        System.out.println("DICE NO: " + diceNo);
    }

    private void setDiceNo(int no) {
        this.diceNo = no;
    }

    public int getDiceNo() {
        return diceNo;
    }
}

/*
public class Dice extends AnimationTimer {

    private int diceNo = 0;
    private ImageView diceImage;
    private Button roll;
    private long FRAMES_PER_SEC = 50L;
    private long INTERVAL = 1000000000L / FRAMES_PER_SEC;
    private int MAX_ROLLS = 20;
    private int counter = 0;


    public Dice(ImageView diceImage, Button roll) {
        this.diceImage = diceImage;
        this.roll = roll;
    }

    @Override
    public void handle(long l) {
        Random random = new Random();
        diceNo = (random.nextInt(6) + 1);
        String location = "src/main/resources/com/example/demo1/Dice/dice" + diceNo + ".png";
        File file = new File(location);
        diceImage.setImage(new Image(file.toURI().toString()));
        counter++;
        if (counter >= MAX_ROLLS) {
            stop();
            setDiceNo(diceNo);
            roll.setDisable(false);
        }
    }

    private void setDiceNo(int no) {
        this.diceNo = no;
    }

    public int getDiceNo() {
        return diceNo;
    }
}
 */

// Pig Controller.java
/*
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import pig.model.Game;
import java.io.File;

public class PigController {

    // Data Fields
    Game pig;

    // FXML Connections
    @FXML
    ImageView dieImage;

    @FXML
    Button rollButton;

    @FXML
    Button holdButton;

    @FXML
    TextField p1turn;

    @FXML
    TextField p2turn;

    @FXML
    TextField p1total;

    @FXML
    TextField p2total;

    @FXML
    VBox p1box;

    @FXML
    VBox p2box;

    @FXML
    Label title;

    private Roller clock;

    private class Roller extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private int MAX_ROLLS = 20;

        private long last = 0;
        private int count = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                int r = 2 + (int)(Math.random() * 5);
                setDieImage(r);
                last = now;
                count++;
                if (count > MAX_ROLLS) {
                    clock.stop();
                    disableButtons(false);
                    roll();
                    count = 0;
                }
            }
        }
    }

    @FXML
    public void initialize() {
        clock = new Roller();
        pig = new Game("Player 1", "Player 2");
        updateViews();
    }

    public void updateViews() {
        setDieImage(pig.getDie().getTop());
        p1turn.setText("" + pig.getP1().getTurnScore());
        p1total.setText("" + pig.getP1().getTotalScore());
        p2turn.setText("" + pig.getP2().getTurnScore());
        p2total.setText("" + pig.getP2().getTotalScore());
        if (pig.p1Turn()) {
            p1box.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            p2box.setBackground(null);
        } else {
            p2box.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, null, null)));
            p1box.setBackground(null);
        }
        if (pig.gameOver()) {
            disableButtons(true);
            title.setText("Game Over! " + pig.getCurrent().getName() + " wins!");
        }
    }

    public void setDieImage(int top) {
        //dieImage.setImage(new Image("pig/resources/Dice" + top + ".png"));

        File f = new File("src/pig/resources/Dice" + top + ".png");
        System.out.println(f.toURI().toString());
        dieImage.setImage(new Image(f.toURI().toString()));
    }

    public void disableButtons(boolean disable) {
        rollButton.setDisable(disable);
        holdButton.setDisable(disable);
    }

    public void rollAnimation() {
        clock.start();
        disableButtons(true);
    }

    public void roll() {
        pig.roll();
        updateViews();
    }

    public void hold() {
        pig.hold();
        updateViews();
    }
}
 */
// Game.java
/*
public class Game {

    public static final int MAX_SCORE = 100;

    // Data Fields
    private Die d;
    private Player p1;
    private Player p2;
    private Player current;

    // Constructor
    public Game(String p1name, String p2name) {
        d = new Die();
        p1 = new Player(p1name);
        p2 = new Player(p2name);
        current = p1;
    }

    // Accessor methods

    public Die getDie() {
        return d;
    }

    public Player getCurrent() {
        return current;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    // Status Methods
    public boolean gameOver() {
        return current.getTotalScore() >= MAX_SCORE;
    }

    public boolean p1Turn() {
        return current == p1;
    }

    // Game Play Methods
    public void switchTurn() {
        if (p1Turn()) {
            current = p2;
        } else {
            current = p1;
        }
    }

    public void roll() {
        d.roll();
        int t = d.getTop();
        current.updateTurn(t);
        if (t == 1) {
            current.resetTurnScore();
            switchTurn();
        }
    }

    public void hold() {
        current.saveScore();
        if (!gameOver()) {
            switchTurn();
            d.setTop(0);
        }
    }

    public static void main(String[] args) {
        Game g = new Game("Mark", "Ryan");
        for (int i = 0; i < 10; i++) {
            g.roll();
            g.roll();
            g.hold();
            System.out.println("Die Rolled " + g.getDie().getTop());
            System.out.println("p1 Turn: " + g.getP1().getTurnScore());
            System.out.println("p1 Total: " + g.getP1().getTotalScore());
            System.out.println("p2 Turn: " + g.getP2().getTurnScore());
            System.out.println("p2 Total: " + g.getP2().getTotalScore());
        }
    }
}
 */