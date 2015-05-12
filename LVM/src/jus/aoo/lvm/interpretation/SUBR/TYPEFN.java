package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.*;

public class TYPEFN extends Subr
{
	public TYPEFN() {
		str = "typefn";
	}

	public SExpr apply() throws LispException 
	{
		SExpr e = Context.get("arg1");
		SExpr arg = eval_arg(e);
		
		if (arg instanceof Atome)
		{
			Fonction f = Context.getFonction(arg.toString());
			
			if (f != null)
				if (f instanceof Subr)
					return new Symbole("subr");
				if (f instanceof FSubr)
					return new Symbole("fsubr");
				if (f instanceof Expr)
					return new Symbole("expr");
				if (f instanceof FExpr)
					return new Symbole("fexpr");
		}
		
		throw new LispException(e + " n'est pas une fonction...");
	}
	
	@Override
	public int getNbr_arg() {
		return 1;
	}
}
