package testSmellDetector;

import bean.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public class IndirectTesting {

	public boolean isIndirectTesting(ClassBean pClassBean, ClassBean pProductionClass, Collection<MethodBean> pMethods) {

		for(MethodBean methodBean: pClassBean.getMethods()) {
			if(! isInProductionClass(methodBean, pClassBean)) {
				for(MethodBean call: pMethods) {
					if(call.getBelongingClass().getName().equals(pProductionClass.getName())) {
						if(call.getName().equals(methodBean.getName())) {
							return true;
						}

					}					
				}

			}
		}

		return false;
	}

	public boolean isIndirectTesting(MethodBean pMethodBean, ClassBean pProductionClass, Collection<MethodBean> pMethods) {

		Collection<MethodBean> testCalls = pMethodBean.getMethodCalls();
		int countProductionClass = 0;
		int countNonAPIMethods = 0;
		
		for(MethodBean method: pProductionClass.getMethods()) {
			for(MethodBean testCall: testCalls) {
				
				if(testCall.getName().equals(method.getName())) {
					countProductionClass++;
				}
			}
		}
		
		if(countProductionClass == testCalls.size()) { 
			return false;
		} else {
			
			for(MethodBean method: pMethods) {
				for(MethodBean testCall: testCalls) {
					
					if(testCall.getName().equals(method.getName())) {
						countNonAPIMethods++;
					}
				}
			}
			
			if(countNonAPIMethods > 30) 
				return true;
			
		}
		
		return false;
	}

	private boolean isInProductionClass(MethodBean pMethodBean, ClassBean pProductionClass) {

		for(MethodBean methodBean: pProductionClass.getMethods()) {
			if(methodBean.getName().equals(pMethodBean.getName())) {
				return true;
			} else return false;
		}

		return false;
	}

	public static Collection<MethodBean> findInvocations(Vector<PackageBean> pPackages) {
		ArrayList<MethodBean> invocations = new ArrayList<MethodBean>();

		for(PackageBean packageBean: pPackages) {
			for(ClassBean classBean: packageBean.getClasses()) {
				for(MethodBean methodBean: classBean.getMethods()) {
					methodBean.setBelongingClass(classBean);
					invocations.add(methodBean);
				}
			}
		}

		return invocations;
	}
}
