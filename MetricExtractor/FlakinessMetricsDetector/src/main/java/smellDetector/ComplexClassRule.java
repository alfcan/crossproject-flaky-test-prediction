package smellDetector;

import bean.*;
import metrics.CKMetrics;

public class ComplexClassRule {

	public int isComplexClass(ClassBean pClass) {

		if(CKMetrics.getMcCabeMetric(pClass) > 200)
				return CKMetrics.getMcCabeMetric(pClass);

		return 0;
	}
}