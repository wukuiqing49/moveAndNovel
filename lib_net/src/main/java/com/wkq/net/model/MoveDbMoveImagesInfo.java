package com.wkq.net.model;

import androidx.databinding.BaseObservable;

import java.util.List;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/26
 * <p>
 * 简介:  剧照
 */
public class MoveDbMoveImagesInfo extends BaseObservable {

    /**
     * id : 51533
     * backdrops : []
     * posters : [{"aspect_ratio":0.6811989100817438,"file_path":"/zlRqquOy2WRTaKO8pNd7vaUixyM.jpg","height":1468,"iso_639_1":"zh","vote_average":5.348,"vote_count":2,"width":1000},{"aspect_ratio":0.7334801762114538,"file_path":"/nBmHoBzrwdrIsO6ZFPpmQRnoiyH.jpg","height":908,"iso_639_1":"zh","vote_average":5.326,"vote_count":2,"width":666},{"aspect_ratio":0.6796941376380629,"file_path":"/ecMIG9AmRj86Tp2c2YXJ4PcrMMN.jpg","height":1177,"iso_639_1":"zh","vote_average":5.260416666666666,"vote_count":1,"width":800},{"aspect_ratio":0.683076923076923,"file_path":"/L6IBcD0HttHNvy8r2SnZcrdAwQ.jpg","height":975,"iso_639_1":"zh","vote_average":5.238095238095238,"vote_count":1,"width":666},{"aspect_ratio":0.7125700560448359,"file_path":"/tK0nEUsiPX3bJwXO8hqYWW0qztW.jpg","height":1249,"iso_639_1":"zh","vote_average":0,"vote_count":0,"width":890},{"aspect_ratio":0.7121057985757884,"file_path":"/o6unceFcF5eMOhZ6QUa8CemW0e2.jpg","height":983,"iso_639_1":"zh","vote_average":0,"vote_count":0,"width":700},{"aspect_ratio":0.6666666666666666,"file_path":"/o0gMCNbw1hRUZUhQs58ixvrByzV.jpg","height":3000,"iso_639_1":"zh","vote_average":0,"vote_count":0,"width":2000},{"aspect_ratio":0.6666666666666666,"file_path":"/vUvrjRRmC1BeI8QyMaRBdtIqHHn.jpg","height":2250,"iso_639_1":"zh","vote_average":0,"vote_count":0,"width":1500}]
     */

    private int id;
    private List<?> backdrops;
    private List<PostersBean> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<?> backdrops) {
        this.backdrops = backdrops;
    }

    public List<PostersBean> getPosters() {
        return posters;
    }

    public void setPosters(List<PostersBean> posters) {
        this.posters = posters;
    }

    public static class PostersBean {
        /**
         * aspect_ratio : 0.6811989100817438
         * file_path : /zlRqquOy2WRTaKO8pNd7vaUixyM.jpg
         * height : 1468
         * iso_639_1 : zh
         * vote_average : 5.348
         * vote_count : 2
         * width : 1000
         */

        private double aspect_ratio;
        private String file_path;
        private int height;
        private String iso_639_1;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
