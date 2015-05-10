package jus.aoo.lvm.environment;

import java.io.FileNotFoundException;

import jus.aoo.lvm.javacc.ParseException;

public class Application {

	private static Support support;
	
	public static void main(String[] args) throws LispException, FileNotFoundException, ParseException {
		// TODO Auto-generated method stub

		//Si lecture à partir d'un fichier (TODO: le if correspondant)
		support= new LectureFichier("C:\\test.txt");
		support.executer();
		
		//Si lecture via console (TODO)
		
	}

}
