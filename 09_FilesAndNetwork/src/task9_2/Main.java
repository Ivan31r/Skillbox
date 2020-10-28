package task9_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        String fromDirectory = getPath();
        File fromFolder = new File(fromDirectory);
        String toDirectory = getPath();
        File toFolder = new File(toDirectory);

//        File fromFolder = new File("D:\\Ivan_Usov\\from_copy_folder");
//        File toFolder = new File("D:\\Ivan_Usov\\to_copy_folder" );

        try {
            copyDirectory(fromFolder,toFolder);
        } catch (IOException e) {
            System.out.println("печалька =/ ");
            e.printStackTrace();
        }


    }

    public static String getPath() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = null;
        try {
            path = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void copyDirectory(File fromFolder, File toFolder) throws IOException {
        for (File file : fromFolder.listFiles()) {
            if (file.isFile()) {
                Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(toFolder.getAbsolutePath() + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
            }
            if (file.isDirectory()) {
                Files.createDirectory(Paths.get(toFolder.getAbsolutePath() + File.separator + file.getName()));
                copyDirectory(file, new File(toFolder.getAbsolutePath() + File.separator + file.getName()));
            }
        }
    }
}
