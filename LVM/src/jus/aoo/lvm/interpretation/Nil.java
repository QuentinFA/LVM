package jus.aoo.lvm.interpretation;

/**
 * Représente le symbole nil () en Lisp
 */
public class Nil extends Atome implements SList
{
	public String toString()
	{
		return "()";
	}
}
