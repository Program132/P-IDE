package fr.program;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

public class Interactions_FS {
    private static String mode = "N/A";
    private static String getMode() {
        return mode;
    }
    public static void setMode(String m) {
        mode = m;
    }

    public static void execute(String content, TextArea output_zone) throws IOException {
        String projectRootPath = System.getProperty("user.dir");

        if (getMode().equalsIgnoreCase("fpl")) {
            writeInFile("fastscript/code/fpl.fpl", content);

            executeCodeAndSeeOutput(output_zone, "bin/fpl/fpl-2.3.exe", "F.P.L","fastscript/code/fpl.fpl");
            // Running FPL V3 Parser...
        }
        else if (getMode().equalsIgnoreCase("java")) {
            String real_content_java = "package fastscript.code; \n" + content;
            writeInFile("fastscript/code/java.java", real_content_java);

            Service<String> run_bytecode_java = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    "bin/jdk-20/bin/javac.exe",
                                    "fastscript/code/java.java"
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();

                            return all_lines;
                        }
                    };
                }
            };

            Service<String> run_java_class = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    "bin/jdk-20/bin/java.exe",
                                    "fastscript.code.java"
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "Running in Java: \n";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();

                            return all_lines;
                        }
                    };
                }
            };

            run_bytecode_java.setOnSucceeded(event -> {
                String result = run_bytecode_java.getValue();
                output_zone.setText(result);
                run_java_class.start();
            });
            run_bytecode_java.start();

            run_java_class.setOnSucceeded(event -> {
                String result = run_java_class.getValue();
                output_zone.setText(result);

                String file_class = projectRootPath.replace("\\", "/") + "/fastscript/code/java.class";
                File file = new File(file_class);

                if (file.exists()) {
                    file.delete();
                    file.deleteOnExit();
                }
            });
        }
        else if (getMode().equalsIgnoreCase("cpp")) {
            writeInFile("fastscript/code/cpp.cpp", content);

            // Running cmake and g++
            // cmake -G "MinGW Makefiles" -D CMAKE_C_COMPILER=D:\GitHub\P-IDE\bin\mingw64\bin\gcc.exe -D CMAKE_CXX_COMPILER=D:\GitHub\P-IDE\bin\mingw64\bin\g++.exe -DCMAKE_BUILD_TYPE=Release D:\GitHub\P-IDE\fastscript\code\
            // cmake --build

            String cmakePath = projectRootPath + "\\bin\\CMake\\bin\\cmake.exe";
            String gppPath = projectRootPath + "\\bin\\mingw64\\bin\\g++.exe";
            String gccPath = projectRootPath + "\\bin\\mingw64\\bin\\gcc.exe";
            String codePath = projectRootPath + "\\fastscript\\code\\";

            output_zone.setText("Please be patient...\n");

            Service<String> build_cmake = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    cmakePath.replace("\\", "/"),
                                    "-G",
                                    "\"MinGW Makefiles\"",
                                    "-D",
                                    "CMAKE_C_COMPILER=" + gccPath.replace("\\", "/"),
                                    "-D",
                                    "CMAKE_CXX_COMPILER=" + gppPath.replace("\\", "/"),
                                    "-DCMAKE_BUILD_TYPE=Release",
                                    codePath.replace("\\", "/")
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "Generate CMake Config: \n";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();
                            return all_lines;
                        }
                    };
                }
            };

            Service<String> build_task_cmake = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    cmakePath.replace("\\", "/"),
                                    "--build",
                                    projectRootPath.replace("\\", "/")
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "\n Generate CMake Build: \n";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();
                            return all_lines;
                        }
                    };
                }
            };

            Service<String> run_exe = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    projectRootPath.replace("\\", "/") + "/cpp.exe"
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "\n Executed in C++: \n";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();
                            return all_lines;
                        }
                    };
                }
            };

            build_cmake.setOnSucceeded(event -> {
                String result = build_cmake.getValue();
                output_zone.appendText(result);
                build_task_cmake.start();
            });
            build_cmake.start();

            build_task_cmake.setOnSucceeded(event -> {
                String result = build_task_cmake.getValue();
                output_zone.appendText(result);
                run_exe.start();
            });

            run_exe.setOnSucceeded(event -> {
                String result = run_exe.getValue();
                output_zone.appendText(result);

                String cache = projectRootPath + "/CMakeCache.txt";
                String install = projectRootPath + "/cmake_install.cmake";
                String make = projectRootPath + "/Makefile";
                String exe = projectRootPath + "/cpp.exe";


                String cmakeFiles = projectRootPath + "/CMakeFiles/";

                try {
                    Files.deleteIfExists(Paths.get(cache));
                    Files.deleteIfExists(Paths.get(install));
                    Files.deleteIfExists(Paths.get(make));
                    Files.deleteIfExists(Paths.get(exe));

                    Path directory = Paths.get(cmakeFiles);

                    Files.walk(directory)
                            .sorted(Comparator.reverseOrder())
                            .forEach(path -> {
                                try {
                                    Files.delete(path);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }
        else if (getMode().equalsIgnoreCase("lua")) {
            writeInFile("fastscript/code/lua.lua", content);

            executeCodeAndSeeOutput(output_zone, "bin/lua-5.4.2/lua54.exe", "Lua", "fastscript/code/lua.lua");
        }
        else if (getMode().equalsIgnoreCase("py")) {
            writeInFile("fastscript/code/py.py", content);

            executeCodeAndSeeOutput(output_zone, "bin/Python311/python.exe", "Python", "fastscript/code/py.py");
        }
        else if (getMode().equalsIgnoreCase("ark")) {
            writeInFile("fastscript/code/ark.ark", content);

            Service<String> arkscript = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    projectRootPath + "\\bin\\ArkScript\\ark.exe",
                                    "fastscript/code/ark.ark",
                                    "--lib",
                                    "bin/ArkScript/lib"
                            );
                            System.out.println(processBuilder.command());
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

    private static String openShell(String pathApplication, String lang, String arg) throws IOException, InterruptedException {
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
