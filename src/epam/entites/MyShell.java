package epam.entites;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import epam.entites.workers.*;

public class MyShell {
	Map<String, CommandWorker> map = new HashMap<String, CommandWorker>();
	String cmd;

	public MyShell() {
		initDefault();
	}

	public void start() {
		CommandWorker worker;
		Scanner sc = new Scanner(System.in);
		String firstcmd;

		printInput(PathWorker.pathSign);
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
				worker.work(cmd, PathWorker.path);
			} else {
				printUnknownCommand(firstcmd);
			}

			printInput(PathWorker.pathSign);
		}
		sc.close();

	}

	public void initDefault() {
		PromptWorker prompt = new PromptWorker();
		TreeWorker tree = new TreeWorker();
		DirWorker dir = new DirWorker();
		CdWorker cd = new CdWorker();
		ExitWorker exit = new ExitWorker();

		map.put(prompt.getKey(), prompt);
		map.put(tree.getKey(), tree);
		map.put(dir.getKey(), dir);
		map.put(cd.getKey(), cd);
		map.put(exit.getKey(), exit);
	}

	public void printUnknownCommand(String cmd) {
		StringBuilder sb = new StringBuilder();
		sb.append(cmd);
		sb.append(" : Unknown command");
		System.out.println(sb);
	}

	public void printInput(String pathSign) {
		StringBuilder sb = new StringBuilder("[MyShell] ");
		sb.append(pathSign);
		sb.append(">");
		System.out.print(sb);
	}

}
