package calc.core;

import java.io.*;
import java.util.*;
import pt.utl.ist.po.ui.InputInteger;

	/**
   * Class Parser
   * 
 	 *@author :		group 60
	 *						Bernardo Graça 76531
	 *						Vasco Fernandes 76462
	 *						Rui Furtado 76379
	 * @version 1.0
	 */

public class Parser {
	
		/** parseFile method parse the input 
     *@param String nomeFicheiro
     */
	
	public static FolhaCalc parseFile(String nomeFicheiro) throws IOException, FileNotFoundException {
		BufferedReader reader = null;

		try {
	  	reader = new BufferedReader(new FileReader(nomeFicheiro));
	   	String linha;
	   	int linhas, colunas;
	   	String[] args;
	    
	    linha = reader.readLine();
	    args = linha.split("=");
	    linhas = Integer.parseInt(args[1]);
	    
	    linha = reader.readLine();
	    args = linha.split("=");
	    colunas = Integer.parseInt(args[1]);
	    
	    FolhaCalc folha = new FolhaCalc(linhas,colunas); 
	    
	    while ((linha = reader.readLine()) != null) {
				parseExpressao(folha, linha);
	    }
	    
	    return folha;
		} finally 
			{
	  	if (reader != null)
				reader.close();
			}
  }

		/** parseExpressao method parse the input 
     *@param FolhaCalc folha
     *@param String expressao
     */

	public static void parseExpressao(FolhaCalc folha, String expressao) {
		String[] args = expressao.split("\\|");
		int[] endereco = parseEndereco(args[0]);
		Content cont = null;

		if (args.length > 1)
			cont = parseConteudo(folha, args[1]);
	
		folha.getMatriz().insereContent(endereco[0], endereco[1], cont); 
    }

		/** parseCelula method parse the input 
     *@param String endereco
     *@return a vetor
     */

	public static int[] parseCelula(String endereco) {
		String[] args = endereco.split(";");
		int[] vec = {Integer.parseInt(args[0]), Integer.parseInt(args[1])};
		return vec;
  }

		/** parseConteudo method parse the input 
     *@param FolhaCalc folha
     *@param String conteudo
     *@return another parse method
     */

	public static Content parseConteudo(FolhaCalc folha, String conteudo) {
		if (conteudo.contains("(")) { // é uma função
	  	String funcao = conteudo.substring(1); // remove =
	  	String nomeFuncao = parseNomeFuncao(funcao);
	  	String argumento = parseArgumentoFuncao(funcao);

	   	if (conteudo.contains(","))
				return parseFuncaoBinaria(folha, nomeFuncao, argumento);
	   	else
				return parseFuncaoIntervalo(folha, nomeFuncao, argumento);
		} 
		else if (conteudo.contains("=")) {  // é uma referencia 
			return parseReferencia(folha, conteudo.substring(1));
		} 
		else
	  	return parseLiteral(conteudo);
  }	
	
		/** parseNomeFuncao method parse the input 
     *@param String funcao
     *@return string
     */
	
	private static String parseNomeFuncao(String funcao) {
		return funcao.substring(0, funcao.indexOf("("));
	}

		/** parseArgumentoFuncao method parse the input 
     *@param String funcao
     *@return String
     */

  private static String parseArgumentoFuncao(String funcao) {
		return funcao.substring(funcao.indexOf("(") + 1, funcao.indexOf(")"));
	}

		/** parseFuncaoBinaria method parse the input 
     *@param FolhaCalc folha
     *@param String nomeFuncao
     *@param String argumento
     *@return a especific funtion or null
     */

	public static FuncBin parseFuncaoBinaria(FolhaCalc folha, String nomeFuncao, String argumento) {
		String[] argumentos = argumento.split(",");

		Argumento arg1 = parseArgumento(folha, argumentos[0]);
		Argumento arg2 = parseArgumento(folha, argumentos[1]);
			
		switch(nomeFuncao) {
		
			case "MUL":
	    	return new Mul(arg1,arg2,nomeFuncao);
			case "DIV":
	    	return new Div(arg1,arg2,nomeFuncao);
			case "SUB":
	    	return new Sub(arg1,arg2,nomeFuncao);
			case "ADD":
	    	return new Add(arg1,arg2, nomeFuncao);
		}
	
		return null;
	}

		/** parseFuncaoIntervalo method parse the input 
     *@param FolhaCalc folha
     *@param String nomeFuncao
     *@param String argumento
     *@return a especific funtion or null
     */

	public static FuncInt parseFuncaoIntervalo(FolhaCalc folha, String nomeFuncao, String argumento) {
		switch(nomeFuncao) {
			case "PRD":
	    	return new Prd(argumento,nomeFuncao,folha.getMatriz());
			case "AVG":
	    	return new Avg(argumento,nomeFuncao,folha.getMatriz());
		}
		return null;
	}

		/** parseArgumento method parse the input 
     *@param FolhaCalc folha
     *@param String argumento
     *@return another parse 
     */

	public static Argumento parseArgumento(FolhaCalc folha, String argumento) {
		if (argumento.contains(";")) {
	  	return parseReferencia(folha, argumento);
		}
			return parseLiteral(argumento);
	}

		/** parseLiteral method parse the input 
     *@param String literal
     *@return a call to a new Literal
     */	
	
	public static Literal parseLiteral(String literal) {
		return new Literal(literal);
	}

		/** parseReferencia method parse the input 
     *@param String referencia 
     *@param FolhaCalc folha
     *@return a call to a new Reference
     */	

	public static  Reference parseReferencia(FolhaCalc folha, String referencia) {
		int[] vec = parseCelula(referencia);
		return new Reference(folha, vec);
	}
  	
  	/** parseEndereco method parse the input 
     *@param String endereco
     *@return a vetor
     */		
  	
	public static int[] parseEndereco(String endereco){
  	int[]resposta;
  	if(endereco.contains(":")){
  		resposta=parseIntervalo(endereco);
  		return resposta;
  	}
  	else{
  		resposta=parseCelula(endereco);
  		return resposta;
  	}
	}
  
 		/** parseIntervalo method parse the input 
     *@param String endereco
     *@return a vetor
     */	
  
	public static int[] parseIntervalo(String endereco){
		String[] argumentos = endereco.split(":");
		int[] argumentos2=parseCelula(argumentos[0]);
		int[] argumentos3=parseCelula(argumentos[1]);
		int[] resposta = {argumentos2[0],argumentos2[1],argumentos3[0],argumentos3[1]};
		return resposta;		
	}
	
		/** parsePartir method parse the input 
     *@param String rui
     *@return String 
     */	
	
	public static String parsePartir(String rui){
		if(rui.contains("=")){
			String [] argumentos= rui.split("=");
			String resultado=argumentos[0];
			return resultado;
		}
		else{
			return rui;
		}
	}
	
		/** parsePartirFuncao method parse the input 
     *@param String rui
     *@return a String
     */	
	
	public static String parsePartirFuncao(String rui){
		if(rui.contains("=")){
			String [] argumentos= rui.split("=");
			String resultado=argumentos[1];
			if(resultado.contains("(")){
				String[]resultado2=resultado.split("\\(");
				String fim=resultado2[0];
				return fim;
			}
			else
				return resultado;
		}
		else
			return rui;
	}	
}	




