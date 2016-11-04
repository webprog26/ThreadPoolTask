package com.example.webprog26.threadspooltask;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by webprog26 on 03.11.2016.
 */

class AssetsReader {

    private static final String TAG = "AssetsReader";


    private AssetManager mAssetManager;

    AssetsReader(Activity activity) {
        mAssetManager = activity.getAssets();
    }

    /**
     * Returns {@link ArrayList<String>} of URLs
     * @param fileName {@link String}
     * @return fileName {@link String}
     */
    ArrayList<String> getURLsList(String fileName)
    {
        ArrayList<String> urlsList = new ArrayList<>();

        BufferedReader reader = null;
        InputStream inputStream = null;
        try{
            inputStream = mAssetManager.open(fileName);
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            while((line = reader.readLine()) != null)
            {
                urlsList.add(line);
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        } finally {

            if(inputStream != null)
            {
                try {
                    inputStream.close();
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }

            if(reader != null)
            {
                try {
                    reader.close();
                } catch (IOException ioe)
                {
                    ioe.printStackTrace();
                }
            }
        }

        return urlsList;
    }
}
