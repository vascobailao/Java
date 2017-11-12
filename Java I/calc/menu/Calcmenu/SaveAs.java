package calc.menu.Calcmenu;


import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;

import calc.textui.main.MenuEntry;
import calc.core.*;
import calc.textui.*;
import calc.textui.main.*;
import java.io.*;

/**
 * This class represents the sixth command of the CalcMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */


public class SaveAs extends Command<Calc>
{

		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
     */	
	
	public SaveAs(Calc app)	
	{
		super(MenuEntry.SAVE_AS, app);
	}
	
	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputString nomeFicheiro = new InputString(f,Message.saveAs());
		f.parse();
		String atual=nomeFicheiro.value();
		entity().getFolha().setName(atual);
		if (atual != null)
		  try {
		     entity().getFolha().save(atual);
		  } catch (IOException e) {
					throw new InvalidOperation(e.toString());
		  }
	}
}

			
			
