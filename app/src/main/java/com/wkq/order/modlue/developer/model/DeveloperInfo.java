package com.wkq.order.modlue.developer.model;

import androidx.databinding.BaseObservable;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2019-12-24
 * <p>
 * 用途:
 */


public class DeveloperInfo extends BaseObservable {
    int id;

    public DeveloperInfo(int id, String itemStrig) {
        this.id = id;
        this.itemStrig = itemStrig;
    }

    String itemStrig;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemStrig() {
        return itemStrig;
    }

    public void setItemStrig(String itemStrig) {
        this.itemStrig = itemStrig;
    }
}
