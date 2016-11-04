package com.example.webprog26.threadspooltask;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * Created by webprog26 on 03.11.2016.
 */

class DownloadCompletionService extends ExecutorCompletionService {

    private ExecutorService mExecutorService;

    DownloadCompletionService(ExecutorService executorService) {
        super(executorService);
        this.mExecutorService = executorService;
    }

    /**
     * Stops {@link ExecutorCompletionService}
     */
    void shutdown(){
        mExecutorService.shutdown();
    }

    /**
     * Checks is {@link ExecutorCompletionService} terminated
     * @return isTerminated boolean
     */
    boolean isTerminated(){
        return mExecutorService.isTerminated();
    }
}
