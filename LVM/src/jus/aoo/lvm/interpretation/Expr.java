package jus.aoo.lvm.interpretation;

import java.util.HashMap;
import jus.aoo.lvm.environment.Context;

public class Expr extends Foncteur
{
	public Expr(SExpr arg)
	{
		recup_arg(arg);
	}
	
	@Override
	public SExpr apply() 
	{
		tf = new HashMap<String, SExpr>();
		for (int i=0; i < args.size(); i++)
			tf.put(args.get(i), eval_arg(Context.get("arg"+(i+1))));

		if (args.size() > 0)
			return (lier(definition)).eval();
		else
			return definition.eval();
	}

	public SExpr eval_arg(SExpr expr) {
		return expr.eval();
	}
}
