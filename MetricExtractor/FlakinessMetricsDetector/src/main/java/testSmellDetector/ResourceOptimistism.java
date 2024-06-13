package testSmellDetector;

import bean.*;

import java.io.File;

public class ResourceOptimistism {

	public boolean isResourceOptimistism(ClassBean pTestSuite) {
		String code = pTestSuite.getTextContent();
		String lines[] = code.split("\n");

		for (int k = 0; k < lines.length; k++) {
			if (lines[k].contains(" File ")) {

				if (lines[k].indexOf("\"") != -1) {
					String definedPath = lines[k].substring(lines[k].indexOf("\"") + 1, lines[k].lastIndexOf("\""));

					File definedFile = new File(definedPath);

					if (!definedFile.exists())
						return true;
				}
			}
		}

		return false;
	}

	public int isResourceOptimistism(MethodBean pTestCase) {
		String code = pTestCase.getTextContent();
		String lines[] = code.split("\n");
		int numberOfFiles = 0;
		int numberOfChecks = 0;

		try {
			while (code.contains(" File ")) {
				int indexStart = code
						.indexOf(" File ");
				int indexEnd = indexStart;
				char c = code.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = code.charAt(indexEnd);
					substring += c + "";
				}
				numberOfFiles++;

				code = code.replaceFirst(
						" File ", "");
			}

			while (code.contains("exists")) {
				int indexStart = code
						.indexOf("exists");
				int indexEnd = indexStart;
				char c = code.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = code.charAt(indexEnd);
					substring += c + "";
				}
				numberOfChecks++;

				code = code.replaceFirst(
						"exists", "");
			}
			while (code.contains("isReadable")) {
				int indexStart = code
						.indexOf("isReadable");
				int indexEnd = indexStart;
				char c = code.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = code.charAt(indexEnd);
					substring += c + "";
				}
				numberOfChecks++;

				code = code.replaceFirst(
						"isReadable", "");
			}
			while (code.contains("isWritable")) {
				int indexStart = code
						.indexOf("isWritable");
				int indexEnd = indexStart;
				char c = code.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = code.charAt(indexEnd);
					substring += c + "";
				}
				numberOfChecks++;

				code = code.replaceFirst(
						"isWritable", "");
			}

			while (code.contains("isExecutable")) {
				int indexStart = code
						.indexOf("isExecutable");
				int indexEnd = indexStart;
				char c = code.charAt(indexStart);
				String substring = c + "";
				while (c != ';') {
					indexEnd++;
					c = code.charAt(indexEnd);
					substring += c + "";
				}
				numberOfChecks++;

				code = code.replaceFirst(
						"isExecutable", "");
			}
		}catch (Exception e) {

		}
		if (numberOfChecks > numberOfFiles)
			return 0;

		return numberOfFiles - numberOfChecks;
	}
}