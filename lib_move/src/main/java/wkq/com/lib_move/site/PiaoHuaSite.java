package wkq.com.lib_move.site;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import wkq.com.lib_move.utlis.MovieNetUtil;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-02
 * <p>
 * 用途:
 */


public class PiaoHuaSite extends Site {
    public static String baseUrl = "http://www.xpiaohua.com/";

    public void getCcontent() throws IOException {
        String htmlContent = MovieNetUtil.getHtml(baseUrl);
        Document document = Jsoup.parse(htmlContent);
//        document.getElementsByTag();
    }
}
