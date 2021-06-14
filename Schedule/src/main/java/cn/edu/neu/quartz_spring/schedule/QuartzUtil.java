package cn.edu.neu.quartz_spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Quartz 定时作业容器
 *
 * @author muhaobing
 */
@Slf4j
@Component
public class QuartzUtil {
    @Autowired
    private SchedulerFactoryBean factory;

    /**
     * 创建新的定时任务，不带使用 dataMap
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @param cron cron
     * @param jobClass jobClass
     * @throws SchedulerException SchedulerException
     */
    public void newJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, Class<? extends BaseJob> jobClass) throws SchedulerException {
        newJob(jobName, jobGroup, triggerName, triggerGroup, cron, null, jobClass);
    }

    /**
     * 创建新的定时任务，使用 dataMap
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @param cron cron
     * @param dataMap dataMap
     * @param jobClass jobClass
     * @throws SchedulerException SchedulerException
     */
    public void newJob(String jobName, String jobGroup, String triggerName, String triggerGroup, String cron, Map<String, Object> dataMap, Class<? extends BaseJob> jobClass) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, jobGroup)
                .build();
        if (dataMap != null) {
            jobDetail.getJobDataMap().putAll(dataMap);
        }
        CronTrigger trigger = newCronTrigger(triggerName, triggerGroup, cron);
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("xxxxxx");
    }

    /**
     * 修改定时任务的 Cron 表达式
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @param newCron newCron
     * @throws SchedulerException SchedulerException
     */
    public void modifyJob(String triggerName, String triggerGroup, String newCron) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        if (trigger != null) {
            String oldCron = trigger.getCronExpression();
            if (!oldCron.equalsIgnoreCase(newCron)) {
                trigger = newCronTrigger(triggerName, triggerGroup, newCron);
                scheduler.rescheduleJob(TriggerKey.triggerKey(triggerName, triggerGroup), trigger);
            }
        } else {
            log.error("找不到指定触发器@triggerName={},@triggerGroup={}", triggerName, triggerGroup);
            throw new SchedulerException("找不到指定触发器");
        }
    }

    /**
     * 停止调度并移除指定定时任务
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @throws SchedulerException SchedulerException
     */
    public void removeJob(String jobName, String jobGroup, String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.unscheduleJob(TriggerKey.triggerKey(triggerName, triggerGroup));
        scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));
    }

    /**
     * 通过 JobDetail 暂停相关定时任务的调度
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @throws SchedulerException SchedulerException
     */
    public void pauseScheduleByJobDetail(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
        if (jobDetail == null) {
            log.error("找不到指定任务@jobName={},@jobGroup={}", jobName, jobGroup);
            throw new SchedulerException("找不到指定任务");
        } else {
            scheduler.pauseJob(JobKey.jobKey(jobName, jobGroup));
        }
    }

    /**
     * 通过 Trigger 暂停相关定时任务的调度
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @throws SchedulerException SchedulerException
     */
    public void pauseScheduleByTrigger(String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        if (trigger == null) {
            log.error("找不到指定触发器@triggerName={},@triggerGroup={}", triggerName, triggerGroup);
            throw new SchedulerException("找不到指定触发器");
        } else {
            scheduler.pauseTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        }
    }

    /**
     * 通过 JobDetail 恢复相关定时任务的调度
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @throws SchedulerException SchedulerException
     */
    public void resumeScheduleByJobDetail(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
        if (jobDetail == null) {
            log.error("找不到指定任务@jobName={},@jobGroup={}", jobName, jobGroup);
            throw new SchedulerException("找不到指定任务");
        } else {
            scheduler.resumeJob(JobKey.jobKey(jobName, jobGroup));
        }
    }

    /**
     * 通过 Trigger 恢复相关定时任务的调度
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @throws SchedulerException SchedulerException
     */
    public void resumeScheduleByTrigger(String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        if (trigger == null) {
            log.error("找不到指定触发器@triggerName={},@triggerGroup={}", triggerName, triggerGroup);
            throw new SchedulerException("找不到指定触发器");
        } else {
            scheduler.resumeTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        }
    }

    /**
     * 获取指定任务的 JobDataMap
     *
     * @param jobName jobName
     * @param jobGroup jobGroup
     * @return JobDataMap
     * @throws SchedulerException SchedulerException
     */
    public Map<String, Object> getJobDataMap(String jobName, String jobGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobName, jobGroup));
        if (jobDetail == null) {
            log.error("找不到指定任务@jobName={},@jobGroup={}", jobName, jobGroup);
            throw new SchedulerException("找不到指定触发器");
        } else {
            return jobDetail.getJobDataMap().getWrappedMap();
        }
    }

    /**
     * 获取指定触发器的调度状态
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @return
     * @throws SchedulerException
     */
    public int getScheduleState(String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        Trigger.TriggerState state = scheduler.getTriggerState(TriggerKey.triggerKey(triggerName, triggerGroup));
        switch (state) {
            case NONE:
                return 0;
            case NORMAL:
                return 1;
            case PAUSED:
                return 2;
            case COMPLETE:
                return 3;
            case ERROR:
                return 4;
            case BLOCKED:
                return 5;
            default:
                return -1;
        }
    }

    /**
     * 获取指定触发器的 Cron 表达式
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @return Cron Exp
     * @throws SchedulerException SchedulerException
     */
    public String getCronExpression(String triggerName, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(TriggerKey.triggerKey(triggerName, triggerGroup));
        if (trigger == null) {
            log.error("找不到指定触发器@triggerName={},@triggerGroup={}", triggerName, triggerGroup);
            throw new SchedulerException("找不到指定触发器");
        } else {
            return trigger.getCronExpression();
        }
    }

    /**
     * 私有方法，用于创建 CronTrigger
     *
     * @param triggerName triggerName
     * @param triggerGroup triggerGroup
     * @param cron cron
     * @return CronTrigger
     */
    private CronTrigger newCronTrigger(String triggerName, String triggerGroup, String cron) {
        return TriggerBuilder.newTrigger()
                .withIdentity(triggerName, triggerGroup)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
    }
}
