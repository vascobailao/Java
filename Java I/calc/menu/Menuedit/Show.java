package calc.menu.Menuedit;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;
import java.io.IOException;
import calc.textui.*;
import calc.core.*;
import calc.textui.edit.*;

/**
 * This class represents the seventh command of the MenuEdit for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class Show extends Command<Calc>
{
		/**
     * Constructor for class MenuEdit
     *
     * @param app represents the FolhaDeCalculo.
     */
	public Show(Calc app)	
	{
		super(MenuEntry.SHOW, app);
	}
	
	public final void execute() throws InvalidOperation {
		Form f = new Form(title());
		InputString endereco = new InputString(f, Message.addressRequest());
		f.parse();
		int[]enderecos;
		try{
			enderecos=Parser.parseEndereco(endereco.value());
			if(enderecos.length==2){
				Display d= new Display(title());
				d.add(enderecos[0]+";"+enderecos[1]+"|");
				if(entity().getFolha().getMatriz().getCelula(enderecos[0],enderecos[1]).getContent()==null){}
				else{
					d.add(entity().getFolha().getMatriz().getCelula(enderecos[0],enderecos[1]).getContent().toString());
				}
				d.display();
			}
			else{
				Int intervalo=new Int(enderecos,entity().getFolha().getMatriz());
			
				for(Cell auxiliar : intervalo.getIntervalo()){
					Display d= new Display(title());
					d.add(auxiliar.getLine()+";"+auxiliar.getColumn()+"|");
					if(entity().getFolha().getMatriz().getCelula(auxiliar.getLine(),auxiliar.getColumn()).getContent()==null){}
					else{
						d.add(entity().getFolha().getMatriz().getCelula(auxiliar.getLine(),auxiliar.getColumn()).getContent().toString());
					}
					d.display();	
				}
			}
		}
		catch(Exception e){
			throw new InvalidCellRange(endereco.value());
		}
	}
}
			
			
