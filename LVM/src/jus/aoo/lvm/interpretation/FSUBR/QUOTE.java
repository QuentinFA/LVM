package jus.aoo.lvm.interpretation.FSUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.*;


public class QUOTE extends FSubr
{
	public QUOTE() {
		str = "quote";
	}
	
	@Override
	public SExpr apply() {
		SExpr arg = eval_arg(Context.get("arg1"));
					
		return arg;
	}
}
