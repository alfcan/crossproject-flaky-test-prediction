

package parser;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

	/**
	 * This class allows to visit the MethodInvocation node (i.e.: example.getX()) of the AST representing the Class;
	 *
	 * @author Fabio Palomba;
	 */
	public class MethodInvocationsVisitor extends ASTVisitor {
		private List<MethodInvocation> methods = new ArrayList<MethodInvocation>();
	

		@Override
			public boolean visit(MethodInvocation node) {
				methods.add(node);
		
			  return super.visit(node);
			}

		/**
		 * This method allows to get all the MethodInvocation for the Class on which it is;
		 * 
		 * @return
		 * 				a List of all MethodInvocation;
		 */
			public Collection<MethodInvocation> getMethods() {
				return methods;			
			}
	}