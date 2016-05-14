package epam.interfaces;

import epam.exceptions.MyCommandExecption;

public interface ShellInterface {
	//regular methods
	public boolean prompt() throws Exception; // implements command 'prompt'
	public boolean cd () throws MyCommandExecption; // implements command 'cd'
	public boolean dir () throws Exception; // implements command 'dir'
	public boolean tree() throws Exception; // implements command 'tree'
	public boolean exit() throws Exception; // implements command 'exit'
	//bonus method
	public boolean dir1() throws  Exception; // implements command 'dir1' cascade tree display all directories and files in them, WORKS BUT IS'NT PLUGED IN
	public boolean tree1() throws  Exception;; // implements command 'tree1' another implementation of tree (also reverse branches displays), WORKS BUT IS'NT PLUGED IN
		
}
