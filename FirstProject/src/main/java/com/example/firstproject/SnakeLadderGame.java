package com.example.firstproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadderGame extends Application {

    public  static  final int tilesize=60, width=10, height=10;
    public  static  final int buttonLine = height*tilesize + 45;
    private static Dice dice = new Dice();
    private Player player1, player2;
    boolean gameStarted = false, player1Turn = false, player2Turn = false;

    //Creation of 2D plane
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tilesize,height*tilesize + 100);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));




    //creating tiles in whole 2D Plane
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width ; j++){
                Tile tile = new Tile(tilesize);
                tile.setTranslateX(i*tilesize);
                tile.setTranslateY(j*tilesize);
                root.getChildren().add(tile);

            }
        }

        // sticking the image on plane
        Image img = new Image("C:\\Users\\kasif\\OneDrive\\Desktop\\javaFrame(Swing)\\FirstProject\\src\\main\\istockphoto-531466314-1024x1024.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);


        // Creating bottons for playsers
        Button playerOneButton = new Button("Player 1");
        playerOneButton.setPrefSize(120,40);
        playerOneButton.setFont(new Font(15));
        playerOneButton.setStyle("-fx-background-color: yellow;");
        playerOneButton.setTextFill(Color.BLACK);
        playerOneButton.setTranslateX(25);
        playerOneButton.setTranslateY(buttonLine-30);
        playerOneButton.setDisable(true);

        Button playerTwoButton = new Button("Player 2");
        playerTwoButton.setPrefSize(120,40);
        playerTwoButton.setFont(new Font(15));
        playerTwoButton.setStyle("-fx-background-color: yellow;");
        playerTwoButton.setTextFill(Color.BLACK);
        playerTwoButton.setTranslateX(450);
        playerTwoButton.setTranslateY(buttonLine-30);
        playerTwoButton.setDisable(true);

        Button startButton = new Button("Start Game");
        startButton.setPrefSize(100,30);
        startButton.setFont(new Font(15));
        startButton.setStyle("-fx-background-color: red;");
        startButton.setTextFill(Color.WHITE);
        startButton.setTranslateX(250);
        startButton.setTranslateY(buttonLine-30);


        //Creating Lables
        Label p1Lable = new Label("");
        p1Lable.setPrefSize(200,50);
        p1Lable.setFont(new Font(14));
        p1Lable.setTranslateX(40);
        p1Lable.setTranslateY(buttonLine);

        Label p2Lable = new Label("");
        p2Lable.setPrefSize(200,50);
        p2Lable.setFont(new Font(14));
        p2Lable.setTranslateX(460);
        p2Lable.setTranslateY(buttonLine);

        //lable for show dice value
        Label diceLable = new Label("Dice Value --");
        diceLable.setFont(new Font(15));
        diceLable.setPrefSize(500,50);
        diceLable.setTextFill(Color.BLACK);
        diceLable.setTranslateX(255);
        diceLable.setTranslateY(buttonLine-5);


        player1 = new Player(tilesize-7, Color.LIGHTPINK, "Kasif");
        player2 = new Player(tilesize-11, Color.BLUE, "Harry");


        //Player 1 Actions
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(player1Turn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLable.setText("Dice Value - "+diceValue);
                        player1.movePlayer(diceValue);

                        //Winning Condition
                        if(player1.isWinner()){
                            diceLable.setText("Winner is "+player1.getName());

                            //disable everything
                            player1Turn = false;
                            playerOneButton.setDisable(true);
                            p1Lable.setText("");
                            player2Turn = true;
                            playerTwoButton.setDisable(true);
                            p2Lable.setText("");

                            //restart
                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            diceLable.setText(" Winner is! "+player1.getName());
                            gameStarted = false;

                        }else {
                            player1Turn = false;
                            playerOneButton.setDisable(true);
                            p1Lable.setText("");

                            player2Turn = true;
                            playerTwoButton.setDisable(false);
                            p2Lable.setText("Your Turn! "+player2.getName());
                        }


                    }
                }


            }
        });

        //Player 2 Actions
       playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               if(gameStarted){
                   if(player2Turn){
                       int diceValue2 = dice.getRolledDiceValue();
                       diceLable.setText("Dice Value - "+diceValue2);
                       player2.movePlayer(diceValue2);

                       //Winning Condition
                       if(player2.isWinner()){
                           diceLable.setText("Winner is "+player2.getName());

                           //disable everything
                           player1Turn = false;
                           playerOneButton.setDisable(true);
                           p1Lable.setText("");
                           player2Turn = true;
                           playerTwoButton.setDisable(true);
                           p2Lable.setText("");

                           //restart
                           startButton.setDisable(false);
                           startButton.setText("Restart");
                           diceLable.setText(" Winner is! "+player2.getName());

                       }else{
                           player1Turn = true;
                           playerOneButton.setDisable(false);
                           p1Lable.setText("Your Turn! "+player1.getName());

                           player2Turn = false;
                           playerTwoButton.setDisable(true);
                           p2Lable.setText("");
                       }

                   }
               }
           }
       });

        //StartBotton Action
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                diceLable.setText("Game Started");
                startButton.setDisable(true);
                player1Turn = true;
                p1Lable.setText("Your Turn! "+ player1.getName());
                playerOneButton.setDisable(false );
                player1.bringPlayersToBackAfterReset();

                player2Turn = false;
                p2Lable.setText("");
                playerTwoButton.setDisable(true);
                player2.bringPlayersToBackAfterReset();
            }
        });
        root.getChildren().addAll(board, playerOneButton, playerTwoButton, startButton, p1Lable,p2Lable,diceLable, player1.getCoin(), player2.getCoin()
        );




        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake Ladder!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}