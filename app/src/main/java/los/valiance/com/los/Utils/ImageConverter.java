package los.valiance.com.los.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static los.valiance.com.los.Helper.Constants.IMAGE_SIZE;


/**
 * To convert the image from 1 type to another
 */
public class ImageConverter {
    public static final void resizeImageMemory(Context ctx, String newPath, String oldPath) {
        Bitmap bitmap = BitmapFactory.decodeFile(oldPath);
        int totalBytes = bitmap.getByteCount();
        int maxAllowedSize = IMAGE_SIZE * 8;
        int difference = totalBytes / maxAllowedSize;
        float ratio = 1;
        if (difference > 1) {
            ratio = (float) Math.sqrt(difference);
        } else {
            ratio = 1;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = (int) ratio;
        Bitmap newImg = BitmapFactory.decodeFile(oldPath, options);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newPath);
            newImg.compress(Bitmap.CompressFormat.JPEG, 40, fos);
        } catch (FileNotFoundException e) {
            //  Toast.makeText(ctx, R.string.error_processing_image, Toast.LENGTH_LONG).show();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }

    public static String base64method(Bitmap picturePath) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        picturePath.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        //added lines
        picturePath.recycle();
        picturePath = null;
        //added lines
        byte[] b = null;
        b = baos.toByteArray();
        String b64 = Base64.encodeToString(b, Base64.DEFAULT);
        return b64;
    }

    public static Bitmap bitmap(String strBase64) {
        try {
            byte[] decodedString = Base64.decode(strBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;
        } catch (Exception e) {
            return null;
        }
    }
}
