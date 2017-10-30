package dontcare.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by ThoNh on 10/4/2017.
 */

public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();


    public static Bitmap resizeBitmap(String imgPath, float widthScreen) {
        try {
            float dstWidth;
            float dstHeight;

            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap originBitmap = decodeFile(imgPath, options);

            float scaleFactor = (float) originBitmap.getWidth() / originBitmap.getHeight();
            if (scaleFactor > 1.0f) {
                dstWidth = widthScreen;  // ảnh rộng
                dstHeight = dstWidth / scaleFactor;
            } else {
                dstHeight = widthScreen; // ảnh đứng
                dstWidth = dstHeight * scaleFactor;
            }


            int inSampleSize = calculateInSampleSize(options, dstWidth, dstHeight);
            int orientation = getImageOrientation(imgPath);

            Matrix matrix = new Matrix();
            matrix.postRotate(orientation);


            options.inJustDecodeBounds = true;
            decodeFile(imgPath, options);
            options.inSampleSize = inSampleSize;
            options.inJustDecodeBounds = false;

            Bitmap bitmapDecode = BitmapFactory.decodeFile(imgPath, options);
            return Bitmap.createBitmap(bitmapDecode, 0, 0, bitmapDecode.getWidth(), bitmapDecode.getHeight(), matrix, true);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Lấy orient của ảnh gốc
     * @param pathImage
     * @return
     * @throws IOException
     */
    private static int getImageOrientation(String pathImage) throws IOException {
        int rotate = 0;
        ExifInterface exifInterface = new ExifInterface(pathImage);
        int imgOrientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

        switch (imgOrientation) {
            case ExifInterface.ORIENTATION_ROTATE_270:
                rotate = 270;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                rotate = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                rotate = 90;
                break;
        }
        Log.e(TAG, "imgRotate: " + rotate);
        return rotate;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, float reqWidth, float reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        Log.e(TAG, "inSampleSize:" + inSampleSize);
        return inSampleSize;
    }
}
