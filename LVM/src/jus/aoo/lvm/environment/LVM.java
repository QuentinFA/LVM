package jus.aoo.lvm.environment;

import java.io.FileNotFoundException;
import java.util.Scanner;

import jus.aoo.lvm.interpretation.*;
import jus.aoo.lvm.interpretation.FSUBR.*;
import jus.aoo.lvm.interpretation.SUBR.*;

import jus.aoo.lvm.javacc.ParseException;
import jus.aoo.lvm.javacc.Reader;

public class LVM {
	
	/**
	 * Initialise la LVM
	 */
	protected static void initialiser()
	{
		//Initialisation des variables de base
		Context.addVar("()", Nil.NIL);
		Context.addVar("'", new Symbole("quote"));
		Context.addVar("vrai", Symbole.TRUE);
		Context.addVar("faux", Nil.NIL);
		
		//Initialisation des fonctions et predicats de base
		Context.addFonction("df", new DF());
		Context.addFonction("de", new DE());
		Context.addFonction("car", new CAR());
		Context.addFonction("cdr", new CDR());
		Context.addFonction("atom", new ATOM());
		Context.addFonction("eq", new EQ());
		Context.addFonction("cons", new CONS());
		Context.addFonction("eval", new EVAL());
		Context.addFonction("cond", new COND());
		Context.addFonction("set", new SET());
		Context.addFonction("typefn", new TYPEFN());
		Context.addFonction("implode", new IMPLODE());
		Context.addFonction("explode", new EXPLODE());
	}
	
	/**
	 * Affiche une SExpr de façon esthétique
	 * @param s : la SExpr à afficher
	 */
	public static void afficher(SExpr expr)
	{
		try
		{
			if (expr.car().toString().equals("de") || expr.car().toString().equals("df") || expr.car().toString().equals("set"))
			{
				System.out.println("(" + expr.toString() + ")");
				expr.eval();
			}
			else if (expr instanceof Atome)
			{
				if (expr.eval()	 instanceof Atome)
					System.out.println(expr.toString() + " -> " + expr.eval().toString());
				else
					System.out.println(expr.toString() + " -> " + "(" + expr.eval().toString() + ")");
			}
			else
			{
				if (expr.eval() instanceof Atome)
					System.out.println("(" + expr.toString() + ")" + " -> " + expr.eval().toString());
				else
					System.out.println("(" + expr.toString() + ")" + " -> " + "(" + expr.eval().toString() + ")");
			}
		}
		catch (LispException e)
		{System.out.println(e.getMessage());}
	}
	
	public static void main(String[] args) throws LispException, FileNotFoundException, ParseException
	{
		initialiser();
		
		//Lecture fichier
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
		
		//Lecture clavier
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
