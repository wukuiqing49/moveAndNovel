package wkq.com.lib_move.utlis;

import java.io.IOException;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-01
 * <p>
 * 用途:
 */


public class OkHttpNet implements EasyMovieNet {

    @Override
    public String getHtml(String url, String encodeType) throws IOException {
        try {
            return getHtml(url, null, null, encodeType);
        } catch (IOException e) {
            System.err.print(url);
            throw e;
        }
    }

    @Override
    public String getHtml(String url, Map<String, String> header, RequestBody requestBody, String encodeType) throws IOException {
        Request.Builder builder = new Request.Builder()
                .addHeader("accept", "*/*")
                .addHeader("connection", "Keep-Alive")
                .addHeader("Charsert", encodeType)
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        if (requestBody != null) {
            builder.post(requestBody);
        } else {
            builder.get();
        }
            Request request = builder
                    .url(url)
                    .build();

            ResponseBody body = MovieNetUtil.okHttpClient.newCall(request).execute().body();
            if (body == null) {
                return "";
            } else {
                return new String(body.bytes(), encodeType);
            }
        }
    }