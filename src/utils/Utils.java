package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
}
