package calc.menu.Calcmenu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InputBoolean;
import pt.utl.ist.po.ui.InputInteger;
import pt.utl.ist.po.ui.InvalidOperation;
import pt.utl.ist.po.ui.InputString;

import java.io.IOException;
import calc.core.*;
import calc.textui.*;
import calc.textui.main.*;

/**
 * This class represents the first command of the MainMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class New extends Command<Calc>
{

		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
     */
     
	public New(Calc app)
	{
		super(MenuEntry.NEW, app);
	}
	
	public final void execute() throws InvalidOperation{
		Form guarda = new Form(title());
		InputString yesorno = new InputString(guarda, Message.saveBeforeExit());
		guarda.parse();
		if (yesorno.value().contains("s") || yesorno.value().contains("S")){
			try {
				if(entity().getFolha().getName() == null)
					menu().entry(2).execute();
			} catch (InvalidOperation e) {
				e.printStackTrace();
			}
		}
		Form f = new Form(title());
		InputInteger linhas = new InputInteger(f, Message.linesRequest());
		InputInteger colunas = new InputInteger(f, Message.columnsRequest());
		f.parse();
		entity().setFolha(new FolhaCalc(linhas.value(),colunas.value()));
		menu().entry(2).visible();
		menu().entry(3).visible();
		menu().entry(4).visible();
		menu().entry(5).visible();
	}
}
			
			
