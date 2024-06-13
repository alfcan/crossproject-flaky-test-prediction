package smellDetector;

import bean.*;
import metrics.CKMetrics;

public class ClassDataShouldBePrivateRule {

	public int isClassDataShouldBePrivate(ClassBean pClass) {

		if (CKMetrics.getNOPA(pClass) > 10)
			return CKMetrics.getNOPA(pClass);

		return 0;
	}

}
