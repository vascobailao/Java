package calc.core;

import java.io.Serializable;

	/**
   * Class Content, with their methods 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public abstract class Content implements Serializable { 	
	private static final long serialVersion=12345673456432L;
	private Cell cell;
	
	/**
	 *@param Cell cell, this method will set a cell
	 */
	
	public void setCelula(Cell cell){
		this.cell = cell;
	}
	
	
	/**
	 *this method allows access to cell
	 */
	
	public Cell getCelula(){
		return this.cell;
	}
		
	public abstract Integer getContent();
	public abstract String toString();
}
