package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.*;

public class EVAL extends Subr
{
	public EVAL() {
		str = "eval";
	}
	
	/**Applique la primitive CDR à arg
	 * @return cdr arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException {
		SExpr arg = eval_arg(Context.get("arg1"));
					
		return arg;
	}
}
