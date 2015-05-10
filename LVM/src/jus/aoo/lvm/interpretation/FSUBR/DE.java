package jus.aoo.lvm.interpretation.FSUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.FSubr;
import jus.aoo.lvm.interpretation.SExpr;


public class DE extends FSubr
{
	public DE() {
		str = "de";
	}
	
	/**
	 * 
	 */
	public SExpr apply() {
		SExpr arg = eval_arg(Context.get("arg1"));
		
		//Récupérer le nom
		Context.addVar(arg.car().toString(), arg.cdr());
					
		return arg;
	}
}
