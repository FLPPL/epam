package epam.entites;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import epam.exceptions.MyCommandExecption;
import epam.interfaces.ShellInterface;

public class MyShell implements ShellInterface {

	Path wd; // working directory path
	String pathSign; // sign for console input header like '$' or 'path' before
						// >
	boolean pMode; // prompt mode flag
	String cmnd; // current command

	public MyShell() {
		// TODO Auto-generated constructor stub
		this.init();
	}

	// standard Init
	void init() {
		wd = Paths.get("").toAbsolutePath();
		pathSign = "$";
		pMode = false;
	}

	// LOOP METHOD
	public void start() {
		Scanner sc = new Scanner(System.in);

		printInput();
		while (sc.hasNext()) {
			cmnd = sc.nextLine();
			cmnd = cmnd.trim();
			try {
				if (exit()) {
					if (prompt()) {
						if (dir()) {
							if (tree()) {
								if (cd()) {
									this.printUnknownCommand();
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				printInput();
			}
		}
		sc.close();
	}

	// print console header
	void printInput() {
		StringBuilder sb = new StringBuilder("[MyShell] ");
		sb.append(pathSign);
		sb.append(">");
		System.out.print(sb);
	}

	// Unknown command message
	void printUnknownCommand() {

		StringBuilder sb = new StringBuilder();
		// get first word of command (without parameters)
		if (cmnd.indexOf(' ') > 0) {
			sb.append(cmnd.substring(0, cmnd.indexOf(' ')));
		} else {
			sb.append(cmnd);
		}
		sb.append(" : Unknown command");
		System.out.println(sb);
	}

	@Override
	public boolean prompt() throws Exception {

		if (Pattern.compile("prompt\\s+(\\$\\w+|\\w+)").matcher(cmnd).matches()) {
			cmnd = cmnd.replaceFirst("prompt", "");
			cmnd = cmnd.trim();
			pathSign = cmnd;

			if (Pattern.compile("reset").matcher(cmnd).matches()) {
				pathSign = "$";
				pMode = false;
			}

			if (Pattern.compile("\\$cwd").matcher(cmnd).matches()) {
				pMode = true;
				pathSign = wd.toString();
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean cd() throws MyCommandExecption {

		// 'cd' command works only in prompt mode
		if (Pattern.compile("cd\\s+(\\..|\\w+)").matcher(cmnd).matches() && pMode) {
			cmnd = cmnd.replaceFirst("cd", "");
			cmnd = cmnd.trim();

			if (Pattern.compile("\\.\\.").matcher(cmnd).matches()) {
				StringBuilder sb = new StringBuilder(wd.toString());
				String nwp;
				if (sb.toString().split(Pattern.quote(sb.toString())).length > 1) {
					nwp = sb.substring(0, sb.lastIndexOf(File.separator));
				} else { // Doesnt cut last File.separator from path to save
							// Windows structure C:\
					nwp = sb.substring(0, sb.lastIndexOf(File.separator) + 1);
				}
				wd = Paths.get(nwp);
				pathSign = wd.toString();
			} else {
				StringBuilder sb = new StringBuilder(wd.toString());
				sb.append(File.separator).append(cmnd);

				if (Files.exists(Paths.get(sb.toString()))) { // ...
					wd = Paths.get(sb.toString());
					pathSign = wd.toString();
				} else {
					throw new MyCommandExecption();
				}
			}
			// DOORS for connected command input like 'prompt $cwd dir' or
			// while (Pattern.compile("cd (\\..|\\w+)").matcher(cmnd).find()) {
			// System.out.printf("group: %s%n", matcher.group());
			// }
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean dir() throws Exception {
		// In cases like this one, I can even check that String.equals() method insted of regex
		// Leaved regex i want to 'open doors' for connected command input like 'prompt $cwd dir' or 'prompt $cwd cd smth'
		if (Pattern.compile("dir").matcher(cmnd).matches()) {
			StringBuilder sb = new StringBuilder("Content of ");
			sb.append(wd);
			System.out.println(sb);
			try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(wd)) {
				for (Path entry : stream1) {
					if (Files.isRegularFile(entry)) {
						sb = new StringBuilder("FILE ");
						sb.append(entry.getFileName());
						System.out.println(sb);
					}

					if (Files.isDirectory(entry)) {
						sb = new StringBuilder("DIR ");
						sb.append(entry.getFileName());
						System.out.println(sb);
					}
				}
				stream1.close();
			} catch (Exception e) {
				throw e;
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean tree() throws Exception {
		if (Pattern.compile("tree").matcher(cmnd).matches()) {
			try {
				WalkDirectories walker = new WalkDirectories(wd.toString().split(Pattern.quote(File.separator)).length);
				Files.walkFileTree(Paths.get(wd.toString()), walker);
			}

			catch (Exception e) {
				throw e;
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean exit() throws Exception {
		// Thinking about move this functionality to Main class and execute System.exit() when instance of MyConsole class is destroyed
		if (Pattern.compile("exit").matcher(cmnd).matches()) {
			try {
				System.out.println("Bye");
				System.exit(0);
			} catch (Exception e) {
				throw e;
			}

		}
		return true;
	}

	@Override
	public boolean dir1() throws Exception {

		if (Pattern.compile("dir1").matcher(cmnd).matches()) {
			try (Stream<Path> filePathStream = Files.walk(Paths.get(wd.toString()))) {
				filePathStream.forEach(filePath -> {

					if (Files.isRegularFile(filePath)) {
						StringBuilder sb = new StringBuilder("FILE ");
						sb.append(filePath.getFileName());
						System.out.println(sb);
					}

					if (Files.isDirectory(filePath)) {
						StringBuilder sb = new StringBuilder("DIR ");
						sb.append(filePath.getFileName());
						System.out.println(sb);
					}

				});
			} catch (Exception e) {
				throw e;
			}
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean tree1() throws Exception {

		if (Pattern.compile("tree1").matcher(cmnd).matches()) {
			int countpath = wd.toString().split(Pattern.quote(File.separator)).length;
			try (Stream<Path> filePathStream = Files.walk(Paths.get(wd.toString()))) {
				Files.walk(Paths.get(wd.toString())).filter(Files::isDirectory).forEach(filep -> {
					if (Files.isDirectory(filep)) {
						int z = filep.toString().split(Pattern.quote(File.separator)).length;
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < z - countpath; i++) {
							sb.append("-");
						}
						sb.append(filep.getFileName());
						System.out.println(sb);
					}
				});
			} catch (Exception e) {
				throw e;
			}
			return false;
		} else {
			return true;
		}
	}

	public static class WalkDirectories extends SimpleFileVisitor<Path> {

		int countpath;

		public WalkDirectories(int countpath) {
			// TODO Auto-generated constructor stub
			this.countpath = countpath;

		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
			int z = dir.toString().split(Pattern.quote(File.separator)).length;

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < z - countpath; i++) {
				sb.append("-");
			}
			sb.append(dir.getFileName());

			System.out.println(sb.toString());
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) {
			System.err.println(exc);
			return FileVisitResult.CONTINUE;
		}
	}
}
