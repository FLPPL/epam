package epam;

import java.io.Console;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import epam.entites.MyShell;

public class Main {
public static void main(String[] args) {	
	
	//TESTED ONLY ON LINUX
		MyShell shell = new MyShell();		
		shell.start();	
}

}
