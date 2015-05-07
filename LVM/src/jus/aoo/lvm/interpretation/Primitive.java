package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.LispException;

public abstract class Primitive extends Atome implements Fonction
{
	public SExpr eval_arg(SExpr expr) {
		return expr;
	}
	
	@Override
	public SExpr car() throws LispException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SExpr cdr() throws LispException {
		// TODO Auto-generated method stub
		return null;
	}
}
