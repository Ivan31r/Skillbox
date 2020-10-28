package ImageResizerWithStar;

import org.imgscalr.Scalr;
import org.w3c.dom.ls.LSOutput;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Queue;

public class ImageResizeV2 extends Thread {
    private Queue<File> images;
    private int newWidth;
    private String dstFolder;
    private long start;

    int core = Runtime.getRuntime().availableProcessors();

    public ImageResizeV2(Queue<File> images, int newWidth, String dstFolder, long start) {
        this.images = images;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
        this.start = start;
    }


    @Override
    public void run() {
            for(int count =0;count<core;count++) {
                try {
                    File file = images.poll();
                    BufferedImage image = ImageIO.read(file);
                    if (image == null) {
                        System.exit(0);
                    }
                    BufferedImage newImage = Scalr.resize(image, 300);
                    ImageIO.write(newImage, "jpg", new File(dstFolder + "/" + file.getName()));
                } catch (Exception e) {
                    e.getMessage();
                }

            }
        System.out.println("Duration " + (System.currentTimeMillis() - start));
    }
}




