import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends BaseDay {

	public Day02() {
		DAY = "02";
		part1TestSolution = 2;
		part2TestSolution = 4;
	}

	@Override
	protected long part1() {

		return lines.stream()
				.map(Utils::extractAllUnsignedInts)
				.filter(this::isSafe)
				.count();
	}

	@Override
	protected long part2() {
		return lines.stream()
				.map(Utils::extractAllUnsignedInts)
				.filter(this::isAlmostSafe)
				.count();
	}

	private boolean isSafe(List<Integer> numbers) {
		return isSafe(numbers, true) || isSafe(numbers, false);
	}

	private boolean isSafe(List<Integer> numbers, boolean increasing) {
		boolean safe = true;
		for (int i = 0; i < numbers.size() - 1; ++i) {
			safe = isSafe(numbers.get(i), numbers.get(i + 1), increasing);
			if (!safe) break;
		}
		return safe;
	}

	private boolean isSafe(int left, int right, boolean increasing) {
		int diff = Math.abs(left - right);

		if (diff < 1 || diff > 3) {
			return false;
		}

		if (increasing)
			return left < right;
		else {
			return right < left;
		}
	}

	private boolean isAlmostSafe(List<Integer> numbers) {
		return isAlmostSafe(numbers, true) || isAlmostSafe(numbers, false);
	}

	private boolean isAlmostSafe(List<Integer> numbers, boolean increasing) {
		boolean safe = true;
		for (int i = 0; i < numbers.size() - 1; ++i) {
			safe = isSafe(numbers.get(i), numbers.get(i + 1), increasing);
			if (!safe) {
				var withoutLeft = copyWithoutElementAt(numbers, i);
				var withoutRight = copyWithoutElementAt(numbers, i + 1);
				safe = isSafe(withoutLeft) || isSafe(withoutRight);
				if (!safe) break;
			}
		}
		return safe;
	}

	private List<Integer> copyWithoutElementAt(List<Integer> list, int index) {
		var copy = new ArrayList<>(list);
		copy.remove(index);
		return copy;
	}
}
