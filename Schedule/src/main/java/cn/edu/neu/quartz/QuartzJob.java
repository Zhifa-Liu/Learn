package cn.edu.neu.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 32098
 */
public class QuartzJob implements Job {
    String timePrinterName;

    public void setTimePrinterName(String timePrinterName){
        this.timePrinterName = timePrinterName;
    }

    /**
     *
     * @param jobExecutionContext job 执行上下文对象
     * @throws JobExecutionException ""
     */
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 编写业务逻辑
        System.out.println(timePrinterName+"\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        // if no timePrinterName and set method of timePrinterName, we need get printerName by
//        String printerName = jobExecutionContext.getJobDetail().getJobDataMap().getString("timePrinterName");
//        System.out.println(printerName+"\t"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
