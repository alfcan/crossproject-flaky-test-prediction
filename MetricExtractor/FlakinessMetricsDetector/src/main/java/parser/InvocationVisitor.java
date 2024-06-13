package parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.Collection;

public class InvocationVisitor extends ASTVisitor {
	
	private Collection<String> invocations;
	
	public InvocationVisitor(Collection<String> pInvocations) {
		invocations = pInvocations;
	}
	
	public boolean visit(MethodInvocation pInvocationNode) {
		invocations.add(pInvocationNode.getName().toString());
		return true;
	}
	
	public boolean visit(TypeDeclaration pClassNode) {
		return false;
	}
	
	private boolean isLocalInvocation(MethodInvocation pInvocationNode) {
		
		// Get the important data of the invocation
		String invocation = pInvocationNode.toString();
		String invocationName = pInvocationNode.getName().toString();
		int index = invocation.indexOf(invocationName);
		
		// The invocation has not a qualifier
		if (index == 0)
			return true;
		
		// The invocation has this as qualifier
		else if (index >= 5 && invocation.substring(index-5, index-1).equals("this"))
			return true;
		
		// The invocation has some other qualifier
		else
			return false;
		
	}
	
}
