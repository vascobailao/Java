package pt.tecnico.bubbledocs.domain;

//import java.util.List;

public class Prd extends Prd_Base {

	private int fim=1;
	private int iteracoes = 0;
	
	public Prd(Argumento intervalo, String nomeFuncao) {
		init(intervalo,nomeFuncao);
		//System.out.println("INTERVALO " +intervalo.getIntervalo());
		//System.out.println("NOME FUNCAO NO PRD : " + nomeFuncao);
		compute();
	}

	public int getResultado(){
		return fim;
	}
	
	@Override
	public Integer compute(){
	
		for (Celula c : this.getArg().getIntervalo()){
			if (c.getConteudo() == null){
				//System.out.println("#AQUIIII VALUE");
				return null;
			}
			else{
				if (iteracoes == this.getArg().getIntervalo().size()){
					break;
				}
				
				//System.out.println("SIZE" + this.getArg().getIntervalo().size() );
				iteracoes ++;
				int cont = c.getConteudo().getContent();
				//System.out.println("imprimir conteudo das cells:   " + cont);
				fim=fim*cont;
			}

		} 
		
		Integer resultado=fim;
		//this.getArg().setStringContent(String.valueOf(resultado));
		//this.getArg().getCelula().getConteudo().setStringContent(String.valueOf(resultado));
		//System.out.println("RESULTADOOOO " + resultado);
		return resultado;
	}

}

