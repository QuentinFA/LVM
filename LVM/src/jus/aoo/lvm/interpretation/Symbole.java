package jus.aoo.lvm.interpretation;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;

public class Symbole extends Atome
{
	public static final SExpr TRUE = new Symbole("t");
	
	public SExpr eval() {
		
		SExpr expr = Context.get(str);
		if (expr != null)
			return expr;
		else
			return this;
	}
	
	public Symbole(String s) {
		str = s;
	}
	
	public SExpr car() throws LispException {throw new LispException("Exception: car _symbole_");}
	public SExpr cdr() throws LispException {throw new LispException("Exception: cdr _symbole_");}
}
