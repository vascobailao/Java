package pt.tecnico.bubbledocs.service.integration; 

import pt.tecnico.bubbledocs.exception.BubbleDocsException;
import pt.tecnico.bubbledocs.service.AssignBinaryFunctionToCell;

public class AssignBinaryFunctionServiceIntegrator extends BubbleDocsIntegrator{

	private String cellid;
	private int folhaID;
	private String usertoken;
	private String content;
	private String result;
	
	public String getResult() {
		return this.result;
	}
	
	public AssignBinaryFunctionServiceIntegrator(String cellID, int folhaID, String userToken, String conteudo) {
		// add code here
		this.cellid = cellID;
		this.folhaID = folhaID;
		this.usertoken = userToken;
		this.content = conteudo;
	}
	
	@Override
	protected void dispatch() throws BubbleDocsException {
		// TODO Auto-generated method stub
		AssignBinaryFunctionToCell binary = new AssignBinaryFunctionToCell(this.cellid,this.folhaID,this.usertoken,this.content);
    	binary.execute();
    	this.result=binary.getResult();
		
	}	
}
