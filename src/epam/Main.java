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
	//TESTED ONLY ON LINUX
	static Map<String, CommandWorker> map = new HashMap<String, CommandWorker>();

	public static void main(String[] args) {
		initDefault();
		start();
	}

	public static void start() {
		CommandWorker worker;
		Scanner sc = new Scanner(System.in);
		String firstcmd;
		String cmd;
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

	public static void initDefault() {
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

	public static void printUnknownCommand(String cmd) {
		StringBuilder sb = new StringBuilder();
		sb.append(cmd);
		sb.append(" : Unknown command");
		System.out.println(sb);
	}

	public static void printInput(String pathSign) {
		StringBuilder sb = new StringBuilder("[MyShell] ");
		sb.append(pathSign);
		sb.append(">");
		System.out.print(sb);
	}

}
