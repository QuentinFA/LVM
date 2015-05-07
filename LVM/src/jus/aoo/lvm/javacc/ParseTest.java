package jus.aoo.lvm.javacc;

import java.io.FileNotFoundException;
import java.io.FileReader;

import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.SExpr;

public class ParseTest {

	public static void main(String[] args) throws LispException, ParseException, FileNotFoundException {
		
		
		// TODO Auto-generated method stub
		
			Reader parser = null;
			//  = new Reader (new FileReader("test.txt"));  
			//String s = " aejorlf () (dndjdnf () ) dk";
			//SExpr expr= parser.read(s);
			  SExpr expr = parser.importe("C:\\test.txt");
			
			System.out.println("lecture du fichier, evaluation et affichage:" + expr.eval().toString()  ); 
			

	}

}
