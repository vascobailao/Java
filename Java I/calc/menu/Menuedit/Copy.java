package calc.menu.Menuedit;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;
import pt.utl.ist.po.ui.InputString;

import calc.textui.*;
import calc.core.*;
import calc.textui.edit.*;

/**
 * This class represents the first command MenuEdit for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Copy extends Command<Calc>
{
		/**
     * Constructor for class MenuEdit
     *
     * @param folha represents the FolhaDeCalculo.
     */
     
	public Copy(Calc app)	
	{
		super(MenuEntry.COPY, app);
	}
	
	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputString posicao = new InputString(f, Message.addressRequest());
		f.parse();
		Content ex;
		int[] enderecos;
		try{
			enderecos=Parser.parseEndereco(posicao.value());
			entity().getFolha().getCutBuffer().tira();
			if(enderecos.length==2){
				ex=entity().getFolha().getMatriz().copiaCell(enderecos[0],enderecos[1]);
				entity().getFolha().getCutBuffer().put(ex);
			}
			else{
				Int range=new Int(enderecos, entity().getFolha().getMatriz());
				for(Cell auxiliar: range.getIntervalo()){
					ex=entity().getFolha().getMatriz().copiaCell(auxiliar.getLine(), auxiliar.getColumn());
					entity().getFolha().getCutBuffer().put(ex);
				}
			}
		}
		catch(Exception e){
			throw new InvalidCellRange(posicao.value());
		}
	}
}

			
			
