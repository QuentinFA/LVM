package jus.aoo.lvm.interpretation;

public abstract class Atome implements SExpr
{
	protected String str; //Nom de l'atome
	
	public SExpr eval() {
		return this;
	}
	
	public String toString() {
		return str;
	}
}
