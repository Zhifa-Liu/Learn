package cn.edu.neu.easy;
 
import java.util.Timer;

/**
 * @author xxx
 */
public class TaskStarter {
	public static void main(String[] args) {
		Timer timer = new Timer("爬虫");
		// 延时1秒之后开始执行任务，每60分钟执行一次
		timer.schedule(new SpiderTask(), 1000,1000*60*60);
	}
}
