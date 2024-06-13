package testSmellDetector;

import bean.*;


public class SensitiveEquality {

	public boolean isSensitiveEquality(ClassBean pClassBean) {
		boolean sensitiveEquality = false;

		for (MethodBean mb : pClassBean.getMethods()) {
			try {	
				if (!sensitiveEquality) {
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

						if (substring.toLowerCase().contains(
								"tostring")) {
							sensitiveEquality = true;
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

						if (substring.toLowerCase().contains(
								"tostring")) {
							sensitiveEquality = true;
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

						if (substring.toLowerCase().contains(
								"tostring")) {
							sensitiveEquality = true;
						}

						methodBody = methodBody.replaceFirst(
								"fail \\(", "");
					}
				}
			} catch(StringIndexOutOfBoundsException e) {
				return false;
			}
		}

		return sensitiveEquality;
	}

	public double isSensitiveEquality(MethodBean pMethodBean) {
		double sensitiveEquality = 0.0;


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

					if (substring.toLowerCase().contains(
							"tostring")) {
						sensitiveEquality++;
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

					if (substring.toLowerCase().contains(
							"tostring")) {
						sensitiveEquality++;
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

					if (substring.toLowerCase().contains(
							"tostring")) {
						sensitiveEquality++;
					}

					methodBody = methodBody.replaceFirst(
							"fail \\(", "");
				}

			} catch (Exception e) {

			}

		return sensitiveEquality;
	}

}
