package jus.aoo.lvm.interpretation;

public abstract class Atome implements SExpr
{
	public SExpr car()
	{
		throw new LispException();
	}

	public SExpr cdr()
	{
		throw new LispException();
	}
}
