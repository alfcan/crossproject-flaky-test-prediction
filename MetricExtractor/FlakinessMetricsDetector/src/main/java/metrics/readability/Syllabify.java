package metrics.readability;

public class Syllabify {

	static String[] SubSyl = { "cial", "tia", "cius", "cious", "giu", "ion", "iou", "sia$", ".ely$" };

	static String[] AddSyl = { "ia", "riet", "dien", "iu", "io", "ii", "[aeiouym]bl$", "[aeiou]{3}", "^mc", "ism$",
			"[^aeiouy][^aeiouy]l$", "[^l]lien", "^coa[dglx].", "[^gq]ua[^auieo]", "dnt$" };

	public static int syllable(String word) {

		word = word.toLowerCase();
		word = word.replaceAll("'", " ");

		if (word.equals("i"))
			return 1;
		if (word.equals("a"))
			return 1;

		if (word.endsWith("e")) {
			word = word.substring(0, word.length() - 1);
		}

		String[] phonems = word.split("[^aeiouy]+");

		int syl = 0;
		for (int i = 0; i < SubSyl.length; i++) {
			String syllabe = SubSyl[i];
			if (word.matches(syllabe)) {
				syl--;
			}
		}
		for (int i = 0; i < AddSyl.length; i++) {
			String syllabe = AddSyl[i];
			if (word.matches(syllabe)) {
				syl++;
			}
		}
		if (word.length() == 1) {
			syl++;
		}

		for (int i = 0; i < phonems.length; i++) {
			if (phonems[i].length() > 0)
				syl++;
		}

		if (syl == 0) {
			syl = 1;
		}

		return syl;
	}

}