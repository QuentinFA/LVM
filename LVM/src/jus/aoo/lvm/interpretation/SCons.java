package jus.aoo.lvm.interpretation;

import jus.aoo.lvm.environment.Context;
import jus.aoo.lvm.environment.LispException;

/**
 * Un SCons est un couple avec :
 * 		- Une rÃ©fÃ©rence vers un autre SCons (si composÃ© de liste)
 * 		- Un symbole pour une valeur directe
 */
public class SCons implements SList
{
	private SExpr car;
	private SExpr cdr;

	public String toString() 
	{
		if (car instanceof Symbole && cdr instanceof Symbole) //Paire pointï¿½e
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
	public SExpr eval() throws LispException
	{
		Fonction f = Context.getFonction(car.toString()); //null si pas de fonction associée

		if (f != null) //Fonctions
		{	
			
			int nbrMax = f.getNbr_arg(); //Nombre d'argments à récupérer
			
			//Récupérer les arguments
			SExpr cdr_temp = cdr;
			int nbr = 0;
			
			if (cdr_temp.car() instanceof Symbole && cdr_temp.cdr() instanceof Symbole) // (a.a)
			{
				nbr ++;
				if (nbr > nbrMax) throw new LispException("Trop d'arguments dans " + f.toString() +"...");
				Context.addFVar("arg"+nbr, cdr_temp);
			}
			else
			{
				while (cdr_temp instanceof SCons) //Argument
				{		
					nbr ++;
					if (nbr > nbrMax) throw new LispException("Trop d'arguments dans " + f.toString() +"...");
					Context.addFVar("arg"+nbr, cdr_temp.car());
					
					cdr_temp = cdr_temp.cdr();
				}
				if (cdr_temp instanceof Symbole) // (    .a)
				{	
					nbr ++;
					if (nbr > nbrMax) throw new LispException("Trop d'arguments dans " + f.toString() +"...");
					Context.addFVar("arg"+nbr, cdr_temp);
				}
			}
			
			if (nbr != nbrMax) throw new LispException("Pas assez d'arguments dans " + f.toString() +"...");
			
			SExpr result = f.apply(); //Appliquer Fonction
			for (int i=0; i < nbr; i++) //Supprimer arguments
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
