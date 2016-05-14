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

public class Epam {
public static void main(String[] args) {
	
	/*
	Console con = System.console();
	 if (con != null) {
	     Scanner sc = new Scanner(con.reader());
	     
	 }*/
	
	//YET TESTED ONLY ON LINUX
	//I'LL ADD COMMENTS LATER
	//UI LOOP AND REGEX TESTED
	
	System.out.println("epam app");
	Path path = Paths.get("").toAbsolutePath();
	String pathSign = "$";
	//Pattern.quote("prompt (\\w+)")

	
	boolean  pMode = false;
	//	String path1 = System.getProperty("user.dir");										
   //     System.out.print(path0); 
	Scanner sc = new Scanner(System.in);
	//System.out.print(pathSign + ">");
	System.out.print(pathSign + ">");
	

	 while(sc.hasNext())
     {
		// System.out.print("nwl");
		// System.out.println("P2");
		 //System.out.println("P4");
			
		String cmnd = sc.nextLine(); 
		 //akuku
		 if(Pattern.compile("prompt (\\$\\w+|\\w+)").matcher(cmnd).matches())
		 {
			 //System.out.print("IN");
			 cmnd = cmnd.replaceFirst("prompt", ""); 
			 cmnd = cmnd.trim();
		
	    	 pathSign = cmnd;
	    	 
	    	 if(Pattern.compile("reset").matcher(cmnd).matches())
			 {
				 System.out.println("RESET");		
				 pathSign = "$";
				 pMode= false;
			//	 while (Pattern.compile("reset").matcher(cmnd).find()) 
				 {
					  
				 }
				
			 }
				    	 
	    	 if(Pattern.compile("\\$cwd").matcher(cmnd).matches())
			 {	
				// String path = Paths.get("").toAbsolutePath().toString();												
		       //  System.out.println(path); 
	    		 pMode = true;
		    	 pathSign = path.toString();
			//	 while (Pattern.compile("cwd").matcher(cmnd).find()) 
		         {
				
				 }
			 }
	   		
	    	//	System.out.print(pathSign + ">");	 
		 }
				 
		 
		 if(pMode == true)
		 {
			 
	      //	 System.out.println("CmD " + cmnd);
	         //  "(\\w)(\\s+)([\\.,])"
	            while(Pattern.compile("cd (\\..|\\w+)").matcher(cmnd).matches())
				 {		            	
	            	 cmnd = cmnd.replaceFirst("cd", ""); 
	            	 cmnd = cmnd.trim();
	            	 
	            	 if(Pattern.compile("\\.\\.").matcher(cmnd).matches())
					 {	            	 
	                   	 System.out.println("CD " + cmnd);
	                 	StringBuilder sb = new StringBuilder(path.toString());
	                 	
	                 	String nwp = sb.substring(0, sb.lastIndexOf("/"));
	                  	 System.out.println("nwp " + nwp);
	                 		path = 	Paths.get(nwp);
	                 		 pathSign = path.toString();
	                 		 
	                 	 System.out.println("sb " + path);
	              //   	sb.getChars(srcBegin, srcEnd, dst, dstBegin);
	                   	 
					 }
	            	 else
	            	 {	 
	            		StringBuilder sb = new StringBuilder(path.toString());
	            		sb.append("//").append(cmnd); 
	            		//path.
	            		path = Paths.get(sb.toString());
	            	    pathSign = path.toString();
	                   	 System.out.println("CD " + cmnd);
	            	 }
	           // 	  System.out.print(path + "?>"); //pmode
	  	       //       cmnd = sc.nextLine();
				 }
	          
	         
			 
		 }
		 
		 
		   if(Pattern.compile("dir").matcher(cmnd).matches())
	   		 {			 
	   			 //String path = Paths.get("").toAbsolutePath().toString();
	   			 System.out.println("Content of" + path);
	   			 try (DirectoryStream<Path> stream1 = Files.newDirectoryStream(path)) {
	   			       for (Path entry: stream1) {
	   			       
	   			           if (Files.isRegularFile(entry)) {
	   				    		StringBuilder sb = new StringBuilder("FILE ");
	   				    		sb.append(entry.getFileName());
	   				    		System.out.println(sb);
	   				        }
	   				    	
	   				     	if (Files.isDirectory(entry)) {
	   				     		StringBuilder sb = new StringBuilder("DIR ");
	   				     		sb.append(entry.getFileName());
	   				    		System.out.println(sb);
	   				        }				       
	   			       }
	   			   	stream1.close();
	   		//	  System.out.print(path + "!>"); //pmode
	          //    cmnd = sc.nextLine();
	   			   }
	   			 
	   			catch(Exception e)
	   			 {
	   				 e.printStackTrace();
	   			 }			 
	   			// System.out.println("P3");
	   			
	   		 }
		 //	Pattern p0 = Pattern.compile("[$]*\\w+");
	
		
		 
		 if(Pattern.compile("tree").matcher(cmnd).matches())
		 {	
		
			 try  {				 
				 WalkDirectories wd = new WalkDirectories(path.toString().split("/").length);
				 Files.walkFileTree( Paths.get(path.toString()), wd);
				 
				}
			 
			 catch(Exception e)
			 {
				 e.printStackTrace();				 
			 }			 
		 }
		 
		 
		 if(Pattern.compile("tree1").matcher(cmnd).matches())
		 {			 
			 //String path = Paths.get("").toAbsolutePath().toString();		 
			 int countpath = path.toString().split("/").length;
			 try (Stream<Path> filePathStream= Files.walk(Paths.get(path.toString()))) 
			 {		
				 Files.walk(Paths.get(path.toString())).filter(Files::isDirectory).forEach(
						 filep -> {						 
							
						     	if (Files.isDirectory(filep)) {
						     		
						     		int  z = filep.toString().split("/").length;
						     		
						     		
						     		StringBuilder sb = new StringBuilder();
						     		for(int i =0; i< z - countpath; i++){		
						     			sb.append("-");
						     		}	 
						     		sb.append(filep.getFileName());				     		
						    
						    		System.out.println(sb);
						        }				    
							 
						 }
						 );				 
			 }
			 
			 catch(Exception e)
			 {
				 e.printStackTrace();				 
			 }
	    	 
	    	 
		 }
		 
		 
		 //GOING ON WHOLE TREE
		 if(Pattern.compile("dirall").matcher(cmnd).matches())
		 {			 
			 //String path = Paths.get("").toAbsolutePath().toString();
			 
			 try (Stream<Path> filePathStream= Files.walk(Paths.get(path.toString()))) {
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
				}
			 
			 catch(Exception e)
			 {
				 e.printStackTrace();				 
			 }
			 
		 }
		 
		 
			//cmnd = sc.nextLine(); 
			System.out.print(pathSign + ">");
     }
	 
     sc.close();
	// sc.next()()
     
   
     System.out.println("bye");
	
}


public static class WalkDirectories
extends SimpleFileVisitor<Path> {
	
	int countpath;
	public WalkDirectories(int countpath) {
		// TODO Auto-generated constructor stub
	this.countpath = countpath;
		
	}

@Override
public FileVisitResult postVisitDirectory(Path dir,
                                      IOException exc) {
	int  z = dir.toString().split("/").length;
	
	StringBuilder sb = new StringBuilder();
	for(int i =0; i< z - countpath; i++){		
		sb.append("-");
	}	
	sb.append(dir.getFileName());	
	
    System.out.println(sb.toString());
    return  FileVisitResult.CONTINUE;
}

@Override
public FileVisitResult visitFileFailed(Path file,
                                   IOException exc) {
    System.err.println(exc);
    return  FileVisitResult.CONTINUE;
}
}

}
