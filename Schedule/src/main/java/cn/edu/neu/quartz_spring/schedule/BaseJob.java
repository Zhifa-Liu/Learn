package cn.edu.neu.quartz_spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author 32098
 */
@Slf4j
public class BaseJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        beforeExecute(jobExecutionContext);
        Boolean isSuccess = actuallyExecute(jobExecutionContext);
        afterExecute(isSuccess, jobExecutionContext);
    }

    public void beforeExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    }

    public Boolean actuallyExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        return null;
    }

    public void afterExecute(Boolean isSuccess, JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (isSuccess) {
            log.info("Job Schedule Success");
        } else {
            log.error("Job Schedule Failure");
        }
    }
}
