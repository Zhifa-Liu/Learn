package cn.edu.neu.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 32098
 */
public class SpiderScheduler {
    public static void main(String[] args) throws SchedulerException {
        // 1. Create Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2. Create JobDetail: Bind Job
        JobDetail jobDetail1 = JobBuilder.newJob(SpiderJob.class)
                .withIdentity("spider_job_toutiao", "default_job_group")
                .usingJobData("pythonShellPath", "D:\\Code\\Python\\SpiderModule\\toutiao_main.py")
                .build();
        JobDetail jobDetail2 = JobBuilder.newJob(SpiderJob.class)
                .withIdentity("spider_job_weibo", "default_job_group")
                .usingJobData("pythonShellPath", "D:\\Code\\Python\\SpiderModule\\weibo_main.py")
                .build();
        // 3. Create Trigger
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("spider_trigger_toutiao", "default_trigger_group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(60).repeatForever())
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("spider_trigger_weibo", "default_trigger_group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
                .build();
        // 4. 启动scheduler
        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);
        scheduler.start();
    }
}
