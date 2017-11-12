package calc.core;

import java.util.*;
import java.io.Serializable;

	/**
   * Class Int, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class Int implements Serializable{
	private static final long serial=987654321234568L;
	private ArrayList<Cell> intervalo = new ArrayList<Cell>();
	private Matriz aux;
	
	/**
	 *@param enderecos, is a vector with size 4
	 *@param Matriz l, allows to set a matrix
	 */
	
	public Int(int[] enderecos, Matriz l){
		setMatriz(l);
		int segue=0, inicio=1, fim=1, naoMuda=-1;
		int linhas1=enderecos[0];
		int colunas1=enderecos[1];
		int linhas2=enderecos[2];
		int colunas2=enderecos[3];
		
		if(linhas1==linhas2){
			if(colunas1<colunas2){
				inicio=colunas1;
				fim=colunas2;
			}
			else{
				inicio=colunas2;
				fim=colunas2;
			}
			segue=0;
			naoMuda=linhas1;
		}
		else{
			if(colunas1==colunas2){
				if(linhas1<linhas2){
					inicio=linhas1;
					fim=linhas2;
				}
				else{
					inicio=linhas2;
					fim=linhas1;
				}
				segue=1;
				naoMuda=colunas1;
			}
		}
		if(segue==0){
			for(int i=inicio;i<=fim;i++)
				this.intervalo.add(getMatriz().getCelula(naoMuda,i));
		}
		if(segue==1){
			for(int j=inicio;j<=fim;j++)
				this.intervalo.add(getMatriz().getCelula(j,naoMuda));
		}
	}
	
	/**
	 *@return, this method access to hte current matrix
	 */	
	
	public Matriz getMatriz(){
		return this.aux;
	}
	
	/**
	 *@param, this method set a matrix
	 */	
	
	public void setMatriz(Matriz e){
		this.aux=e;
	}
	
	/**
	 *@return, this method acces to the current array
	 */	
	
	public ArrayList<Cell> getIntervalo(){
		return this.intervalo;
	}
}
