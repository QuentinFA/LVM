package jus.aoo.lvm.environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jus.aoo.lvm.interpretation.Fonction;
import jus.aoo.lvm.interpretation.SExpr;

/**
 * Contient les associations des variables, fonctions, et param�tre des fonctions</br>
 * Le contexte est repr�sent� sous forme de "pile" de HashMap, afin de pouvoir cr�er des variables avec
 * des noms d�j� existant sans effacer les anciennes valeurs
 * notamment pour le passage de param�tres fonctionnels / param�tres effectifs (ex: arg1, ..., argN)
 */
public class Context
{
	private static List<Map<String, SExpr>> ctx; //Variables et param�tres
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
	 * Renvoie l'expression associ�e � un nom de variable
	 * @param s : le nom de la variable dont on veut r�cup�rer la valeur
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
	 * Renvoie la fonction associ�e � un nom de fonction
	 * @param s : le nom de la fonction que l'on veut r�cup�rer
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
	 * Ajout d'une fonction nomm�e s dans le contexte</br>
	 * Si elle est d�j� pr�sente l'ancienne fonction est remplac�e
	 * @param s : le nom de la fonction � ajouter
	 * @param v : la fonction
	 */
	public static void addFonction(String s, Fonction f)
	{
		tf.put(s, f);
	}
	
	/**
	 * Ajout d'un param�tre nomm� s dans le contexte</br>
	 * S'il est d�j� pr�sent, un nouveau contexte contenant la nouvelle valeur est ajout�,
	 * sinon, il est simplement ajout� dans le contexte actuel.</br>
	 * Utilis� pour passer les param�tres � une fonction.
	 * @param s : le nom du param�tre � ajouter
	 * @param v : sa valeur associ�e
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
	 * Ajout d'une variable nomm�e s dans le contexte</br>
	 * Si ce nom de variable est d�j� utilis�, alors la valeur correspondante est remplac�e par la nouvelle.
	 * @param s : le nom de la variable � ajouter
	 * @param v : sa valeur associ�e
	 */
	public static void addVar(String s, SExpr v)
	{
		ctx.get(0).put(s, v);
	}
	
	/**
	 * Supprime une variable nomm�e s du contexte</br>
	 * Si le contexte le plus haut est vide (et diff�rent du contexte de base), il est effac�</br>
	 * Utilis� pour effacer les param�tres d'une fonction apr�s utilisation de celle-ci
	 * @param s : le nom de la variable � supprimer
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