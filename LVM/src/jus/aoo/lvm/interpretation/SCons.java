package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.Context;

/**
 * Un SCons est un couple avec :
 * 		- Une référence vers un autre SCons (si composé de liste)
 * 		- Un symbole pour une valeur directe
 */
public class SCons implements SList
{
	private SExpr car;
	private SExpr cdr;

	public String toString() {
		
		if (car instanceof Symbole && cdr instanceof Symbole) //Paire point�e
			return "(" + car.toString() + "." + cdr.toString() + ")";
		
		if (car instanceof SCons)
		{
			if (cdr instanceof SCons)
				return "(" + car.toString() + ")" + " " + "(" + cdr.toString() + ")";
			return "(" + car.toString() + ")";
		}
		else
		{
			if (cdr instanceof Nil)
				return car.toString();
			else
				return car.toString() + " " + cdr.toString();
		}
	}
	
	public SCons(SExpr car, SExpr cdr) 
	{	
		this.car=car;
		this.cdr=cdr;
	}

	@Override
	public SExpr eval() {
		if (car instanceof Fonction)
		{
			SExpr cdr_temp = cdr;
			int nbr = 1;
			while (cdr_temp instanceof SCons)
			{
				if (cdr_temp.car() instanceof SCons)
					Context.addFVar("arg"+nbr, cdr_temp.car().eval());
				else
					Context.addFVar("arg"+nbr, cdr_temp.car().eval());
				
				nbr ++;
				cdr_temp = cdr_temp.cdr();
			}
			if (cdr_temp instanceof Atome)
				Context.addFVar("arg"+nbr, cdr_temp.eval());
			
			SExpr result = ((Fonction)car).apply();
			for (int i=1; i <= nbr; i++)
				Context.delVar("arg"+nbr);
			return result;
		}
		
		car = car.eval();
		cdr = cdr.eval();
		return this;
	}

	@Override
	public SExpr car() throws LispException {
		return car;
	}

	@Override
	public SExpr cdr() throws LispException {
		return cdr;
	}
}
