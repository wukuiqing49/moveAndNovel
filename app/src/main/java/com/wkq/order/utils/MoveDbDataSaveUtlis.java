package com.wkq.order.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wkq.baseLib.utlis.SharedPreferencesHelper;
import com.wkq.order.modlue.login.MoveDbMoveType;
import com.wkq.order.modlue.main.modle.BannerInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import wkq.com.lib_move.model.MTimeHomeBean;
import wkq.com.lib_move.model.MoveInfo;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public class MoveDbDataSaveUtlis {


    public static void putType(Context context) {

        String data = SharedPreferencesHelper.getInstance(context).getValue(Constant.MOVE_DB_TYPE_KEY);
        if (!TextUtils.isEmpty(data)) return;

        List<MoveDbMoveType> types = new ArrayList<>();
        types.add(new MoveDbMoveType(37, "西部"));
        types.add(new MoveDbMoveType(10402, "音乐"));
        types.add(new MoveDbMoveType(10749, "爱情"));
        types.add(new MoveDbMoveType(36, "历史"));
        types.add(new MoveDbMoveType(53, "惊悚"));
        types.add(new MoveDbMoveType(878, "科幻"));
        types.add(new MoveDbMoveType(10752, "战争"));
        types.add(new MoveDbMoveType(27, "恐怖"));
        types.add(new MoveDbMoveType(10767, "脱口秀"));
        types.add(new MoveDbMoveType(10766, "肥皂剧"));
        types.add(new MoveDbMoveType(10765, "科幻"));
        types.add(new MoveDbMoveType(10764, "真人秀"));
        types.add(new MoveDbMoveType(10763, "新闻"));
        types.add(new MoveDbMoveType(10762, "儿童"));
        types.add(new MoveDbMoveType(9648, "悬疑"));
        types.add(new MoveDbMoveType(10751, "家庭"));
        types.add(new MoveDbMoveType(18, "文艺"));
        types.add(new MoveDbMoveType(99, "纪录片"));
        types.add(new MoveDbMoveType(80, "犯罪"));
        types.add(new MoveDbMoveType(35, "喜剧"));
        types.add(new MoveDbMoveType(10759, "动作冒险"));
        types.add(new MoveDbMoveType(16, "动画片"));
        types.add(new MoveDbMoveType(28, "动作"));
        types.add(new MoveDbMoveType(12, "冒险"));
        String typeStrings = MoveDbDataSaveUtlis.list2json(types);
        SharedPreferencesHelper.getInstance(context).setValue(Constant.MOVE_DB_TYPE_KEY, typeStrings);
    }

    //将集合转化为json

    public static String list2json(List<MoveDbMoveType> list) {
        if (list == null) return "";
        JSONArray array = new JSONArray();
        JSONObject jsonObject = null;
        MoveDbMoveType info = null;
        for (int i = 0; i < list.size(); i++) {
            info = list.get(i);
            jsonObject = new JSONObject();
            try {
                jsonObject.put("id", info.getId() + "");
                jsonObject.put("type", info.getType());
                array.put(jsonObject);

            } catch (JSONException e) {
                return "";
            }

        }
        return array.toString();

    }

    /**
     * 将string 转化为 list
     *
     * @param listJsons
     * @return
     */
    public static List<MoveDbMoveType> json2list(String listJsons) {

        List<MoveDbMoveType> items = new ArrayList<>();
        if (TextUtils.isEmpty(listJsons)) return items;

        JSONArray array = null;
        try {
            array = new JSONArray(listJsons);
            JSONObject object = null;
            MoveDbMoveType item = null;
            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                String key = object.getString("id");
                String value = object.getString("type");
                int id = Integer.parseInt(key);
                item = new MoveDbMoveType(id, value);
                items.add(item);
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;

    }

    /**
     * 将string 转化为 list
     *
     * @param listJsons
     * @return
     */
    public static List<MTimeHomeBean> mtJson2list(String listJsons) {

        List<MTimeHomeBean> items = new ArrayList<>();
        if (TextUtils.isEmpty(listJsons)) return null;

        JSONArray array = null;
        try {
            array = new JSONArray(listJsons);


            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                MTimeHomeBean item = new MTimeHomeBean();
                String titleName = object.getString("titleName");
                item.setTitleName(titleName);
                JSONArray moveInfos = object.getJSONArray("moveInfos");
                List<MoveInfo> mMoveLists=new ArrayList<>();
                for (int j = 0; j < moveInfos.length(); j++) {
                    MoveInfo moveInfo = new MoveInfo();
                    JSONObject object2 = moveInfos.getJSONObject(j);
                    String moveName = object2.getString("moveName");

//                    String createTime = object2.getString("createTime");
//                    String authorName = object2.getString("authorName");
//                    String moveType = object2.getString("moveType");
                    String moveCover = object2.getString("moveCover");
                    String moveHref = object2.getString("moveHref");
                    String moveScore = object2.getString("moveScore");

                    moveInfo.setMoveScore(moveScore);
                    moveInfo.setMoveHref(moveHref);
                    moveInfo.setMoveCover(moveCover);
//                    moveInfo.setMoveType(moveType);
                    moveInfo.setMoveName(moveName);
//                    moveInfo.setAuthorName(authorName);
//                    moveInfo.setCreateTime(createTime);
                    mMoveLists.add(moveInfo);

                }

                item.setMoveInfos(mMoveLists);
                items.add(item);
            }
            return items;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;

    }


    public static String getType(Context conetxt, int id) {
        String data = SharedPreferencesHelper.getInstance(conetxt).getValue(Constant.MOVE_DB_TYPE_KEY);
        List<MoveDbMoveType> list = json2list(data);
        if (list != null && list.size() > 0) {
            for (MoveDbMoveType moveDbMoveType : list) {
                if (id == moveDbMoveType.getId()) {
                    return moveDbMoveType.getType();
                }
            }
        }
        return "其他";
    }

    /**
     * 保存Banner数据
     *
     * @param context
     * @param bannerList
     * @return
     */

    public static boolean saveBannerData(Context context, List bannerList) {

        //3.把list或对象转化为json

        try {
            Gson gson2 = new Gson();
            String str = gson2.toJson(bannerList);
            SharedPreferencesHelper.getInstance(context).setValue(Constant.MOVE_DB_HOME_BANNER_KEY, str);
            return true;
        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }

        return false;
    }

    /**
     * 获取Banner
     *
     * @param context
     * @return
     */
    public static List<BannerInfo> getBannerList(Context context) {
        List<BannerInfo> banners = new ArrayList<>();
        String bannerStr = SharedPreferencesHelper.getInstance(context).getValue(Constant.MOVE_DB_HOME_BANNER_KEY);
        if (TextUtils.isEmpty(bannerStr)) return banners;

        Gson gson = new Gson();

        Type type = new TypeToken<List<BannerInfo>>() {
        }.getType();
        banners = gson.fromJson(bannerStr, type);
        return banners;
    }

}
