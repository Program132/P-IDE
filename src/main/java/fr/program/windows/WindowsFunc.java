package fr.program.windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WindowsFunc {

    public static Label getTitleTerminal() {
        Label title_terminal = new Label();
        title_terminal.setText("Résultats :");
        title_terminal.setStyle("-fx-font-size: 13px; -fx-text-fill: #ffffff; -fx-font-weight: bold");
        title_terminal.setAlignment(Pos.CENTER_LEFT);
        return title_terminal;
    }

    public static String getFileContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public static TreeItem<String> createTreeItem(File file) {
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

    public static void mouseHoverEffect_Buttons(Button button, DropShadow defaultEffect) {
        button.setOnMouseEntered(event -> button.setEffect(defaultEffect));
        button.setOnMouseExited(event -> button.setEffect(null));
    }

    public static Button createButtonWithStyle(String content, String style) {
        Button b = new Button(content);
        b.setStyle(style);
        return b;
    }

    public static void copyPasteFile(File buildDir, String path, String child) {
        File exeFile = new File(path);
        Path target = new File(buildDir, child).toPath();
        try {
            Files.copy(exeFile.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {}
    }
}
