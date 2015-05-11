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
			else
				return "(" + car.toString() + ")" + " " + cdr.toString();
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
	public SExpr eval() 
	{
		Fonction f = Context.getFonction(car.toString());
		
		if (f != null)
		{	
			SExpr cdr_temp = cdr;
			int nbr = 0;
			
			if (cdr_temp.car() instanceof Symbole && cdr_temp.cdr() instanceof Symbole)
				Context.addFVar("arg"+nbr, cdr_temp);
			else
			{
				while (cdr_temp instanceof SCons)
				{		
					nbr ++;
					//Liaison des paramètres
					Context.addFVar("arg"+nbr, cdr_temp.car());
					
					cdr_temp = cdr_temp.cdr();
				}
				if (cdr_temp instanceof Symbole)
				{	
					nbr ++;
					Context.addFVar("arg"+nbr, cdr_temp);
				}
			}

			SExpr result = f.apply();
			for (int i=0; i < nbr; i++)
				Context.delVar("arg"+(i+1));
			
			return result;
		}
		return new SCons(car.eval(), cdr.eval());
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
