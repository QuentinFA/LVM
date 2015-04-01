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

	@Override
	public SExpr eval()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SExpr car() throws LispException
	{
		// TODO Auto-generated method stub
		return car;
	}

	@Override
	public SExpr cdr() throws LispException
	{
		// TODO Auto-generated method stub
		return cdr;
	}
	
}
