package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import calc.textui.edit.*;
import calc.core.FolhaCalc;
import calc.menu.Menuedit.*;
import calc.textui.*;

/**
 * This class represents the MenuEdit for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class MenuEdit extends Menu
{
		/**
     * Constructor for class MenuEdit
     *
     * @param app represents the FolhaDeCalculo.
		 */
		 
	public MenuEdit(Calc app)	
	{
		super(MenuEntry.TITLE, new Command<?>[] {
			new Show(app),
			new Insert(app),
			new Copy(app),
			new Delete(app),
			new Cut (app),
			new Paste (app),
			new ShowCutBuffer(app)
		});	
	}
}
			
			
