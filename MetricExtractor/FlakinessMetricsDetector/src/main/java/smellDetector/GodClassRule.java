package smellDetector;

import bean.*;
import metrics.CKMetrics;

/**
 * 
 * Implementation of DECOR's God Class Rule Card (reported in the following)
 * 
 * RULE_CARD: Blob {
	RULE: Blob {ASSOC: associated FROM: mainClass ONE
		TO: DataClass MANY}
	RULE: mainClass {UNION LargeClassLowCohesion ControllerClass}
	RULE: LargeClassLowCohesion {UNION LargeClass LowCohesion} 
	RULE: LargeClass {(METRIC: NMD + NAD, VERY_HIGH, 20)} 
	RULE: LowCohesion {(METRIC: LCOM5, VERY_HIGH, 20)}
	RULE: ControllerClass {UNION (SEMANTIC: METHODNAME,
		{Process, Control, Command, Manage, Drive, System}), (SEMANTIC: CLASSNAME, {Process, Control, Command, Manage, Drive, System}}
	RULE: DataClass {(STRUCT: METHOD_ACCESSOR, 90)} };
 * 
 * @author fabiopalomba
 *
 */
public class GodClassRule {

	public double isGodClass(ClassBean pClass) {

		if(isControllerClass(pClass)) {
			return isLargeClassLowCohesion(pClass);
		}

		return 0;
	}

	private static double isLargeClassLowCohesion(ClassBean pClass) {
		int featureSum = CKMetrics.getWMC(pClass) + CKMetrics.getNOA(pClass);

		if( (CKMetrics.getLCOM2(pClass) > 350) || (featureSum > 20)) {
			if(CKMetrics.getELOC(pClass) > 500) {
				double sum = (CKMetrics.getLCOM2(pClass) - 350) + (featureSum - 20) + (CKMetrics.getELOC(pClass) - 500);
				return sum;
			}
		}
		return 0;
	}

	private static boolean isControllerClass(ClassBean pClass) {
		String pClassName = pClass.getName().toLowerCase();

		if( (pClassName.contains("process")) || (pClassName.contains("control") || pClassName.contains("command") 
				|| pClassName.contains("manage") || pClassName.contains("drive") || pClassName.contains("system"))) {
			int featureSum = CKMetrics.getWMC(pClass) + CKMetrics.getNOA(pClass);

			if( (CKMetrics.getLCOM2(pClass) > 350) || (featureSum > 20)) {
				if(CKMetrics.getELOC(pClass) > 500)  
					return true;
			}
		}

		return false;
	}
}