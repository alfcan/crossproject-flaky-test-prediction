package smellDetector;

import bean.*;

public class FeatureEnvyRule {

	public boolean isFeatureEnvy(int threshold, MethodBean pMethod, ClassBean pBelongingClass, ClassBean pCandidateEnvyClass) {

		int dependenciesWithCandidateEnvyClass = computeDependencies(pMethod, pCandidateEnvyClass);
		int dependenciesWithBelongingClass = computeDependencies(pMethod, pBelongingClass);
		double structuralDiff = dependenciesWithCandidateEnvyClass - dependenciesWithBelongingClass;

		if(structuralDiff > threshold) {
			return true;
		}

		return false;
	}

	private int computeDependencies(MethodBean pMethod, ClassBean pClass) {
		int dependencies = 0;

		for(MethodBean calledMethod : pMethod.getMethodCalls()) {

			for(MethodBean classMethod: pClass.getMethods()) {
				if(calledMethod.getName().equals(classMethod.getName())) 
					dependencies++;
			}

		}

		return dependencies;
	}
}