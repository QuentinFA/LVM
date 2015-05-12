package jus.aoo.lvm.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.aoo.lvm.interpretation.Fonction;
import jus.aoo.lvm.interpretation.SExpr;

/**
 * Contient les associations des variables, fonctions, et paramètre des fonctions</br>
 * Le contexte est représenté sous forme de "pile" de HashMap, afin de pouvoir créer des variables avec
 * des noms déjà existant sans effacer les anciennes valeurs
 * notamment pour le passage de paramètres fonctionnels / paramètres effectifs (ex: arg1, ..., argN)
 */
public class Context
{
	private static List<Map<String, SExpr>> ctx; //Variables et paramètres
	private static Map<String, Fonction> tf; //Fonctions
	
	public static final Context CONTEXT = new Context();
	
	private Context()
	{
		ctx = new ArrayList<Map<String, SExpr>>();
		ctx.add(new HashMap<String, SExpr>());
		ctx.add(new HashMap<String, SExpr>());

		tf = new HashMap<String, Fonction>();
	}
	
	/**
	 * Renvoie l'expression associée à un nom de variable
	 * @param s : le nom de la variable dont on veut récupérer la valeur
	 * @return SExpr correspondante / null si aucune association
	 */
	public static SExpr get(String s)
	{
		for(int i = ctx.size() - 1; i >= 0; i--)
			if (ctx.get(i).containsKey(s))
				return ctx.get(i).get(s);

		return null;
	}
	
	/**
	 * Renvoie la fonction associée à un nom de fonction
	 * @param s : le nom de la fonction que l'on veut récupérer
	 * @return Fonction correspondante / null si aucune association
	 */
	public static Fonction getFonction(String s)
	{
		Fonction d = null;
		if (tf.containsKey(s))
			d = tf.get(s);
		
		return d;
	}
	
	public static int getNbrParam()
	{
		return ctx.get(1).size();
	}
	
	/**
	 * Ajout d'une fonction nommée s dans le contexte</br>
	 * Si elle est déjà présente l'ancienne fonction est remplacée
	 * @param s : le nom de la fonction à ajouter
	 * @param v : la fonction
	 */
	public static void addFonction(String s, Fonction f)
	{
		tf.put(s, f);
	}
	
	/**
	 * Ajout d'un paramètre nommé s dans le contexte</br>
	 * S'il est déjà présent, un nouveau contexte contenant la nouvelle valeur est ajouté,
	 * sinon, il est simplement ajouté dans le contexte actuel.</br>
	 * Utilisé pour passer les paramètres à une fonction.
	 * @param s : le nom du paramètre à ajouter
	 * @param v : sa valeur associée
	 */
	public static void addFVar(String s, SExpr v)
	{
		if (ctx.get(ctx.size()-1).containsKey(s)) 
		{
			Map<String, SExpr> newCtx = new HashMap<String, SExpr>();
			newCtx.put(s, v);
			ctx.add(newCtx);
		}
		else
			ctx.get(ctx.size()-1).put(s, v);
		
	}
	
	/**
	 * Ajout d'une variable nommée s dans le contexte</br>
	 * Si ce nom de variable est déjà utilisé, alors la valeur correspondante est remplacée par la nouvelle.
	 * @param s : le nom de la variable à ajouter
	 * @param v : sa valeur associée
	 */
	public static void addVar(String s, SExpr v)
	{
		ctx.get(0).put(s, v);
	}
	
	/**
	 * Supprime une variable nommée s du contexte</br>
	 * Si le contexte le plus haut est vide (et différent du contexte de base), il est effacé</br>
	 * Utilisé pour effacer les paramètres d'une fonction après utilisation de celle-ci
	 * @param s : le nom de la variable à supprimer
	 */
	public static void delVar(String s)
	{
		int i = ctx.size() - 1;
		
		while(i >= 0 && ctx.get(i).get(s) == null)
			i--;
		
		if (i <= 1)
			return;
		
		ctx.get(i).remove(s);
		
		if(i != 0 && ctx.get(i).isEmpty())
			ctx.remove(i);
	}
}