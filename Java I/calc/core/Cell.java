package calc.core;

import java.io.Serializable;
import pt.utl.ist.po.ui.InputInteger;

	/**
   * Class Cell, with their methods and construtors
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */
	 
	 
public class Cell implements Serializable {

	private final long id=1234354543467L;
	private int line;
	private int column;
	private Content conteudo;
	private Matriz cell;
	
	/**
	 * @param line number of a specific line
	 * @param column number of a specific column
	 * And introduces the contens of the cell
	 */
	
	public Cell (int line, int column){
		this.line= line;
		this.column= column;
		this.conteudo=null;
	}
	
	/**
	 * @param linhas number of a specific line
	 * @param colunas number of a specific column
	 *@param Matriz m, an array that will allow access to multiple cells
	 */
	 
	public Cell (int linhas, int colunas, Matriz m){
    	this.conteudo = null;
    	this.line = linhas;
    	this.column = colunas;
    	this.cell = m;
  }
  
  /**
	 * @return line number of a specific line
	 * 
	 */
  
	public int getLine() {
		return this.line;
	}
		
	/**
	 * @return column number of a specific column
	 * 
	 */	
		
	public int getColumn() {
		return this.column;
	}
	
	/**
	 *@param cell (object)
	 *@return a boolean, 1 if its equal, and 0 otherwise
	 */
	
	public boolean equals(Object cell) {
		return cell instanceof Cell
			&& ((Cell) cell).getLine()==this.line && ((Cell) cell).getColumn()==this.column; 		
	}
	
	
	/**
	 *@return a boolean, 1 if the content of the cell is null, and 0 otherwise
	 */
	
	public boolean vaziaCell(){
    	if (this.conteudo == null)
    		return true;
    	else
    		return false;
  }
    
  
	/**
	 *@param Content conteudo, after accessing the cell will be introduced this content
	 */  
  
  public void insereConteudo(Content conteudo){
    conteudo.setCelula(this);
    this.setConteudo(conteudo);
  }
  
  /**
	 *this method will delete the content of a cell
	 */
  
  public void eliminaConteudo(){
		this.conteudo = null;
    }
    
  /**
	 * this method will set the content of a cell
	 */
  
	public void setConteudo(Content conteudo){
		this.conteudo = conteudo;
	}
	
	/**
	 *@return conteudo, this method will return the content of a cell
	 */
	 
	public Content getContent(){
		return this.conteudo;
	}
		
	/**
	 *@return cell, this method will return the matrix
	 */	
		
	public Matriz getMatriz(){
		return this.cell;
	}
	
	/**
	 *@return conteudo, this method will prepare the contents of a cell to be copied
	 */
	 
	public Content copiaConteudo(){
		return this.conteudo;
  }
  
  /**
	 *@return conteudo, this method will prepare the contents of a cell to be paste
	 */
  
  public void colaConteudo(Content cont){
  	cont.setCelula(this);
    this.setConteudo(cont);
  }
}
