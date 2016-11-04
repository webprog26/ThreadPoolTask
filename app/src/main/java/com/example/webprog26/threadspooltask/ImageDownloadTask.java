package com.example.webprog26.threadspooltask;

import android.graphics.Bitmap;

import java.util.concurrent.Callable;

/**
 * Created by webprog26 on 03.11.2016.
 */

class ImageDownloadTask implements Callable<Bitmap> {

    private String mImageUrl;

        ImageDownloadTask(String mImageUrl) {
            this.mImageUrl = mImageUrl;
        }

        @Override
        public Bitmap call() throws Exception {
            return BitmapUtils.downloadRemoteImage(mImageUrl);
        }
}
