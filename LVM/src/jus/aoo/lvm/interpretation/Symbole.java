package jus.aoo.lvm.interpretation;
import jus.aoo.lvm.environment.LispException;

public class Symbole extends Atome
{
	public static final SExpr TRUE = new Symbole("t");
	
	public SExpr eval() {
			return this;
	}
	
	public Symbole(String s) {
		str = s;
	}
	
	public SExpr car() throws LispException {throw new LispException();}
	public SExpr cdr() throws LispException {throw new LispException();}
}
