package jus.aoo.lvm.interpretation.FSUBR;
import java.util.ArrayList;

import jus.aoo.lvm.environment.Context;
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
		
		ArrayList<SExpr> args = new ArrayList<SExpr>();
		for (int i=0; i < Context.getNbrParam(); i++)
			args.add(eval_arg(Context.get("arg"+(i+1))));
		
		for (int i=0; i < Context.getNbrParam(); i++)
		{
			if ((args.get(i).car().eval()).equals(Symbole.TRUE))
				return args.get(i).cdr().eval();
		}

		return Nil.NIL;
	}

	@Override
	public int getNbr_arg() {
		return -1;
	}

}
