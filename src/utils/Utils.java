package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public abstract class Utils {
	public static void print(String s) {
		System.out.println(s);
	}

	public static void newLine() {
		print("");
	}

	public static List<String> readFile(String filename) {
		try {
			return Files.readAllLines(Paths.get("input/", filename));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Integer> extractAllUnsignedInts(String s) {
		List<Integer> numbers = new ArrayList<>();
		String[] parts = s.split("\\D+");

		for (String part : parts) {
			if (!part.isEmpty()) {
				numbers.add(Integer.parseInt(part));
			}
		}
		return numbers;
	}

	public static List<String> extractAll(String s, String regex) {
		return Pattern
				.compile(regex)
				.matcher(s)
				.results()
				.map(MatchResult::group)
				.toList();
	}

	public static List<MatchResult> matchAll(String s, String regex) {
		return Pattern
				.compile(regex)
				.matcher(s)
				.results()
				.toList();
	}
}
