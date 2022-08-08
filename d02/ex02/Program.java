package d02.ex02;

import java.io.IOException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        if (args.length != 1){
            System.err.println("Wrong number of arguments");
        }else {
            if (!args[0].startsWith("--current-folder=")){
                System.out.println("Error");
                return;
            }
            FileManager fileManager = new FileManager(args[0].substring("--current-folder=".length()));
            Scanner scanner = new Scanner(System.in);
            try {
                String input;
                String[] cmd;

                while ((input = scanner.nextLine()) != null){
                    cmd = input.split(" ");

                    switch (cmd[0]) {
                        case "exit":
                            scanner.close();
                            return;
                        case "cd":
                            if (cmd.length == 2) {
                                fileManager.changeDirectory(cmd[1]);
                            }else if (cmd.length == 1){
                                fileManager.setCurrentPath(fileManager.getParentPath());
                                System.out.println(fileManager.getParentPath());
                            }else{
                                System.err.println("cd: too many arguments");
                            }
                            break;
                        case "mv":
                            if (cmd.length == 3) {
                                fileManager.moveFile(cmd[1], cmd[2]);
                            }else{
                                System.err.println("mv: wrong number of arguments");
                            }
                            break;
                        case "ls":
                            if (cmd.length == 1) {
                                fileManager.printCurrentDirectory();
                            }else{
                                System.err.println("mv: wrong number of arguments");
                            }
                            break;
                        default:
                            System.out.println("Unknown command");
                            break;
                    }
                }
            } catch (IOException e) {
                scanner.close();
                System.out.println(e.getMessage());
            }
        }
    }
}
