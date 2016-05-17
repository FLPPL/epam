package epam.entites;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.regex.Pattern;

public class WalkerDirectory extends SimpleFileVisitor<Path> {
	int countpath;

	public WalkerDirectory(int countpath) {
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
