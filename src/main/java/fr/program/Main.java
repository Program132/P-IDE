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
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(fpl_btn, java_btn, cpp_btn, lua_btn, py_btn);
        VBox.setMargin(buttonBox, new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        VBox.setMargin(fpl_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(java_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(cpp_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(lua_btn, new Insets(0, 0, 0, 10));
        VBox.setMargin(py_btn, new Insets(0, 0, 0, 10));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 255, 0.7));
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(4);
        dropShadow.setRadius(8);
        dropShadow.setSpread(0.1);
        fpl_btn.setOnMouseEntered(event -> fpl_btn.setEffect(dropShadow));
        fpl_btn.setOnMouseExited(event -> fpl_btn.setEffect(null));
        java_btn.setOnMouseEntered(event -> java_btn.setEffect(dropShadow));
        java_btn.setOnMouseExited(event -> java_btn.setEffect(null));
        cpp_btn.setOnMouseEntered(event -> cpp_btn.setEffect(dropShadow));
        cpp_btn.setOnMouseExited(event -> cpp_btn.setEffect(null));
        lua_btn.setOnMouseEntered(event -> lua_btn.setEffect(dropShadow));
        lua_btn.setOnMouseExited(event -> lua_btn.setEffect(null));
        py_btn.setOnMouseEntered(event -> py_btn.setEffect(dropShadow));
        py_btn.setOnMouseExited(event -> py_btn.setEffect(null));

        // Add title + buttons
        root.setCenter(buttonBox);
        root.setTop(titlePane);



        // Fast Scripting :
        VBox FS_MainBox = createVBOXwithPaddingStyle(10,10, "-fx-background-color: #202020;");

        Label FS_titleLabel = createLabelWithStyle("Fast Scripting :", "-fx-font-size: 18px; -fx-text-fill: #4a9eb4; -fx-font-weight: bold;");
        Label FS_subtitleLabel = createLabelWithStyle("Tester un programme basic rapidement", "-fx-font-size: 15px; -fx-text-fill: #afafaf;");

        HBox FS_buttonsBox = createHBOXwithPadding(10,10);
        Button FS_Execute = createButtonWithStyle("Execute", "-fx-background-color: #01770e; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
        Button FS_fpl = createButtonWithStyle("FPL", "-fx-background-color: blue; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        Button FS_java = createButtonWithStyle("Java", "-fx-background-color: blue; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        Button FS_cpp = createButtonWithStyle("C++", "-fx-background-color: blue; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        Button FS_lua = createButtonWithStyle("Lua", "-fx-background-color: blue; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        Button FS_py = createButtonWithStyle("Python", "-fx-background-color: blue; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        FS_fpl.setOnMouseEntered(event -> FS_fpl.setEffect(dropShadow));
        FS_fpl.setOnMouseExited(event -> FS_fpl.setEffect(null));
        FS_java.setOnMouseEntered(event -> FS_java.setEffect(dropShadow));
        FS_java.setOnMouseExited(event -> FS_java.setEffect(null));
        FS_cpp.setOnMouseEntered(event -> FS_cpp.setEffect(dropShadow));
        FS_cpp.setOnMouseExited(event -> FS_cpp.setEffect(null));
        FS_lua.setOnMouseEntered(event -> FS_lua.setEffect(dropShadow));
        FS_lua.setOnMouseExited(event -> FS_lua.setEffect(null));
        FS_py.setOnMouseEntered(event -> FS_py.setEffect(dropShadow));
        FS_py.setOnMouseExited(event -> FS_py.setEffect(null));
        FS_buttonsBox.getChildren().add(FS_Execute);
        FS_buttonsBox.getChildren().add(FS_fpl);
        FS_buttonsBox.getChildren().add(FS_java);
        FS_buttonsBox.getChildren().add(FS_cpp);
        FS_buttonsBox.getChildren().add(FS_lua);
        FS_buttonsBox.getChildren().add(FS_py);


        TextArea FS_TextEditor = createTextAreaWithStyle(true, "-fx-control-inner-background: #111111; -fx-text-fill: #afafaf;");

        Label FS_subtitleLabel_output = createLabelWithStyle("Résultats :", "-fx-font-size: 14px; -fx-text-fill: #e5e5e5;");
        TextArea FS_output = createTextAreaWithStyle(false, "-fx-control-inner-background: #424242; -fx-text-fill: #afafaf;");

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
        });
        FS_java.setOnAction(event -> {
            Interactions_FS.setMode("java");
        });
        FS_cpp.setOnAction(event -> {
            Interactions_FS.setMode("cpp");
        });
        FS_lua.setOnAction(event -> {
            Interactions_FS.setMode("lua");
        });
        FS_py.setOnAction(event -> {
            Interactions_FS.setMode("py");
        });

        FS_Execute.setOnAction(event -> {
            try {
                Interactions_FS.execute(FS_TextEditor.getText(), FS_output);
            } catch (IOException e) {

            }
        });
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
