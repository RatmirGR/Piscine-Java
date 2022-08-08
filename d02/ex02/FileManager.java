package d02.ex02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {
    private Path currentPath;
    private Path parentPath;

    public FileManager(String currentPath){
        Path curPath =  Paths.get(currentPath).toAbsolutePath().normalize();
        if (!Files.isDirectory(curPath)){
            System.err.println(curPath + " No such directory");
        }else{
            this.currentPath = curPath;
            this.parentPath = curPath;
            System.out.println(currentPath);
        }
    }

    public void setCurrentPath(Path currentPath) {
        Path path = currentPath.toAbsolutePath().normalize();
        if (!Files.isDirectory(path)){
            System.err.println("Not a directory: " + path);
        }else {
            this.currentPath = currentPath;
        }
    }

    public void moveFile(String oldPath, String newPath) throws IOException {
        Path from = Paths.get(currentPath + "/" + oldPath).toAbsolutePath().normalize();
        if (!Files.exists(from)){
            System.err.println("mv: " + from.getFileName() + " No such file or directory");
            return;
        }

        Path to = Paths.get(currentPath + "/" + newPath).toAbsolutePath().normalize();

        if (Files.isDirectory(to)) {
            to = Paths.get(to + "/" + oldPath);
            if (from.getParent().getFileName().compareTo(to.getParent().getFileName()) == 0) {
                System.err.println("mv: " + oldPath + " and " + newPath + " are identical");
                return;
            }
        }
        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
    }

    public void changeDirectory(String newPath) throws IOException{
        Path to = Paths.get(currentPath + "/" + newPath);
        if (Files.exists(to) && Files.isDirectory(to)){
            setCurrentPath(to.toAbsolutePath().normalize());
            System.out.println(currentPath);
        }else{
            System.err.println("cd: no such file or directory: " + to.getFileName());
        }
    }

    public void printCurrentDirectory() throws IOException {
        List<Path> listPaths = Files.walk(currentPath, 1).collect(Collectors.toList());
        for (Path listPath : listPaths) {
            if (!Files.isDirectory(listPath)) {
                System.out.println(listPath.getFileName() + " " + Files.size(listPath) / 1024 + " KB");
            }else if (!listPath.equals(currentPath)){
                System.out.println(listPath.getFileName() + " " + Files.size(listPath) + " KB");
            }
        }
    }

    public Path getParentPath() {
        return parentPath;
    }
}
