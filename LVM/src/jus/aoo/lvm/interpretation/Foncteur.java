package jus.aoo.lvm.interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Foncteur implements Fonction
{
	protected SExpr definition;
	protected ArrayList<String> args;
	protected HashMap<String, SExpr> tf;
	protected String str;
	
	public int getNbr_arg() {return args.size();}
	
	protected void recup_arg(SExpr arg)
	{
		args = new ArrayList<String>();
		definition = arg.cdr();

		if (arg.car() instanceof Symbole)
			args.add(arg.car().toString());
		else
		{
			SExpr cdr_temp = arg.car();
			
			if (cdr_temp.car() instanceof Symbole && cdr_temp.cdr() instanceof Symbole)
				args.add(cdr_temp.toString());
			else
			{
				while (cdr_temp instanceof SCons)
				{
					if (!(cdr_temp.car() instanceof Nil))
							args.add(cdr_temp.car().toString());
					cdr_temp = cdr_temp.cdr();
				}
				if (cdr_temp instanceof Symbole)
					args.add(cdr_temp.toString());
			}
		}
	}
	
	protected SExpr lier(SExpr expr)
	{
		SExpr temp;
		if (expr instanceof Atome)
		{
			temp = tf.get(expr.toString());
			if (temp != null)
				return temp;
			else
				return expr;
		}
		else
		{
			SExpr s1 = lier(expr.car()), s2 = lier(expr.cdr());
			
			if (s1 instanceof Atome && s2 instanceof Nil)
				return s1;
			else
				return new SCons(s1, s2);
		}
	}
	
	public String toString()
	{
		return str;
	}
}
