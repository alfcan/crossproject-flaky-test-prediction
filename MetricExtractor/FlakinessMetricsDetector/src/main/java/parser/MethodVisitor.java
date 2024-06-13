package parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.Collection;

public class MethodVisitor extends ASTVisitor {
	
	private Collection<MethodDeclaration> methodNodes;
	private boolean firstTime;
	
	public MethodVisitor(Collection<MethodDeclaration> pMethodNodes) {
		methodNodes = pMethodNodes;
		firstTime = true;
	}
	
	public boolean visit(MethodDeclaration pMethodNode) { 
		methodNodes.add(pMethodNode);
		return true;
	}
	
	public boolean visit(TypeDeclaration pClassNode) {
		if (firstTime) {
			firstTime = false;
			return true;
		}
		return firstTime;
	}
	
}
