package jus.aoo.lvm.interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Foncteur implements Fonction
{
	protected SExpr definition;
	protected ArrayList<String> args;
	protected HashMap<String, SExpr> tf;
	
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
}
