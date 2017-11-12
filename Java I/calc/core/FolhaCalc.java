package calc.core;

import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import pt.utl.ist.po.ui.Display;

import java.io.*;
import java.util.*;

	/**
   * Class FolhaCalc, with their methods and construtors 
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class FolhaCalc implements Serializable {
	private static final long serialVersion=12345673456432L;
	private Matriz matriz;
	private String name=null;
	private CutBuffer aux;
	
	/**
	 * @param linha number of a specific line
	 * @param columa number of a specific column
	 * builds the array with the limits, linha;coluna
	 * builds the CutBuffer, arrayList
	 */
	
	public FolhaCalc(int linha, int coluna){
		this.matriz=new Matriz(linha,coluna);
		this.aux=new CutBuffer();
	}
	
	/**
	 * builds the array with the limits, linha;coluna
	 * builds the CutBuffer, arrayList
	 */
	
	public FolhaCalc(){
		this.matriz=new Matriz();
		this.aux=new CutBuffer();
	}
	
	/**
	 *@return allows access to the current spreadsheet
	 */
	
	public FolhaCalc getFolha(){
		return this;
	}
	
	/**
	 *@param Matriz m, allows access to the current spreadsheet
	 */
	 
	public void setMatriz(Matriz m){
		this.matriz = m;
	}
	
	/**
	 *this method allows access to the current matrix
	 */
	
	public Matriz getMatriz(){
		return this.matriz;
	}

	/**
	 *this method allows set a name to the current spreadsheet
	 */	
	
	public void setName(String nome){
		this.name = nome;
	}
	
	/**
	 *@return this method returns the name of the current spreadsheet
	 */
	
	public String getName(){
		return this.name;
	}
	
	/**
	 *@param CutBuffer aux, this method allows set a name to the current CutBuffer
	 */	
	
	public void setCutBuffer(CutBuffer aux){
		this.aux=aux;
	}
	
	
	/**
	 *@return this method returns the current CutBuffer
	 */
	
	public CutBuffer getCutBuffer(){
		return this.aux;
	}
		
	/**
	 *@param linha
	 *@param coluna
	 *@return, this method allows set a spreadsheet and return it
	 */
	
	public FolhaCalc newFolhaCalc(int linha, int coluna){
		FolhaCalc fc = new FolhaCalc(linha, coluna);
		return fc;
	}
	
	/**
   * Loads FolhaCalc
   * @param file String type name of file to load 
   *@return the load spreadsheet
   */
	
	
	public static FolhaCalc load(String file)  throws IOException, ClassNotFoundException{
	  ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
	  FolhaCalc calc = (FolhaCalc)in.readObject();
	  in.close();
	  return calc;
	}
		
	/**
   *Save FolhaCalc
   *@param file String type name of file to load 
   */	
	
	public void save(String file) throws IOException {
	  ObjectOutputStream out =
	    new ObjectOutputStream(new FileOutputStream(file));
	  out.writeObject(this);
	  out.close();
	}
	
}
