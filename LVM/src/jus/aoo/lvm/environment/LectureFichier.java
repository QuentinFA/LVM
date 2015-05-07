package jus.aoo.lvm.environment;

import java.io.FileNotFoundException;
import java.util.Scanner;

import jus.aoo.lvm.interpretation.SExpr;
import jus.aoo.lvm.javacc.ParseException;
import jus.aoo.lvm.javacc.Reader;

public class LectureFichier extends LVM implements Support {
	
	private SExpr e;
	private boolean boo;
	private Scanner s;
	
	
	public LectureFichier(String file) throws LispException, ParseException, FileNotFoundException{
		
		super();
		this.boo=true;
		java.io.File fichier = new java.io.File(file);
		s = new Scanner(fichier);
		try
		{
			SExpr se = Reader.importe(file);
			this.e= se;
			
		}
		catch (LispException | ParseException | FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public void executer() throws LispException, ParseException {
		// TODO Auto-generated method stub
		SExpr se ;
		System.out.println("lecture du fichier, evaluation et affichage: \n");
		System.out.println(e.eval().toString()); 
		
			while (boo && s.hasNextLine()) {
				// ligne suivante
				String ligne = s.nextLine();
				// Appel à l'évaluateur
				se= Reader.read(ligne);
				this.e= se;
				System.out.println(e.eval().toString()); 
				
			}
		
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.boo=false;
	}

	

}
