package calc.menu.Calcmenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;
import static pt.utl.ist.po.ui.UserInteraction.IO;

import calc.textui.*;
import calc.core.*;
import java.io.IOException;
import java.util.*;
import calc.textui.main.*;

/**
 * This class represents the second command of the CalcMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Open extends Command<Calc>
{
		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
     */
	public Open(Calc app)	
	{
		super(MenuEntry.OPEN, app);
	}
	
	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputString nomeFicheiro = new InputString(f,Message.openFile());
		f.parse();
		if (nomeFicheiro.value() != null){
			  try {
			  	FolhaCalc app=(FolhaCalc.load(nomeFicheiro.value()));
			  	entity().setFolha(app);
			  	menu().entry(2).visible();
					menu().entry(3).visible();
					menu().entry(4).visible();
					menu().entry(5).visible();
			  } 
			  catch (IOException e) {
					throw new InvalidOperation(Message.fileNotFound(nomeFicheiro.value()));
			  }	
			  catch(ClassNotFoundException ei){
			  	throw new InvalidOperation(ei.toString());
			  }
		menu().entry(2).visible();
		menu().entry(3).visible();
		menu().entry(4).visible();
		menu().entry(5).visible();
		}
	}
}
			
			
