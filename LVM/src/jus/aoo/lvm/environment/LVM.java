package jus.aoo.lvm.environment;

import jus.aoo.lvm.interpretation.Nil;
import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.interpretation.Symbole;
import jus.aoo.lvm.interpretation.FSUBR.QUOTE;
import jus.aoo.lvm.interpretation.SUBR.ATOM;
import jus.aoo.lvm.interpretation.SUBR.CAR;
import jus.aoo.lvm.interpretation.SUBR.CDR;
import jus.aoo.lvm.interpretation.SUBR.CONS;
import jus.aoo.lvm.interpretation.SUBR.EQ;
import jus.aoo.lvm.interpretation.SUBR.EVAL;
import jus.aoo.lvm.javacc.ParseException;
import jus.aoo.lvm.javacc.Reader;

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
		
		try
		{
			SExpr se = Reader.read();
			System.out.println(se.eval().toString());
		}
		catch (LispException | ParseException e)
		{
			e.printStackTrace();
		}
		
		/*SExpr test = new SCons(new EVAL(), new SCons(new SCons(new CAR(), new SCons(new Symbole("a"), new Symbole("b"))), Nil.NIL));
		
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
		}*/
	}
}
