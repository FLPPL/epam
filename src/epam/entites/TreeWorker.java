package epam.entites;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class TreeWorker extends CommandWorker {

	public TreeWorker() {
		// TODO Auto-generated constructor stub
		this.pattern = Pattern.compile("tree");
		this.key = "tree";
	}

	@Override
	public void doWork(String cmnd, Path wd) throws Exception {
		try {
			WalkerDirectory walker = new WalkerDirectory(wd.toString().split(Pattern.quote(File.separator)).length);
			Files.walkFileTree(Paths.get(wd.toString()), walker);
		}

		catch (Exception e) {
			throw e;
		}
	}

}
