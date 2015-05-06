package jus.aoo.lvm.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.interpretation.Symbole;

/**
 * Représente le stockage des variables et de leur valeur.</br>
 * Le contexte est représenté sous forme de "pile" de HashMap, afin de pouvoir créer des variables avec
 * des noms déjà existant sans effacer les anciennes valeurs 
 * (notamment pour le passage de paramètres fonctionnels / paramètres effectifs).
 */
public class Context
{
	private static List<Map<Symbole, SExpr>> ctx;
	
	public static final Context CONTEXT = new Context();
	
	private Context()
	{
		ctx = new ArrayList<Map<Symbole, SExpr>>();
		ctx.add(new HashMap<Symbole, SExpr>());
	}
	
	/**
	 * Fonction de récupération d'une valeur à partir d'un symbole dans le contexte
	 * @param s : Le symbole dont ont veut récupérer la valeur associée
	 * @return SExpr correspondante, ou null si non présente
	 */
	public static SExpr get(String s)
	{
		SExpr r;
		
		for(int i = ctx.size() - 1; i >= 0; i--)
		{
			if((r = ctx.get(i).get(s)) != null)
				return r;
		}
		
		return null;
	}
	
	/**
	 * Ajout d'un symbole et de sa valeur dans le contexte</br>
	 * S'il est déjà présent, un nouveau contexte contenant la nouvelle valeur est ajouté,
	 * sinon, il est simplement ajouté dans le contexte actuel.</br>
	 * Utilisé pour passer les paramàtres à une fonction.
	 * @param s : Le symbole à ajouter
	 * @param v : La valeur correspondante
	 */
	public static void addFVar(Symbole s, SExpr v)
	{
		if(ctx.get(ctx.size() - 1).get(s) != null)
		{
			Map<Symbole, SExpr> newCtx = new HashMap<Symbole, SExpr>();
			newCtx.put(s, v);
			ctx.add(newCtx);
		}
		else
			ctx.get(ctx.size() - 1).put(s, v);
	}
	
	/**
	 * Fonction d'ajout d'une variable.</br>
	 * Si ce nom de variable est déjà utilisé, alors la valeur correspondante est remplacée par la nouvelle.
	 * @param s : Le symbole auquel associer la valeur
	 * @param v : La valeur correspondant au symbole
	 */
	public static void addVar(Symbole s, SExpr v)
	{
		ctx.get(ctx.size() - 1).put(s, v);
	}
	
	/**
	 * Enlève un lien d'un symbole et une valeur du contexte.</br>
	 * Si le contexte le plus haut est vide (et différent du contexte de base), il est effacé.</br>
	 * Utilisé pour effacer les variables ajoutées aprés un passage de paramètre à une fonction.
	 * @param s : Le Symbole à effacer
	 */
	public static void delVar(Symbole s)
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