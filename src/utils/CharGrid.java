package utils;

import java.util.List;

public class CharGrid extends Grid<Character> {
	public CharGrid(List<String> lines) {
		this.data = lines.stream()
				.map(line -> line.chars().mapToObj(c -> (char) c).toList())
				.toList()
				.reversed();
	}
}
