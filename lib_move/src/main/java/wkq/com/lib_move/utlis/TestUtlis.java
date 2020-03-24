package wkq.com.lib_move.utlis;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-01
 * <p>
 * 用途:
 */


public class TestUtlis {
    public static void test(){
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            //优先使用zbar识别一次二维码
            String content = null;
            String html = MovieNetUtil.getHtml("http://www.xpiaohua.com/", "UTF-8");

            Document document = Jsoup.parse(html);
            Log.e("", "");


        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("", s);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
