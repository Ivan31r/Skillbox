package ImageResizer;

import org.w3c.dom.ls.LSOutput;

import java.io.File;

public class Main {
    private static final int newWidth = 300;  //ла ла ла

    public static void main(String[] args) {
        String srcFolder = "D:\\Ivan_Usov\\Modul_11\\from";
        String dstFolder = "D:\\Ivan_Usov\\Modul_11\\to";

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        int core = Runtime.getRuntime().availableProcessors(); //до этого момента все работало

        System.out.println(core + " core");   //core = 4

        int part = (files.length / core) + 1;           //part = 15/4 = 3,75
        System.out.println(part + " part");

        System.out.println(files.length + " files.length");     //у меня это 15 файлов

        int insertIndex = 0;

        File[] files1;
        for (int count = 0; count < part; count++) {
            if (part - count == 1) {
                part = files.length - (((files.length / core) + 1) * (core - 1));
            }
            files1 = new File[part];
            System.arraycopy(files, insertIndex, files1, 0, files1.length);
            ImageResizeModel imageResizeModel = new ImageResizeModel(files1, newWidth, dstFolder, start);
            imageResizeModel.start();
            insertIndex += part;
        }
    }
}
