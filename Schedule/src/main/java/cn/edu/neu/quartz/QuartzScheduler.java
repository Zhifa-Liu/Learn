package cn.edu.neu.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author 32098
 */
public class QuartzScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1. Create Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2. Create JobDetail: Bind Job
        /*
         * Why?
         * JobDetail定义的是任务数据(任务名等)，而真正的执行逻辑是存在于Job；
         * 由于任务是有可能并发执行，如果 Scheduler 直接使用Job，就会存在对同一个Job实例并发访问的问题；
         * 采用 JobDetail & Job 的方式，Scheduler 的每次执行，都会根据 JobDetail 创建一个新的 Job 实例以避免并发访问的问题；
         */
        JobDetail jobDetail1 = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("time_print_job_1", "default_job_group")
                .usingJobData("timePrinterName", "time_printer_1")
                .build();
        JobDetail jobDetail2 = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("time_print_job_2", "default_job_group")
                .usingJobData("timePrinterName", "time_printer_2")
                .build();
        // 3. Create Trigger
        /*
         * Trigger 是 Quartz 的触发器，会去通知 Scheduler 何时去执行对应的 Job
         */
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("time_print_trigger1", "default_trigger_group")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .build();
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("time_print_trigger2", "default_trigger_group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
                .build();
        // 4. 启动scheduler
        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);
        scheduler.start();
    }
}


