package main.java.org.example.example;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 将文本转换成字符串
 */
public class TextToString {
    public static String textToString(String file) {
        File path = new File(file);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
            String s;
            while ((s = br.readLine()) != null) text.append(System.lineSeparator()).append(s);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return delete(text.toString());
    }

    /**
     * 清除输入字符串中除了文字逗号句号外的标签、回车等
     */
    public static String delete(String s) {
        s = Jsoup.clean(s, Whitelist.none());
        s = s.toLowerCase();
        String[] strings = {" ", "\n", "\r", "\t", "\\r", "\\n", "\\t", "&nbsp;"};
        for (String index : strings) {
            s = s.replaceAll(index, "");
        }
        return s;
    }

}
