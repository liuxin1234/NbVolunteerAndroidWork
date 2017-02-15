package com.example.renhao.wevolunteer.adapter;
/*
 *
 * Created by Ge on 2016/8/22  11:08.
 *
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.renhao.wevolunteer.R;

import java.io.IOException;
import java.util.List;

public class List_major_pic_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<String> list;
    private Bitmap bitmap;

    public List_major_pic_Adapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void getpic(List<String> data) {
        list = data;
    }

    @Override
    public int getCount() {
        if(list!=null)
        return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public final class ViewHolder {
        public ImageView certificate_pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.v("MyListViewBase", "getView " + position + " " + convertView);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_major_ability_pic, null);//根据布局产生一个view
            holder = new ViewHolder();
            holder.certificate_pic = (ImageView) convertView.findViewById(R.id.image_major_item_pic);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
        //临时bitmap，压缩
        bitmap = decodeSampledBitmapFromFile(list.get(position), 280, 160);
        //判断创建完成的bitmap与原图相比是否旋转，若旋转则恢复
        bitmap=reviewPicRotate(bitmap, list.get(position));
        holder.certificate_pic.setImageBitmap(bitmap);

        return convertView;
    }

    //大图片的优化显示
    //通过目标要求的宽和高，来计算出inSampleSize的代码
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    //先要通过设置inJustDecodeBounds为true，解析一次，获取到options的图片原始宽高。
    // 然后计算出inSampleSize之后，把inJustDecodeBounds置为false，再解析一次
    public static Bitmap decodeSampledBitmapFromFile(String path,
                                                     int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    /**
     * 获取图片文件的信息，是否旋转了90度，如果是则反转
     *
     * @param bitmap 需要旋转的图片
     * @param path   图片的路径
     */
    public static Bitmap reviewPicRotate(Bitmap bitmap, String path) {
        int degree = getPicRotate(path);
        if (degree != 0) {
            Matrix m = new Matrix();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            m.setRotate(degree); // 旋转angle度
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
        }
        return bitmap;
    }

    /**
     * 读取图片文件旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片旋转的角度
     */
    public static int getPicRotate(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
