package fr.program;

import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    public void start(Stage stage) throws Exception {
        stage.setTitle("P-IDE | V0.1");
        stage.setHeight(500);
        stage.setWidth(700);

        // Generate Buttons
        Button fpl_btn = createImageButton("D:\\GitHub\\P-IDE\\img\\btn\\fpl.png", 50, 50);
        Button java_btn = createImageButton("D:\\GitHub\\P-IDE\\img\\btn\\java.png", 50, 50);
        Button cpp_btn = createImageButton("D:\\GitHub\\P-IDE\\img\\btn\\cpp.png", 50, 50);
        Button lua_btn = createImageButton("D:\\GitHub\\P-IDE\\img\\btn\\lua.png", 50, 50);
        Button py_btn = createImageButton("D:\\GitHub\\P-IDE\\img\\btn\\python.png", 50, 50);

        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(fpl_btn, java_btn, cpp_btn, lua_btn, py_btn);
        VBox.setMargin(buttonBox, new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        StackPane titlePane = new StackPane();
        titlePane.setStyle("-fx-background-color: #333333;");
        titlePane.setPadding(new Insets(10));
        VBox titleBox = new VBox(10);
        titleBox.setAlignment(Pos.CENTER);
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


        Label titleLabel = new Label("Bienvenue sur l'IDE de Program");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white;"); // Ajustez la taille de la police selon vos besoins

        Label subtitleLabel = new Label("Sélectionner un langage pour commencer un projet");
        subtitleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;"); // Ajustez la taille de la police selon vos besoins

        titleBox.getChildren().addAll(titleLabel, subtitleLabel);
        titlePane.getChildren().add(titleBox);

        BorderPane root = new BorderPane(); // Utiliser BorderPane comme conteneur principal
        root.setCenter(buttonBox);
        root.setTop(titlePane);

        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #202020;"); // Couleur de fond globale
        container.getChildren().add(root);

        Scene scene = new Scene(container, 500, 700);
        stage.setScene(scene);

        // Appliquer le style CSS aux boutons
        String buttonStyle = "-fx-background-radius: 15; -fx-background-color: #555555;";
        fpl_btn.setStyle(buttonStyle);
        java_btn.setStyle(buttonStyle);
        cpp_btn.setStyle(buttonStyle);
        lua_btn.setStyle(buttonStyle);
        py_btn.setStyle(buttonStyle);

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
}
