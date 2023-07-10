package fr.program;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("--------------- @Program132 -------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("/*\\                       /*\\");
        System.out.println(" |      P-IDE Launched     |");
        System.out.println("\\*/                       \\*/");

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("P-IDE | V0.1");
        stage.setHeight(600);
        stage.setWidth(900);
        stage.setResizable(false);

        BorderPane root = new BorderPane();

        // Title

        StackPane titlePane = new StackPane();
        titlePane.setStyle("-fx-background-color: #333333;");
        titlePane.setPadding(new Insets(10));
        VBox titleBox = new VBox(10);
        titleBox.setAlignment(Pos.CENTER);
        Label titleLabel = createLabelWithStyle("Bienvenue sur l'IDE de Program", "-fx-font-size: 20px; -fx-text-fill: white; -fx-font-weight: bold;");
        Label subtitleLabel = createLabelWithStyle("Sélectionner un langage pour commencer un projet", "-fx-font-size: 16px; -fx-text-fill: white;");
        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        titlePane.getChildren().add(titleBox);

        // Languages Buttons -> open project window (interaction)
        Button fpl_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\fpl.png");
        Button java_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\java.png");
        Button cpp_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\cpp.png");
        Button lua_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\lua.png");
        Button py_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\python.png");
        Button ark_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\arkscript.png");
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(fpl_btn, java_btn, cpp_btn, lua_btn, py_btn, ark_btn);
        VBox.setMargin(buttonBox, new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(fpl_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(java_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(cpp_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(lua_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(py_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(ark_btn, new Insets(0, 0, 0, 10));

        DropShadow dropShadow_blue = new DropShadow();
        dropShadow_blue.setColor(Color.rgb(0, 0, 255, 0.7));
        dropShadow_blue.setOffsetX(0);
        dropShadow_blue.setOffsetY(4);
        dropShadow_blue.setRadius(8);
        dropShadow_blue.setSpread(0.1);

        DropShadow dropShadow_red = new DropShadow();
        dropShadow_red.setColor(Color.rgb(255, 0, 0, 0.7));
        dropShadow_red.setOffsetX(0);
        dropShadow_red.setOffsetY(4);
        dropShadow_red.setRadius(8);
        dropShadow_red.setSpread(0.1);

        DropShadow dropShadow_FS_buttons_selected = new DropShadow();
        dropShadow_FS_buttons_selected.setColor(Color.rgb(162,1,1, 0.7));
        dropShadow_FS_buttons_selected.setOffsetX(0);
        dropShadow_FS_buttons_selected.setOffsetY(4);
        dropShadow_FS_buttons_selected.setRadius(8);
        dropShadow_FS_buttons_selected.setSpread(0.1);

        DropShadow dropShadow_green = new DropShadow();
        dropShadow_green.setColor(Color.rgb(0, 200, 0, 0.7));
        dropShadow_green.setOffsetX(0);
        dropShadow_green.setOffsetY(4);
        dropShadow_green.setRadius(8);
        dropShadow_green.setSpread(0.1);

        EffectMainButtonMenu_Hover(fpl_btn, java_btn, cpp_btn, dropShadow_red);
        EffectMainButtonMenu_Hover(lua_btn, py_btn, ark_btn, dropShadow_red);

        // Add title + buttons
        root.setCenter(buttonBox);
        root.setTop(titlePane);



        // Fast Scripting :
        VBox FS_MainBox = createVBOXwithPaddingStyle(10,10, "-fx-background-color: #202020;");

        Label FS_titleLabel = createLabelWithStyle("Fast Scripting :", "-fx-font-size: 18px; -fx-text-fill: #4a9eb4; -fx-font-weight: bold;");
        Label FS_subtitleLabel = createLabelWithStyle("Tester un programme basic rapidement", "-fx-font-size: 15px; -fx-text-fill: #afafaf;");

        HBox FS_buttonsBox = createHBOXwithPadding(10,10);
        String FS_Buttons_normal_style = "-fx-background-color: #1a5bef; -fx-text-fill: #ffffff; -fx-font-style: italic;";
        String FS_Buttons_selected_style = "-fx-background-color: #a20101; -fx-text-fill: #ffffff; -fx-font-style: italic;";
        Button FS_Execute = createButtonWithStyle("Execute", "-fx-background-color: #01770e; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Button FS_fpl = createButtonWithStyle("FPL", FS_Buttons_normal_style);
        Button FS_java = createButtonWithStyle("Java", FS_Buttons_normal_style);
        Button FS_cpp = createButtonWithStyle("C++", FS_Buttons_normal_style);
        Button FS_lua = createButtonWithStyle("Lua", FS_Buttons_normal_style);
        Button FS_py = createButtonWithStyle("Python", FS_Buttons_normal_style);
        Button FS_arkscript = createButtonWithStyle("ArkScript", FS_Buttons_normal_style);
        Button FS_reset = createButtonWithStyle("Reset", "-fx-background-color: red; -fx-text-fill: #ffffff; -fx-font-style: italic;");

        FS_Execute.setOnMouseEntered(event -> FS_Execute.setEffect(dropShadow_green));
        FS_Execute.setOnMouseExited(event -> FS_Execute.setEffect(null));

        FS_fpl.setOnMouseEntered(event -> {
            String buttonStyle = FS_fpl.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_fpl.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_fpl.setEffect(dropShadow_blue);
            }
        });
        FS_fpl.setOnMouseExited(event -> FS_fpl.setEffect(null));

        FS_java.setOnMouseEntered(event -> {
            String buttonStyle = FS_java.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_java.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_java.setEffect(dropShadow_blue);
            }
        });
        FS_java.setOnMouseExited(event -> FS_java.setEffect(null));

        FS_cpp.setOnMouseEntered(event -> {
            String buttonStyle = FS_cpp.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_cpp.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_cpp.setEffect(dropShadow_blue);
            }
        });
        FS_cpp.setOnMouseExited(event -> FS_cpp.setEffect(null));

        FS_lua.setOnMouseEntered(event -> {
            String buttonStyle = FS_lua.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_lua.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_lua.setEffect(dropShadow_blue);
            }
        });
        FS_lua.setOnMouseExited(event -> FS_lua.setEffect(null));

        FS_py.setOnMouseEntered(event -> {
            String buttonStyle = FS_py.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_py.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_py.setEffect(dropShadow_blue);
            }
        });
        FS_py.setOnMouseExited(event -> FS_py.setEffect(null));

        FS_arkscript.setOnMouseEntered(event -> {
            String buttonStyle = FS_arkscript.getStyle();
            if (buttonStyle.equals(FS_Buttons_selected_style)) {
                FS_arkscript.setEffect(dropShadow_FS_buttons_selected);
            } else {
                FS_arkscript.setEffect(dropShadow_blue);
            }
        });
        FS_arkscript.setOnMouseExited(event -> FS_arkscript.setEffect(null));

        FS_reset.setOnMouseEntered(event -> FS_reset.setEffect(dropShadow_red));
        FS_reset.setOnMouseExited(event -> FS_reset.setEffect(null));

        FS_buttonsBox.getChildren().add(FS_Execute);
        FS_buttonsBox.getChildren().add(FS_fpl);
        FS_buttonsBox.getChildren().add(FS_java);
        FS_buttonsBox.getChildren().add(FS_cpp);
        FS_buttonsBox.getChildren().add(FS_lua);
        FS_buttonsBox.getChildren().add(FS_py);
        FS_buttonsBox.getChildren().add(FS_arkscript);
        FS_buttonsBox.getChildren().add(FS_reset);


        TextArea FS_TextEditor = createTextAreaWithStyle(true, "-fx-control-inner-background: #111111; -fx-text-fill: #ffffff;");

        Label FS_subtitleLabel_output = createLabelWithStyle("Résultats :", "-fx-font-size: 14px; -fx-text-fill: #e5e5e5;");
        TextArea FS_output = createTextAreaWithStyle(false, "-fx-control-inner-background: #424242; -fx-text-fill: #FAFAFA;");

        VBox textEditorBox = new VBox(10);
        textEditorBox.getChildren().addAll(FS_TextEditor, FS_subtitleLabel_output, FS_output);

        FS_MainBox.getChildren().addAll(FS_titleLabel, FS_subtitleLabel, FS_buttonsBox, textEditorBox);
        root.setRight(FS_MainBox);




        // Add elements

        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #202020;"); // Couleur de fond globale
        container.getChildren().add(root);

        Scene scene = new Scene(container, 600, 900);
        stage.setScene(scene);

        // Show main window:
        stage.show();
        stage.centerOnScreen();



        // Events FS

        FS_fpl.setOnAction(event -> {
            Interactions_FS.setMode("fpl");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_fpl.setStyle(FS_Buttons_selected_style);
            FS_fpl.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_java.setOnAction(event -> {
            Interactions_FS.setMode("java");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_java.setStyle(FS_Buttons_selected_style);
            FS_java.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_cpp.setOnAction(event -> {
            Interactions_FS.setMode("cpp");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_cpp.setStyle(FS_Buttons_selected_style);
            FS_cpp.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_lua.setOnAction(event -> {
            Interactions_FS.setMode("lua");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_lua.setStyle(FS_Buttons_selected_style);
            FS_lua.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_py.setOnAction(event -> {
            Interactions_FS.setMode("py");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_py.setStyle(FS_Buttons_selected_style);
            FS_py.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_arkscript.setOnAction(event -> {
            Interactions_FS.setMode("ark");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);

            FS_arkscript.setStyle(FS_Buttons_selected_style);
            FS_arkscript.setEffect(dropShadow_FS_buttons_selected);
        });
        FS_reset.setOnAction(event -> {
            Interactions_FS.writeInFile("fastscript/code/fpl.fpl", "");
            Interactions_FS.writeInFile("fastscript/code/java.java", "");
            Interactions_FS.writeInFile("fastscript/code/cpp.cpp", "");
            Interactions_FS.writeInFile("fastscript/code/lua.lua", "");
            Interactions_FS.writeInFile("fastscript/code/py.py", "");
            Interactions_FS.writeInFile("fastscript/code/ark.ark", "");
            Interactions_FS.setMode("N/A");
            FS_output.setText("");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);
        });

        FS_Execute.setOnAction(event -> {
            try {
                Interactions_FS.execute(FS_TextEditor.getText(), FS_output);
            } catch (IOException e) {

            }
        });
    }

    private void EffectMainButtonMenu_Hover(Button lua_btn, Button py_btn, Button ark_btn, DropShadow dropShadow_red) {
        lua_btn.setOnMouseEntered(event -> lua_btn.setEffect(dropShadow_red));
        lua_btn.setOnMouseExited(event -> lua_btn.setEffect(null));
        py_btn.setOnMouseEntered(event -> py_btn.setEffect(dropShadow_red));
        py_btn.setOnMouseExited(event -> py_btn.setEffect(null));
        ark_btn.setOnMouseEntered(event -> ark_btn.setEffect(dropShadow_red));
        ark_btn.setOnMouseExited(event -> ark_btn.setEffect(null));
    }

    private Button createImageButton(String imgpath, int width, int height) {
        Button btn = new Button();
        Image iconImage = new Image(imgpath);
        ImageView iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(width);
        iconImageView.setFitHeight(height);
        btn.setGraphic(iconImageView);
        return btn;
    }

    private Button createImageButtonWithStyle(String imgpath, int width, int height, String style) {
        Button btn = createImageButton(imgpath, width, height);
        btn.setStyle(style);
        return btn;
    }

    private Button createImageButtonWithStyle(String imgpath) {
        Button btn = createImageButton(imgpath, 50, 50);
        btn.setStyle("-fx-background-radius: 15; -fx-background-color: #555555;");
        return btn;
    }

    private Button createButtonWithStyle(String content, String style) {
        Button b = new Button(content);
        b.setStyle(style);
        return b;
    }

    private VBox createVBOXwithPaddingStyle(int spacing, int padding, String style) {
        VBox box = new VBox(spacing);
        box.setPadding(new Insets(padding));
        box.setStyle(style);
        return box;
    }

    private HBox createHBOXwithPadding(int spacing, int padding) {
        HBox box = new HBox(spacing);
        box.setPadding(new Insets(padding));
        return box;
    }


    private Label createLabelWithStyle(String content, String style) {
        Label l = new Label(content);
        l.setStyle(style);
        return l;
    }

    private TextArea createTextAreaWithStyle(boolean editable, String style) {
        TextArea area = new TextArea();
        area.setEditable(editable);
        area.setStyle(style);
        return area;
    }
}
