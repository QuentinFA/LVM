package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.LispException;

/**
 * Interface représentant les éléments du langage Lisp
 */
public interface SExpr
{
	/**
	 * Evaluer l'expression
	 */
	public SExpr eval();
	
	/**
	 * Renvoie la cha�ne de caract�res associ�es
	 */
	public String toString();
	
	public SExpr car() throws LispException;
	public SExpr cdr() throws LispException;
}
