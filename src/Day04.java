import java.util.ArrayList;
import java.util.List;

import static utils.Utils.reverse;

public class Day04 extends BaseDay {
	public Day04() {
		DAY = "04";
		part1TestSolution = 18;
		part2TestSolution = 9;
	}

	@Override
	protected long part1() {
		long sum = 0;

		for (int y = 0; y < lines.size(); ++y) {
			for (int x = 0; x < lines.getFirst().length(); ++x) {
				sum += countXMAS(x, y);
			}
		}

		return sum;
	}

	@Override
	protected long part2() {
		long sum = 0;

		for (int y = 0; y < lines.size(); ++y) {
			for (int x = 0; x < lines.getFirst().length(); ++x) {
				if (isCenterOfCrossMAS(x, y)) {
					sum++;
				}
			}
		}

		return sum;
	}

	private long countXMAS(int x, int y) {
		char currentLetter = lines.get(y).charAt(x);

		if (currentLetter != 'X' && currentLetter != 'S') return 0;

		List<String> candidates = new ArrayList<>();
		if (y > 2) {
			candidates.add("" + currentLetter
					+ lines.get(y - 1).charAt(x)
					+ lines.get(y - 2).charAt(x)
					+ lines.get(y - 3).charAt(x)
			);
		}

		if (x > 2) {
			candidates.add("" + currentLetter
					+ lines.get(y).charAt(x - 1)
					+ lines.get(y).charAt(x - 2)
					+ lines.get(y).charAt(x - 3)
			);
		}

		if (x > 2 && y > 2) {
			candidates.add("" + currentLetter
					+ lines.get(y - 1).charAt(x - 1)
					+ lines.get(y - 2).charAt(x - 2)
					+ lines.get(y - 3).charAt(x - 3)
			);
		}

		if (x < lines.getFirst().length() - 3 && y > 2) {
			candidates.add("" + currentLetter
					+ lines.get(y - 1).charAt(x + 1)
					+ lines.get(y - 2).charAt(x + 2)
					+ lines.get(y - 3).charAt(x + 3)
			);
		}

		return candidates.stream().filter(this::isXMAS).count();
	}

	private boolean isCenterOfCrossMAS(int x, int y) {
		char currentLetter = lines.get(y).charAt(x);

		if (currentLetter != 'A') return false;
		if (x == 0 || y == 0 || x >= lines.getFirst().length() - 1 || y >= lines.size() - 1) return false;

		List<String> diagonals = new ArrayList<>();
		diagonals.add(""
				+ lines.get(y - 1).charAt(x - 1)
				+ currentLetter
				+ lines.get(y + 1).charAt(x + 1)
		);
		diagonals.add(""
				+ lines.get(y - 1).charAt(x + 1)
				+ currentLetter
				+ lines.get(y + 1).charAt(x - 1)
		);

		return diagonals.stream().allMatch(this::isMAS);
	}

	private boolean isXMAS(String s) {
		String xmas = "XMAS";
		return s.equals(xmas) || s.equals(reverse(xmas));
	}

	private boolean isMAS(String s) {
		String mas = "MAS";
		return s.equals(mas) || s.equals(reverse(mas));
	}
}