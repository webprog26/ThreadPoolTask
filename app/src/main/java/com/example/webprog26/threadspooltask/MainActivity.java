package com.example.webprog26.threadspooltask;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity_TAG";

    private static final int NUMBER_OF_THREADS_IN_POOL = 5;
    private static final int TIMEOUT_IN_SECONDS = 1;

    private GridView mGridView;
    private ArrayList<Bitmap> mBitmaps;
    private GridItemsAdapter mGridItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView) findViewById(R.id.gridView);

        String urlsFileName = getResources().getString(R.string.urls_file);
        AssetsReader assetsReader = new AssetsReader(this);
        ArrayList<String> urls= assetsReader.getURLsList(urlsFileName);
        mBitmaps = new ArrayList<>();

        DownloadCompletionService ecs = new DownloadCompletionService(Executors.newFixedThreadPool(NUMBER_OF_THREADS_IN_POOL));
        new ConsumerThread(ecs).start();

        if(urls != null){
            for(String url: urls){
                ecs.submit(new ImageDownloadTask(url));
            }
        }
        ecs.shutdown();
        mGridItemsAdapter = new GridItemsAdapter(mBitmaps, this);
        mGridView.setAdapter(mGridItemsAdapter);
    }

    private class ConsumerThread extends Thread{

        private DownloadCompletionService mEcs;

        public ConsumerThread(DownloadCompletionService ecs) {
            this.mEcs = ecs;
        }

        @Override
        public void run() {
            super.run();
            try {
                while (!mEcs.isTerminated())
                {
                    Future<Bitmap> future = mEcs.poll(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
                    if(future != null){
                        addImage(future.get());
                    }
                }
            } catch (InterruptedException ie){
                ie.printStackTrace();
            } catch (ExecutionException ee){
                ee.printStackTrace();
            }
        }
    }

    private void addImage(final Bitmap bitmap){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBitmaps.add(bitmap);
                mGridItemsAdapter.notifyDataSetChanged();
            }
        });
    }
}
