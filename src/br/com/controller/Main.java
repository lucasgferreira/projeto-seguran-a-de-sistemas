package br.com.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public String tela;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.setWidth(600);
        primaryStage.setHeight(320);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("br/com/view/img/avatar.png"));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
