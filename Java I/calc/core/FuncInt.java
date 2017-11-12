package calc.core;

	/**
   * Class FuncInt, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public abstract class FuncInt extends Func{

	public String k1;
	public String nomeFunc;
	public Matriz aux;
	
	/**
	 *@param String nomeFunc, this method allows set a name to the current Func
	 */
	
	public void setNomeFunc(String nomeFunc){
		this.nomeFunc=nomeFunc;
	}
	
	/**
	 *@return, this method access to a name to the current Func
	 */	
	
	
	public String getNomeFuncao(){
		return this.nomeFunc;
	}
	
	/**
	 *@return, this method return the resulte of compute()
	 */	
	
	public Integer getContent(){
		return compute();
	}
	
	/**
	 *@return, null if any result of compute is null, and the result otherwise
	 */
	
	
	public String toString(){
		int [] intervalo=Parser.parseIntervalo(this.k1);
		if(compute()==null)
			return "#VALUE="+this.nomeFunc+"("+intervalo[0]+";"+intervalo[1]+":"+intervalo[2]+";"+intervalo[3]+")";
		return compute()+"="+this.nomeFunc+"("+intervalo[0]+";"+intervalo[1]+":"+intervalo[2]+";"+intervalo[3]+")";
	}
}
