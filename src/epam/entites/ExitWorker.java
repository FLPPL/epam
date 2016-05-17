package epam.entites;

import java.nio.file.Path;
import java.util.regex.Pattern;

public class ExitWorker extends CommandWorker {
	public ExitWorker() {
		// TODO Auto-generated constructor stub
		this.pattern = Pattern.compile("exit");
		this.key = "exit";
	}

	@Override
	public void doWork(String cmnd, Path wd) throws Exception {
		try {
			System.out.println("Bye");
			System.exit(0);
		} catch (Exception e) {
			throw e;
		}
	}
}
