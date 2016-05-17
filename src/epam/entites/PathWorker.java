package epam.entites;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathWorker  {
	
	private boolean pmode;
	private String pathSign;
	private Path path;
	public PathWorker() {
		pmode = false;
		pathSign = "$";
		path = Paths.get("").toAbsolutePath();
		// TODO Auto-generated constructor stub
	}
	public boolean isPmode() {
		return pmode;
	}
	public void setPmode(boolean pmode) {
		this.pmode = pmode;
	}
	public String getPathSign() {
		return pathSign;
	}
	public void setPathSign(String pathSign) {
		this.pathSign = pathSign;
	}
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}	
	
	public void printUnknownCommand(String cmd) {
		StringBuilder sb = new StringBuilder();
		sb.append(cmd);
		sb.append(" : Unknown command");
		System.out.println(sb);
	}

	public void printInput() {
		StringBuilder sb = new StringBuilder("[MyShell] ");
		sb.append(pathSign);
		sb.append(">");
		System.out.print(sb);
	}
	
	
}
