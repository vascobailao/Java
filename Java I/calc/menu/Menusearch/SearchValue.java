package calc.menu.Menusearch;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InputInteger;

import calc.textui.*;
import calc.core.*;
import calc.textui.search.*;

/**
 * This class represents the second command of the MenuSearch for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class SearchValue extends Command<Calc>
{
		/**
     * Constructor for class MenuSearch
     *
     * @param app represents the FolhaDeCalculo.
     */
     
	public SearchValue(Calc app)	
	{
		super(MenuEntry.SEARCH_VALUES, app);
	}
	
	public final void execute() {
		Form f = new Form(title());
		InputString search = new InputString(f, Message.searchValue());
		f.parse();
		Display d= new Display(title());
		int linhas=entity().getFolha().getMatriz().getLimitesLinhas();
		int colunas=entity().getFolha().getMatriz().getLimitesColunas();
		for(int i=1; i<=linhas; i++)
		{
			for(int j=1; j<=colunas;j++)
			{
				if(entity().getFolha().getMatriz().getCelula(i,j).getContent()==null){
				}
				else{ 
					String rui=entity().getFolha().getMatriz().getCelula(i,j).getContent().toString();
					String valor=Parser.parsePartir(rui);
					if(valor.equals(search.value()))
							d.addNewLine(i+";"+j+"|"+rui);
				}
			}
		}
		d.display();
	}
}
			
			
