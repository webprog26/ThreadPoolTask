package com.example.webprog26.threadspooltask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by webprog26 on 03.11.2016.
 */

public class BitmapUtils {

    public static Bitmap downloadRemoteImage(String imageURL){
        try {
            URL url = new URL(imageURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
