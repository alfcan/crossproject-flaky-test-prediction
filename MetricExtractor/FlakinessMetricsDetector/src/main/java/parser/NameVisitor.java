package parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.Collection;

public class NameVisitor extends ASTVisitor {
	
	private Collection<String> names;
	
	public NameVisitor(Collection<String> pNames) {
		names = pNames;
	}
	
	public boolean visit(SimpleName pNameNode) {
		names.add(pNameNode.toString());
		return true;
	}
	
	public boolean visit(TypeDeclaration pClassNode) {
		return false;
	}
	
}
