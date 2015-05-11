package jus.aoo.lvm.interpretation;

public abstract class FSubr extends Primitive
{
	public SExpr eval_arg(SExpr expr) {
		return expr;
	}
}
