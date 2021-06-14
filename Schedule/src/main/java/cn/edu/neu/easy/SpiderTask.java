package cn.edu.neu.easy;
 
import java.io.IOException;
import java.util.TimerTask;
 
/**
 * @author xxx
 */
public class SpiderTask extends TimerTask{
	private static final String ORDER = "D:\\Program Files\\AnacondaInstall\\python";
	private static final String PYTHON_SHELL_PATH = "D:\\Code\\Python\\SpiderModule\\main.py";
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String[] str = {ORDER, PYTHON_SHELL_PATH};
			ShellCaller.call(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

