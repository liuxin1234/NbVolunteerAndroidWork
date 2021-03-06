package com.example.renhao.wevolunteer.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Environment;
import android.util.Base64;

import com.example.renhao.wevolunteer.application.BaseApplication;
import com.orhanobut.logger.Logger;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：WeVolunteer
 * 类描述：
 * 创建人：renhao
 * 创建时间：2016/8/21 23:11
 * 修改备注：
 * 正式环境拼接的URL：http://www.nbzyz.org
 * 测试环境凭借的URL：http://115.238.150.174:5018/admin
 *
 */
public class Util {
    private static final String TAG = "Util";

    public static final String IMG_URL = "http://www.nbzyz.org";
//    public static final String IMG_URL = "http://115.238.150.174:5018";
    public static final String APK_URL = "http://admin.nbzyz.org";

    //二进制流转换为图片
    public static Bitmap getBitmapFromByte(byte[] temp) {
        if (temp != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(temp, 0, temp.length);
            return bitmap;
        } else {
            return null;
        }
    }

    //二进制流转换为图片
    public static Bitmap byteToBitmap(String img) {
        InputStream input = null;
        Bitmap bitmap = null;
        byte[] temp = Base64.decode(img.getBytes(), Base64.DEFAULT);
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (temp.length > (1024 * 512)) {
            options.inSampleSize = 2;
        }
        if (temp.length > (1024 * 1024)) {
            options.inSampleSize = 4;
        }
        if (temp.length > (1024 * 2048)) {
            options.inSampleSize = 8;
        }
        input = new ByteArrayInputStream(temp);
        SoftReference softRef = new SoftReference(BitmapFactory.decodeStream(
                input, null, options));
        bitmap = (Bitmap) softRef.get();
        if (temp != null) {
            temp = null;
        }

        try {
            if (input != null) {
                input.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bitmap;
    }


    /**
     * 获取手机的MAC地址
     *
     * @return
     */
    public static String getMac() {
        String mac = null;
        try {
            String path = "sys/class/net/eth0/address";
            FileInputStream fis_name = new FileInputStream(path);
            byte[] buffer_name = new byte[8192];
            int byteCount_name = fis_name.read(buffer_name);
            if (byteCount_name > 0) {
                mac = new String(buffer_name, 0, byteCount_name, "utf-8");
            }


            if (mac == null) {
                fis_name.close();
                return "";
            }
            fis_name.close();
        } catch (Exception io) {
            String path = "sys/class/net/wlan0/address";
            FileInputStream fis_name;
            try {
                fis_name = new FileInputStream(path);
                byte[] buffer_name = new byte[8192];
                int byteCount_name = fis_name.read(buffer_name);
                if (byteCount_name > 0) {
                    mac = new String(buffer_name, 0, byteCount_name, "utf-8");
                }
                fis_name.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        if (mac == null) {
            return "";
        } else {
            return mac.trim();
        }

    }

    /**
     * 获取当前的时间
     *
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     *
     * @param formatStr 时间日期格式
     * @return
     */
    public static String getNowDate(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr, Locale.CHINA);
        return format.format(new Date());
    }

    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSDcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPhoneNumber(String number) {
        String pattern = "0?(11|12|13|14|15|16|17|18|19)[0-9]{9}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(number);
        return m.matches();
    }

    public static boolean isEmail(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);
        return m.matches();
    }

    public static boolean isID(String id) {
        String pattern = "\\d{17}[\\d|X|x]|\\d{15}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(id);
        return m.matches();
    }

    public static String getRealUrl(String url) {
        String temp = "http://11";
        if (url != null) {
            if (url.length() > 1) {
                temp = IMG_URL + url.substring(1, url.length());
            }
        }
        return temp;
    }

    /**
     * 目前无用
     * @param url
     * @return
     */
    public static String getApkRealUrl(String url) {
        String temp = "http://11";
        if (url != null) {
            if (url.length() > 1) {
                temp = APK_URL + url.substring(1, url.length());
            }
        }
        return temp;
    }

    public static String html2String(String html) {
        html = html.replace("&nbsp;", " ");
        html = html.replace("<br />", "\n");
        return html;
    }

    public static String getAppVersion(Context context) {
        String version = "";
        PackageManager manager;
        PackageInfo info = null;
        manager = context.getPackageManager();

        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       /* info.versionCode;
        info.versionName;
        info.packageName;
        info.signatures;*/

        return version;
    }


    /**
     * 两个日期之间的所有日期
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public static List<Date> dateSplit(Date startDate, Date endDate)
            throws Exception {
        if (!startDate.before(endDate)) {
            throw new Exception("开始时间应该在结束时间之后");
        }
        Long spi = endDate.getTime() - startDate.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(endDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime()
                    - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }

    /**
     * 判断是否连接wifi
     * @param
     * @return
     */
    public static boolean isWifi(){
        ConnectivityManager con = (ConnectivityManager) BaseApplication.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = false;
        if (con != null) {
            wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        }
        return wifi;
    }

    /**
     * 判断是否连接流量
     * @param
     * @return
     */
    public static boolean isInternet (){
        ConnectivityManager con = (ConnectivityManager) BaseApplication.getContext().getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean internet = false;
        if (con != null) {
            internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        }
        return internet;
    }
}
