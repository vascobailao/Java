package calc.core;

import java.util.*;

	/**
   * Class Literal, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class Literal extends Content implements Argumento {
		private int value;
		
	/**
	 *@param String value, is a string with a value
	 */
		
		public Literal(String value){
				this.value = Integer.parseInt(value);
		}
		
	/**
	 *@return the value in a string
	 */
		
		
		@Override
		public String toString(){
			return ""+ this.value;
		}
		
	/**
	 *@return the value 
	 */
		
		public Integer getContent(){
				return this.value;
		}
		
	/**
	 *@return,  this method will call other method and return the value	
	 */
	 
		public Integer getValue(){
				return getContent();	
		}
}




