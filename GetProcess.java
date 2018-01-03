import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GetProcess {

	public static String executeCommand(String cmdStr) {
		String[] cmd = new String[] { "sh", "-c", cmdStr };
		Process proc = null;
		StringBuffer sb = new StringBuffer();
		try {
			proc = Runtime.getRuntime().exec(cmd);
			InputStream is = proc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = "";
			try {
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (proc != null) {
				proc.destroy();
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String cmd = "ls > lsResult";
		executeCommand(cmd);
	}
}
