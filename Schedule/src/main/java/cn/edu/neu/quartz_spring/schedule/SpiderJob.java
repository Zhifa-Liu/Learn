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
    String pythonShellPath;

    public void setPythonShellPath(String pythonShellPath) {
        this.pythonShellPath = pythonShellPath;
    }

    @SneakyThrows
    @Override
    public Boolean actuallyExecute(JobExecutionContext jobExecutionContext) {
        System.out.println("execute...");
        ShellCaller.call(new String[]{"D:\\Program Files\\AnacondaInstall\\python", pythonShellPath});
        return true;
    }
}

