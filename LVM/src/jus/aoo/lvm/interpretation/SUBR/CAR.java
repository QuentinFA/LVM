package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.*;

public class CAR extends Subr
{
	public CAR() {
		str = "car";
	}

	/**Applique la primitive CAR � arg
	 * @return car arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException {
		SExpr arg = Context.get("arg1");
		
		if (arg instanceof Atome)
			return ((Atome)arg).car();
		if (arg instanceof SList)
			return ((SList)arg).car();
		
		return null;
	}
}