package calc.core;

	/**
   * Class Mul, extends from FuncBin allowing access to methods and attributes
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */


public class Mul extends FuncBin{
	
	 /**
		*@param Argumento arg1, some value that will be used in the operation
		*@param Argumento arg2, some value that will be used in the operation
		*@param String nomeFuncao, a name that will be associated to the spreadsheet
		*/
	
	public Mul(Argumento arg1, Argumento arg2, String nomeFuncao){
		this.v1=arg1;
		this.v2=arg2;
		this.nomeFunc=nomeFuncao;
	}
	
	/**
	 *@return, null if any value does not exist, and the result otherwise
	 */
	
	@Override
	@SuppressWarnings("nls")
	public Integer compute(){
		if(this.v1.getValue() == null || this.v2.getValue() == null)
			return null;
		else
			return this.v1.getValue()*this.v2.getValue();
		}
}
