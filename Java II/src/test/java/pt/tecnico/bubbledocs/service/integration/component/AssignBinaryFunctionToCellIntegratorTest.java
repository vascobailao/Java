package pt.tecnico.bubbledocs.service.integration.component;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import pt.tecnico.bubbledocs.service.integration.AssignBinaryFunctionServiceIntegrator;
import pt.tecnico.bubbledocs.exception.*;
import pt.tecnico.bubbledocs.domain.*;

import org.junit.Test;

public class AssignBinaryFunctionToCellIntegratorTest extends BubbleDocsServiceTest {
	
    private String jp; // the token for user jp
    private String ab; //the token for user ab
    private String rf; //the token for user rf
 
    private static final String USERNAME = "jp";
    private static final String PASSWORD = "jp#";
    private static final String USERNAME2 = "ab";
    private static final String PASSWORD2 = "bc";
    private static final String USERNAME3 = "rf";
    private static final String PASSWORD3 = "rf2";
    private static int row = 100;
    private static int column = 100;
    private  int docId = 0;
    private static String SPREADSHEET_NAME="FOLHA_JP_1";
    private static final String EMAIL= "antonio@gmail";
    
    @Override
    public void populate4Test() {
         
        createUser(USERNAME, PASSWORD, "João Pereira",EMAIL);
        createUser(USERNAME2, PASSWORD2, "Abilio Batata",EMAIL);
        createUser(USERNAME3, PASSWORD3, "Ricardo Ferreira",EMAIL);
        jp = addUserToSession("jp","João Pereira");
        ab = addUserToSession("ab","Abilio Batata");
        rf = addUserToSession("rf","Ricardo Ferreira");
        createSpreadSheet(getUserFromUsername(USERNAME), SPREADSHEET_NAME, row, column);
        this.docId=getSpreadSheet(SPREADSHEET_NAME).getIdentificador();
        Utilizador utilizador = getUserFromUsername(USERNAME);
        Set<FolhaCalculo> folhas = utilizador.getFolhacalculoSet();
        for(FolhaCalculo f: folhas){
        	if(f.getIdentificador()==this.docId){
        		Celula c3 = new Celula(2,1);
        		String valor3="1";
        		c3.setContent(valor3);
        		Conteudo conteudo3=Conteudo.parseConteudo(f,valor3);
        		c3.insereConteudo(conteudo3);
        		conteudo3.setStringContent(valor3);
        		f.addCelula(c3);
        
        	}
        }	
    }
    
    @Test
    public void success() {  
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("2;2", docId, jp, "=ADD(1,2;1)");
        service.execute();         
        Utilizador utilizador = getUserFromUsername(USERNAME);
        Set<FolhaCalculo> folhas = utilizador.getFolhacalculoSet();
        for(FolhaCalculo f: folhas){
        	if(f.getIdentificador()==docId) {
	        	Set<Celula> celulas = f.getCelulaSet();
	        	for(Celula c: celulas) {
	        		if(c.getLinha()==2 && c.getColuna()==2) {

		        		assertEquals(c.getConteudo().toString()," "+2);
			        	}
	        		}
	        	}
        	}
        }
    
    
    @Test
    public void success2(){ //verifica se um utilizador que nao o criador da folha, mas com autorizacao, pode escrever
    	getSpreadSheet("FOLHA_JP_1").addEscritor(getUserFromUsername(USERNAME3));
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("4;4", docId, rf, "=MUL(1,2;2)");
        service.execute();
        Utilizador utilizador = getUserFromUsername(USERNAME3);
        Set<FolhaCalculo> folhas = utilizador.getFolhacalculoSet();
        for(FolhaCalculo f: folhas){
        	if(f.getIdentificador()==docId) {
	        	Set<Celula> celulas = f.getCelulaSet();
	        	for(Celula c: celulas) {
		        		assertEquals(c.getConteudo().toString()," "+2);
			        	}
	        		}
	        	}
        	}
    
    
    @Test
    public void success3(){ //verifica se um utilizador que nao o criador da folha, mas com autorizacao, pode escrever
    	getSpreadSheet("FOLHA_JP_1").addEscritor(getUserFromUsername(USERNAME3));
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("4;4", docId, rf, "=DIV(2,2;2)");
        service.execute();
        Utilizador utilizador = getUserFromUsername(USERNAME3);
        Set<FolhaCalculo> folhas = utilizador.getFolhacalculoSet();
        for(FolhaCalculo f: folhas){
        	if(f.getIdentificador()==docId) {
	        	Set<Celula> celulas = f.getCelulaSet();
	        	for(Celula c: celulas) {
	        		if(c.getLinha()==4 && c.getColuna()==4) {
		        		assertEquals(c.getConteudo().toString()," "+2);
			        	}
	        		}
	        	}
        	}
    }
    
