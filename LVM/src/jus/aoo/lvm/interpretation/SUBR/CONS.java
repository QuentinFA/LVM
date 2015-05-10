package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.SCons;
import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.interpretation.Subr;

public class CONS extends Subr 
{
	public CONS() {
		str = "cons";
	}
	
	/**Applique la primitive CONS à arg1 et arg2
	 * @return cons arg1 arg2
	 * @param SExpr arg1
	 * @param SExpr arg2
	 */
	public SExpr apply() throws LispException {
		SExpr arg1 = eval_arg(Context.get("arg1"));
		SExpr arg2 = eval_arg(Context.get("arg2"));
		
		return new SCons(arg1, arg2);
	}
}
