package com.example.api.net;

import android.text.TextUtils;

import com.example.api.utils.EncryptUtil;
import com.example.model.AccessTokenBO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 项目名称：WeVolunteer
 * 类描述：Http连接引擎类，使用okhttp与服务器进行交互
 * 创建人：renhao
 * 创建时间：2016/8/2 13:45
 * 修改备注：
 * 测试环境域名：115.238.150.174:5019
 * 正式环境域名：webapi.nbzyz.org
 */
public class HttpEngine {
    private static final String TAG = "HttpEngine";

    public static final String APP_ID = "5b93977d-42a7-46fe-9b69-91d9d0142fb6";
    public static final String APP_SECRET = "MpUusGgG7+6XDiVtWjZY3Q==";
    public static final String BASIC = "NWI5Mzk3N2QtNDJhNy00NmZlLTliNjktOTFkOWQwMTQyZmI2Ok1wVXVzR2dHNys2WERpVnRXalpZM1E9PQ==";
    public static final String SERVER_URL = "http://115.238.150.174:5019/api/";
    public static final String ACCESSTOKEN_URL = "http://115.238.150.174:5019/token";

    public static final int CONNECT_TIMEOUT = 20000;
    public static final int READ_TIMEOUT = 20000;
    public static final int WRITE_TIMEOUT = 20000;

    private volatile static HttpEngine httpEngine = null;
    private OkHttpClient client = null;

    public OkHttpClient getClient() {
        return client;
    }

