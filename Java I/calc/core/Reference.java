package calc.core;

import java.util.*;

	/**
   * Class Reference, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class Reference extends Content implements Argumento {
		private int line;
		private int column;
		private FolhaCalc folha;
		
		/**
		 *@param FolhaCalc folha
		 *@param vec
	   */
		
		public Reference(FolhaCalc folha, int[] vec){
				this.folha=folha;
				this.line=vec[0];
				this.column=vec[1];
		}
		
	/**
	 *@param String conteudo, is a string with a value
	 */
		
		public Reference(String conteudo){
				int[] vec=Parser.parseEndereco(conteudo);
				this.line=vec[0];
				this.column=vec[1];
		}
		
		 /**
			* @see java.lang.Object#toString()	
			* @return value of the content of each cell
			*/
			
		public String toString(){
			Cell um=folha.getMatriz().getCelula(this.line,this.column);
			if(um.vaziaCell())
				return "#VALUE="+this.line+";"+this.column;
				if(compute()==null)
					return "#VALUE="+this.line+";"+this.column;
			else
				return compute()+"="+this.line+";"+this.column;
		}
		
		/**
		 *@return Operating method
		 */
		
		public Integer getContent(){
			return compute();
		}
		
		/**
		 * @return value after the operations of the selected cell
		 */
		
		public Integer compute(){
			if(folha.getMatriz().getCelula(this.line,this.column).getContent()== null)
				return null;
			else
				return folha.getMatriz().getCelula(this.line,this.column).getContent().getContent();
		}
		
		/*
		 * @return another method and is value
		 */
		
		
		@Override
		public Integer getValue(){
			return getContent();
		}
}




