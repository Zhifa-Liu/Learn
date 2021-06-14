package cn.edu.neu.quartz;

import cn.edu.neu.easy.ShellCaller;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author 32098
 */
public class SpiderJob implements Job {
    String pythonShellPath;

    public void setPythonShellPath(String pythonShellPath) {
        this.pythonShellPath = pythonShellPath;
    }

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ShellCaller.call(new String[]{"D:\\Program Files\\AnacondaInstall\\python", pythonShellPath});
    }
}
