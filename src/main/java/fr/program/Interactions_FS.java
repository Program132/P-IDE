package fr.program;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;

public class Interactions_FS {
    private static String mode;
    private static String getMode() {
        return mode;
    }
    public static void setMode(String m) {
        mode = m;
    }

    public static void execute(String content, TextArea zone) throws IOException {
        // FORMAT : ProcessBuilder processBuilder = new ProcessBuilder("chemin_vers_executable", "argument1", "argument2");

        if (getMode().equalsIgnoreCase("fpl")) {
            writeInFile("fastscript/code/fpl.fpl", content);


        } else if (getMode().equalsIgnoreCase("java")) {
            writeInFile("fastscript/code/java.java", content);


        } else if (getMode().equalsIgnoreCase("cpp")) {
            writeInFile("fastscript/code/cpp.cpp", content);


        } else if (getMode().equalsIgnoreCase("lua")) {
            writeInFile("fastscript/code/lua.lua", content);

            ProcessBuilder processBuilder = new ProcessBuilder("bin/lua-5.4.2/lua54.exe");
            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String all_lines = "";
            while ((line = reader.readLine()) != null) {
                all_lines += line + "\n";
            }
            reader.close();

            String finalAll_lines = all_lines;
            Platform.runLater(() -> {
                zone.setText(finalAll_lines);
            });
        } else if (getMode().equalsIgnoreCase("py")) {
            writeInFile("fastscript/code/py.py", content);


        }
    }

    private static void writeInFile(String path, String content) {
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
