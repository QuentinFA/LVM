package jus.aoo.lvm.environment;

public class LispException extends RuntimeException
{
	String message;
	public LispException(String string) {
		message = "     ";
		message += string;
		message += "\n";
	}

	public LispException() {
		
	}

	public String getMessage()
	{
		return message;
	}

	private static final long serialVersionUID = -6817553690812840517L;
	
}
