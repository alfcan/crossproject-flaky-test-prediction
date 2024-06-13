package parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.Collection;

public class ClassVisitor extends ASTVisitor {
	
	private Collection<TypeDeclaration> classNodes;
	
	public ClassVisitor(Collection<TypeDeclaration> pClassNodes) {
		classNodes = pClassNodes;
	}
	
	public boolean visit(TypeDeclaration pClassNode) {
		classNodes.add(pClassNode);
		return true;
	}
	
}
