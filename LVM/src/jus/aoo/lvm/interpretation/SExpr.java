package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.LispException;

/**
 * Interface reprÃ©sentant les Ã©lÃ©ments du langage Lisp
 */
public interface SExpr
{
	/**
	 * Evaluer l'expression
	 */
	public SExpr eval();
	
	/**
	 * Renvoie la chaîne de caractères associèes
	 */
	public String toString();
	
	public SExpr car() throws LispException;
	public SExpr cdr() throws LispException;
}
