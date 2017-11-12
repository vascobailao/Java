package calc.core;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;
import pt.utl.ist.po.ui.InputString;

import java.io.Serializable;

	/**
   * Class Matriz, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class Matriz implements Serializable {
	private static final long serialVersion=12345673456432L;
	
	private int linhas;
	private int colunas;
	private Cell[][] matriz;
	
	/**
	 *@param linhas, with a specific value
	 *@param colunas, with a specific value
	 */
		
	
	public Matriz(int linhas, int colunas){
		this.linhas = linhas;
		this.colunas = colunas;
		this.matriz = new Cell[linhas+1][colunas+1];
		int i,j;
		for (i=0; i<=this.linhas; i++){
			for (j=0; j<=this.colunas; j++)
				this.matriz[i][j] = new Cell(i,j);
		}
	}
	
	/**
	 *this method will set a matrix with value null
	 */
		
	public Matriz(){
		this.matriz = null;
	}
	
	/**
	 *@return linhas
	 */
	
	public int getLimitesLinhas(){
		return this.linhas;
	}
	
	/**
	 *@return colunas
	 */
	
	public int getLimitesColunas(){
		return this.colunas;
	}
    
    
  /**
	 *@param linhas, with a specific value
	 *@param colunas, with a specific value
	 *@return the new matrix
	 */
  
  public Matriz newMatriz(int linhas, int colunas){
  	Matriz m = new Matriz(linhas,colunas);
    return m;
    }
  
  /**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 *@return the cell in a especific position on matrix
	 */
   
	public Cell getCelula(int l, int c){
			return this.matriz[l][c];
	}
	
	/**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 *@param Content content
	 */
	
	public void insereContent(int l, int c, Content content){
		this.matriz[l][c].insereConteudo(content);
	}
	
	/**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 *@param Content content
	 */
	
	
	public void insereCell(int l,int c, Content content){
		this.matriz[l][c].insereConteudo(content);
	}
	
	/**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 */
	
	
	public void removeCell(int l, int c){
		this.matriz[l][c].eliminaConteudo();
		}
	
	/**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 *@return the content in a especific cell
	 */
	
	
	public Content copiaCell(int linha, int coluna){
			return this.matriz[linha][coluna].copiaConteudo();
	}
	
	/**
	 *@param l, with a specific value
	 *@param c, with a specific value
	 *@param Content cont
	 */
	
	
	public void colaCell(int linha,int coluna, Content cont){
		this.matriz[linha][coluna].colaConteudo(cont);
	}
}

	
