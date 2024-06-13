package testSmellDetector;

import bean.*;

public class MysteryGuest {

	public boolean isMysteryGuest(ClassBean pClassBean) {
		boolean mysteryGuest = false;

		for (MethodBean mb : pClassBean.getMethods()) {
			String body = mb.getTextContent();

			if (!mysteryGuest){
				if (body.contains(" File ") || body.contains(" File(") || body.contains("db")){
					mysteryGuest = true;
				}
			}
		}

		return mysteryGuest;
	}

	public double isMysteryGuest(MethodBean pMethodBean) {
		double mysteryGuest = 0.0;
		String body = pMethodBean.getTextContent();

		try {

			while (body.contains("File")) {
				int indexStart = body
						.indexOf("File");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";

				}
				mysteryGuest++;
				body = body.replaceFirst(
						"File", "");
			}

			while (body.contains("File(")) {
				int indexStart = body
						.indexOf("File(");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";

				}
				mysteryGuest++;
				body = body.replaceFirst(
						"File\\(", "");
			}

			while (body.contains("db")) {
				int indexStart = body
						.indexOf("db");
				int indexEnd = indexStart;
				char c = body.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = body.charAt(indexEnd);
					substring += c + "";

				}
				mysteryGuest++;
				body = body.replaceFirst(
						"db", "");
			}
		}catch (Exception e) {

		}

		return mysteryGuest;
	}
}