package jus.aoo.lvm.interpretation.SUBR;
import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;
import jus.aoo.lvm.interpretation.*;

public class IMPLODE extends Subr
{
	public IMPLODE() {
		str = "implode";
	}

	public SExpr apply() throws LispException 
	{
		SExpr e = eval_arg(Context.get("arg1"));
		String s = "";
		
		while (e != null)
		{
			if (e instanceof Atome)
				throw new LispException("L'argument doit être une liste de caractères...");
			if (e.car() instanceof SList)
				throw new LispException("Erreur argument...");
			s += e.car().toString();
			
			if (e.cdr() instanceof Atome)
				break;
			
			if (e.cdr() instanceof Symbole)
				s += e.cdr().toString();

			e = e.cdr();
		}
		
		return new Symbole(s);
	}
	
	@Override
	public int getNbr_arg() {
		return 1;
	}
}
