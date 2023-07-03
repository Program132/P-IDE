package fr.program;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("--------------- @Program132 -------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("/*\\                       /*\\");
        System.out.println(" |      P-IDE Launched     |");
        System.out.println("\\*/                       \\*/");

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("P-IDE | V0.1");
        stage.setHeight(500);
        stage.setWidth(700);

        stage.show();
        stage.centerOnScreen();
    }
}