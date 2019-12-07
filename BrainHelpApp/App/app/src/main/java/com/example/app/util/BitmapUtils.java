package com.example.app.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import id.zelory.compressor.Compressor;

public class BitmapUtils {
    public static Bitmap cropBitmapToCircle(Bitmap bitmap) {
        Bitmap toReturn = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(toReturn);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return toReturn;
    }

    public static String bitmapToBase64(Bitmap bmp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 75, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    public static Bitmap base64ToBitmap(String base64String) {
        byte[] imgBytes = Base64.decode(base64String, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);
    }

    public static Bitmap scaleDown(Bitmap bmp, float maxImgSize, boolean filter) {
        float ratio = Math.min((float) maxImgSize / bmp.getWidth(), (float) maxImgSize / bmp.getHeight());
        int width = Math.round((float) ratio * bmp.getWidth());
        int height = Math.round((float) ratio * bmp.getHeight());

        return Bitmap.createScaledBitmap(bmp, width, height, filter);
    }

    public static Compressor getCompressor(Context ctx) {

        return new Compressor
                .Builder(ctx)
                .setMaxHeight(400)
                .setMaxWidth(400)
                .setCompressFormat(Bitmap.CompressFormat.PNG)
                .setQuality(50)
                .build();
    }
}