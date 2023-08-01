package fr.program;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import java.io.*;

public class FuncUtils {
    public static DropShadow createDropShadow(Color color) {
        DropShadow ds = new DropShadow();
        ds.setColor(color);
        ds.setOffsetX(0);
        ds.setOffsetY(4);
        ds.setRadius(8);
        ds.setSpread(0.1);
        return ds;
    }

    public static String openShell(String pathApplication, String lang, String arg) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(pathApplication, arg);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder all_lines = new StringBuilder("Running in " + lang + ": \n");
        while ((line = reader.readLine()) != null) {
            all_lines.append(line).append("\n");
        }
        reader.close();

        return all_lines.toString();
    }

    public static void executeCodeAndSeeOutput(TextArea output_zone, String pathApp, String lang, String arg) {
        Service<String> service = new Service<>() {
            @Override
            protected Task<String> createTask() {
                return new Task<>() {
                    @Override
                    protected String call() throws Exception {
                        return FuncUtils.openShell(pathApp, lang, arg);
                    }
                };
            }
        };
        service.setOnSucceeded(event -> {
            String result = service.getValue();
            output_zone.setText(result);
        });
        service.start();
    }

    public static void writeInFile(String path, String content) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(content);
                writer.close();
            } catch (IOException e) {
                //
            }
        }
    }
}
