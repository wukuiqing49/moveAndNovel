package com.wkq.order.modlue.main.observable;

import java.util.Observable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-28
 * <p>
 * 用途:
 */


public class HomePageChangeObservable extends Observable {
    private static HomePageChangeObservable instance;

    public static HomePageChangeObservable newInstance() {

        synchronized (HomePageChangeObservable.class) {
            if (instance == null) instance = new HomePageChangeObservable();
        }

        return instance;
    }


    public void updateChange(int page){
        setChanged();
        notifyObservers(page);
    }

}
