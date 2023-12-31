package fr.program.windows;

import fr.program.FuncUtils;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArkScript_Window {

    private static final String[] HIGHLIGHT_INSTRUCTIONS = {
            "let", "mut", "set", "fun", "begin", "print", "and", "or", "while", "import", "puts", "not", "nil", "async", "await", ""
    };

    private static final String[] HIGHLIGHT_TYPES = {
            ""
    };

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("\\b(" +
            String.join("|", HIGHLIGHT_INSTRUCTIONS) + ")\\b");

    private static final Pattern TYPES_PATTERN = Pattern.compile("\\b(" +
            String.join("|", HIGHLIGHT_TYPES) + ")\\b");

    public static void show_CreateOpenProject() {
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int height = 400;
        int width = 500;
        AtomicReference<String> zoneFolder_project = new AtomicReference<>("N/A");

        Stage lua_CreateOpenProject = new Stage();
        lua_CreateOpenProject.setTitle("P-IDE | Créer/Ouvrir un nouveau projet (ArkScript)");
        lua_CreateOpenProject.setHeight(height);
        lua_CreateOpenProject.setWidth(width);
        lua_CreateOpenProject.setResizable(false);

        BorderPane root = new BorderPane();

        StackPane titlePane = new StackPane();
        titlePane.setStyle("-fx-background-color: #333333;");
        titlePane.setPadding(new Insets(10));

        VBox titleBox = new VBox(10);
        titleBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label();
        titleLabel.setText("Créer/Ouvrir un nouveau projet en ArkScript");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #fdfdfd; -fx-font-weight: bold;");


        VBox mainBox = new VBox(10);
        mainBox.setAlignment(Pos.TOP_CENTER);

        HBox versionBox = new HBox(0);
        versionBox.setAlignment(Pos.TOP_CENTER);

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
        directoryChooser.setTitle("Sélectionnez un répertoire");

        Button buttonToSelectZoneProject = new Button();
        buttonToSelectZoneProject.setText("Sélectionner");
        buttonToSelectZoneProject.setStyle("-fx-background-color: #252525; -fx-font-size: 11px; -fx-text-fill: #30779d; -fx-font-weight: bold;");

        CheckBox checkBox = new CheckBox();
        AtomicBoolean isChecked = new AtomicBoolean(false);
        Label title = new Label("Créer un fichier main.ark");
        title.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-style: italic");
        HBox createMainFPL_box = new HBox(10);
        createMainFPL_box.setAlignment(Pos.CENTER);
        createMainFPL_box.getChildren().addAll(title, checkBox);
        createMainFPL_box.setPadding(new Insets(10));


        HBox actionButtonsBox = new HBox(0);
        actionButtonsBox.setAlignment(Pos.TOP_CENTER);

        DropShadow buttonHover = FuncUtils.createDropShadow(Color.rgb(100,100,100,0.8));

        Button createBTN = new Button();
        createBTN.setText("Coder");
        createBTN.setStyle("-fx-background-color: #098a1d; -fx-font-size: 18px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-pref-height: 20px;");

        Button cancelBTN = new Button();
        cancelBTN.setText("Annuler");
        cancelBTN.setStyle("-fx-background-color: #9a0412; -fx-font-size: 18px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold; -fx-pref-width: 100px; -fx-pref-height: 20px;");

        WindowsFunc.mouseHoverEffect_Buttons(createBTN, buttonHover);
        WindowsFunc.mouseHoverEffect_Buttons(cancelBTN, buttonHover);

        HBox.setMargin(title_path, new Insets(33, 0, 0, 0));
        HBox.setMargin(textZonePath, new Insets(30, 5, 0, 5));
        HBox.setMargin(buttonToSelectZoneProject, new Insets(31, 5, 0, 5));
        HBox.setMargin(createBTN, new Insets(50, 10, 0, 10));
        HBox.setMargin(cancelBTN, new Insets(50, 10, 0, 10));



        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////   Adding Elements    //////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        titleBox.getChildren().addAll(titleLabel);
        titlePane.getChildren().add(titleBox);

        pathBox.getChildren().addAll(title_path, textZonePath, buttonToSelectZoneProject);

        actionButtonsBox.getChildren().addAll(createBTN, cancelBTN);

        mainBox.getChildren().addAll(versionBox, pathBox, createMainFPL_box, actionButtonsBox);

        root.setTop(titlePane);
        root.setCenter(mainBox);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Events Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        buttonToSelectZoneProject.setOnAction(event -> {
            File selectedDirectory = directoryChooser.showDialog(lua_CreateOpenProject);

            if (selectedDirectory != null) {
                zoneFolder_project.set(selectedDirectory.getAbsolutePath());
                textZonePath.setText(zoneFolder_project.get());
            }
        });

        createBTN.setOnAction(event -> {
            if (!zoneFolder_project.get().equalsIgnoreCase("N/A")) {
                lua_CreateOpenProject.close();

                if (isChecked.get()) {
                    try {
                        File directory = new File(zoneFolder_project.get());
                        File file = new File(directory, "main.ark");
                        FileWriter fileWriter = new FileWriter(file);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write("(print \"hello world\")");
                        bufferedWriter.close();
                        editor_show(zoneFolder_project.get());
                    } catch (IOException ignored) {}
                } else {
                    editor_show(zoneFolder_project.get());
                }
            }
        });

        cancelBTN.setOnAction(event -> lua_CreateOpenProject.close());

        checkBox.setOnAction(event -> isChecked.set(checkBox.isSelected()));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Show Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        StackPane container = new StackPane();
        container.setStyle("-fx-background-color: #4b4b4b;");
        container.getChildren().add(root);

        Scene scene = new Scene(container, width, height);
        lua_CreateOpenProject.setScene(scene);

        // Show:
        lua_CreateOpenProject.show();
        lua_CreateOpenProject.centerOnScreen();
    }





    private static void editor_show(String repository) {
        String projectRootPath = System.getProperty("user.dir");
        AtomicReference<String> currentFile = new AtomicReference<>("N/A");
        DropShadow dropShadow_red = FuncUtils.createDropShadow(Color.rgb(255,0,0,0.7));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Base Window    ////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int height = 675;
        int width = 850;

        Path projectPath = Paths.get(repository);
        String projectName = projectPath.getFileName().toString();

        Stage editor_fpl = new Stage();
        editor_fpl.setTitle("P-IDE | ArkScript Project - " + projectName);
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
        title_buttons.setText("ArkScript Project");
        title_buttons.setStyle("-fx-font-size: 13px; -fx-text-fill: #fcfcfc; -fx-font-weight: bold");

        DropShadow topButtons_mouseHover_DropShadow = FuncUtils.createDropShadow(Color.LIGHTGREEN);
        DropShadow topButtons_mouseHover_DropShadow_SAVES = FuncUtils.createDropShadow(Color.LIGHTBLUE);
        String codeButtons_Style = "-fx-background-color: #2a8a09; -fx-font-size: 13px; -fx-text-fill: #d9d9d9; -fx-font-weight: bold;";

        Button save_button = new Button();
        save_button.setText("Sauvegarder");
        save_button.setStyle("-fx-background-color: #09598a; -fx-font-size: 12px; -fx-text-fill: #d9d9d9; ");

        Button save_as_button = new Button();
        save_as_button.setText("Sauvegarder sous");
        save_as_button.setStyle("-fx-background-color: #09598a; -fx-font-size: 12px; -fx-text-fill: #d9d9d9; ");

        Button run_button = new Button();
        run_button.setText("Exécuter");
        run_button.setStyle(codeButtons_Style);

        Button build_button = new Button();
        build_button.setText("Construire le projet");
        build_button.setStyle(codeButtons_Style);

        WindowsFunc.mouseHoverEffect_Buttons(run_button, topButtons_mouseHover_DropShadow);
        WindowsFunc.mouseHoverEffect_Buttons(build_button, topButtons_mouseHover_DropShadow);
        WindowsFunc.mouseHoverEffect_Buttons(save_button, topButtons_mouseHover_DropShadow_SAVES);
        WindowsFunc.mouseHoverEffect_Buttons(save_as_button, topButtons_mouseHover_DropShadow_SAVES);

        HBox.setMargin(title_buttons, new Insets(15, 0, 0, 15));
        HBox.setMargin(save_button, new Insets(11, 0, 0, 0));
        HBox.setMargin(save_as_button, new Insets(11, 15, 0, 5));
        HBox.setMargin(run_button, new Insets(10, 0, 0, 10));
        HBox.setMargin(build_button, new Insets(10, 0, 0, 10));

        HBox.setHgrow(title_buttons_box, Priority.ALWAYS);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////   Explorer + Code Editor Window    ////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        HBox main_editor = new HBox();
        main_editor.setAlignment(Pos.CENTER);

        DropShadow explorer_buttons_DropShadow = FuncUtils.createDropShadow(Color.LIGHTGRAY);

        Label title_explorer = new Label();
        title_explorer.setText("Projet :");
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
        TreeItem<String> rootItem = WindowsFunc.createTreeItem(rootDirectory);


        VBox explorer_box = new VBox();
        explorer_box.setAlignment(Pos.CENTER_LEFT);

        HBox explorer_buttons = new HBox();
        explorer_buttons.setAlignment(Pos.CENTER_LEFT);

        WindowsFunc.mouseHoverEffect_Buttons(explorer_add_file, explorer_buttons_DropShadow);
        WindowsFunc.mouseHoverEffect_Buttons(explorer_remove_file, explorer_buttons_DropShadow);

        HBox.setMargin(title_explorer, new Insets(10, 2, 10, 10));
        HBox.setMargin(projectName_explorer, new Insets(10, 0, 10, 0));
        HBox.setMargin(explorer_add_file, new Insets(10, 5, 10, 5));
        HBox.setMargin(explorer_remove_file, new Insets(10, 0, 10, 5));

        CodeArea codeEditor = new CodeArea();
        codeEditor.setId("codeEditor");
        codeEditor.setParagraphGraphicFactory(LineNumberFactory.get(codeEditor));
        codeEditor.getStylesheets().add(ArkScript_Window.class.getResource("/codeEditor.css").toExternalForm());

        codeEditor.richChanges()
                .filter(ch -> !ch.getInserted().equals(ch.getRemoved()))
                .subscribe(change -> {
                    codeEditor.setStyleSpans(0, managerHighlight_CodeEditor(codeEditor.getText()));
                });

        TreeView<String> explorer_TreeView = new TreeView<>();
        explorer_TreeView.setRoot(rootItem);
        explorer_TreeView.setStyle("-fx-control-inner-background: #3d3d3d; -fx-focus-color: transparent; -fx-border-color: transparent;");
        refreshTreeView(explorer_TreeView, rootDirectory, projectRootPath, repository, currentFile, codeEditor);



        VBox.setMargin(explorer_TreeView, new Insets(10, 0, 0, 15));
        HBox.setMargin(codeEditor, new Insets(10, 10, 0, 10));

        HBox.setHgrow(explorer_TreeView, Priority.ALWAYS);
        HBox.setHgrow(codeEditor, Priority.ALWAYS);

        VBox.setVgrow(explorer_TreeView, Priority.ALWAYS);
        VBox.setVgrow(codeEditor, Priority.ALWAYS);
        VBox.setVgrow(main_editor, Priority.ALWAYS);



        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////   Terminal Window    ////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Label title_terminal = WindowsFunc.getTitleTerminal();

        HBox titleTerminal_box = new HBox();
        titleTerminal_box.setAlignment(Pos.CENTER_LEFT);
        titleTerminal_box.getChildren().add(title_terminal);

        TextArea terminal_window = new TextArea();
        terminal_window.setEditable(false);
        terminal_window.setStyle("-fx-control-inner-background: #2a2a2a; -fx-text-fill: #cccccc; -fx-focus-color: transparent; -fx-text-box-border: transparent;");

        Button terminal_clear = WindowsFunc.createButtonWithStyle("Vider le terminal", "-fx-background-color: red; -fx-text-fill: #ffffff; -fx-font-style: italic;");
        terminal_clear.setOnMouseEntered(event -> terminal_clear.setEffect(dropShadow_red));
        terminal_clear.setOnMouseExited(event -> terminal_clear.setEffect(null));

        HBox.setMargin(title_terminal, new Insets(30, 0, 0, 20));
        HBox.setMargin(terminal_clear, new Insets(30, 0, 0, 10));
        VBox.setMargin(terminal_window, new Insets(10, 30, 10, 30));


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////   Adding Elements to Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        title_buttons_box.getChildren().add(title_buttons);
        buttons_code_box.getChildren().addAll(save_button,  save_as_button, run_button, build_button);
        utils_box_buttons.getChildren().addAll(title_buttons_box, buttons_code_box);

        explorer_buttons.getChildren().addAll(title_explorer, projectName_explorer, explorer_add_file, explorer_remove_file);
        explorer_box.getChildren().addAll(explorer_buttons, explorer_TreeView);

        main_editor.getChildren().addAll(explorer_box, codeEditor);

        titleTerminal_box.getChildren().addAll(terminal_window, terminal_clear);

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




        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////   Events Window    ////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        AtomicReference<String> saveAs_fileName_Selected = new AtomicReference<>("unknown");

        Stage save_as_stage = new Stage();
        save_as_stage.setTitle("P-IDE | ArkScript Project - "  + projectName);
        save_as_stage.setHeight(100);
        save_as_stage.setWidth(500);
        save_as_stage.setResizable(false);
        BorderPane save_as_stage_root = new BorderPane();

        HBox ui_saveAs = new HBox();
        ui_saveAs.setAlignment(Pos.CENTER);

        Label saveAs_Title = new Label();
        saveAs_Title.setText("Nom du fichier (sans .ark) : ");
        saveAs_Title.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff;");

        TextField saveAs_FileName = new TextField();
        saveAs_FileName.setText("unknown");
        saveAs_FileName.setStyle("-fx-background-color: #383838; -fx-font-size: 13px; -fx-text-fill: #eaeaea; -fx-font-style: italic;");

        Button ok_button = new Button();
        ok_button.setText("Sauvegarder");
        ok_button.setStyle("-fx-background-color: #565656; -fx-font-size: 13px; -fx-text-fill: #008506; -fx-font-weight: bold;");
        WindowsFunc.mouseHoverEffect_Buttons(ok_button, explorer_buttons_DropShadow);

        ui_saveAs.getChildren().addAll(saveAs_Title, saveAs_FileName, ok_button);

        save_as_stage_root.setCenter(ui_saveAs);

        StackPane save_as_stage_container = new StackPane();
        save_as_stage_container.setStyle("-fx-background-color: #3C3F41;");
        save_as_stage_container.getChildren().add(save_as_stage_root);

        Scene save_as_stage_scene = new Scene(save_as_stage_container, 100, 100);
        save_as_stage.setScene(save_as_stage_scene);

        explorer_remove_file.setOnAction(event -> {
            if (!currentFile.get().equalsIgnoreCase("N/A")) {
                String path = repository + "\\" + currentFile.get();
                File file = new File(path);
                if (file.exists() && file.delete()) {
                    refreshTreeView(explorer_TreeView, rootDirectory, projectRootPath, repository, currentFile, codeEditor);
                }
            }
        });

        explorer_add_file.setOnAction(event -> {
            save_as_stage.show();
            save_as_stage.centerOnScreen();
        });

        save_button.setOnAction(event -> {
            if (!currentFile.get().equalsIgnoreCase("N/A")) {
                String path = repository + "\\" + currentFile.get();

                String currentCode = codeEditor.getText();
                FuncUtils.writeInFile(path, currentCode);
            }
        });

        ok_button.setOnAction(event -> {
            saveAs_fileName_Selected.set(saveAs_FileName.getText() + ".ark");

            String currentCode = codeEditor.getText();
            try {
                File SaveAs_file = new File(new File(repository), saveAs_fileName_Selected.get());
                FileWriter SaveAs_fileWriter = new FileWriter(SaveAs_file);
                BufferedWriter SaveAs_bufferedWriter = new BufferedWriter(SaveAs_fileWriter);
                SaveAs_bufferedWriter.write(currentCode);
                SaveAs_bufferedWriter.close();
            } catch (IOException ignored) {}

            refreshTreeView(explorer_TreeView, rootDirectory, projectRootPath, repository, currentFile, codeEditor);

            save_as_stage.close();
        });

        save_as_button.setOnAction(event -> {
            save_as_stage.show();
            save_as_stage.centerOnScreen();
        });

        run_button.setOnAction(event -> {
            String path = repository + "\\" + currentFile.get();
            FuncUtils.writeInFile(path, codeEditor.getText());

            // FuncUtils.executeCodeAndSeeOutput(terminal_window, "bin/Python311/python.exe", "Python", path);
            arkscript_runButton(projectRootPath, terminal_window, path);
        });

        build_button.setOnAction(event -> {
            String path = repository + "\\" + currentFile.get();
            FuncUtils.writeInFile(path, codeEditor.getText());

            File buildDir = new File(repository + "\\build");
            if (!buildDir.exists()) {
                buildDir.mkdir();
            }

            WindowsFunc.copyPasteFile(buildDir, repository + "\\" + currentFile.get(), currentFile.get());

            WindowsFunc.copyPasteFile(buildDir, "bin/ArkScript/ark.exe", "ark.exe");

            refreshTreeView(explorer_TreeView, rootDirectory, projectRootPath, repository, currentFile, codeEditor);
        });

        terminal_clear.setOnAction(event -> terminal_window.setText(""));


        Stage stage_rename_file = new Stage();
        stage_rename_file.setTitle("P-IDE | ArkScript Project - "  + projectName + "( Renommer )");
        stage_rename_file.setHeight(100);
        stage_rename_file.setWidth(500);
        stage_rename_file.setResizable(false);
        BorderPane BP_stage_rename_file = new BorderPane();

        HBox ui_rename = new HBox();
        ui_rename.setAlignment(Pos.CENTER);

        Label rename_Title = new Label();
        rename_Title.setText("Nouveau nom (sans .ark) : ");
        rename_Title.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff;");

        TextField rename_FileName = new TextField();
        rename_FileName.setText(saveAs_fileName_Selected.get());
        rename_FileName.setStyle("-fx-background-color: #383838; -fx-font-size: 13px; -fx-text-fill: #eaeaea; -fx-font-style: italic;");

        Button rename_button = new Button();
        rename_button.setText("Renommer");
        rename_button.setStyle("-fx-background-color: #565656; -fx-font-size: 13px; -fx-text-fill: #008506; -fx-font-weight: bold;");
        WindowsFunc.mouseHoverEffect_Buttons(rename_button, explorer_buttons_DropShadow);

        ui_rename.getChildren().addAll(rename_Title, rename_FileName, rename_button);

        BP_stage_rename_file.setCenter(ui_rename);

        StackPane rename_container = new StackPane();
        rename_container.setStyle("-fx-background-color: #4b4b4b;");
        rename_container.getChildren().add(BP_stage_rename_file);

        Scene rename_scene = new Scene(rename_container, width, height);
        stage_rename_file.setScene(rename_scene);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.F2) {
                stage_rename_file.show();
                stage_rename_file.centerOnScreen();
                event.consume();
            }
        });


        rename_button.setOnAction(event -> {
            try {
                String fileName = rename_FileName.getText();
                String currentCode = codeEditor.getText();
                File newfile = new File(new File(repository), fileName + ".ark");
                FileWriter newfile_Writer = new FileWriter(newfile);
                BufferedWriter newfile_bufferedWriter = new BufferedWriter(newfile_Writer);
                newfile_bufferedWriter.write(currentCode);
                newfile_bufferedWriter.close();

                if (!currentFile.get().equalsIgnoreCase("N/A")) {
                    String path = repository + "\\" + currentFile.get();
                    File file = new File(path);
                    if (file.exists() && file.delete()) {
                        stage_rename_file.close();
                        refreshTreeView(explorer_TreeView, rootDirectory, projectRootPath, repository, currentFile, codeEditor);
                    }
                }
            } catch (IOException ignored) {}
        });
    }

    private static StyleSpans<Collection<String>> managerHighlight_CodeEditor(String text) {
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        Matcher matcher = Pattern.compile("\\b(" +
                String.join("|", HIGHLIGHT_INSTRUCTIONS) + "|" +
                String.join("|", HIGHLIGHT_TYPES) + ")\\b").matcher(text);

        int lastKwEnd = 0;
        while (matcher.find()) {
            spansBuilder.add(Collections.singleton("text"), matcher.start() - lastKwEnd);

            if (Arrays.stream(HIGHLIGHT_INSTRUCTIONS).anyMatch(s -> matcher.group().equals(s))) {
                spansBuilder.add(Collections.singleton("instruction"), matcher.end() - matcher.start());
            } else if (Arrays.stream(HIGHLIGHT_TYPES).anyMatch(s -> matcher.group().equals(s))) {
                spansBuilder.add(Collections.singleton("types"), matcher.end() - matcher.start());
            }

            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.singleton("text"), text.length() - lastKwEnd);

        return spansBuilder.create();
    }

    private static void refreshTreeView(TreeView<String> m_treeView, File rootDirectory, String projectRootPath, String repository, AtomicReference<String> currentFile, CodeArea codeEditor) {
        TreeItem<String> newRootItem = WindowsFunc.createTreeItem(rootDirectory);
        m_treeView.setRoot(newRootItem);
        m_treeView.setCellFactory(treeView -> {
            TreeCell<String> cell = new TreeCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item);
                        setStyle("-fx-text-fill: #cccccc; -fx-background-color: #3d3d3d; -fx-font-weight: bold;");

                        if (getTreeItem().isLeaf() && item.endsWith(".ark")) { // File
                            ImageView imageView = new ImageView(new Image("file:" + projectRootPath.replace("\\", "/") + "/img/extension/ark.png"));
                            imageView.setFitWidth(16);
                            imageView.setFitHeight(16);
                            setGraphic(imageView);
                        } else if (!getTreeItem().isLeaf()) { // Folder
                            ImageView imageView = new ImageView(new Image("file:" + projectRootPath.replace("\\", "/") + "/img/file.png"));
                            imageView.setFitWidth(16);
                            imageView.setFitHeight(16);
                            setGraphic(imageView);
                        } else {
                            setGraphic(null);
                        }
                    }
                }
            };

            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !cell.isEmpty()) {
                    TreeItem<String> selectedItem = cell.getTreeItem();
                    String filePath = selectedItem.getValue();
                    String fileContent = "";
                    try {
                        fileContent = WindowsFunc.getFileContent(repository + "\\" + filePath);
                        currentFile.set(filePath);
                    } catch (IOException e) {
                        // Gérer l'erreur
                    }
                    codeEditor.replaceText(fileContent);

                    cell.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                } else if (event.getClickCount() == 1 && !cell.isEmpty()) {
                    cell.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            });

            return cell;
        });
    }
    private static void arkscript_runButton(String projectRootPath, TextArea output_zone, String pathCurrentFile) {
        Service<String> arkscript = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        ProcessBuilder processBuilder = new ProcessBuilder(
                                projectRootPath + "\\bin\\ArkScript\\ark.exe",
                                pathCurrentFile,
                                "--lib",
                                "bin/ArkScript/lib"
                        );
                        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                        Process process = processBuilder.start();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                        String line;
                        String all_lines = "Running in ArkScript: \n";
                        while ((line = reader.readLine()) != null) {
                            all_lines += line + "\n";
                        }
                        reader.close();
                        return all_lines;
                    }
                };
            }
        };

        arkscript.setOnSucceeded(event -> {
            String result = arkscript.getValue();
            output_zone.setText(result);
        });
        arkscript.start();
    }
}
