package com.guw.gubook.Action;

import android.app.job.JobParameters;
import android.util.Log;

public class JobService extends android.app.job.JobService {
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("TAG", "Job Started");
        doBackgroundWork(params);

        return false;
    }

    private void doBackgroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    Log.i("Tag", "run: " + 1);

                    if (jobCancelled) {
                        return;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Log.i("TAG", "Job Finished");
                jobFinished(params, false);
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("TAG", "Job Cancelled before completion");
        jobCancelled = false;
        return false;
    }
}

