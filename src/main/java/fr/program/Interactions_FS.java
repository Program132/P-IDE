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
            FuncUtils.writeInFile("fastscript/code/fpl.fpl", content);

            FuncUtils.executeCodeAndSeeOutput(output_zone, "bin/fpl/fpl-3.exe", "F.P.L","fastscript/code/fpl.fpl");
        }
        else if (getMode().equalsIgnoreCase("java")) {
            String real_content_java = "package fastscript.code; \n" + content;
            FuncUtils.writeInFile("fastscript/code/java.java", real_content_java);

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
            FuncUtils.writeInFile("fastscript/code/cpp.cpp", content);
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
                                } catch (IOException ignored) {}
                            });
                } catch (IOException ignored) {}
            });


        }
        else if (getMode().equalsIgnoreCase("lua")) {
            FuncUtils.writeInFile("fastscript/code/lua.lua", content);

            FuncUtils.executeCodeAndSeeOutput(output_zone, "bin/lua-5.4.2/lua54.exe", "Lua", "fastscript/code/lua.lua");
        }
        else if (getMode().equalsIgnoreCase("py")) {
            FuncUtils.writeInFile("fastscript/code/py.py", content);

            FuncUtils.executeCodeAndSeeOutput(output_zone, "bin/Python311/python.exe", "Python", "fastscript/code/py.py");
        }
        else if (getMode().equalsIgnoreCase("ark")) {
            FuncUtils.writeInFile("fastscript/code/ark.ark", content);

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
        else if (getMode().equalsIgnoreCase("ts")) {
            FuncUtils.writeInFile("fastscript/code/ts.ts", content);

            Service<String> ts_build = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    "bin/npm/tsc.cmd",
                                    "fastscript/code/ts.ts"
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

            Service<String> ts_run = new Service<String>() {
                @Override
                protected Task<String> createTask() {
                    return new Task<String>() {
                        @Override
                        protected String call() throws Exception {
                            ProcessBuilder processBuilder = new ProcessBuilder(
                                    "bin/nodejs/node.exe",
                                    "fastscript/code/ts.js"
                            );
                            processBuilder.redirectOutput(ProcessBuilder.Redirect.PIPE);
                            Process process = processBuilder.start();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            String all_lines = "Running in TypeScript: \n";
                            while ((line = reader.readLine()) != null) {
                                all_lines += line + "\n";
                            }
                            reader.close();
                            return all_lines;
                        }
                    };
                }
            };

            ts_build.setOnSucceeded(event -> {
                String result = ts_build.getValue();
                output_zone.setText(result);
                ts_run.start();
            });
            ts_build.start();

            ts_run.setOnSucceeded(event -> {
                String result = ts_run.getValue();
                output_zone.setText(result);

                String file_class = projectRootPath.replace("\\", "/") + "/fastscript/code/ts.js";
                File file = new File(file_class);

                if (file.exists()) {
                    file.delete();
                    file.deleteOnExit();
                }
            });
        }
    }
}
