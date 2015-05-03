package jus.aoo.lvm.interpretation;
import jus.aoo.lvm.environment.Context;

public class Symbole extends Atome
{
	public static final SExpr TRUE = new Symbole("t");
	
	public Symbole(String s) {
		str = s;
		Context.addVar(str, this);
	}
	
	public SExpr car() throws LispException {throw new LispException();}
	public SExpr cdr() throws LispException {throw new LispException();}
}
