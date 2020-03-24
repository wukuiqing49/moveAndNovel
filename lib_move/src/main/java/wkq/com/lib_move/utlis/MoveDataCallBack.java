package wkq.com.lib_move.utlis;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-03
 * <p>
 * 用途:
 */


public interface MoveDataCallBack<T> {
    void onFail(String message);

    void onSuccess(T t);

    void onLoading();

}
