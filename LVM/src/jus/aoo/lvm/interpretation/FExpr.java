package jus.aoo.lvm.interpretation;

import java.util.ArrayList;
import java.util.HashMap;

import jus.aoo.lvm.environment.Context;

public class FExpr extends Foncteur
{
	public FExpr(SExpr arg)
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
					args.add(cdr_temp.car().toString());
					cdr_temp = cdr_temp.cdr();
				}
				if (cdr_temp instanceof Symbole)
					args.add(cdr_temp.toString());
			}
		}
	}
	@Override
	public SExpr apply() 
	{
		tf = new HashMap<String, SExpr>();
		for (int i=0; i < args.size(); i++)
			tf.put(args.get(i), eval_arg(Context.get("arg"+(i+1))));
		
		if (args.size() > 0)
			return lier(definition);
		else
			return definition;
	}

	public SExpr eval_arg(SExpr expr) {
		return expr;
	}
}
