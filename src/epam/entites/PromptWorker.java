package epam.entites;

import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromptWorker extends CommandWorker {

	Pattern cwdPattern;
	Pattern resetPattern;
	PathWorker pathWorker;
	
	public PathWorker getPathWorker() {
		return pathWorker;
	}

	public void setPathWorker(PathWorker pathWorker) {
		this.pathWorker = pathWorker;
	}

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
			pathWorker.setPathSign("$");
			pathWorker.setPmode(false);
		} else {
			m = cwdPattern.matcher(cmnd);
			if (m.matches()) {
				pathWorker.setPmode(true);
				pathWorker.setPathSign(wd.toString());
			} else {
				pathWorker.setPathSign(cmnd);			
			}
		}
	}
	
}
