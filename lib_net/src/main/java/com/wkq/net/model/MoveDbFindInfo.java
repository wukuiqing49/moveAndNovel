package com.wkq.net.model;

import androidx.databinding.BaseObservable;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/25
 * <p>
 * 简介:
 */
public class MoveDbFindInfo extends BaseObservable {


    /**
     * page : 1
     * total_results : 4
     * total_pages : 1
     * results : [{"popularity":0.6,"vote_count":0,"video":false,"poster_path":"/o9M6P2ByqdxaFSeCBv4uyCdZmQX.jpg","id":494133,"adult":false,"backdrop_path":null,"original_language":"zh","original_title":"残废科幻","genre_ids":[878],"title":"残废科幻","vote_average":0,"overview":"","release_date":"2013-11-29"},{"popularity":7.14,"vote_count":85,"video":false,"poster_path":"/sBSoW79Hpm9VsfBkriwZferKfen.jpg","id":3596,"adult":false,"backdrop_path":"/t2d4jUdcqseF1Q0HkAZlfmoA5kk.jpg","original_language":"en","original_title":"Things to Come","genre_ids":[18,878],"title":"科幻双故事片","vote_average":6.5,"overview":"","release_date":"1936-02-20"},{"popularity":8.503,"id":27275,"video":false,"vote_count":116,"vote_average":7.3,"title":"科幻豪杰总动员","release_date":"2004-11-12","original_language":"tr","original_title":"G.O.R.A.","genre_ids":[12,35,878],"backdrop_path":"/g90Hlbaj8OofowG9hDg9ScD5fqG.jpg","adult":false,"overview":"土耳其一个地毯商人常爱伪造UFO照片到报社骗取钱财。一天在他向旅游者高价兜售他的地毯时，他被劫持到一个奇异的星球－GORA。他不停用手机和地球的朋友联系，但是没人相信他被外星人劫持。他只好设法自己逃走。一个自称CARAVEL的人的影子常出现在他身边称他就是\u201c被选择的那个人\u201d。在这个GORA星球上，司令官正希望娶公主成为国王的继承人。但是国王却计划将女儿嫁给另一个人。司令官与父亲串通向GORA发射了一个火球。司令官要国王许诺如果自己能射中火球，国王就要将公主嫁给他。为了保住GORA国王同意了。关键时刻司令官的枪出了问题。地毯商出现用魔法击中了火球。公主爱上了地毯商。公主从母亲那里得知自己真正的父亲是地球人。她想去地球寻找父亲。公主，地毯商，一个机器人，一个色情片导演逃离了王宫。公主被司令官抓回。地毯商遇上CARAVEL得知CARAVEL是公主亲生父亲的..","poster_path":"/hXLKHzlybVWloEbfWKZ43APdGUX.jpg"},{"popularity":18.017,"vote_count":1815,"video":false,"poster_path":"/amqgIuISRBt8tsZM6cTT6gO9WLR.jpg","id":6795,"adult":false,"backdrop_path":"/jzcOVeydNYF4hX0OiNftWC8QZJz.jpg","original_language":"en","original_title":"Zathura: A Space Adventure","genre_ids":[12,35,14,878,10751],"title":"勇敢者的游戏2：太空飞行棋","vote_average":6.3,"overview":"父亲因为工作需要出门，姐姐莉莎（克里斯汀\u2022斯图尔特 Kristen Stewart 饰）作为老大不得已担负起照顾两个弟弟丹尼和沃特的责任。然而莉莎偷懒留下丹尼（乔纳什\u2022波波 Jonah Bobo 饰）和沃特（乔什\u2022哈切森 Josh Hutcherson 饰）这两个生性好动，年幼顽皮的的家伙自己上楼去睡觉。无聊至极的二人只得自娱自乐，但二人不断的争吵让丹尼赌气离开，独自来到地下室淘宝，意外的发现了一套太空飞行棋。好奇心起的丹尼开启了游戏的开关，并邀请哥哥沃特参与到游戏中来。不断的流星雨和外星人侵袭让这场游戏越发的惊心动魄，而中途加入的神秘太空人真实的身份更是让人大跌眼镜。渐渐他们意识到，这不是一盘普通的游戏棋，而一旦开启了游戏的开关，如若不能到达游戏的终点扎图拉行星，等待他们的将是永远滞留在外太空。那么两兄弟能否在这场真实的冒险中尽释前嫌，联手度过难关？","release_date":"2005-11-06"}]
     */

    private int page;
    private int total_results;
    private int total_pages;
    private List<ResultsBean> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * popularity : 0.6
         * vote_count : 0
         * video : false
         * poster_path : /o9M6P2ByqdxaFSeCBv4uyCdZmQX.jpg
         * id : 494133
         * adult : false
         * backdrop_path : null
         * original_language : zh
         * original_title : 残废科幻
         * genre_ids : [878]
         * title : 残废科幻
         * vote_average : 0
         * overview :
         * release_date : 2013-11-29
         */

        private double popularity;
        private int vote_count;
        private boolean video;
        private String poster_path;
        private int id;
        private boolean adult;
        private Object backdrop_path;
        private String original_language;
        private String original_title;
        private String title;
        private int vote_average;
        private String overview;
        private String release_date;
        private List<Integer> genre_ids;

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isAdult() {
            return adult;
        }

        public void setAdult(boolean adult) {
            this.adult = adult;
        }

        public Object getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(Object backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVote_average() {
            return vote_average;
        }

        public void setVote_average(int vote_average) {
            this.vote_average = vote_average;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public void setRelease_date(String release_date) {
            this.release_date = release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }
    }
}