    @Test
    public void success4(){ //verifica se um utilizador que nao o criador da folha, mas com autorizacao, pode escrever
    	getSpreadSheet("FOLHA_JP_1").addEscritor(getUserFromUsername(USERNAME3));
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("4;4", docId, rf, "=SUB(2,2;2)");
        service.execute();
        Utilizador utilizador = getUserFromUsername(USERNAME3);
        Set<FolhaCalculo> folhas = utilizador.getFolhacalculoSet();
        for(FolhaCalculo f: folhas){
        	if(f.getIdentificador()==docId) {
	        	Set<Celula> celulas = f.getCelulaSet();
	        	for(Celula c: celulas) {
	        		if(c.getLinha()==4 && c.getColuna()==4) {
		        		assertEquals(c.getConteudo().toString()," "+1);
			        	}
	        		}
	        	}
        	}
    }
    
    
    
    @Test (expected = InvalidCellIdFormat.class)
    public void invalidCellFormat(){
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("A;2", docId, jp, "=ADD(1,2;2");
    	service.execute();
    }
    
    @Test (expected = WrongFunctionFormatException.class) 
    public void invalidFunctionFormat(){
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("3;2", docId, jp, "=HGF(2,2;2)");
    	service.execute();
    }
 
    @Test(expected = CellDoesNotExistException.class)
    public void cellDoesNotExist() {
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("101;2", docId, jp, "=ADD(2,2;2)");
        service.execute();
    }
    
    @Test(expected = InvalidCellId.class)
    public void InvalidCellFormat() {
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("2;2", docId, jp, "=ADD(2,B;2)");
    	service.execute();
    }
    
    @Test(expected = InvalidLiteralTypeException.class)
    public void InvalidLiteralType() {
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("2;2", docId, jp, "=ADD(A,2;2)");
    	service.execute();
    }
    
    @Test(expected = UnauthorizedOperationException.class)
    public void userWithoutPermission(){
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("3;2", docId, ab, "=ADD(2,2;2)");
    	service.execute();
    }
    
    @Test(expected = UnknownBubbleDocsSpreadSheetException.class)
    public void invalidId() {
    	AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("3;2", 20, jp, "=ADD(2,2;2)");
    	service.execute();
    }
    
    @Test(expected = UserNotInSessionException.class)
    public void accessUsernameNotExist() {
        removeUserFromSession(jp);
        AssignBinaryFunctionServiceIntegrator service = new AssignBinaryFunctionServiceIntegrator("3;2", docId, jp, "=ADD(2,2;2)");
    	service.execute();
    }
    
    /*
     * User doesn't have permission to write in this SpreadSheet but have permission to read
     */
    @Test(expected = UnauthorizedOperationException.class)
    public void notPermitedUserRead() {
        String smf = addUserToSession(USERNAME2, "Sérgio Fernandes");
        new AssignBinaryFunctionServiceIntegrator("2;2", docId, smf, "=ADD(2,2;2)").execute();   
    }
    
    
    

}
