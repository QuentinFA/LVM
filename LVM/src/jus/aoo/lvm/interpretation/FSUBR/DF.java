package jus.aoo.lvm.interpretation.FSUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.interpretation.FSubr;
import jus.aoo.lvm.interpretation.*;


public class DF extends FSubr
{
	public DF() {
		str = "df";
	}
	
	/**
	 * 
	 */
	public SExpr apply() {
		SExpr arg1 = eval_arg(Context.get("arg1"));
		SExpr arg2 = eval_arg(Context.get("arg2"));
		SExpr arg3 = eval_arg(Context.get("arg3"));
		//System.out.println(new SCons(arg2, arg3));
		//Récupérer le nom
		
		Context.addFonction(arg1.toString(), new FExpr(new SCons(arg2, arg3)));
					
		return Symbole.TRUE;
	}

}
