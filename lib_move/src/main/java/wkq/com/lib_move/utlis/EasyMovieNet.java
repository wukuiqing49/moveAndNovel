package wkq.com.lib_move.utlis;

import java.io.IOException;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-01
 * <p>
 * 用途:
 */


public interface EasyMovieNet {
    //GET请求
    String getHtml(String url, String encodeType) throws IOException;

    //POST请求，传入post参数是RequestBody，可instance of强转为FormBody获取参数
    String getHtml(String url, Map<String, String> header, RequestBody requestBody, String encodeType) throws IOException;
}