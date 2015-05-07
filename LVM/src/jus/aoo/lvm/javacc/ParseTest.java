package jus.aoo.lvm.javacc;

//import java.io.FileReader;

import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.SExpr;

public class ParseTest {

	public static void main(String[] args) throws LispException, ParseException {
		
		
		// TODO Auto-generated method stub
		
			Reader parser = null;
			//Reader parser = new Reader (new FileReader("test.txt"));  
			String s = "(ghj ())";
			SExpr expr= parser.read(s);
			
			System.out.println("Test de l'Atome (needs a ToString method in Symbole.java :"  ); 
			

	}

}
