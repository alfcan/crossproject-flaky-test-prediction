package metrics;

import bean.*;

import java.io.IOException;

public class ConceptualMetrics {

	static String pathToStopword = "/Users/fabiopalomba/Desktop/stopword";
	
	public static double getC3(ClassBean cb) {
		Double c3=0.0;
		double comparisons=0.0;
		CosineSimilarity cosineSimilarity = new CosineSimilarity(ConceptualMetrics.pathToStopword);

		for(MethodBean methodBean: cb.getMethods()) {
			for(MethodBean methodBean2: cb.getMethods()) {

				if(! methodBean.equals(methodBean2)) {
					String[] document1=new String[2];
					String[] document2=new String[2];

					document1[0] = methodBean.getName();
					document1[1] = methodBean.getTextContent();

					document2[0] = methodBean2.getName();
					document2[1] = methodBean2.getTextContent();

					try {
						c3+=cosineSimilarity.computeSimilarity(document1, document2);
						comparisons++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		c3=c3/comparisons;
		
		if (c3.isNaN()) {
			return 0.0;
		} else if(c3.isInfinite()) {
			return 0.0;
		} else return c3;
	}
	
	public static double getCCBC(ClassBean pClass, ClassBean pClass2) {
		Double ccbc=0.0;
		
		double comparisons=0.0;
		CosineSimilarity cosineSimilarity = new CosineSimilarity(ConceptualMetrics.pathToStopword);

		for(MethodBean methodBean: pClass.getMethods()) {
			for(MethodBean methodBean2: pClass2.getMethods()) {

				if(! methodBean.equals(methodBean2)) {
					String[] document1=new String[2];
					String[] document2=new String[2];

					document1[0] = methodBean.getName();
					document1[1] = methodBean.getTextContent();

					document2[0] = methodBean2.getName();
					document2[1] = methodBean2.getTextContent();

					try {
						ccbc+=cosineSimilarity.computeSimilarity(document1, document2);
						comparisons++;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		ccbc=ccbc/comparisons;
		
		if (ccbc.isNaN()) {
			return 0.0;
		} else if(ccbc.isInfinite()) {
			return 0.0;
		} else return ccbc;
	}
}




