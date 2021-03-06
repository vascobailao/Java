package calc.textui;

import pt.utl.ist.po.ui.*;

import calc.menu.CalcMenu;
import calc.core.FolhaCalc;
import calc.core.*;
import java.io.*;

/**
 * This is a sample FolhaDeCalculo.
 * 
 *@author :		group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Calc implements Serializable {
	private static final long serialVersion=1234567346432L;
	private FolhaCalc folha;
	
	/**
	 * initializes a new worksheet
	 * 
	 */
	 
	public Calc(){
		this.folha=new FolhaCalc();
	}
	 
	/**
	 * @return allows access to the spredsheet 
	 * 
	 */
	  
	public FolhaCalc getFolha(){
		return this.folha;
	}
	
	/**
	 * @param allows you to set a spredsheet as the current
	 * 
	 */
	
	public void setFolha(FolhaCalc folha){
			this.folha=folha;
	}
	
	/**
	 * @param args
	 * initializes the spreadsheet imported file, making parseFile to your content 
	 */
	 
	public static void main(String args[]) throws IOException {
		Calc app=new Calc();
		String filename =System.getProperty("import");
		if(filename!=null){
			app.setFolha(Parser.parseFile(filename));
		}
		Menu m = new CalcMenu(app);
		if(filename==null)
		{	
			m.entry(2).invisible();
			m.entry(3).invisible();
			m.entry(4).invisible();
			m.entry(5).invisible();
		}	
		m.open();	
	}
}

