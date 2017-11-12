package pt.tecnico.bubbledocs.service.integration.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pt.tecnico.bubbledocs.domain.*;
import pt.tecnico.bubbledocs.service.integration.*;
import pt.tecnico.bubbledocs.exception.*;
import pt.tecnico.bubbledocs.service.*;

import java.util.Set;


public class AssignIntervalFunctionIntegratorTest extends BubbleDocsServiceTest{

	private static final String USERNAME_TO_DELETE = "smf"; //ESTE NAO ESTA EM SESSAO
	private static final String USERNAME = "ars"; //ESTE ESTA EM SESSAO e tem permissao
	private static final String PASSWORD = "ars"; 
	private String ab; //the token for user ab
	private   int ID_SpreadSheet=0; //ID VALIDO
	private static final String SPREADSHEET_NAME = "spread"; //nome da folha
	private static final String VALID_CELL_ID = "1;1";
    private static final String USERNAME2 = "ab";
    private static final String PASSWORD2 = "ab";
    


	private static final String VALID_CELL_ID2 = "1;5";
	private static final String VALID_FUNCTION = "\0PRD(1;1:1;3)";
	private static final String VALID_FUNCTION2 = "\0AVG(1;5:1;7)";
	private static final String EMAIL= "antonio@gmail";
	private FolhaCalculo folha = null;


	// the tokens for user in session
	private String ars;
	//  private String smf;

	@Override
	public void populate4Test() {
		createUser(USERNAME2, PASSWORD2, "Abilio Batata",EMAIL);
		Utilizador ars = createUser(USERNAME, PASSWORD, "António Rito Silva",EMAIL);
		Utilizador smf = createUser(USERNAME_TO_DELETE, "smf", "Sérgio Fernandes",EMAIL);
		folha= createSpreadSheet(ars, SPREADSHEET_NAME, 20, 20);
		this.ID_SpreadSheet=getSpreadSheet(SPREADSHEET_NAME).getIdentificador();
		//Adicionar a permissao ao user 'smf' de leitura
		this.addPermissionToUserRead(smf, SPREADSHEET_NAME);


	};

	//SUCESSO PARA PRD 
	public void successPrd() {

		AssignLiteralCellIntegrator integrator1= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,VALID_CELL_ID,"2");
		AssignLiteralCellIntegrator integrator2= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,"1;2","3");
		AssignLiteralCellIntegrator integrator3= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,"1;3","4");
		integrator1.execute();
		integrator2.execute();
		integrator3.execute();

		AssignIntervalFunctionServiceIntegrator service= new  AssignIntervalFunctionServiceIntegrator ("1;4", VALID_FUNCTION,ID_SpreadSheet, ars);
		service.execute();
		String result = service.getResult();
		Integer resultFinal = service.getResultFinal();

		assertEquals(result,"\0PRD(1;1:1;3)");
		assertEquals(resultFinal.toString(),"24");

	}

	//SUCESSO PARA AVG 
	public void successAvg() {

		AssignLiteralCellIntegrator integrator4= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,VALID_CELL_ID2,"2");
		AssignLiteralCellIntegrator integrator5= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,"1;6","6");
		AssignLiteralCellIntegrator integrator6= new AssignLiteralCellIntegrator(ars,ID_SpreadSheet,"1;7","4");
		integrator4.execute();
		integrator5.execute();
		integrator6.execute();

		AssignIntervalFunctionServiceIntegrator serviceAvg = new  AssignIntervalFunctionServiceIntegrator ("1;4", VALID_FUNCTION2,ID_SpreadSheet, ars);
		serviceAvg.execute();
		String result = serviceAvg.getResult();
		Integer resultFinalAvg = serviceAvg.getResultFinal();

		assertEquals(result,"\0AVG(1;5:1;7)");
		assertEquals(resultFinalAvg.toString(),"4");

	}
	
	
	@Test
	public void successToInsertIntervalPrd() {
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session
		successPrd();

	}

	@Test
	public void successToInsertIntervalAvg() {
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session
		successAvg();

	}
	
	//TESTES DE EXCEPCOES-------------------------------------------------------------------------
	
	@Test (expected = InvalidCellIdFormat.class)
    public void invalidCellFormat(){
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session
    	AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("A;2", "AVG(1;1:1;4)", ID_SpreadSheet, ars);
    	service.execute();
    }
	
	
	@Test (expected = InvalidIntervalType.class) 
	public void invalidIntervalFormat(){
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session

		AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("12", "AVG(3.A:1;4)", ID_SpreadSheet, ars);
		service.execute();
	}
	
	@Test (expected = InvalidCellId.class)
	public void cellDoesNotExist() {
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session
		AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("101;2", "AVG(1;1:1;4)", ID_SpreadSheet, ars);
		service.execute();
	}
	
	@Test(expected = UnauthorizedOperationException.class)
    public void userWithoutPermission(){
		this.ab = addUserToSession(USERNAME2,"Abilio Batata"); //Insert user in session
    	AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("3;2", "=AVG(3;4:1;4)", ID_SpreadSheet, ab);
    	service.execute();
    	
    }
	
	@Test(expected = UnknownBubbleDocsSpreadSheetException.class)
    public void invalidIdSpreadsheet() {
		this.ars = addUserToSession(USERNAME,"António Rito Silva"); //Insert user in session
    	AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("3;2", "AVG(2;4:1;4)", 20, ars);
    	service.execute();
    }
	
	@Test(expected = UserNotInSessionException.class)
    public void accessUsernameNotExist() {
		this.ars = addUserToSession(USERNAME,"António Rito Silva");
        removeUserFromSession(ars);
    	AssignRangeFunctionToCell service = new AssignRangeFunctionToCell("3;2","=PRD(3;4:1;4)", ID_SpreadSheet,ars);
    	service.execute();
    }

	
	
}
