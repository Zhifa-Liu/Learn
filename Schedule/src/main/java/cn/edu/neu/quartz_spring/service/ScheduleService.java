package cn.edu.neu.quartz_spring.service;

import cn.edu.neu.quartz_spring.schedule.PrintTimeJob;
import cn.edu.neu.quartz_spring.schedule.QuartzUtil;
import cn.edu.neu.quartz_spring.schedule.SpiderJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 32098
 */
@Service
public class ScheduleService {
    @Autowired
    private QuartzUtil quartzUtil;

    private final String JOB_GROUP = "default_job_group";
    private final String TRIGGER_GROUP = "default_trigger_group";

    public Boolean newTimePrintJob(String name, String cron) {
        try {
            quartzUtil.newJob(name, JOB_GROUP, name, TRIGGER_GROUP, cron, PrintTimeJob.class);
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }

    public Boolean newSpiderJob(String cron) {
        try {
            Map<String, Object> shellPathToutiao = new HashMap<>(1);
            shellPathToutiao.put("pythonShellPath", "D:\\Code\\Python\\SpiderModule\\toutiao_main.py");
            quartzUtil.newJob("toutiao_spider_job", JOB_GROUP, "toutiao_spider_trigger", TRIGGER_GROUP, cron, shellPathToutiao, SpiderJob.class);
            Map<String, Object> shellPathWeibo = new HashMap<>(1);
            shellPathToutiao.put("pythonShellPath", "D:\\Code\\Python\\SpiderModule\\weibo_main.py");
            quartzUtil.newJob("weibo_spider_job", JOB_GROUP, "weibo_spider_trigger", TRIGGER_GROUP, cron, shellPathWeibo, SpiderJob.class);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean modifyJob(String name, String newCron) {
        try {
            quartzUtil.modifyJob(name, TRIGGER_GROUP, newCron);
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }

    public Boolean pauseJob(String name) {
        try {
            quartzUtil.pauseScheduleByJobDetail(name, JOB_GROUP);
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }

    public Boolean resumeJob(String name) {
        try {
            quartzUtil.resumeScheduleByJobDetail(name, JOB_GROUP);
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }

    public Boolean removeJob(String name) {
        try {
            quartzUtil.removeJob(name, JOB_GROUP, name, TRIGGER_GROUP);
            return true;
        } catch (SchedulerException e) {
            return false;
        }
    }
}
