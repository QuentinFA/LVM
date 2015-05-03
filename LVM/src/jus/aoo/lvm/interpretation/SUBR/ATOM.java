package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.*;

public class ATOM extends Subr
{
	public ATOM() {
		str = "atom";
	}
	
	/**Applique la primitive ATOM à arg
	 * @return atom arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException {
		SExpr arg = Context.get("arg1");
		
		if (arg instanceof Atome)
			return Symbole.TRUE;
		if (arg instanceof SList)
			return Nil.NIL;
					
		return null;
	}
}
