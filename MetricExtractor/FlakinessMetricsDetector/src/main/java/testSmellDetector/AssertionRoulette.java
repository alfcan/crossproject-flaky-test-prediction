package testSmellDetector;

import bean.*;

public class AssertionRoulette {

	public double isAssertionRoulette(ClassBean pClassBean) {
		double assertionRoulette = 0.0;

		for (MethodBean mb : pClassBean.getMethods()) {

				if (assertionRoulette != 0.0) {
					String methodBody = mb.getTextContent();
					methodBody = methodBody.replace(mb.getName(),
							"");

					while (methodBody.contains("assert")) {
						int indexStart = methodBody
								.indexOf("assert");
						int indexEnd = indexStart;
						char c = methodBody.charAt(indexStart);
						String substring = c + "";
						while (c != ';') {
							indexEnd++;
							c = methodBody.charAt(indexEnd);
							substring += c + "";
						}

						if (!substring.contains("\"")) {
							assertionRoulette ++;
						}

						methodBody = methodBody.replaceFirst(
								"assert", "");
					}

					while (methodBody.contains("fail(")) {
						int indexStart = methodBody
								.indexOf("fail(");
						int indexEnd = indexStart;
						char c = methodBody.charAt(indexStart);
						String substring = c + "";
						while (c != ';') {
							indexEnd++;
							c = methodBody.charAt(indexEnd);
							substring += c + "";
						}

						if (!substring.contains("\"")) {
							assertionRoulette++;
						}

						methodBody = methodBody.replaceFirst(
								"fail\\(", "");
					}

					while (methodBody.contains("fail (")) {
						int indexStart = methodBody
								.indexOf("fail (");
						int indexEnd = indexStart;
						char c = methodBody.charAt(indexStart);
						String substring = c + "";
						while (c != ';') {
							indexEnd++;
							c = methodBody.charAt(indexEnd);
							substring += c + "";
						}

						if (!substring.contains("\"")) {
							assertionRoulette++;
						}

						methodBody = methodBody.replaceFirst(
								"fail \\(", "");
					}
				}
			}


		return assertionRoulette;
	}

	public double isAssertionRoulette(MethodBean pMethodBean) {
		double assertionRoulette = 0.0;

			String methodBody = pMethodBean.getTextContent();
			methodBody = methodBody.replace(pMethodBean.getName(),
					"");
			try {
				while (methodBody.contains("assert")) {
					int indexStart = methodBody
							.indexOf("assert");
					int indexEnd = indexStart;
					char c = methodBody.charAt(indexStart);
					String substring = c + "";
					while (c != ';') {
						indexEnd++;
						c = methodBody.charAt(indexEnd);
						substring += c + "";
					}

					if (!substring.contains("\"")) {
						assertionRoulette++;
					}

					methodBody = methodBody.replaceFirst(
							"assert", "");
				}

				while (methodBody.contains("fail(")) {
					int indexStart = methodBody
							.indexOf("fail(");
					int indexEnd = indexStart;
					char c = methodBody.charAt(indexStart);
					String substring = c + "";
					while (c != ';') {
						indexEnd++;
						c = methodBody.charAt(indexEnd);
						substring += c + "";
					}

					if (!substring.contains("\"")) {
						assertionRoulette++;
					}

					methodBody = methodBody.replaceFirst(
							"fail\\(", "");
				}

				while (methodBody.contains("fail (")) {
					int indexStart = methodBody
							.indexOf("fail (");
					int indexEnd = indexStart;
					char c = methodBody.charAt(indexStart);
					String substring = c + "";
					while (c != ';') {
						indexEnd++;
						c = methodBody.charAt(indexEnd);
						substring += c + "";
					}

					if (!substring.contains("\"")) {
						assertionRoulette++;
					}

					methodBody = methodBody.replaceFirst(
							"fail \\(", "");
				}
			}catch (Exception e) {

			}

		return assertionRoulette;
	}

}
