package com.example.firstproject;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
         //list to store x & y
    ArrayList<Pair<Integer,Integer>> positionOfCordinates;
    ArrayList<Integer> snakeLadderPosition;

    Board(){
        positionOfCordinates = new ArrayList<Pair<Integer, Integer>>();
        populatePositionCordinates();
        populateSnakeLadder();
    }

    //adding cordinates
    private void populatePositionCordinates() {
        positionOfCordinates.add(new Pair<>(0, 0));

        for (int i = 0; i < SnakeLadderGame.height; i++) {
            for (int j = 0; j < SnakeLadderGame.width; j++) {
                // x cordinates
                int xCord = 0;
                if (i % 2 == 0) {
                    //x/coin moving ---->
                    xCord = j * SnakeLadderGame.tilesize + SnakeLadderGame.tilesize / 2;

                } else {
                    //x/coin moving <----
                    xCord = SnakeLadderGame.tilesize * SnakeLadderGame.height - (j * SnakeLadderGame.tilesize) - SnakeLadderGame.tilesize / 2;
                }
                int yCord = SnakeLadderGame.tilesize * SnakeLadderGame.height - (i * SnakeLadderGame.tilesize) - SnakeLadderGame.tilesize / 2;

                positionOfCordinates.add(new Pair<>(xCord, yCord));
            }
        }
    }
    private void populateSnakeLadder(){
        snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,25);
        snakeLadderPosition.set(27,5);
        snakeLadderPosition.set(13,46);
        snakeLadderPosition.set(33,49);
        snakeLadderPosition.set(40,3);
        snakeLadderPosition.set(42,63);
        snakeLadderPosition.set(43,18);
        snakeLadderPosition.set(50,69);
        snakeLadderPosition.set(54,31);
        snakeLadderPosition.set(62,81);
        snakeLadderPosition.set(66,45);
        snakeLadderPosition.set(76,58);
        snakeLadderPosition.set(89,53);
        snakeLadderPosition.set(74,92);
        snakeLadderPosition.set(99,41);

    }

    int getNewPosition(int currentPosition){
        if(currentPosition >= 0 && currentPosition <= 100){
            return snakeLadderPosition.get(currentPosition);
        }
        return -1;
    }
       int getXCordinates(int position){
            if(position >= 1 && position <= 100){
                return positionOfCordinates.get(position).getKey();
            }
            return -1;
        }
       int getYCordinates(int position){
        if(position >= 1 && position <= 100){
            return positionOfCordinates.get(position).getValue();
        }
        return -1;
    }



//
//    public static void main(String[] args) {
//        Board board = new Board();
//        for (int i = 0; i < board.positionOfCordinates.size(); i++) {
//            System.out.println(i +" $ x: " + board.positionOfCordinates.get(i).getKey()+" y: "+ board.positionOfCordinates.get(i).getValue());
//        }
//    }
//


}
