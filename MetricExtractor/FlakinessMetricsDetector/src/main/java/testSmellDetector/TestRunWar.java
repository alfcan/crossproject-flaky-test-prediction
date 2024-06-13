package testSmellDetector;

import bean.*;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunWar {

	public double isTestRunWar(MethodBean pMethod) {
		double testRunWar = 0.0;

		Collection<MethodBean> testCases = pMethod.getBelongingClass().getMethods();		
		String code = pMethod.getTextContent();
		String lines[] = code.split("\n");

		String path = ""; 


		for(int k=0; k<lines.length; k++) {
			if(lines[k].contains(" File(")) {
				Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
				Matcher matcher = pattern.matcher(lines[k]);
				if(matcher.matches())
					path = matcher.group();
				return 0;
			}
		}

		for(MethodBean testCase: testCases) {
			if(! testCase.equals(pMethod)) {
				String methodCode = pMethod.getTextContent();
				String methodLines[] = methodCode.split("\n");

				for(int k=0; k<methodLines.length; k++) {
					if(methodLines[k].contains(" File( ")) {
						Pattern pattern = Pattern.compile("([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?");
						Matcher matcher = pattern.matcher(methodLines[k]);

						if(matcher.group().equals(path))
							testRunWar++;
					}
				}

			}


		}


		return testRunWar;
	}
}
