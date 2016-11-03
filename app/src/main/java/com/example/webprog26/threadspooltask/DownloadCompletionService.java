package com.example.webprog26.threadspooltask;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * Created by webprog26 on 03.11.2016.
 */

public class DownloadCompletionService extends ExecutorCompletionService {

    private ExecutorService mExecutorService;

    public DownloadCompletionService(ExecutorService executorService) {
        super(executorService);
        this.mExecutorService = executorService;
    }

    public void shutdown(){
        mExecutorService.shutdown();
    }

    public boolean isTerminated(){
        return mExecutorService.isTerminated();
    }
}
