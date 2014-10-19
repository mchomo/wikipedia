package templateparser;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.*;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File fileDir = new File("/Users/Matthew/Documents/FIIT/VI/skwiki-latest-pages-meta-history.xml");

		// BufferedReader in = new BufferedReader(new InputStreamReader(new
		// FileInputStream(fileDir), "UTF8"));
		//
		// String text = "";
		// String line;
		// // while ((line = in.readLine()) != null) {
		// while ((line = in.readLine()) != null) {
		// text = text + "\n" + line;
		// }
		// in.close();
		//
		// RegexChecker(text);
		// System.out.println(text);

		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileDir), "UTF-8");
		Scanner scanner_templates;
		scanner_templates = new Scanner(isr);
		scanner_templates.useDelimiter("\\{\\{");

		int numOfLeft = 0;
		int numOfRight = 0;

		String longTemplate = "";

		// Rozklad na tokeny
		scanner_templates.next(); // prvy token
		while (scanner_templates.hasNext()) {
			String token = scanner_templates.next();
			numOfLeft++; // lava zatvorka

			int rBrackets = countOfSubstring("}}", token);
			if (rBrackets == 0)
				longTemplate += "{{" + token;
			else {
				longTemplate += "{{";
				numOfRight += rBrackets;
				String arr[] = token.split("}}");
				if (rBrackets == 1)
					longTemplate += arr[0] + "}}";
				else {
					for (int k = 0; k < rBrackets - 1; k++)
						longTemplate += arr[k] + "}}";
					longTemplate += "}}";
				}
			}

			if (numOfRight != 0 && numOfLeft != 0 && (numOfLeft == numOfRight)) {
				System.out.print("\n" + longTemplate + "\n");
				System.out.println("\n----------------------------------------------");
				numOfLeft = 0;
				numOfRight = 0;
				longTemplate = "";
			}
		}

	}

	public static int countOfSubstring(String patrn, String txt) {
		Pattern pattern = Pattern.compile(patrn);
		Matcher matcher = pattern.matcher(txt);
		int patternCount = 0;
		while (matcher.find()) {
			patternCount++;
		}
		// System.out.println("parttern: " + patternCount);
		return patternCount;
	}

	public static void RegexChecker(String str2check) {

		Pattern checkRegex = Pattern.compile("\\{\\{\\s*(.+)\\}\\}");
		Matcher regexMatcher = checkRegex.matcher(str2check);

	}
}
