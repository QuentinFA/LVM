package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.Atome;
import jus.aoo.lvm.interpretation.*;

public class SET extends Subr
{
	public SET() {
		str = "set";
	}
	
	public SExpr apply() throws LispException {
		SExpr arg1 = eval_arg(Context.get("arg1"));
		SExpr arg2 = eval_arg(Context.get("arg2"));
		
		if (arg1 instanceof Atome)
			Context.addVar(arg1.toString(), arg2);
		else 
			throw new LispException("Le paramètre doit être un nom de variable dans set...");
					
		return Symbole.TRUE;
	}
	
	@Override
	public int getNbr_arg() {
		return 2;
	}
}
