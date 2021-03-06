package calc.menu.Calcmenu;

import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;

import calc.textui.*;
import calc.core.*;
import calc.menu.MenuEdit;
import calc.textui.main.*;


/**
 * This class represents the third command of the CalcMenu for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class OpenMenuEdit extends Command<Calc>
{
		/**
     * Constructor for class CalcMenu
     *
     * @param app represents the FolhaDeCalculo.
     */
	public OpenMenuEdit(Calc app)	
	{
		super(MenuEntry.MENU_CALC, app);
	}
	
	public final void execute() {
		Menu m=new MenuEdit(entity());
		m.open();
	}
}
			
			
