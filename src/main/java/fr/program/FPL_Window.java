package fr.program;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FPL_Window {
    public static void show_createProject() {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int height = 400;
        int width = 500;

        Stage fpl_createProject = new Stage();
        fpl_createProject.setTitle("P-IDE | Créer un nouveau projet (FPL)");
        fpl_createProject.setHeight(height);
        fpl_createProject.setWidth(width);
        fpl_createProject.setResizable(false);

        BorderPane root = new BorderPane();

        StackPane titlePane = new StackPane();
        titlePane.setStyle("-fx-background-color: #333333;");
        titlePane.setPadding(new Insets(10));

        VBox titleBox = new VBox(10);
        titleBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label();
        titleLabel.setText("Créer un nouveau projet en F.P.L");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #fdfdfd; -fx-font-weight: bold;");


        HBox versionBox = new HBox(20);

        versionBox.setAlignment(Pos.TOP_CENTER);

        Label title_version = new Label();
        title_version.setText("Choisir votre version : ");
        title_version.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-style: italic");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton option1 = new RadioButton("2.3");
        option1.setStyle("-fx-text-fill: #558cff; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        option1.setToggleGroup(toggleGroup);
        RadioButton option2 = new RadioButton("3.0");
        option2.setToggleGroup(toggleGroup);
        option2.setStyle("-fx-text-fill: #558cff; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        versionBox.setMargin(title_version, new Insets(10, 0, 0, 0));
        versionBox.setMargin(option1, new Insets(10, 0, 0, 0));
        versionBox.setMargin(option2, new Insets(10, 0, 0, 0));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////   Adding Elements    //////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        titleBox.getChildren().addAll(titleLabel);
        titlePane.getChildren().add(titleBox);

        versionBox.getChildren().addAll(title_version, option1, option2);

        root.setTop(titlePane);
        root.setCenter(versionBox);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Show Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #4b4b4b;");
        container.getChildren().add(root);

        Scene scene = new Scene(container, width, height);
        fpl_createProject.setScene(scene);

        // Show:
        fpl_createProject.show();
        fpl_createProject.centerOnScreen();
    }
}
