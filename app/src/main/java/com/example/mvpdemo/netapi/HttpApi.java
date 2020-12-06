package com.example.mvpdemo.netapi;

import com.example.mvpdemo.bean.WelfareBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface HttpApi {
    @GET("toutiao/index")
    Observable<ResponseBody> getNewsDataForQuery(@Query("type") String type, @Query("key") String key);

    /**
     * 文件下载
     */
    @GET()
    @Streaming
//使用Streaming 方式 Retrofit 不会一次性将ResponseBody 读取进入内存，否则文件很多容易OOM
    Call<ResponseBody> downloadFile(@Url String fileUrl);//返回值使用 ResponseBody 之后会对ResponseBody 进行读取

    @GET()
    @Streaming
    Observable<ResponseBody> downloadFileWithUrlSync(@Url String fileUrl);

    /**
     * category 可接受参数 All(所有分类) | Article | GanHuo | Girl
     * type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl ...，即分类API返回的类型数据
     * count: [10, 50]
     * page: >=1
     *
     */
    @GET("api/v2/data/category/Girl/type/Girl/page/{page}/count/{count}")
    Observable<WelfareBean> getImagesData(@Path("page") int page, @Path("count") int count);
}
