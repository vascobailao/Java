package calc.menu;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import calc.textui.search.*;
import calc.core.FolhaCalc;
import calc.menu.Menusearch.*;
import calc.textui.*;
/**
 * This class represents the MenuSearch for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class MenuSearch extends Menu
{
		/**
     * Constructor for class MenuSearch
     *
     * @param app represents the FolhaDeCalculo.
		 */
		 
	public MenuSearch(Calc app)	
	{
		super(MenuEntry.TITLE, new Command<?>[] {
			new SearchValue(app),
			new SearchFunction(app)
		});	
	}
}
			
			
