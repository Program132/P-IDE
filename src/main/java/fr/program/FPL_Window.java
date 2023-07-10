package fr.program;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class FPL_Window {
    public static void show_createProject() {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int height = 400;
        int width = 500;
        AtomicReference<String> zoneFolder_project = new AtomicReference<>("N/A");

        Stage fpl_createProject = new Stage();
        fpl_createProject.setTitle("P-IDE | Créer un nouveau projet (F.P.L)");
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


        VBox mainBox = new VBox(10);
        mainBox.setAlignment(Pos.TOP_CENTER);

        HBox versionBox = new HBox(0);
        versionBox.setAlignment(Pos.TOP_CENTER);

        Label title_version = new Label();
        title_version.setText("Choisir votre version : ");
        title_version.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-style: italic");

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton v_2_3 = new RadioButton("2.3");
        v_2_3.setStyle("-fx-text-fill: #558cff; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        v_2_3.setToggleGroup(toggleGroup);
        RadioButton v_3_0 = new RadioButton("3.0");
        v_3_0.setToggleGroup(toggleGroup);
        v_3_0.setStyle("-fx-text-fill: #558cff; -fx-font-weight: bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");

        HBox pathBox = new HBox();
        pathBox.setAlignment(Pos.TOP_CENTER);

        Label title_path = new Label();
        title_path.setText("Choisir un répertoire : ");
        title_path.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-style: italic;");

        TextField textZonePath = new TextField();
        textZonePath.setEditable(false);
        textZonePath.setText("");
        textZonePath.setStyle("-fx-background-color: #858585; -fx-font-size: 13px; -fx-text-fill: #802020; -fx-font-style: italic; -fx-font-weight: bold;  -fx-pref-width: 200px; -fx-pref-height: 10px;");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Sélectionnez un répertoire pour votre projet F.P.L");

        Button buttonToSelectZoneProject = new Button();
        buttonToSelectZoneProject.setText("Sélectionner");
        buttonToSelectZoneProject.setStyle("-fx-background-color: #252525; -fx-font-size: 11px; -fx-text-fill: #30779d; -fx-font-weight: bold;");

        HBox actionButtonsBox = new HBox(0);
        actionButtonsBox.setAlignment(Pos.TOP_CENTER);

        DropShadow buttonHover = createDropShadow(Color.rgb(100,100,100,0.8));

        Button createBTN = new Button();
        createBTN.setText("Créer");
        createBTN.setStyle("-fx-background-color: #098a1d; -fx-font-size: 18px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-pref-height: 20px;");

        Button cancelBTN = new Button();
        cancelBTN.setText("Annuler");
        cancelBTN.setStyle("-fx-background-color: #9a0412; -fx-font-size: 18px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-pref-height: 20px;");

        mouseHoverEffect_Buttons(createBTN, buttonHover);
        mouseHoverEffect_Buttons(cancelBTN, buttonHover);

        versionBox.setMargin(title_version, new Insets(30, 0, 0, 0));
        versionBox.setMargin(v_2_3, new Insets(30, 10, 0, 10));
        versionBox.setMargin(v_3_0, new Insets(30, 10, 0, 10));
        pathBox.setMargin(title_path, new Insets(33, 0, 0, 0));
        pathBox.setMargin(textZonePath, new Insets(30, 5, 0, 5));
        pathBox.setMargin(buttonToSelectZoneProject, new Insets(31, 5, 0, 5));
        actionButtonsBox.setMargin(createBTN, new Insets(50, 10, 0, 10));
        actionButtonsBox.setMargin(cancelBTN, new Insets(50, 10, 0, 10));



        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////   Adding Elements    //////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        titleBox.getChildren().addAll(titleLabel);
        titlePane.getChildren().add(titleBox);

        versionBox.getChildren().addAll(title_version, v_2_3, v_3_0);

        pathBox.getChildren().addAll(title_path, textZonePath, buttonToSelectZoneProject);

        actionButtonsBox.getChildren().addAll(createBTN, cancelBTN);

        mainBox.getChildren().addAll(versionBox, pathBox, actionButtonsBox);

        root.setTop(titlePane);
        root.setCenter(mainBox);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Events Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        buttonToSelectZoneProject.setOnAction(event -> {
            File selectedDirectory = directoryChooser.showDialog(fpl_createProject);

            if (selectedDirectory != null) {
                zoneFolder_project.set(selectedDirectory.getAbsolutePath());
                textZonePath.setText(zoneFolder_project.get());
            }
        });

        createBTN.setOnAction(event -> {
            if (!zoneFolder_project.get().equalsIgnoreCase("N/A")) {
                if (v_2_3.isSelected() || v_3_0.isSelected()) {
                    fpl_createProject.close();
                    try {
                        File directory = new File(zoneFolder_project.get());
                        File file = new File(directory, "main.fpl");
                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        if (v_2_3.isSelected()) {
                            bufferedWriter.write("envoyer \"Hello World\";");
                            bufferedWriter.close();
                        } else if (v_3_0.isSelected()) {
                            bufferedWriter.write("envoyer \"Hello World\"");
                            bufferedWriter.close();
                        }

                        editor(zoneFolder_project.get());
                    } catch (IOException e) {

                    }
                }
            }
        });

        cancelBTN.setOnAction(event -> {
            fpl_createProject.close();
        });

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


    private static void editor(String repository) {

    }


    private static DropShadow createDropShadow(Color color) {
        DropShadow ds = new DropShadow();
        ds.setColor(color);
        ds.setOffsetX(0);
        ds.setOffsetY(4);
        ds.setRadius(8);
        ds.setSpread(0.1);
        return ds;
    }

    private static void mouseHoverEffect_Buttons(Button button, DropShadow defaultEffect) {
        button.setOnMouseEntered(event -> {
            button.setEffect(defaultEffect);
        });

        button.setOnMouseExited(event -> button.setEffect(null));
    }
}
