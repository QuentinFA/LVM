package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.*;


public class EQ extends Subr
{
	public EQ() {
		str = "eq";
	}
	
	/**Applique la primitive EQ à arg
	 * @return eq arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException
	{
		SExpr arg1 = Context.get("arg1");
		SExpr arg2 = Context.get("arg2");
		
		if (arg1.equals(arg2))
			return Symbole.TRUE;
		else
			return Nil.NIL;
	}
}
