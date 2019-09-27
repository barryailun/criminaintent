package com.taihao.criminaintent.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * @author chenwei
 * @date 2019/9/27
 * description：图片工具
 */
public class PictureUtils {

    /**
     *
     * @param path
     * @param destWidth
     * @param destHight
     * @return
     */
    public static Bitmap getScaledBitmap(String path, int destWidth, int destHight) {
        // 读取磁盘上图像的尺寸
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        float srcWidth = options.outWidth;
        float srcHight = options.outHeight;
        // 计算要缩小多少，inSampleSize值很关键。它决定着缩略图像素的大小。
        // 假设这个值是1的话，就表明缩略图和原始照片的水平像素大小一样。如果是2的话，
        // 它们的水平像素比就是1∶2。因此，inSampleSize值为2时，缩略图的像素数就是原始文件的四分之一
        int inSampleSize = 1;
        if (srcWidth > destWidth || srcHight > destHight) {
            float heightScale = srcHight / destHight;
            float widthScale = srcWidth / destWidth;

            inSampleSize = Math.round(heightScale > widthScale ? heightScale :
                    widthScale);
        }
        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        // 读入并创建最终位图
        return BitmapFactory.decodeFile(path, options);
    }

    public static Bitmap getScaledBitmap(String path, Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay()
                .getSize(size);
        return getScaledBitmap(path, size.x, size.y);
    }
}
