package jus.aoo.lvm.environment;

import jus.aoo.lvm.javacc.ParseException;

public interface Support {
	
	public void executer() throws LispException, ParseException;
	public void stop();
	

}
