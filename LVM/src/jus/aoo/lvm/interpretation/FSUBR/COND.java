package jus.aoo.lvm.interpretation.FSUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.FSubr;
import jus.aoo.lvm.interpretation.*;


public class COND extends FSubr
{
	
	public COND() {
		str = "cond	";
	}
	
	/**
	 * 
	 */
	public SExpr apply() {
		
		SExpr arg1 = eval_arg(Context.get("arg1"));
		SExpr cond = (arg1.car()).eval();
		SExpr condTrue = arg1.cdr();

		SExpr condFalse = eval_arg(Context.get("arg2"));
		if (cond.equals(Symbole.TRUE))
			return condTrue;
		else
			return condFalse;
	}

	@Override
	public int getNbr_arg() {
		return 2;
	}

}
