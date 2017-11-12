package pt.tecnico.bubbledocs.service.integration.system;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import pt.tecnico.bubbledocs.domain.Celula;
import pt.tecnico.bubbledocs.domain.FolhaCalculo;
import pt.tecnico.bubbledocs.domain.Utilizador;
import pt.tecnico.bubbledocs.service.integration.AssignIntervalFunctionServiceIntegrator;
import pt.tecnico.bubbledocs.service.integration.AssignLiteralCellIntegrator;
import pt.tecnico.bubbledocs.service.integration.AssignReferenceCellIntegrator;
import pt.tecnico.bubbledocs.service.integration.CreateSpreadSheetIntegrator;
import pt.tecnico.bubbledocs.service.integration.CreateUserIntegrator;
import pt.tecnico.bubbledocs.service.integration.DeleteUserIntegrator;
import pt.tecnico.bubbledocs.service.integration.ExportDocumentIntegrator;
import pt.tecnico.bubbledocs.service.integration.GetSpreadSheetContentIntegrator;
import pt.tecnico.bubbledocs.service.integration.ImportDocumentIntegrator;
import pt.tecnico.bubbledocs.service.integration.LoginUserIntegrator;
import pt.tecnico.bubbledocs.service.integration.RenewPasswordIntegrator;
import pt.tecnico.bubbledocs.service.remote.IDRemoteServices;
import pt.tecnico.bubbledocs.service.remote.StoreRemoteServices;


/*Passos:
 * - Criação de dois utilizadores com o root - DONE
 * - Logar os dois user's  - DONE
 * - Criar Três folhas para o primeiro e uma para o segundo - DONE
 * - Inserir três literais e uma referencia   numa das folhas de cada user e um literal numa das duas folhas que sobram do user 1 -  DONE
 * - Inserir uma funcao binaria e uma de intervalo numa dessas duas folhas - 
 * - Ver o conteudo das duas folhas com conteudos -  DONE
 * - Exportar a folha do user1 que tem apenas um literal  - DONE
 * - Apagar a folha exportada - DONE
 * - e voltar a importar para o mesmo user 1 - DONE
 * - Obter o conteudo da folha importada mas para o user 1  - DONE
 * - Renovar a password de um user - DONE
 * - Apagar o segundo user - DONE
 */


/*Assert's:
*  - verificar se os dois user's estao em sessao : DONE
*  - verificar as folhas : DONE
*  - verificar alguns resultados dos conteudos de uma das folhas : DONE
*  - verificar que a folha foi mesmo apagada : 
*  - verificar a folha importada se e igual :
*  - verificar se o segundo user foi mesmo apagado :
 */



public class LocalSystemTest extends BubbleDocsLocalSystemTest{  //extender?? para criar o root e preciso
	//Final and global Test
    private static final String ROOT_USERNAME = "root";
    private static final String ROOT_PASSWORD = "rootroot";
    private static final String User1 = "Sport Lisboa e Benfica";
    private static final String Username1 = "SLB";
    private static final String User2 = "Rumo ao 34";
    private static final String Username2 = "34";
    private static final String PASSWORD1 = "34";
    private static final String PASSWORD2 = "slb";
    private static final String FOLHA1 = "Notas Es";
    private static final String FOLHA2 = "Notas ESEMB";
    private static final String FOLHA3 = "Notas SCOM";
    private static final String FOLHA12 = "Notas Es 2";
	private byte[] bytes;
    
