package calc.menu.Menuedit;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InvalidOperation;

import calc.textui.*;
import calc.core.*;
import calc.textui.edit.*;
import java.util.*;

/**
 * This class represents the eighth command of the MenuEdit for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */

public class ShowCutBuffer extends Command<Calc>
{
		/**
     * Constructor for class MenuEdit
     *
     * @param app represents the FolhaDeCalculo.
     */
     
	public ShowCutBuffer(Calc app)	
	{
		super(MenuEntry.SHOW_CUT_BUFFER, app);
	}
	
	public final void execute() {
		Display a=new Display(title());	
		int iniLinhas=1, iniColunas=1, endereco=0;
		ArrayList<Content> buffer=new ArrayList<Content>();
		buffer= entity().getFolha().getCutBuffer().getCutBuffer();
		
		if(buffer.isEmpty()){
			a.display();
		}
		else	{
			if(buffer.size()==1){
				a.add(iniLinhas+";"+iniColunas+"|");
				a.add(entity().getFolha().getCutBuffer().getBuffer(0).toString());
				a.display();
			}
			else{
				if(buffer.get(0).getCelula().getLine()==buffer.get(1).getCelula().getLine()){
					while(iniColunas<=buffer.size()){
						a.add(iniLinhas+";"+iniColunas+"|");
						if(entity().getFolha().getCutBuffer().getBuffer(endereco)!=null)
							a.add(entity().getFolha().getCutBuffer().getBuffer(endereco).toString());
						if(iniColunas!=buffer.size()){
							a.addNewLine("");
						}
						iniColunas++;
						endereco++;
					}
				}
				else{
					while(iniLinhas<=buffer.size()){
						a.add(iniLinhas+";"+iniColunas+"|");
						if(entity().getFolha().getCutBuffer().getBuffer(endereco)!=null)
							a.add(entity().getFolha().getCutBuffer().getBuffer(endereco).toString());
						if(iniLinhas!=buffer.size()){
							a.addNewLine("");
						}
						iniLinhas++;
						endereco++;
					}
				}
				a.display();
			}
		}
	}			
}
			
			
