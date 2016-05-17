package epam.entites;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CdWorker extends PathWorker {
	Pattern dotsPattern;
	
	public CdWorker() {
		this.pattern = Pattern.compile("cd\\s+(\\..|\\w+)");
		this.dotsPattern = Pattern.compile("\\.\\.");
		this.key = "cd";
	}

	@Override
	public void doWork(String cmnd, Path wd) throws Exception {

		Matcher m = dotsPattern.matcher(cmnd);
		if (pmode) {
			if (m.matches()) {
				StringBuilder sb = new StringBuilder(wd.toString());
				String nwp;
				if (sb.toString().split(Pattern.quote(sb.toString())).length > 1) {
					nwp = sb.substring(0, sb.lastIndexOf(File.separator));
				} else { // Doesnt cut last File.separator from path to save
							// Windows structure C:\
					nwp = sb.substring(0, sb.lastIndexOf(File.separator) + 1);
				}

				path = Paths.get(nwp);
			} else {
				StringBuilder sb = new StringBuilder(wd.toString());
				sb.append(File.separator).append(cmnd);

				if (Files.exists(Paths.get(sb.toString()))) {
					path = Paths.get(sb.toString());
				} else {
					path = wd;
					System.out.println("PATH NOT FOUND");
				}
			}
			pathSign = path.toString();
		} else {
			System.out.println("CD command works only in prompt mode");
		}

	}

}
