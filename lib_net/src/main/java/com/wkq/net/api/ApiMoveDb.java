package com.wkq.net.api;

import com.wkq.net.BaseInfo;
import com.wkq.net.model.MoveDbMoveDetailInfo;
import com.wkq.net.model.MoveDbMoveImagesInfo;
import com.wkq.net.model.MoveDbNowPlayingInfo;
import com.wkq.net.model.MoveDbReviewsInfo;
import com.wkq.net.model.MoveDbSimilarInfo;
import com.wkq.net.model.MoveDataInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.adapter.rxjava2.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 作者: 吴奎庆
 * <p>
 * 时间: 2019/12/24
 * <p>
 * 简介:
 */
public interface ApiMoveDb {

//    top_rated?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1

    //即将上映的大片
    //https://api.themoviedb.org/3/movie/upcoming?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1

    /**
     * 即将上映
     *
     * @param requestMap
     * @return
     */

    @GET("movie/upcoming")
    Observable<Result<BaseInfo<MoveDataInfo>>> getUpComing(@QueryMap Map<String, String> requestMap);

    /**
     * 正在热播
     *
     * @param requestMap
     * @return page=1
     * https://api.themoviedb.org/3/movie/now_playing?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1
     */

    @GET("movie/now_playing")
    Observable<Result<BaseInfo<MoveDataInfo>>> nowPlaying(@QueryMap Map<String, String> requestMap);

    /**
     * 电影详细信息
     * <p>
     * similar_movies  相似电影  关键字 alternative_titles  片花 images  credits  演员列表
     * similar_movies,alternative_titles,images   参数名是 append_to_response
     *
     * @param movieId
     * @return https://api.themoviedb.org/3/movie/51533?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&append_to_response=similar_movies%2Calternative_titles%2Cimages
     */
    @GET("movie/{id}")
    Observable<Result<BaseInfo<MoveDbMoveDetailInfo>>> getMovieDetail(@Path("id") String movieId, @QueryMap Map<String, String> requestMap);

    /**
     * top 排行榜
     *
     * @param requestMap
     * @return https://api.themoviedb.org/3/movie/top_rated?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1
     */
    @GET("movie/top_rated")
    Observable<Result<BaseInfo<MoveDataInfo>>> getTopRated(@QueryMap Map<String, String> requestMap);


    /**
     * 搜索电影
     *
     * @param requestMap
     * @return query  查询条件
     * year  查询的时间
     * https://api.themoviedb.org/3/search/movie?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&query=%E7%A7%91%E5%B9%BB&page=1&include_adult=false
     */

    @GET("search/movie")
    Observable<Result<BaseInfo<MoveDataInfo>>> searchMovies(@QueryMap Map<String, String> requestMap);


    /**
     * 最受欢迎的电影
     *
     * @param requestMap
     * @return query  查询条件
     * year  查询的时间
     * 参数 page =1
     * https://api.themoviedb.org/3/movie/popular?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1&region=CN
     */

    @GET("movie/popular")
    Observable<Result<BaseInfo<MoveDataInfo>>> getPopular(@QueryMap Map<String, String> requestMap);


    /**
     * 剧照
     *
     * @param movieId
     * @return query  查询条件
     * year  查询的时间
     * https://api.themoviedb.org/3/movie/51533/images?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh
     */
    @GET("movie/{id}/images")
    Observable<Result<BaseInfo<MoveDbMoveImagesInfo>>> getMoveImages(@Path("id") String movieId);

    /**
     * 获取评论的接口
     *
     * @param movieId
     * @return page  页数   返回数据没有中文
     * <p>
     * https://api.themoviedb.org/3/movie/278/reviews?api_key=9d16633c17134f489c1f643b3b4e274d&page=1
     */
    @GET("movie/{id}/reviews")
    Observable<Result<BaseInfo<MoveDbReviewsInfo>>> getReviews(@Path("id") String movieId, @QueryMap Map<String, String> requestMap);

    /**
     * 获取相似电影
     *
     * @param movieId
     * @return page  页数
     * <p>
     * https://api.themoviedb.org/3/movie/51533/similar?api_key=9d16633c17134f489c1f643b3b4e274d&language=zh&page=1
     */
    @GET("movie/{id}/reviews")
    Observable<Result<BaseInfo<MoveDbSimilarInfo>>> getSimilar(@Path("id") String movieId, @QueryMap Map<String, String> requestMap);


}
