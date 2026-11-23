package com.example.app.util;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ImagePickerUtils {

    public static String finalImageName = null;
    public static final int PICK_FOTO_FROM_AVATAR = 22;
    public static Intent getPickImageIntent(Context ctx){
        Intent chooserIntent = null;
        List<Intent> intentList = new ArrayList<>();

        try {
            File fotoFile = createImageFile(ctx);
            if (fotoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(ctx, "com.example.app", fotoFile);
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                takePhotoIntent.putExtra("return-data", true);
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                intentList = addIntentsToList(ctx, intentList, takePhotoIntent);
            }
            Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intentList = addIntentsToList(ctx, intentList, pickIntent);

            if (intentList.size() > 0) {
                chooserIntent = Intent.createChooser(intentList.remove(intentList.size() - 1), "texto");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toArray(new Parcelable[]{}));
            }
            return chooserIntent;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return chooserIntent;
    }

    private static List<Intent> addIntentsToList(Context ctx, List<Intent> list, Intent intent) {
        List<ResolveInfo> resInfo = ctx.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resInfo) {
            String packageName = resolveInfo.activityInfo.packageName;
            Intent targetedIntent = new Intent(intent);
            targetedIntent.setPackage(packageName);
            list.add(targetedIntent);
        }

        return list;
    }

    private static File createImageFile(Context ctx) throws IOException {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        finalImageName = image.getName();
        return image;
    }

    public static File parseReturningDataToFile(Intent data, Context ctx) throws IOException {
        File file;
        if (data == null) {
            file = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_PICTURES), ImagePickerUtils.finalImageName);
        } else {
            InputStream inputStream = ctx.getContentResolver().openInputStream(data.getData());
            file = File
                    .createTempFile(
                            UUID.randomUUID().toString(),
                            "jpg",
                            ctx.getExternalCacheDir());

            copyInputStreamToFile(inputStream, file);
        }

        return file;
    }

    private static void copyInputStreamToFile(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}