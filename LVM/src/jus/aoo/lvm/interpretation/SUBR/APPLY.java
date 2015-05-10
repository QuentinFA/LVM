package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.interpretation.Subr;

public class APPLY extends Subr
{
	public APPLY() {
		str = "apply";
	}

	@Override
	public SExpr apply() {
		SExpr arg = eval_arg(Context.get("arg1"));
		
		return arg.eval();
	}
}
