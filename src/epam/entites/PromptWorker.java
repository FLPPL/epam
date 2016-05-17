package epam.entites;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromptWorker extends PathWorker {

	Pattern cwdPattern;
	Pattern resetPattern;

	@Override
	public void work(String cmnd,Path wd){
		// TODO Auto-generated method stub
		 super.work(cmnd, wd);
	}

	public PromptWorker() {
		// TODO Auto-generated constructor stub
		key = "prompt";

		this.pattern = Pattern.compile("prompt\\s+(\\$\\w+|\\w+)");
		this.cwdPattern = Pattern.compile("\\$cwd");
		this.resetPattern = Pattern.compile("reset");
	}

	@Override
	public void doWork(String cmnd, Path wd) {
		Matcher m = resetPattern.matcher(cmnd);
		if (m.matches()) {
			pathSign = "$";
			pmode = false;
		} else {
			m = cwdPattern.matcher(cmnd);
			if (m.matches()) {
				pmode = true;
				pathSign = wd.toString();
			} else {
				pathSign = cmnd;
			}
		}
	}
	
}
