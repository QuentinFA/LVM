package jus.aoo.lvm.interpretation;

/**
 * Interface représentant les éléments du langage Lisp
 */
public interface SExpr
{
	/**
	 * Evaluation de la SExpr
	 */
	public SExpr eval();
	
	public SExpr car() throws LispException;
	public SExpr cdr() throws LispException;
	public SExpr cons(SExpr e) throws LispException;
}
