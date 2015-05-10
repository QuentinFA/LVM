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
		Context.addVar("vrai", new Symbole("t"));
		Context.addVar("faux", Nil.NIL);
		
		//fonctions et predicats
		Context.addVar("car", new CAR());
		Context.addVar("cdr", new CDR());
		Context.addVar("atom", new ATOM());
		Context.addVar("eq", new EQ());
		Context.addVar("cons", new CONS());
		Context.addVar("eval", new EVAL());
		Context.addVar("quote", new QUOTE());
		
		// TODO Ajouter T
	}
	
	protected LVM(){
		initialiser();
	}
	
	
	public static void main(String[] args)
	{
		initialiser();
		
		// Saisie au clavier d'expression et évaluation
		SExpr test = null;
		try
		{
			test = Reader.read();
		}
		catch (LispException | ParseException e)
		{
			e.printStackTrace();
		}
		
		if (test instanceof Atome)
		{
			if (test.eval() instanceof Atome)
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
