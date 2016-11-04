package com.example.webprog26.threadspooltask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by webprog26 on 03.11.2016.
 */

class BitmapUtils {

    private static final String TAG = "BitmapUtils_TAG";
    private static final int IN_SAMPLE_SIZE = 4;

    /**
     * Loads Bitmap from given URL
     * @param imageURL {@link String}
     * @return image {@link Bitmap}
     */
    static Bitmap downloadRemoteImage(String imageURL){
            try {
                URL url = new URL(imageURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inScaled = true;
                options.inSampleSize = IN_SAMPLE_SIZE;
                return BitmapFactory.decodeStream(input, null, options);
            } catch (IOException ioe) {
                ioe.printStackTrace();
                return null;
            }
    }
}
