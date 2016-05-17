package epam.entites;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class DirWorker extends CommandWorker {
	
	
	public DirWorker() {
		// TODO Auto-generated constructor stub
		this.pattern = Pattern.compile("dir");
		this.key = "dir";
	}
	

	@Override
	public void doWork(String cmnd, Path wd) throws Exception {		
	
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
		
	}
	

}
