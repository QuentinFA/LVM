package jus.aoo.lvm.interpretation;

/**
 * Un SCons est un couple avec :
 * 		- Une référence vers un autre SCons (si composé de liste)
 * 		- Un symbole pour une valeur directe
 */
public class SCons implements SList
{
	private SExpr car;
	private SExpr cdr;
	
	public SCons(SExpr car, SExpr cdr)
	{
		this.car=car;
		this.cdr=cdr;
	}

	@Override
	public SExpr eval()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SExpr car() throws LispException
	{
		return car;
	}

	@Override
	public SExpr cdr() throws LispException
	{
		return cdr;
	}

	@Override
	public SExpr cons(SExpr e)
	{
		return new SCons(this, e);
	}
}
