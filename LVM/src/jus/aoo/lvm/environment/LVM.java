package jus.aoo.lvm.environment;
import jus.aoo.lvm.interpretation.*;
import jus.aoo.lvm.interpretation.SUBR.*;

public class LVM {
	
	private static void initialiser()
	{
		Context.addVar("car", new CAR());
		Context.addVar("cdr", new CDR());
		Context.addVar("atom", new ATOM());
		Context.addVar("eq", new EQ());
		Context.addVar("cons", new CONS());
	}
	
	public static void main(String[] args)
	{
		initialiser();
		SExpr lol = new Symbole("a");
		
		//(eq lol lol) -> t
		SExpr test = new SCons(new EQ(), new SCons(lol, new SCons(lol, Nil.NIL)));
		//(cdr (a b)) -> b
		SExpr test2 = new SCons(new CDR(), new SCons(new SCons(new Symbole("a"), new SCons(new Symbole("b"), Nil.NIL)), Nil.NIL));
		//(a.a) -> (a.a)
		SExpr test3 = new SCons(lol, lol);
		//((a b) (c d)) -> ((a b) (c d))
		SExpr test4 = new SCons(new SCons(new Symbole("a"), new SCons(new Symbole("b"), Nil.NIL)), 
				                new SCons(new Symbole("c"), new SCons(new Symbole("d"), Nil.NIL)));
		
		System.out.println("(" + test.toString() + ")" + " -> " + test.eval().toString());
		System.out.println("(" + test2.toString() + ")" + " -> " + test2.eval().toString());
		System.out.println("(" + test3.toString() + ")" + " -> " + test3.eval().toString());
		System.out.println("(" + test4.toString() + ")" + " -> " + test4.eval().toString());
	}
}
