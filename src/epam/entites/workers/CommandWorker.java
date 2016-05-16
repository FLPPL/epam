package epam.entites.workers;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandWorker {

	protected Pattern pattern;
	protected String key;

	public String getKey() {
		return key;
	}

	public CommandWorker() {
		key = "command";
	}

	public void work(String cmnd, Path wd) {

		cmnd = cmnd.trim();
		Matcher m = pattern.matcher(cmnd);

		if (m.matches()) {
			try {
				cmnd = cmnd.replaceFirst(this.key, "").trim();
				doWork(cmnd, wd);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			StringBuilder sb = new StringBuilder(cmnd).append(" : Unknown parameters");
			System.out.println(sb);
		}
	}

	public abstract void doWork(String cmnd, Path wd) throws Exception;
}
