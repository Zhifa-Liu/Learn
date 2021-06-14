package cn.edu.neu.easy;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author xxx
 */
public class ShellCaller {
	public static void call(String[] str) throws IOException {
		if (str == null || str.length == 0) {
			return;
		}
		Process process = Runtime.getRuntime().exec(str);
		BufferedReader printReader = new BufferedReader(
				new InputStreamReader(
					process.getInputStream(), "GBK"
				)
		);

		BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream(), "GBK"));

		String line;
		while ((line = printReader.readLine()) != null) {
			System.out.println(line);
		}
		printReader.close();

		String errorLine;
		while ((errorLine = errorReader.readLine()) != null) {
			System.out.println(errorLine);
		}
		errorReader.close();

		process.destroy();
	}
}

