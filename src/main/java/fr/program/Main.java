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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
        Button fpl_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\fpl.png", 50, 50, "-fx-background-radius: 15; -fx-background-color: #555555;");
        Button java_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\java.png", 50, 50, "-fx-background-radius: 15; -fx-background-color: #555555;");
        Button cpp_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\cpp.png", 50, 50, "-fx-background-radius: 15; -fx-background-color: #555555;");
        Button lua_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\lua.png", 50, 50, "-fx-background-radius: 15; -fx-background-color: #555555;");
        Button py_btn = createImageButtonWithStyle("D:\\GitHub\\P-IDE\\img\\btn\\python.png", 50, 50, "-fx-background-radius: 15; -fx-background-color: #555555;");
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

        Button FS_Execute = createButtonWithStyle("Execute", "-fx-background-color: #01770e; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        TextArea FS_TextEditor = createTextAreaWithStyle(true, "-fx-control-inner-background: #111111; -fx-text-fill: #afafaf;");

        Label FS_subtitleLabel_output = createLabelWithStyle("Résultats :", "-fx-font-size: 14px; -fx-text-fill: #e5e5e5;");
        TextArea FS_output = createTextAreaWithStyle(false, "-fx-control-inner-background: #424242; -fx-text-fill: #afafaf;");

        VBox textEditorBox = new VBox(10);
        textEditorBox.getChildren().addAll(FS_TextEditor, FS_subtitleLabel_output, FS_output);

        FS_MainBox.getChildren().addAll(FS_titleLabel, FS_subtitleLabel, FS_Execute, textEditorBox);
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
