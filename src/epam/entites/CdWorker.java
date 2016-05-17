package epam.entites;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CdWorker extends CommandWorker {
	Pattern dotsPattern;
	PathWorker pathWorker;
	public CdWorker() {
		this.pattern = Pattern.compile("cd\\s+(\\..|\\w+)");
		this.dotsPattern = Pattern.compile("\\.\\.");
		this.key = "cd";
	}
	public PathWorker getPathWorker() {
		return pathWorker;
	}

	public void setPathWorker(PathWorker pathWorker) {
		this.pathWorker = pathWorker;
	}
	@Override
	public void doWork(String cmnd, Path wd) throws Exception {

		Matcher m = dotsPattern.matcher(cmnd);
		if (pathWorker.isPmode()) {
			if (m.matches()) {
				StringBuilder sb = new StringBuilder(wd.toString());
				String nwp;
				if (sb.toString().split(Pattern.quote(sb.toString())).length > 1) {
					nwp = sb.substring(0, sb.lastIndexOf(File.separator));
				} else { // Doesnt cut last File.separator from path to save
							// Windows structure C:\
					nwp = sb.substring(0, sb.lastIndexOf(File.separator) + 1);
				}

				pathWorker.setPath(Paths.get(nwp));
			} else {
				StringBuilder sb = new StringBuilder(wd.toString());
				sb.append(File.separator).append(cmnd);

				if (Files.exists(Paths.get(sb.toString()))) {
					pathWorker.setPath(Paths.get(sb.toString()));
				} else {
					pathWorker.setPath(wd);
					System.out.println("PATH NOT FOUND");
				}
			}
			pathWorker.setPathSign(pathWorker.getPath().toString());
		} else {
			System.out.println("CD command works only in prompt mode");
		}

	}

}
