package cn.edu.neu.quartz_spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 32098
 */
@Slf4j
public class PrintTimeJob extends BaseJob{
    @Override
    public Boolean actuallyExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("[{}]——{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), "");
        return true;
    }
}
