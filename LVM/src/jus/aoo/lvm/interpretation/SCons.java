package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;

/**
 * Un SCons est un couple avec :
 * 		- Une référence vers un autre SCons (si composé de liste)
 * 		- Un symbole pour une valeur directe
 */
public class SCons implements SList
{
	private SExpr car;
	private SExpr cdr;

	public String toString() 
	{
		if (car instanceof Symbole && cdr instanceof Symbole) //Paire point�e
			return car.toString() + "." + cdr.toString();
		
		if (car instanceof SCons)
		{
			if (cdr instanceof Nil)
				return "(" + car.toString() + ")";
			else if (cdr instanceof Atome)
				return "(" + car.toString() + ")" + " " + cdr.toString();
			else
				return "(" + car.toString() + ")" + " " + cdr.toString();
		}
		else
		{
			if (cdr instanceof Nil)
				return car.toString();
			else if (cdr instanceof Atome)
				return car.toString() + " " + cdr.toString();
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
	public SExpr eval() 
	{
		if (car instanceof Fonction)
		{
			SExpr cdr_temp = cdr;
			int nbr = 1;
			
			if (cdr_temp.car() instanceof Atome && cdr_temp.cdr() instanceof Atome)
				Context.addFVar("arg"+nbr, cdr_temp);
			else
			{
				while (cdr_temp instanceof SCons)
				{
					//Liaison des paramètres
					Context.addFVar("arg"+nbr, cdr_temp.car());
					
					nbr ++;
					cdr_temp = cdr_temp.cdr();
				}
				if (cdr_temp instanceof Atome)
					Context.addFVar("arg"+nbr, cdr_temp);
			}
			
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