    @Test
    public void success(@Mocked final IDRemoteServices remote,@Mocked final StoreRemoteServices remote2 ) { 	
    	new Expectations(){
    		{
    			remote.loginUser(ROOT_USERNAME, ROOT_PASSWORD);
    			remote.createUser(Username1, "slb@gmail.com");
    			remote.createUser(Username2, "34@gmail.com");
    			remote.renewPassword(Username1); 
    			remote.removeUser(Username2); 
    		}
    	};
    	
    	createUser(ROOT_USERNAME,ROOT_USERNAME,ROOT_USERNAME,"root@gmail.com");  //criar o root      
        LoginUserIntegrator service_1 = new LoginUserIntegrator(ROOT_USERNAME, ROOT_PASSWORD); //login do root
    	service_1.execute();
    	String rootToken =service_1.getUserToken(); //token do root
    	CreateUserIntegrator service_2= new CreateUserIntegrator(rootToken,Username1, User1, "slb@gmail.com"); //criacao do 1 user
    	service_2.execute();
    	CreateUserIntegrator service_3= new CreateUserIntegrator(rootToken,Username2, User2, "34@gmail.com"); //criacao do 2 user
    	service_3.execute();    	
    	LoginUserIntegrator service_4 = new LoginUserIntegrator(Username1, PASSWORD1); //login do 1 user
       	service_4.execute();
       	String user1Token = service_4.getUserToken(); //token do 1 user
       	LoginUserIntegrator service_5 = new LoginUserIntegrator(Username2, PASSWORD2); //login do 2 user
       	service_5.execute();
       	String user2Token = service_5.getUserToken(); //token do 2 user
       	
       	assertEquals(ROOT_USERNAME, getUserFromSession(rootToken).getUsername()); //verificar o login do user1
       	assertEquals(Username1, getUserFromSession(user1Token).getUsername()); //verificar o login do user1
       	assertEquals(Username1, getUserFromSession(user1Token).getUsername()); //verificar o login do user2

       	
       	CreateSpreadSheetIntegrator service_6 = new CreateSpreadSheetIntegrator(user1Token,FOLHA1,100,100); //1 folha do user 1
		service_6.execute();
		int idFolha_1=service_6.getSheetId(); //id da 1 folha do user 1
		CreateSpreadSheetIntegrator service_7 = new CreateSpreadSheetIntegrator(user1Token,FOLHA2,100,100); //2 folha do user 1
		service_7.execute();
		int idFolha_2=service_7.getSheetId(); //id da 2 folha do user 1
		CreateSpreadSheetIntegrator service_8 = new CreateSpreadSheetIntegrator(user1Token,FOLHA3,100,100); //3 folha do user 1
		service_8.execute();
		CreateSpreadSheetIntegrator service_9 = new CreateSpreadSheetIntegrator(user2Token,FOLHA12,100,100); //1 folha do user 2
		service_9.execute();
		int idFolha_12=service_9.getSheetId(); //id da 1 folha do user 2
		
		assertEquals(FOLHA1, getSpreadSheet(FOLHA1).getNome());
		assertEquals(100, getSpreadSheet(FOLHA1).getNumColunas());
		assertEquals(100, getSpreadSheet(FOLHA1).getNumLinhas());
		assertEquals(Username1, getSpreadSheet(FOLHA1).getUtilizador().getUsername());
		assertEquals(FOLHA2, getSpreadSheet(FOLHA2).getNome());
		assertEquals(100, getSpreadSheet(FOLHA2).getNumColunas());
		assertEquals(100, getSpreadSheet(FOLHA2).getNumLinhas());
		assertEquals(Username1, getSpreadSheet(FOLHA2).getUtilizador().getUsername());
		assertEquals(FOLHA3, getSpreadSheet(FOLHA3).getNome());
		assertEquals(100, getSpreadSheet(FOLHA3).getNumColunas());
		assertEquals(100, getSpreadSheet(FOLHA3).getNumLinhas());
		assertEquals(Username1, getSpreadSheet(FOLHA3).getUtilizador().getUsername());
		assertEquals(FOLHA12, getSpreadSheet(FOLHA12).getNome());
		assertEquals(100, getSpreadSheet(FOLHA12).getNumColunas());
		assertEquals(100, getSpreadSheet(FOLHA12).getNumLinhas());
		assertEquals(Username2, getSpreadSheet(FOLHA12).getUtilizador().getUsername());
		
        new AssignLiteralCellIntegrator(user1Token, idFolha_1, "1;1", "1").execute(); //literal com valor 1 na folha 1 do user 1
        new AssignLiteralCellIntegrator(user1Token, idFolha_2, "1;1", "1").execute(); //literal com valor 1 na folha 2 do user 1
        new AssignLiteralCellIntegrator(user1Token, idFolha_1, "1;2", "2").execute(); //literal com valor 2 na folha 1 do user 1
        new AssignLiteralCellIntegrator(user1Token, idFolha_1, "1;3", "1").execute(); //literal com valor 1 na folha 1 do user 1
        new AssignReferenceCellIntegrator(user1Token, idFolha_1, "1;4", "=1;1").execute(); //referencia com valor 1;1 na folha 1 do user 1
        
        new AssignLiteralCellIntegrator(user2Token, idFolha_12, "1;1", "1").execute(); //literal com valor 1 na folha 1 do user 2
        new AssignLiteralCellIntegrator(user2Token, idFolha_12, "1;2", "2").execute(); //literal com valor 2 na folha 1 do user 2
        new AssignLiteralCellIntegrator(user2Token, idFolha_12, "1;3", "3").execute(); //literal com valor 1 na folha 1 do user 2
        new AssignReferenceCellIntegrator(user2Token, idFolha_12, "2;1", "=1;1").execute(); //referencia com valor 1;1 na folha 1 do user 2
        
        AssignIntervalFunctionServiceIntegrator service_18= new AssignIntervalFunctionServiceIntegrator ("1;4", "\0PRD(1;1:1;3)",idFolha_1, user1Token);
        service_18.execute();
		Integer resultFinal = service_18.getResultFinal();
		assertEquals(resultFinal.toString(),"2");


        FolhaCalculo f_1=getSpreadSheet(FOLHA1);
        Set<Celula> celulas = f_1.getCelulaSet();
    	for(Celula c: celulas) {
    		if(c.getLinha()==1 && c.getColuna()==1) {
        		assertEquals(c.getContent().toString(),"1");
	        }
    	}
        FolhaCalculo f_2=getSpreadSheet(FOLHA12);
    	Set<Celula> celulass = f_2.getCelulaSet();
    	for(Celula c: celulass) {
    		if(c.getLinha()==1 && c.getColuna()==1) {
        		assertEquals(c.getContent().toString(),"1");
	        }
    	}    	
        /* inserir funcao binaria e de intervalo em cada uma das folhas*/        
        FolhaCalculo f=getSpreadSheet(FOLHA2);
		this.bytes = getBytes(f); //transformar a folha 2 do user 1 em bytes
    	new Expectations(){
    		{
				remote2.storeDocument(Username1, FOLHA2, bytes); 
				remote2.loadDocument(Username1, ""+idFolha_2); result = bytes;
    		}
    	};
        GetSpreadSheetContentIntegrator service_10 = new GetSpreadSheetContentIntegrator(user1Token, idFolha_1); //tirar o conteudo da primeira folha do user 1
		service_10.execute();
		GetSpreadSheetContentIntegrator service_11 = new GetSpreadSheetContentIntegrator(user2Token, idFolha_12); //tirar o conteudo da primeira folha do user 2
		service_11.execute();
		
		ExportDocumentIntegrator service_12 = new ExportDocumentIntegrator(user1Token,idFolha_2); //exportar a folha 2, tem apenas um literal do user 1
		service_12.execute();
		
		deleteFolhaCalculo(Username1, FOLHA2); //apagar a folha que exportei
        FolhaCalculo f_delete=getSpreadSheet(FOLHA2);
        assertEquals(f_delete,null);
		
		ImportDocumentIntegrator importDoc = new ImportDocumentIntegrator(user1Token, ""+idFolha_2); //importar a folha 2 do user 1 para o user 1
		importDoc.execute();
		
        FolhaCalculo f2=getSpreadSheet(FOLHA2);        
        GetSpreadSheetContentIntegrator service_13 = new GetSpreadSheetContentIntegrator(user1Token, f2.getIdentificador()); //tirar o conteudo da primeira folha do user 1 que foi importada
		service_13.execute();
		FolhaCalculo f_import=getSpreadSheet(FOLHA2);
	    assertEquals(f_import.getNome(),FOLHA2);
	    assertEquals(f_import.getUtilizador().getUsername(),Username1);

		RenewPasswordIntegrator service_14 = new RenewPasswordIntegrator(user1Token); //renovar a pass do user 1
    	service_14.execute();
	
    	DeleteUserIntegrator service_15 = new DeleteUserIntegrator(rootToken,Username2); // apagar o user 2
    	service_15.execute();
    	Utilizador u = getUserFromUsername(Username2);
    	assertEquals(u, null);   	
    }
}

