package epam.entites.workers;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class PathWorker extends CommandWorker {
	
	public static boolean pmode;
	public static String pathSign;
	public static Path path;
	public PathWorker() {
		pmode = false;
		pathSign = "$";
		path = Paths.get("").toAbsolutePath();
		// TODO Auto-generated constructor stub
	}
	
}
