package com.example.firstproject;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Player {
    private Circle coin;
    private String name;
    private int currPosition;
    private static Board gameBoard = new Board();

    Player(int tilesize, Color cointColor, String playerName){
        coin = new Circle(tilesize/2);
        coin.setFill(cointColor);
        currPosition = 0;
        movePlayer(1);
        name = playerName;
    }

    public void movePlayer(int diceValue){
        if(currPosition+diceValue <= 100){
            currPosition+=diceValue;
            TranslateTransition secondMove = null, firstMove = translateAnimation(diceValue);

            int newPostion = gameBoard.getNewPosition(currPosition);
            if(newPostion != currPosition || currPosition != -1){
                currPosition = newPostion;
                secondMove = translateAnimation(6);
            }

            if(secondMove == null){
                firstMove.play();
            }else {
                SequentialTransition SQ = new SequentialTransition(firstMove,new PauseTransition(Duration.millis(1000)), secondMove);
                SQ.play();

            }
        }
//
//        //taking the cordianates to move coin
//        int x = gameBoard.getXCordinates(currPosition);
//        int y = gameBoard.getYCordinates(currPosition);
//
//        //move coin
//        coin.setTranslateX(x);
//        coin.setTranslateY(y);

    }

    TranslateTransition translateAnimation(int val){
        TranslateTransition animate = new TranslateTransition(Duration.millis(200*val),coin);
        animate.setToX(gameBoard.getXCordinates(currPosition));
        animate.setToY(gameBoard.getYCordinates(currPosition));
        animate.setAutoReverse(false);
        return animate;
    }

    boolean isWinner(){
        if(currPosition == 100) return true;
        return false;
    }

    public void bringPlayersToBackAfterReset(){
        currPosition = 1;
        movePlayer(0);
    }
    public Circle getCoin() {
        return coin;
    }

    public String getName() {
        return name;
    }

    public int getCurrPosition() {
        return currPosition;
    }
}
