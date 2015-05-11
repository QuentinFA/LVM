package jus.aoo.lvm.interpretation;

public interface Fonction
{
	public SExpr apply();
	
	public SExpr eval_arg(SExpr expr);
	
	public int getNbr_arg();
}
