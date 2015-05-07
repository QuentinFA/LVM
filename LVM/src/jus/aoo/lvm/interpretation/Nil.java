package jus.aoo.lvm.interpretation;
import jus.aoo.lvm.environment.LispException;

/**
 * Représente le symbole nil () en Lisp
 */
public class Nil extends Atome implements SList
{
	public static final SExpr NIL = new Nil();
	
	private Nil() {
		str = "()";
	}

	//PRIMITIVE	
	@Override
	public SExpr car() throws LispException {
		return this;
	}

	@Override
	public SExpr cdr() throws LispException {
		return this;
	}
}
