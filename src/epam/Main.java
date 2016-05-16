package epam;

import epam.entites.MyShell;

public class Main {
	public static void main(String[] args) {
		// TESTED ONLY ON LINUX (JRE 8)
		// Encapsulation of functionality in MyShell.class
		// Could be moved to this one Main.class
		MyShell shell = new MyShell();
		shell.start();
	}

}
