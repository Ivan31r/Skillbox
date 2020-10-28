package ImageResizerWithStar;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    private static final String srcFolder = "D:\\Ivan_Usov\\Modul_11\\from";
    private static final String dstFolder = "D:\\Ivan_Usov\\Modul_11\\to";
    private static final File srcDir = new File(srcFolder);
    private static final File[] files = srcDir.listFiles();
    private static final Queue<File> images = new ConcurrentLinkedQueue<>(Arrays.asList(files));
    private static final int newWidth = 300;

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();

            ImageResizeV2 imageResizeV21 = new ImageResizeV2(images, newWidth, dstFolder, start);
            ImageResizeV2 imageResizeV22 = new ImageResizeV2(images, newWidth, dstFolder, start);
            ImageResizeV2 imageResizeV23 = new ImageResizeV2(images, newWidth, dstFolder, start);
            ImageResizeV2 imageResizeV24 = new ImageResizeV2(images, newWidth, dstFolder, start);

            imageResizeV21.start();
            imageResizeV22.start();
            imageResizeV23.start();
            imageResizeV24.start();


    }
}
