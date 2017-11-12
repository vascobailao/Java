package pt.tecnico.bubbledocs.service.integration.system;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.jdom2.output.XMLOutputter;
import org.junit.After;
import org.junit.Before;

import pt.ist.fenixframework.FenixFramework;
import pt.ist.fenixframework.core.WriteOnReadError;
import pt.tecnico.bubbledocs.domain.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
// add needed import declarations

public class BubbleDocsLocalSystemTest {
    @Before
    public void setUp() throws Exception {

        try {
            FenixFramework.getTransactionManager().begin(false);
            BubbleDocs.getInstance();
            populate4Test();
        } catch (WriteOnReadError | NotSupportedException | SystemException e1) {
            e1.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            FenixFramework.getTransactionManager().rollback();
        } catch (IllegalStateException | SecurityException | SystemException e) {
            e.printStackTrace();
        }
    }

    // should redefine this method in the subclasses if it is needed to specify
    // some initial state
    public void populate4Test() {
    }

    // auxiliary methods that access the domain layer and are needed in the test classes
    // for defining the iniital state and checking that the service has the expected behavior
    public Utilizador createUser(String username, String password, String name , String email) {
    	Utilizador u = new Utilizador(name);
    	u.setEmail(email);
    	u.setUsername(username);
    	u.setPassLocal(true);
    	BubbleDocs bubble = FenixFramework.getDomainRoot().getBubbledocs();
    	bubble.addUtilizador(u);
    	return u;
    }

//
//    // returns a spreadsheet whose name is equal to name
//     
    public FolhaCalculo getSpreadSheet(String name) {
	// add code here
         BubbleDocs bubble = FenixFramework.getDomainRoot().getBubbledocs();
         Set <FolhaCalculo> folhas = bubble.getFolhacalculoSet();
         for(FolhaCalculo f : folhas){
        	 if(f.getNome().equals(name)){
        		 return f;
        	 }
         }
        return null;
    }


    
    // returns the user registered in the application whose username is equal to username
    public Utilizador getUserFromUsername(String username) {
    	BubbleDocs bd = FenixFramework.getDomainRoot().getBubbledocs();
    	for (Utilizador u : bd.getUtilizadorSet()){
       		if (username.equals(u.getUsername())){
       			return u;
       		}
       	}
       	return null;
    }
    
    
    // return the user registered in session whose token is equal to token
    public Utilizador getUserFromSession(String token) {
    	BubbleDocs bubble = FenixFramework.getDomainRoot().getBubbledocs();
    	Set<Sessao> sessoes = bubble.getSessaoSet();
    	for(Sessao s : sessoes){
    		if(s.getUserToken().equals(token)){
    			return s.getUtilizador();
    		}
    	}
    	return null;
    }
    
    // add a permission to user to read some spreadsheet
    void addPermissionToUserRead(Utilizador u, String spreadSheetName) {
	// add code here
    	FolhaCalculo f = this.getSpreadSheet(spreadSheetName);
    	u.addFolhacalculoLer(f);
    }
    
    //return the user with that token
    Utilizador getUserFromUserToken(String userToken){
    	BubbleDocs bd = FenixFramework.getDomainRoot().getBubbledocs();
    	for (Utilizador u : bd.getUtilizadorSet()){
    		if(userToken.equals(u.getToken())){
    			return u;
    		}
    	}
    	return null;
    }
    

    public byte[] getBytes(FolhaCalculo f){
		org.jdom2.Document jdomDoc = new org.jdom2.Document();
		jdomDoc.setRootElement(f.exportToXML());
		XMLOutputter xml = new XMLOutputter();
		try {
			return xml.outputString(jdomDoc).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
    public void deleteFolhaCalculo (String username, String folhas){
    	BubbleDocs bd = FenixFramework.getDomainRoot().getBubbledocs();
	    Set <Utilizador> utilizadores = bd.getUtilizadorSet();
	    for(Utilizador user : utilizadores){
	    	if(user.getUsername().equals(username)){
	        	List<FolhaCalculo> folhasUser = user.getDocumentsCreatedByUser(folhas, user);
	        	for(FolhaCalculo folha : folhasUser ){
	        		folha.delete();
	        		
	        	}
	    	}
	    }
    }
    
}
