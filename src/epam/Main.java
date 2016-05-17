package epam;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import epam.entites.CdWorker;
import epam.entites.CommandWorker;
import epam.entites.DirWorker;
import epam.entites.ExitWorker;
import epam.entites.PathWorker;
import epam.entites.PromptWorker;
import epam.entites.TreeWorker;

public class Main {
	//TESTED ONLY ON LINUX AND WINDOWS
	static Map<String, CommandWorker> map = new HashMap<String, CommandWorker>();
	static PathWorker path;
	public static void main(String[] args) {
		initDefault();
		start();
	}

	public static void start() {
		CommandWorker worker;
		Scanner sc = new Scanner(System.in);
		String firstcmd;
		String cmd;
		path.printInput();
		while (sc.hasNext()) {

			cmd = sc.nextLine().trim();
			// cuts first word of command
			if (cmd.indexOf(' ') > 0) {
				firstcmd = cmd.substring(0, cmd.indexOf(' ')).trim();
			} else {
				firstcmd = cmd.trim();
			}
			// check first word of command, that equals some of definied keys
			if (map.containsKey(firstcmd)) {
				worker = map.get(firstcmd);
				worker.work(cmd, path.getPath());
			} else {
				path.printUnknownCommand(firstcmd);
			}

			path.printInput();
		}
		sc.close();

	}

	public static void initDefault() {
		PromptWorker prompt = new PromptWorker();
		TreeWorker tree = new TreeWorker();
		DirWorker dir = new DirWorker();
		CdWorker cd = new CdWorker();
		ExitWorker exit = new ExitWorker();
		path = new PathWorker();
		
		prompt.setPathWorker(path);
		cd.setPathWorker(path);
		map.put(prompt.getKey(), prompt);
		map.put(tree.getKey(), tree);
		map.put(dir.getKey(), dir);
		map.put(cd.getKey(), cd);
		map.put(exit.getKey(), exit);
	}



}
