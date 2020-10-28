package task9_4;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class TestHtml {
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://lenta.ru/").get();
        String title = document.title();
        System.out.println(title);
    }
}
