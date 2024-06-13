package testSmellDetector;

import bean.*;

public class ConditionalTestLogic {

	public double isConditionalTestLogic(MethodBean pMethod) {
		double conditionalTestLogic = 0.0;
		String body = pMethod.getTextContent();

		try {
			while (body.contains("if")) {
				int indexStart = body
						.indexOf("if");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";
				}
					conditionalTestLogic ++;

				body = body.replaceFirst(
						"if", "");
			}

			while (body.contains("switch")) {
				int indexStart = body
						.indexOf("switch");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";
				}
				conditionalTestLogic ++;

				body = body.replaceFirst(
						"switch", "");
			}

			while (body.contains("for")) {
				int indexStart = body
						.indexOf("for");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";
				}
				conditionalTestLogic ++;

				body = body.replaceFirst(
						"for", "");
			}

			while (body.contains("foreach")) {
				int indexStart = body
						.indexOf("foreach");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";
				}
				conditionalTestLogic ++;

				body = body.replaceFirst(
						"foreach", "");
			}

			while (body.contains("while")) {
				int indexStart = body
						.indexOf("while");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";
				}
				conditionalTestLogic ++;

				body = body.replaceFirst(
						"while", "");
			}


		} catch (Exception e) {

		}

		
		/*Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		
		try {
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		return conditionalTestLogic;
	}
}
