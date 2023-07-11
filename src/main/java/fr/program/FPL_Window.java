package fr.program;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

                        editor_show(zoneFolder_project.get());
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


    private static void editor_show(String repository) {

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int height = 675;
        int width = 850;

        Path projectPath = Paths.get(repository);
        String projectName = projectPath.getFileName().toString();

        Stage editor_fpl = new Stage();
        editor_fpl.setTitle("P-IDE | F.P.L Project - " + projectName);
        editor_fpl.setHeight(height);
        editor_fpl.setWidth(width);

        editor_fpl.setMinWidth(width);
        editor_fpl.setMinHeight(height);

        BorderPane root = new BorderPane();

        VBox main_ui_box = new VBox();
        main_ui_box.setAlignment(Pos.TOP_CENTER);



        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////   Top Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        HBox utils_box_buttons = new HBox();
        utils_box_buttons.setAlignment(Pos.TOP_RIGHT);

        HBox title_buttons_box = new HBox();
        title_buttons_box.setAlignment(Pos.TOP_LEFT);

        HBox buttons_code_box = new HBox();
        buttons_code_box.setAlignment(Pos.TOP_RIGHT);

        Label title_buttons = new Label();
        title_buttons.setText("French Programming Language Project");
        title_buttons.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-weight: bold");

        DropShadow topButtons_mouseHover_DropShadow = createDropShadow(Color.LIGHTGREEN);
        String codeButtons_Style = "-fx-background-color: #2a8a09; -fx-font-size: 13px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold;";

        Button run_button = new Button();
        run_button.setText("Exécuter");
        run_button.setStyle(codeButtons_Style);

        Button build_button = new Button();
        build_button.setText("Construire le projet");
        build_button.setStyle(codeButtons_Style);

        mouseHoverEffect_Buttons(run_button, topButtons_mouseHover_DropShadow);
        mouseHoverEffect_Buttons(build_button, topButtons_mouseHover_DropShadow);

        title_buttons_box.setMargin(title_buttons, new Insets(15, 0, 0, 15));
        buttons_code_box.setMargin(run_button, new Insets(10, 0, 0, 0));
        buttons_code_box.setMargin(build_button, new Insets(10, 0, 0, 10));

        HBox.setHgrow(title_buttons_box, Priority.ALWAYS);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////   Explorer + Code Editor Window    ////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        HBox main_editor = new HBox();
        main_editor.setAlignment(Pos.CENTER);


        Label title_explorer = new Label();
        title_explorer.setText("Project:");
        title_explorer.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff; -fx-font-weight: bold");

        Label projectName_explorer = new Label();
        projectName_explorer.setText(repository);
        projectName_explorer.setStyle("-fx-font-size: 12px; -fx-text-fill: #dcdcdc; -fx-font-style: italic");

        String explorer_buttons_style = "-fx-background-color: #565656; -fx-font-size: 13px; -fx-text-fill: #1893f6; -fx-font-weight: bold;";

        Button explorer_add_file = new Button();
        explorer_add_file.setText("Ajouter");
        explorer_add_file.setStyle(explorer_buttons_style);
        Button explorer_remove_file = new Button();
        explorer_remove_file.setText("Retirer");
        explorer_remove_file.setStyle(explorer_buttons_style);

        File rootDirectory = new File(repository);
        TreeItem<String> rootItem = createTreeItem(rootDirectory);
        TreeView<String> explorer_TreeView = new TreeView<>();
        explorer_TreeView.setRoot(rootItem);

        VBox explorer_box = new VBox();
        explorer_box.setAlignment(Pos.CENTER_LEFT);

        HBox explorer_buttons = new HBox();
        explorer_buttons.setAlignment(Pos.CENTER_LEFT);

        explorer_buttons.setMargin(title_explorer, new Insets(10, 2, 10, 10));
        explorer_buttons.setMargin(projectName_explorer, new Insets(10, 0, 10, 0));
        explorer_buttons.setMargin(explorer_add_file, new Insets(10, 5, 10, 5));
        explorer_buttons.setMargin(explorer_remove_file, new Insets(10, 0, 10, 5));

        TextArea codeEditor = new TextArea();
        codeEditor.setStyle("-fx-control-inner-background: #2B2B2B; -fx-text-fill: #dadada; -fx-pref-height: 90%;");

        explorer_box.setMargin(explorer_TreeView, new Insets(10, 0, 0, 15));
        main_editor.setMargin(codeEditor, new Insets(10, 10, 0, 10));

        HBox.setHgrow(explorer_TreeView, Priority.ALWAYS);
        HBox.setHgrow(codeEditor, Priority.ALWAYS);

        VBox.setVgrow(explorer_TreeView, Priority.ALWAYS);
        VBox.setVgrow(codeEditor, Priority.ALWAYS);
        VBox.setVgrow(main_editor, Priority.ALWAYS);


        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////   Terminal Window    ////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Label title_terminal = new Label();
        title_terminal.setText("Résultats :");
        title_terminal.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff; -fx-font-weight: bold");
        title_terminal.setAlignment(Pos.CENTER_LEFT);

        HBox titleTerminal_box = new HBox();
        titleTerminal_box.setAlignment(Pos.CENTER_LEFT);
        titleTerminal_box.getChildren().add(title_terminal);

        TextArea terminal_window = new TextArea();
        terminal_window.setEditable(false);
        terminal_window.setStyle("-fx-control-inner-background: #2a2a2a; -fx-text-fill: #cccccc; -fx-focus-color: transparent; -fx-text-box-border: transparent;");

        titleTerminal_box.setMargin(title_terminal, new Insets(15, 0, 0, 20));
        main_ui_box.setMargin(terminal_window, new Insets(10, 30, 10, 30));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////   Adding Elements to Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        title_buttons_box.getChildren().add(title_buttons);
        buttons_code_box.getChildren().addAll(run_button, build_button);
        utils_box_buttons.getChildren().addAll(title_buttons_box, buttons_code_box);

        explorer_buttons.getChildren().addAll(title_explorer, projectName_explorer, explorer_add_file, explorer_remove_file);
        explorer_box.getChildren().addAll(explorer_buttons, explorer_TreeView);

        main_editor.getChildren().addAll(explorer_box, codeEditor);

        main_ui_box.getChildren().addAll(utils_box_buttons, main_editor, titleTerminal_box, terminal_window);

        root.setCenter(main_ui_box);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Show Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #3C3F41;");
        container.getChildren().add(root);

        Scene scene = new Scene(container, width, height);
        editor_fpl.setScene(scene);

        editor_fpl.show();
        editor_fpl.centerOnScreen();
    }

    private static TreeItem<String> createTreeItem(File file) {
        TreeItem<String> item = new TreeItem<>(file.getName());

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    TreeItem<String> childItem = createTreeItem(childFile);
                    item.getChildren().add(childItem);
                }
            }
        }

        return item;
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
