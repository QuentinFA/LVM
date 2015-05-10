package jus.aoo.lvm.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.aoo.lvm.interpretation.SExpr;

/**
 * Repr�sente le stockage des variables et de leur valeur.</br>
 * Le contexte est repr�sent� sous forme de "pile" de HashMap, afin de pouvoir cr�er des variables avec
 * des noms d�j� existant sans effacer les anciennes valeurs 
 * (notamment pour le passage de param�tres fonctionnels / param�tres effectifs).
 */
public class Context
{
	private static List<Map<String, SExpr>> ctx;
	public static final Context CONTEXT = new Context();
	
	private Context()
	{
		ctx = new ArrayList<Map<String, SExpr>>();
		ctx.add(new HashMap<String, SExpr>());
	}
	
	/**
	 * Fonction de r�cup�ration d'une valeur � partir d'un String dans le contexte
	 * @param s : Le String dont ont veut r�cup�rer la valeur associ�e
	 * @return SExpr correspondante, ou null si non pr�sente
	 */
	public static SExpr get(String s)
	{
		for(int i = ctx.size() - 1; i >= 0; i--)
		{
			if(ctx.get(i).containsKey(s))
				return ctx.get(i).get(s);
		}
		
		return null;
	}
	
	/**
	 * Ajout d'un String et de sa valeur dans le contexte</br>
	 * S'il est d�j� pr�sent, un nouveau contexte contenant la nouvelle valeur est ajout�,
	 * sinon, il est simplement ajout� dans le contexte actuel.</br>
	 * Utilis� pour passer les param�tres � une fonction.
	 * @param s : Le String � ajouter
	 * @param v : La valeur correspondante
	 */
	public static void addFVar(String s, SExpr v)
	{
		if(ctx.get(ctx.size() - 1).get(s) != null)
		{
			Map<String, SExpr> newCtx = new HashMap<String, SExpr>();
			newCtx.put(s, v);
			ctx.add(newCtx);
		}
		else
			ctx.get(ctx.size() - 1).put(s, v);
	}
	
	/**
	 * Fonction d'ajout d'une variable.</br>
	 * Si ce nom de variable est d�j� utilis�, alors la valeur correspondante est remplac�e par la nouvelle.
	 * @param s : Le String auquel associer la valeur
	 * @param v : La valeur correspondant au String
	 */
	public static void addVar(String s, SExpr v)
	{
		ctx.get(ctx.size() - 1).put(s, v);
	}
	
	/**
	 * Enl�ve un lien d'un String et une valeur du contexte.</br>
	 * Si le contexte le plus haut est vide (et diff�rent du contexte de base), il est effac�.</br>
	 * Utilis� pour effacer les variables ajout�es apr�s un passage de param�tre � une fonction.
	 * @param s : Le String � effacer
	 */
	public static void delVar(String s)
	{
		int i = ctx.size() - 1;
		
		while(i >= 0 && ctx.get(i).get(s) == null)
			i--;
		
		if (i < 0)
			return;
		
		ctx.get(i).remove(s);
		
		if(i != 0 && ctx.get(i).isEmpty())
			ctx.remove(i);
	}
}