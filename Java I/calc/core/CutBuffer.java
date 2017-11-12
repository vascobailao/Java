package calc.core;

import java.util.*;
import java.io.Serializable;

/**
   * Class CutBuffer, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class CutBuffer implements Serializable{
	private static final long serial= 987654321234567L;
	private ArrayList<Content> buffer;
	
	/**
	 *this method allows the initialization of the CutBuffer arrayList
	 */
	
	public CutBuffer(){
		this.buffer=new ArrayList<Content>();
	}
	
	/**
	 *@return this method provides access to the CutBuffer arrayList
	 */
	
	public ArrayList<Content> getCutBuffer(){
		return this.buffer;
	}
	
	/**
	 *@param int cont, corresponding to a position 
	 *@return this method allows you to check if the CutBuffer is empty, returning null, otherwise returns the contents of the position
	 */ 
	 
	public Content getBuffer(int cont){
		if(buffer.isEmpty())
			return null;
		else
			return this.buffer.get(cont);
	}
	
	/**
	 *this method allows to introduce a content in CutBuffer
	 */ 
	
	public void put(Content conteudo){
		this.buffer.add(conteudo);
	}
	
	/**
	 *this method allows to delete the CutBuffer
	 */ 
	
	public void tira(){
		this.buffer.clear();
	}
}
	

		
			
