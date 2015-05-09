package jus.aoo.lvm.environment;

import java.io.Serializable;
import java.util.ResourceBundle;

import jus.aoo.lvm.javacc.ParseException;
import jus.aoo.lvm.javacc.Reader;
import jus.util.*;

public class LVM1 implements Serializable {

	
	public	LVM1() {
		/*try {
		//Console.println(ResourceBundle.getBundle("LispEnter"));
		XmlHandler.read("/Lisp.xml");
		} catch (LispException e) {
		Console.println("toplevel:");
		Console.printStack(e);
		}
	*/	//<appel du toplevel >
		
		while (true) {
			Console.print(">");
			try { Console.println(Reader.read().eval().toString());
			} catch (LispException | ParseException e) { Console.println(((Throwable) e).getMessage());
			} catch (Exception e) { Console.println("unexpectedException"); e.printStackTrace();
			} finally {}
			}
		}
		/**
		* arrêt de la machine lisp
		*/
		public static void quit() {
		//Console.println(Resources.getBundle("LispExit"));
		System.exit(0);
		}
		//…
		}

