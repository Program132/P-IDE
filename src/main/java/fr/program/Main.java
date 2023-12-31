package fr.program;

import fr.program.windows.*;
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


import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        String projectRootPath = System.getProperty("user.dir");

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        stage.setTitle("P-IDE | V0.1");
        stage.setHeight(1000);
        stage.setWidth(1000);

        BorderPane root = new BorderPane();


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////   Elements Window    ///////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        VBox buttonBox = new VBox(10);
        VBox.setMargin(buttonBox, new Insets(10));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        Button fpl_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") +"/img/btn/fpl.png");
        Button jdd_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") +"/img/btn/jdd.png");
        Button java_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/java.png");
        Button cpp_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/cpp.png");
        Button lua_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/lua.png");
        Button py_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/python.png");
        Button ark_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/arkscript.png");
        Button typescript_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/typescript.png");
        Button gradle_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/gradle.png");
        Button spigot_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/spigot.png");
        Button forge_btn = createImageButtonWithStyle("file:" + projectRootPath.replace("\\", "/") + "/img/btn/forge.png");
        Button[] buttons = { fpl_btn, jdd_btn, java_btn, cpp_btn, lua_btn, py_btn, ark_btn,
                typescript_btn, gradle_btn, spigot_btn, forge_btn };
        setButtonMargins(buttons);

        buttonBox.getChildren().addAll(fpl_btn, jdd_btn, java_btn, cpp_btn, lua_btn, py_btn, ark_btn, typescript_btn, gradle_btn, spigot_btn, forge_btn);


        DropShadow dropShadow_blue = FuncUtils.createDropShadow(Color.rgb(0,0,255,0.7));
        DropShadow dropShadow_red = FuncUtils.createDropShadow(Color.rgb(255,0,0,0.7));
        DropShadow dropShadow_FS_buttons_selected = FuncUtils.createDropShadow(Color.rgb(162,1,1,0.7));
        DropShadow dropShadow_green = FuncUtils.createDropShadow(Color.rgb(0,200,0,0.7));

        EffectMainButtonMenu_Hover(fpl_btn, jdd_btn, java_btn, dropShadow_red);
        EffectMainButtonMenu_Hover(typescript_btn, cpp_btn, cpp_btn, dropShadow_red);
        EffectMainButtonMenu_Hover(lua_btn, py_btn, ark_btn, dropShadow_red);
        EffectMainButtonMenu_Hover(forge_btn, gradle_btn, spigot_btn, dropShadow_red);
        typescript_btn.setOnMouseEntered(event -> typescript_btn.setEffect(dropShadow_red));
        typescript_btn.setOnMouseExited(event -> typescript_btn.setEffect(null));

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
        Button FS_jdd = createButtonWithStyle("JDD", FS_Buttons_normal_style);
        Button FS_java = createButtonWithStyle("Java", FS_Buttons_normal_style);
        Button FS_cpp = createButtonWithStyle("C++", FS_Buttons_normal_style);
        Button FS_lua = createButtonWithStyle("Lua", FS_Buttons_normal_style);
        Button FS_py = createButtonWithStyle("Python", FS_Buttons_normal_style);
        Button FS_arkscript = createButtonWithStyle("ArkScript", FS_Buttons_normal_style);
        Button FS_typescript = createButtonWithStyle("TypeScript", FS_Buttons_normal_style);
        Button FS_reset = createButtonWithStyle("Reset", "-fx-background-color: red; -fx-text-fill: #ffffff; -fx-font-style: italic;");

        FS_buttonsBox.getChildren().addAll(FS_Execute, FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript, FS_reset);

        TextArea FS_TextEditor = createTextAreaWithStyle(true, "-fx-control-inner-background: #111111; -fx-text-fill: #ffffff; -fx-pref-height: 350px;");

        Label FS_subtitleLabel_output = createLabelWithStyle("Résultats :", "-fx-font-size: 14px; -fx-text-fill: #e5e5e5;");
        TextArea FS_output = createTextAreaWithStyle(false, "-fx-control-inner-background: #424242; -fx-text-fill: #FAFAFA;");

        VBox textEditorBox = new VBox(10);
        textEditorBox.getChildren().addAll(FS_TextEditor, FS_subtitleLabel_output, FS_output);

        FS_MainBox.getChildren().addAll(FS_titleLabel, FS_subtitleLabel, FS_buttonsBox, textEditorBox);
        root.setRight(FS_MainBox);




        // Add elements
        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #202020;");
        container.getChildren().add(root);

        Scene scene = new Scene(container, 800, 1000);
        stage.setScene(scene);

        // Show main window:
        stage.show();
        stage.centerOnScreen();




        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////   Events Window    ///////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Events Main Menu

        fpl_btn.setOnAction(event -> FPL_Window.show_createProject());
        lua_btn.setOnAction(event -> Lua_Window.show_CreateOpenProject());
        py_btn.setOnAction(event -> Python_Window.show_CreateOpenProject());
        ark_btn.setOnAction(event -> ArkScript_Window.show_CreateOpenProject());
        typescript_btn.setOnAction(event -> Typescript_Window.show_CreateOpenProject());

        // Events FS

        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_fpl,
                FS_TextEditor,
                "fpl",
                "envoyer \"Hello World\""
                );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_jdd,
                FS_TextEditor,
                "jdd",
                "println(\"Hello World\");"
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_java,
                FS_TextEditor,
                "java",
                """
                        class java {\s
                           public static void main(String[] args) {\s
                               System.out.println("Hello World");\s
                           }\s
                        }"""
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_cpp,
                FS_TextEditor,
                "cpp",
                """
                        #include <iostream>\s

                        int main() {\s
                            std::cout << "Hello World!" << std::endl;\s
                        }"""
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_lua,
                FS_TextEditor,
                "lua",
                "print(\"Hello World\")"
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_py,
                FS_TextEditor,
                "py",
                "print(\"Hello World\")"
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_arkscript,
                FS_TextEditor,
                "ark",
                "(print \"Hello World\")"
        );
        Config_FastScript_Button(
                Arrays.asList(FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript),
                FS_Buttons_normal_style,
                FS_Buttons_selected_style,
                dropShadow_FS_buttons_selected,
                FS_typescript,
                FS_TextEditor,
                "ts",
                "console.log(\"Hello World!\");"
        );
        FS_reset.setOnAction(event -> {
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\fpl.fpl", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\jdd.jdd", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\java.java", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\cpp.cpp", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\lua.lua", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\py.py", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\ark.ark", "");
            FuncUtils.writeInFile(projectRootPath +  "\\fastscript\\code\\ts.ts", "");
            Interactions_FS.setMode("N/A");
            FS_output.setText("");

            FS_fpl.setStyle(FS_Buttons_normal_style);
            FS_jdd.setStyle(FS_Buttons_normal_style);
            FS_java.setStyle(FS_Buttons_normal_style);
            FS_cpp.setStyle(FS_Buttons_normal_style);
            FS_lua.setStyle(FS_Buttons_normal_style);
            FS_py.setStyle(FS_Buttons_normal_style);
            FS_arkscript.setStyle(FS_Buttons_normal_style);
            FS_typescript.setStyle(FS_Buttons_normal_style);
        });
        FS_Execute.setOnAction(event -> {
            try {
                Interactions_FS.execute(FS_TextEditor.getText(), FS_output);
            } catch (IOException ignored) {}
        });

        FS_Execute.setOnMouseEntered(event -> FS_Execute.setEffect(dropShadow_green));
        FS_Execute.setOnMouseExited(event -> FS_Execute.setEffect(null));
        Button[] buttonArray = {FS_fpl, FS_jdd, FS_java, FS_cpp, FS_lua, FS_py, FS_arkscript, FS_typescript};
        FS_applyButtonStyles(buttonArray, dropShadow_FS_buttons_selected, dropShadow_blue);
        FS_reset.setOnMouseEntered(event -> FS_reset.setEffect(dropShadow_red));
        FS_reset.setOnMouseExited(event -> FS_reset.setEffect(null));
    }

    private void FS_applyButtonStyles(Button[] buttonArray, DropShadow dropShadow_FS_buttons_selected, DropShadow dropShadow_blue) {
        String selectedStyle = "-fx-background-color: blue; -fx-text-fill: white;";

        for (Button button : buttonArray) {
            mouseHoverEffect_FS_Buttons(button, dropShadow_FS_buttons_selected, dropShadow_blue, selectedStyle);
        }
    }

    private void setButtonMargins(Button[] buttons) {
        Insets margin = new Insets(0, 0, 0, 10);
        for (Button button : buttons) {
            VBox.setMargin(button, margin);
        }
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

    private void mouseHoverEffect_FS_Buttons(Button button, DropShadow selectedEffect, DropShadow defaultEffect, String style) {
        button.setOnMouseEntered(event -> {
            String buttonStyle = button.getStyle();
            if (buttonStyle.equals(style)) {
                button.setEffect(selectedEffect);
            } else {
                button.setEffect(defaultEffect);
            }
        });

        button.setOnMouseExited(event -> button.setEffect(null));
    }

    private void Config_FastScript_Button(List<Button> buttons, String normalStyleButton, String selectedStyleButton, DropShadow shadow, Button button, TextArea editor, String mode, String code) {
        button.setOnAction(event -> {
            Interactions_FS.setMode(mode);
            for (Button b : buttons) {
                b.setStyle(normalStyleButton);
                b.setEffect(null);
            }
            button.setStyle(selectedStyleButton);
            button.setEffect(shadow);
            editor.setText(code);
        });
    }
}
