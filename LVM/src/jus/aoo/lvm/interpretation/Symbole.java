package jus.aoo.lvm.interpretation;

public class Symbole extends Atome
{
	@Override
	public SExpr eval() 
	{
		// TODO Liens avec le contexte
		return null;
	}

	@Override
	public SExpr cons(SExpr e) throws LispException 
	{
		return new SCons(this, e);
	}
}