    private HttpEngine(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            //这里设置请求超时时间
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            client = okHttpClientBuilder
//                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//                    .writeTimeout(WRITE_TIMEOUT,TimeUnit.SECONDS)
//                    .connectTimeout(CONNECT_TIMEOUT,TimeUnit.SECONDS)
                    .build();
        } else {
            client = okHttpClient;
        }
    }

    /**
     * GetInstance()的用法在写程序库代码时，有时有一个类需要被所有的其它类使用，但又要求这个类只能实例化一次
     * 这是个服务类，定义一次，其它类使用同一个这个类的实例   防止重复实例化
     *同步原语－－synchronized，当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
     * @return
     */
    public static HttpEngine getInstance() {
        if (httpEngine == null) {
            synchronized (HttpEngine.class) {
                if (httpEngine == null) {
                    httpEngine = new HttpEngine(null);
                    return httpEngine;    //有人提议在此处进行一次返回
                }
                return httpEngine;    //也有人提议在此处进行一次返回
            }
        }
        return httpEngine;
    }

    /**
     * 获取accesstoken 如果用户名和密码为空则为应用访问，否则为用户访问
     *
     * @param username 用户名 可以为空
     * @param password 密码 可以为空
     * @return accessToken
     * @throws IOException
     */
    public AccessTokenBO getAccessToken(String username, String password) throws IOException {
        Logger.v(TAG, "get token  \n" + username + "\n" + password);
        FormBody.Builder builder = new FormBody.Builder();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            builder.add("grant_type", "password");
            builder.add("username", username);
            builder.add("password", EncryptUtil.makeMD5(password));
            builder.add("usertype","1,3");
        } else {
            builder.add("grant_type", "client_credentials");
        }
        RequestBody body = builder.build();

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Basic " + BASIC)
                .url(ACCESSTOKEN_URL)
                .post(body)
                .build();
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            result = result.replace(".issued", "issued").replace(".expires", "expires");//加.gson识别不了，所以进行替换
            Gson gson = new Gson();
            AccessTokenBO accessTokenBO = null;
            try {
                accessTokenBO = gson.fromJson(result, AccessTokenBO.class);
            } catch (JsonSyntaxException e) {
                Logger.e(TAG, "access token json error");
                e.printStackTrace();
            }
            return accessTokenBO;
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }


    /**
     * 刷新票据方法
     *
     * @param refreshToken 刷新票据
     * @return accessToken
     * @throws IOException
     */
    public AccessTokenBO getAccessToken(String refreshToken) throws IOException {
        Logger.v(TAG, "refresh token  \n" + refreshToken);

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("grant_type", "refresh_token");
        builder.add("refresh_token", refreshToken);
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Basic " + BASIC)
                .url(ACCESSTOKEN_URL)
                .post(body)
                .build();
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            result = result.replace(".issued", "issued").replace(".expires", "expires");
            Gson gson = new Gson();
            AccessTokenBO accessTokenBO = null;
            try {
                accessTokenBO = gson.fromJson(result, AccessTokenBO.class);
            } catch (JsonSyntaxException e) {
                Logger.e(TAG, "access token json error");
                e.printStackTrace();
            }
            return accessTokenBO;
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }


    /**
     * Post方法
     *
     * @param params       Json
     * @param serverAction 接口
     * @param typeOfT      返回的类型
     * @param accessToken  票据
     * @param <T>          返回参数的泛型
     * @return
     */
    public <T> T postApiHandler(String params, String serverAction,
                                Type typeOfT, String accessToken) throws IOException {
        Logger.json(TAG, params);
        Logger.v(TAG, "serverAction  \n" + serverAction + "\n");

        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), params);
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Bearer " + accessToken)
                .url(SERVER_URL + serverAction)
                .post(body)
                .build();
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            Gson gson = new Gson();
            //验证失败了怎么办?
            try {
                return gson.fromJson(result, typeOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }


    /**
     * Get方法
     *
     * @param params       请求参数
     * @param serverAction 端口
     * @param typeOfT      返回Date类型
     * @param accessToken  票据
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T getApiHandler(Map<String, String> params, String serverAction, Type typeOfT, String accessToken) throws IOException {

        FormBody.Builder builder = new FormBody.Builder();
        if (params != null)
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Bearer " + accessToken)
                .url(SERVER_URL + serverAction)
                .get()
                .put(body)
                .build();
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            Gson gson = new Gson();
            //验证失败了怎么办
            try {
                return gson.fromJson(result, typeOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }

    /**
     * Get方法
     *
     * @param params       请求参数
     * @param serverAction 端口
     * @param typeOfT      返回Date类型
     * @param accessToken  票据
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T getApiHandler(List<String> params, String serverAction, Type typeOfT, String accessToken) throws IOException {

        String paramsString = "";
        if (params != null)
            for (int i = 0; i < params.size(); i++) {
                paramsString = paramsString + "/" + params.get(i);
            }
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Bearer " + accessToken)
                .url(SERVER_URL + serverAction + paramsString)
                .get()
                .build();
        Logger.v(TAG, SERVER_URL + serverAction + paramsString);
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            Gson gson = new Gson();
            //验证失败了怎么办
            try {
                return gson.fromJson(result, typeOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }

    /**
     * 传递double类型的get方法
     * Get方法
     *
     * @param params       请求参数
     * @param serverAction 端口
     * @param typeOfT      返回Date类型
     * @param accessToken  票据
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T getApiHandler(Double params, String serverAction, Type typeOfT, String accessToken) throws IOException {

        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Bearer " + accessToken)
                .url(SERVER_URL + serverAction /*+ "?timelength="*/ + "/" + params)
                .get()
                .build();
        Logger.v(TAG, SERVER_URL + serverAction + params);
        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            Gson gson = new Gson();
            //验证失败了怎么办
            try {
                return gson.fromJson(result, typeOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }

    /**
     * 修改密码专用post 方法
     *
     * @param params
     * @param serverAction
     * @param typeOfT
     * @param accessToken
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T PSWDpostApiHandler(List<String> params, String serverAction, Type typeOfT, String accessToken) throws IOException {

        String paramsString = "";
        if (params != null)
            for (int i = 0; i < params.size(); i++) {
                paramsString = paramsString + "/" + params.get(i);
            }
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{}");
        Request request = new Request.Builder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "keep-alive")
                .header("Authorization", "Bearer " + accessToken)
                .url(SERVER_URL + serverAction + paramsString)
                .post(body)
                .build();

        Logger.v(TAG, "serverAction  \n" + serverAction + "\n" + paramsString);

        Response response = HttpEngine.getInstance().getClient().newCall(request).execute();
        if (response.isSuccessful()) {
            String result = response.body().string();
            Logger.json(TAG, result);
            Gson gson = new Gson();
            //验证失败了怎么办
            try {
                return gson.fromJson(result, typeOfT);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Logger.e(TAG, "connent error  \n" +
                    response.code() + "\n" +
                    response.message() + "\n" +
                    response.body().string());
        }
        return null;
    }


}
