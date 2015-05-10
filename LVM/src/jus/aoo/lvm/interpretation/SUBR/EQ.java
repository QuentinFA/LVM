package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.interpretation.Subr;


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
		SExpr arg1 = eval_arg(Context.get("arg1"));
		SExpr arg2 = eval_arg(Context.get("arg2"));
		
		/*if (arg1 instanceof Symbole && arg2 instanceof Symbole)
		{
			if (((Symbole)arg1).getVar().equals(((Symbole)arg2).getVar()))
				return Symbole.TRUE;
			else
				return Nil.NIL;
		}
		if (arg1.equals(arg2))
			return Symbole.TRUE;
		else
			return Nil.NIL;*/
		return null;
	}
}
