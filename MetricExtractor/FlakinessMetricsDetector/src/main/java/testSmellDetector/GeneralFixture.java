package testSmellDetector;

import bean.*;

import java.util.Vector;

public class GeneralFixture {

	public boolean isGeneralFixture(ClassBean pClassBean) {

		for (MethodBean mb : pClassBean.getMethods()) {
			
			if (mb.getName().toLowerCase().equals("setup")) {
				Vector<InstanceVariableBean> instanceVariablesInsideSetUp = (Vector<InstanceVariableBean>) mb
						.getUsedInstanceVariables();

				if (instanceVariablesInsideSetUp.size() > 0) {
					for (MethodBean mbInside : pClassBean.getMethods()) {
						if (!mbInside.getName().equals(
								pClassBean.getName())
								&& !mbInside.getName()
								.toLowerCase()
								.equals("setup")
								&& !mbInside.getName()
								.toLowerCase()
								.equals("teardown")) {
							Vector<InstanceVariableBean> tmpUsed = (Vector<InstanceVariableBean>) mbInside
									.getUsedInstanceVariables();
							for (InstanceVariableBean ivb : instanceVariablesInsideSetUp) {
								if (!tmpUsed.contains(ivb)) {
									return true;
								}
							}
						} 
					}

				}
			}
		}
		
		return false;
	}
}
