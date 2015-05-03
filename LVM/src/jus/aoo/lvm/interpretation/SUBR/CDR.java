package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.*;

public class CDR extends Subr
{
	public CDR() {
		str = "cdr";
	}
	
	/**Applique la primitive CDR à arg
	 * @return cdr arg
	 * @param SExpr arg
	 */
	public SExpr apply() throws LispException {
		SExpr arg = Context.get("arg1");
		
		if (arg instanceof Atome)
			return ((Atome)arg).car();
		if (arg instanceof SList)
			return ((SList)arg).cdr();
					
		return null;
	}
}
