package epam.exceptions;

public class MyCommandExecption extends Exception {

	private static final long serialVersionUID = 1L;

	@Override
	public void printStackTrace() {
		// TODO Auto-generated method stub
		// super.printStackTrace();
		System.out.println("Invalid Path");
		System.out.println("Command Not Found");
	}

}
