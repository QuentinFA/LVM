package jus.aoo.lvm.environment;

import java.util.jar.Attributes;

import jus.aoo.lvm.interpretation.Nil;
import jus.aoo.lvm.interpretation.Symbole;
import jus.aoo.lvm.interpretation.FSUBR.QUOTE;
import jus.aoo.lvm.interpretation.SUBR.ATOM;
import jus.aoo.lvm.interpretation.SUBR.CAR;
import jus.aoo.lvm.interpretation.SUBR.CDR;
import jus.aoo.lvm.interpretation.SUBR.CONS;
import jus.aoo.lvm.interpretation.SUBR.EQ;
import jus.aoo.lvm.interpretation.SUBR.EVAL;
import jus.aoo.lvm.javacc.Reader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
* @author morat
*
*/
public class XmlHandler extends DefaultHandler {
	/** la m�thode traitant de l'�v�nement d�but d'�l�ment
	* @param tag le nom de l'�l�ment
	* @param attrs la liste des attributs de cet �l�ment
	* @throws SAXException
	*/
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts)
	throws SAXException{
	//if(localName=="type") traiteType(atts);
	if(localName=="variable") traiteVariable(atts);
	if(localName=="primitive") traitePrimitive(atts);
	if(localName=="load") traiteLoad(atts);
	//if(localName=="loadlibrary") traiteLoadLibrary(atts);
	}
	private void traitePrimitive(Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		try {
			String name = atts.getValue("name");
			switch (name.toLowerCase()) {
            case "cdr" : Context.addVar("cdr", new CDR());
                     break;
            case "car" : Context.addVar("car", new CAR());
            break;
            case "atom" : Context.addVar("atom", new ATOM());
            break;
            case "eq" : Context.addVar("eq", new EQ());
            break;
            case "cons" : Context.addVar("cons", new CONS());
            break;
            case "eval" : Context.addVar("eval", new EVAL());
            break;
            case "quote" : Context.addVar("quote", new QUOTE());
            break;
            default: break;
			}	 
			} 
			catch(Exception e) {
			throw new SAXException("traitePrimitive", e);
			}
		
		
	}
	private void traiteVariable(Attributes atts) throws SAXException {
		// TODO Auto-generated method stub
		
		try {
			String name = atts.getValue("name");
			switch (name.toLowerCase()) {
            case "()" : Context.addVar("()", Nil.NIL);
                     break;
            case "'" : Context.addVar("'", new Symbole ("quote"));
            break;
            case "vrai" : Context.addVar("vrai", new Symbole ("t"));
            break;
            case "faux" : Context.addVar("faux", new Symbole ("()"));
            break;
            default: break;
			}	 
			} 
			catch(Exception e) {
			throw new SAXException("traiteVariable", e);
			}
		
	}
//	private void traiteType(Attributes atts) {
		// TODO Auto-generated method stub
		
	//}
	/** Traite un chargement de fichier
	* @param attrs les attributs
	* @throws SAXException
	*/
	protected void traiteLoad(Attributes attrs) throws SAXException {
	try {
	String name = attrs.getValue("name");
	Reader.importeFile(name);
	} catch(Exception e) {
	throw new SAXException("traiteLoad", e);
	}
	}
	//<Autres m�thodes de traitements>
	//partie ErrorHandler
	// treat validation errors as fatal
	/** traitement de erreur*/
	public void error(SAXParseException e)throws SAXParseException {throw e;}
	// dump warnings too
	/** traitement de warning*/
	public void warning(SAXParseException err) throws SAXParseException {
	Console.println("** Warning"+", line "+err.getLineNumber()+", uri "+err.getSystemId());
	Console.println(" " + err.getMessage());
	}
	/**
	* Acquisition des �l�ments de configuration de la session lisp
	* @param file le fichier de configuration
	*/
	public static void read(String file) {
	//lecture du fichier de description du contexte
	try{
	XmlHandler handler = new XmlHandler();
	XMLReader parser = XMLReaderFactory.createXMLReader();
	parser.setContentHandler(handler); parser.setErrorHandler(handler);
	parser.parse(new InputSource(Reader.class.getResourceAsStream(file)));
	}catch (SAXParseException err) {
	System.err.println("** Error"+", line "+err.getLineNumber()+", uri "+err.getSystemId());
	System.err.println(" " + err.getMessage());
	}catch (SAXException e) {
	Console.printStack((e.getException()!=null)?e.getException():e);
	}catch (Throwable t) {
	Console.printStack(t);
	}
	}
}
