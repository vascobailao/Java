package calc.menu.Calcmenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;

import java.io.*;
import calc.core.*;
import calc.textui.*;
import calc.textui.main.*;

/**
 * This class represents the fifth command of the CalcMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Save extends Command<Calc>
{

		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
     */

	public Save(Calc app)	
	{
		super(MenuEntry.SAVE, app);
	}
	
	public final void execute() throws InvalidOperation {
		String atual;
		if (entity().getFolha().getName() == null){
			Form f = new Form(title());
			InputString nomeFicheiro = new InputString(f,Message.newSaveAs());
			f.parse();
			atual=nomeFicheiro.value();
			entity().getFolha().setName(atual);
		}
		else
			atual=entity().getFolha().getName();
		try {
			entity().getFolha().save(atual);
		} 
		catch (IOException e) {
			throw new InvalidOperation(e.toString());
		}
	}
}
			
			
