package com.example.dictionary;

import com.example.dictionary.core.DatabaseConnection;
import com.example.dictionary.core.Dictionary;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Application extends javafx.application.Application {
    public static Dictionary dictionary;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dictionary");
        primaryStage.setResizable(false);
        try {
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("fxml/searchpage.fxml"));
            Scene scene = new Scene(loader.load(), 850, 550);
            primaryStage.setOnCloseRequest(
                    arg0 -> {
                        dictionary.close();
                        Platform.exit();
                        System.exit(0);
                    });
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadDictionary();
    }

    private void loadDictionary() {
        dictionary = new DatabaseConnection();
        try {
            dictionary.initialize();
            System.out.println("Dictionary loaded");
            //TextToSpeech.soundEnToVi("Welcome to Dictionary");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading dictionary");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
