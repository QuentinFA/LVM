package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.*;

public class CAR extends Subr
{
	public CAR() {
		str = "car";
	}

	/**Applique la primitive CAR à arg
	 * @return car arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException 
	{
		SExpr arg = eval_arg(Context.get("arg1"));
		
		return arg.car();
	}
}
