package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import calc.textui.main.MenuEntry;
import calc.core.FolhaCalc;
import calc.menu.Calcmenu.*;
import calc.textui.*;

/**
 * This class represents the MainMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class CalcMenu extends Menu
{
		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
gs to.
     */
	public CalcMenu(Calc app)	
	{
		super(MenuEntry.TITLE, new Command<?>[] {
			new New(app),
			new Open(app),	
			new Save(app),
			new SaveAs(app),
			new OpenMenuEdit(app),
			new OpenMenuSearch(app)
		});
	}
}
			
			
