package cn.edu.neu.quartz_spring.schedule;

import cn.edu.neu.easy.ShellCaller;
import lombok.SneakyThrows;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 32098
 */
public class SpiderJob extends BaseJob{
//    String pythonShellPath;
//
//    public void setPythonShellPath(String pythonShellPath) {
//        this.pythonShellPath = pythonShellPath;
//    }

    @SneakyThrows
    @Override
    public Boolean actuallyExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("xyz");
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("pythonShellPath"));
        // System.out.println("---"+ pythonShellPath);
        ShellCaller.call(new String[]{"D:\\Program Files\\AnacondaInstall\\python", "D:\\Code\\Python\\SpiderModule\\weibo_main.py"});
        return true;
    }
}


