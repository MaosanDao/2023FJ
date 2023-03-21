package com.vangelis.service.keepalive;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

import com.vangelis.support.util.FjLogUtil;

/**
 * Function：JobScheduler保活
 * Created on 2023/3/21.
 * Comment：
 *      使用jobScheduler保活。
 *      经过测试，貌似也不能正确保活
 *
 * @author Wangpei
 */
public class KeepAliveJobScheduler extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        FjLogUtil.getInstance().d("KeepAliveJobScheduler onStartJob");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        FjLogUtil.getInstance().d("KeepAliveJobScheduler onStopJob");
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        FjLogUtil.getInstance().d("KeepAliveJobScheduler onDestroy");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FjLogUtil.getInstance().d("KeepAliveJobScheduler onCreate");
        startJobSchedule();
    }

    private void startJobSchedule() {
        try {
            JobInfo.Builder builder = new JobInfo.Builder(1, new ComponentName(getPackageName(), KeepAliveJobScheduler.class.getName()));
            builder.setPeriodic(1000 * 60 * 15);//设置重复时间间隔
            builder.setRequiresCharging(true);//设置触发条件
            JobScheduler jobScheduler = (JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE);
            jobScheduler.schedule(builder.build());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}