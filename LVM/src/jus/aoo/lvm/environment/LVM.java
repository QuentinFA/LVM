package jus.aoo.lvm.environment;

import jus.aoo.lvm.interpretation.*;
import jus.aoo.lvm.interpretation.FSUBR.*;
import jus.aoo.lvm.interpretation.SUBR.*;

public class LVM {
	
	private static void initialiser()
	{
		Context.addVar("car", new CAR());
		Context.addVar("cdr", new CDR());
		Context.addVar("atom", new ATOM());
		Context.addVar("eq", new EQ());
		Context.addVar("cons", new CONS());
		Context.addVar("eval", new EVAL());
		
		Context.addVar("quote", new QUOTE());
	}
	
	public static void main(String[] args)
	{
		initialiser();
		
		SExpr test = new SCons(new EVAL(), new SCons(new SCons(new CAR(), new SCons(new Symbole("a"), new Symbole("b"))), Nil.NIL));
		
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
