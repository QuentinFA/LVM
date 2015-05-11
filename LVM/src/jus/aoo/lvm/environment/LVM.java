package jus.aoo.lvm.environment;

import java.io.FileNotFoundException;
import java.util.Scanner;

import jus.aoo.lvm.interpretation.*;
import jus.aoo.lvm.interpretation.FSUBR.*;
import jus.aoo.lvm.interpretation.SUBR.*;

import jus.aoo.lvm.javacc.ParseException;
import jus.aoo.lvm.javacc.Reader;

public class LVM {
	
	protected static void initialiser()
	{
		//initialisation des variables
		Context.addVar("()", Nil.NIL);
		Context.addVar("'", new Symbole("quote"));
		Context.addVar("vrai", Symbole.TRUE);
		Context.addVar("faux", Nil.NIL);
		
		//fonctions et predicats
		Context.addFonction("df", new DF());
		Context.addFonction("de", new DE());
		Context.addFonction("car", new CAR());
		Context.addFonction("cdr", new CDR());
		Context.addFonction("atom", new ATOM());
		Context.addFonction("eq", new EQ());
		Context.addFonction("cons", new CONS());
		Context.addFonction("eval", new EVAL());
		Context.addFonction("cond", new COND());
	}
	
	public static void afficher(SExpr e)
	{
		if (e instanceof Atome)
		{
			if (e.eval()	 instanceof Atome)
				System.out.println(e.toString() + " -> " + e.eval().toString());
			else
				System.out.println(e.toString() + " -> " + "(" + e.eval().toString() + ")");
		}
		else
		{
			if (e.eval() instanceof Atome)
				System.out.println("(" + e.toString() + ")" + " -> " + e.eval().toString());
			else
				System.out.println("(" + e.toString() + ")" + " -> " + "(" + e.eval().toString() + ")");
		}
	}
	
	public static void main(String[] args) throws LispException, FileNotFoundException, ParseException
	{
		initialiser();
		
		//Fichier
		java.io.File fichier = new java.io.File("test.txt");
		Scanner s = new Scanner(fichier);

		while (s.hasNextLine()) {
			
			SExpr se = null;
			try
			{se = Reader.read(s.nextLine());}
			catch (LispException | ParseException e)
			{e.printStackTrace();}
			
			afficher(se);
		}
		s.close();
		
		//Entrée
		while (true) {
			SExpr se = null;
			try
			{se = Reader.read();}
			catch (LispException | ParseException e)
			{e.printStackTrace();}

			if (se != null)
				afficher(se);
		}
	}

}
