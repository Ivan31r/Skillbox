package task9_4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.imageio.spi.ImageInputStreamSpi;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> imgs;

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://lenta.ru/");
        String folder = getPath();
        imgs = getImgs(url);
        writeImgs(imgs, folder);
    }
    //

    public static ArrayList<String> getImgs(URL url) {

        ArrayList<String> listOfImgs = new ArrayList<>();
        try {

            Document document = Jsoup.connect(String.valueOf(url)).get();
            Elements img = document.getElementsByTag("img");
            for (Element el : img) {
                String s = el.attr("src");
                listOfImgs.add(s);
                System.out.println(s);
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return listOfImgs;
    }

    public static void writeImgs(ArrayList<String> imgs, String path) {
        for (String s : imgs) {
            try {
                URL url = new URL(s);
                BufferedImage image = ImageIO.read(url);
                int index = s.lastIndexOf("/");
                String fileName = s.substring(index + 1);
                ImageIO.write(image, "png", new File(path + File.separator + fileName));
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static String getPath() {
        System.out.println("Введите адрес папки, куда будем копировать");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        try {
            s = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;

    }
}
