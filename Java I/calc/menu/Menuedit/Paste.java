package calc.menu.Menuedit;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.Form;
import pt.utl.ist.po.ui.InputString;
import pt.utl.ist.po.ui.InvalidOperation;
import calc.textui.edit.*;
import calc.core.*;
import calc.textui.Calc;
import java.io.*;
import java.util.*;

/**
 * This class represents the sixth command of the MenuEdit for FolhaDeCalculo.
 *
 * @author :	group 60
 *						Bernardo Graça 76531
 *						Vasco Fernandes 76462
 *						Rui Furtado 76379
 * @version 1.0
 */


public class Paste extends Command<Calc>{
	
		/**
     * Constructor for class MenuEdit
     *
     * @param app represents the FolhaDeCalculo.
     */
	
	public Paste(Calc folha){
		super(MenuEntry.PASTE, folha);
	}
	
	public final void execute() throws InvalidOperation{
	
		ArrayList <Content> intervalo = new ArrayList<Content>();
		
		intervalo = entity().getFolha().getCutBuffer().getCutBuffer();
		
		if(intervalo.isEmpty()){}
		
		else{
			Form f = new Form(title());
			InputString endereco = new InputString(f,Message.addressRequest());
			f.parse();
			int[] coordenadas;
			
			int j=0;
			int size = intervalo.size();
			
			try{
				coordenadas = Parser.parseEndereco(endereco.value());
				int linha = coordenadas[0];
				int coluna = coordenadas[1];
				//int linha2 = posicao[2];
				//int coluna2 = posicao[3];
				if(size>1){
					if (intervalo.get(0).getCelula().getLine() == intervalo.get(1).getCelula().getLine()){
						for (int p=coluna; p<coluna+size; p++){
							if(intervalo.get(j)!=null)
								entity().getFolha().getMatriz().colaCell(linha,p,intervalo.get(j));
							else
								entity().getFolha().getMatriz().getCelula(linha,p).setConteudo(null);
							j++;
						}
					}
					if(intervalo.get(0).getCelula().getColumn() == intervalo.get(1).getCelula().getColumn()){
						for (int k=linha; k<linha+size; k++){
							if(intervalo.get(j)!=null)
								entity().getFolha().getMatriz().colaCell(k,coluna,intervalo.get(j));
							else
								entity().getFolha().getMatriz().getCelula(k,coluna).setConteudo(null);
							j++;
						}
					}
				}
				else
					entity().getFolha().getMatriz().colaCell(linha,coluna,intervalo.get(0));
			}
			catch(Exception e){
				throw new InvalidCellRange(endereco.value());
			}	
		}
	}
}
