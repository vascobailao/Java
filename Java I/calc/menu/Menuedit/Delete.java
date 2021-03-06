package calc.menu.Menuedit;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;
import pt.utl.ist.po.ui.InputString;
import java.io.IOException;

import calc.textui.*;
import calc.core.*;
import calc.textui.edit.*;

/**
 * This class represents the fourth command of the MenuEdits for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Delete extends Command<Calc>
{
		/**
     * Constructor for class MenuEdit
     *
     * @param app represents the FolhaDeCalculo.
     */
	public Delete(Calc app)	
	{
		super(MenuEntry.DELETE, app);
	}
	
	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputString posicao = new InputString(f, Message.addressRequest());
		f.parse();
		int[]enderecos;
		try{
			enderecos=Parser.parseEndereco(posicao.value());
			if(enderecos.length==2){
				entity().getFolha().getMatriz().removeCell(enderecos[0],enderecos[1]);
			}
			else{
				Int intervalo=new Int(enderecos,entity().getFolha().getMatriz());
				for(Cell auxiliar : intervalo.getIntervalo()){
					entity().getFolha().getMatriz().removeCell(auxiliar.getLine(),auxiliar.getColumn());
				}
			}
		}
		catch(Exception e){
			throw new InvalidCellRange(posicao.value());
		}
	}
}
			
			
