package ImageResizerWithLibrary;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String srcFolder = "D:\\Ivan_Usov\\Modul_11\\from";
        String dstFolder = "D:\\Ivan_Usov\\Modul_11\\to";

        File srcDir = new File(srcFolder);



        File[] files = srcDir.listFiles();
        for (File file : files){
            BufferedImage image = ImageIO.read(file);
            BufferedImage newImage =  Scalr.resize(image,300);
            ImageIO.write(newImage,"jpg", new File(dstFolder + "/" + file.getName()));
        }
    }
}
