package task9_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = getPath();
        File folder = new File(path);
        long s = folderSize(folder);
        System.out.println(getCurrentView(s));


        System.out.println("-------\n");

    }

    public static long folderSize(File folder) {
        long size = 0;
        for (File file : folder.listFiles()) {
            if (file==null)
                return 0;
            size+=file.isFile()?file.length():folderSize(file);
        }
        return size;
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

    public static String getCurrentView(long s) {
        String[] unit = {"Байт", "Килобайт", "Мегабайт", "Гигабайт", "Терабайт"};
        int count = 0;
        while (s > 1024) {
            s = s / 1024;
            count++;
        }
        return s + " : " + unit[count];
    }
}
