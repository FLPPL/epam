package epam.exceptions;

public class MyCommandExecption extends Exception {

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		//super.printStackTrace();
		System.out.println("Invalid Path");
		System.out.println("Command Not Found");
	}
	
}
