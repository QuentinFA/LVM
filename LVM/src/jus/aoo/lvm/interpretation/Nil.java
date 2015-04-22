package jus.aoo.lvm.interpretation;

/**
 * Représente le symbole nil () en Lisp
 */
public class Nil extends Atome implements SList
{
	public static final SExpr NIL = new Nil();
	
	private Nil()
	{
		
	}
	
	public String toString()
	{
		return "()";
	}


	@Override
	public SExpr eval()
	{
		return this;
	}

	@Override
	public SExpr car() throws LispException
	{
		return this;
	}

	@Override
	public SExpr cdr() throws LispException
	{
		return this;
	}

	@Override
	public SExpr cons(SExpr e) throws LispException 
	{
			return new SCons(Nil.NIL, e);
	}
}
