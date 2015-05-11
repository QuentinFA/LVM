package jus.aoo.lvm.environment;

import jus.aoo.lvm.interpretation.*;
import jus.aoo.lvm.interpretation.FSUBR.*;
import jus.aoo.lvm.interpretation.SUBR.*;
import jus.aoo.lvm.javacc.*;

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
		//Context.addFonction("quote", new QUOTE());
		
		// TODO Ajouter T
	}
	
	protected LVM(){
		initialiser();
	}
	
	
	public static void main(String[] args)
	{
		initialiser();
		while (true)
		{
			// Saisie au clavier d'expression et évaluation
			SExpr test = null;
			try
			{test = Reader.read();}
			catch (LispException | ParseException e)
			{e.printStackTrace();}
			
			if (test instanceof Atome)
			{
				if (test.eval()	 instanceof Atome)
					System.out.println(test.toString() + " -> " + test.eval().toString());
				else
					System.out.println(test.toString() + " -> " + "(" + test.eval().toString() + ")");
			}
			else
			{
				if (test.eval() instanceof Atome)
					System.out.println("(" + test.toString() + ")" + " -> " + test.eval().toString());
				else
					System.out.println("(" + test.toString() + ")" + " -> " + "(" + test.eval().toString() + ")");
			}
		}
	}
}
