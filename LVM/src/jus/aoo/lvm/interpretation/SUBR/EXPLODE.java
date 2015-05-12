package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.*;

public class EXPLODE extends Subr
{
	public EXPLODE() {
		str = "explode";
	}

	public SExpr apply() throws LispException 
	{
		return eval_arg(Context.get("arg1"));
	}
	
	@Override
	public int getNbr_arg() {
		return 1;
	}
}
