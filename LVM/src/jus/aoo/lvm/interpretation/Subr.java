package jus.aoo.lvm.interpretation;

public abstract class Subr extends Primitive
{
	public SExpr eval_arg(SExpr expr) {
		//System.out.println(expr);
		return expr.eval();
	}
}
