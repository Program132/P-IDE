package fr.program;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.io.*;

public class Interactions_FS {
    private static String mode = "N/A";
    private static String getMode() {
        return mode;
    }
    public static void setMode(String m) {
        mode = m;
    }

    public static void execute(String content, TextArea output_zone) throws IOException {
        // FORMAT : ProcessBuilder processBuilder = new ProcessBuilder("chemin_vers_executable", "argument1", "argument2");

        if (getMode().equalsIgnoreCase("fpl")) {
            writeInFile("fastscript/code/fpl.fpl", content);

            // Running FPL V3 Parser
        } else if (getMode().equalsIgnoreCase("java")) {
            writeInFile("fastscript/code/java.java", content);

            executeCodeAndSeeOutput(output_zone, "bin/jre-1.8/bin/java.exe", "Java", "fastscript/code/java.java");
        } else if (getMode().equalsIgnoreCase("cpp")) {
            writeInFile("fastscript/code/cpp.cpp", content);

            // Running cmake and g++
        } else if (getMode().equalsIgnoreCase("lua")) {
            writeInFile("fastscript/code/lua.lua", content);

            executeCodeAndSeeOutput(output_zone, "bin/lua-5.4.2/lua54.exe", "Lua", "fastscript/code/lua.lua");
        } else if (getMode().equalsIgnoreCase("py")) {
            writeInFile("fastscript/code/py.py", content);

            executeCodeAndSeeOutput(output_zone, "bin/Python311/python.exe", "Python", "fastscript/code/py.py");
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

    private static String openShell(String pathApplication, String lang, String arg) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(pathApplication, arg);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
        Process process = processBuilder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        String all_lines = "Running in " + lang + ": \n";
        while ((line = reader.readLine()) != null) {
            all_lines += line + "\n";
        }
        reader.close();

        return all_lines;
    }

    private static void executeCodeAndSeeOutput(TextArea output_zone, String pathApp, String lang, String arg) {
        Service<String> service = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        return openShell(pathApp, lang, arg);
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
}
