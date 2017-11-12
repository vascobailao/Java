package calc.core;

import java.util.*;

	/**
   * Class Avg, extends from FuncInt allowing access to methods and attributes
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */


public class Avg extends FuncInt{	

	/**
	 *@param String intervalo, some content that will be used in the operation, after Parser
	 *@param String nomeFuncao, a name that will be associated to the spreadsheet
	 *@param Matriz auxiliar, an array that will allow access to multiple cells
	 */

	public Avg(String intervalo, String nomeFuncao, Matriz auxiliar){
			this.k1=intervalo;
			this.nomeFunc=nomeFuncao;
			this.aux=auxiliar;
		}
		
	/**
	 *@return, null if any value of any cell does not exist, and the result otherwise
	 */
		
	@Override
	@SuppressWarnings("nls")
	public Integer compute(){
		int[] enderecos=Parser.parseIntervalo(this.k1);
		Int intervalo=new Int(enderecos,this.aux);
		int fim=0;
		int iteracoes=0;
		for(Cell auxiliar : intervalo.getIntervalo()){
			if(auxiliar.getContent()==null){
				return null;
			}
			else{
				String cont=auxiliar.getContent().toString();
				String resultado=Parser.parsePartir(cont);
				int valor=Integer.parseInt(resultado);
				fim+=valor;
				iteracoes++;
			}
		}
		return fim/iteracoes;
	}	
}	
