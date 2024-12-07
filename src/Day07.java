import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day07 extends BaseDay {

	private List<Calculation> calculations;

	public Day07() {
		DAY = "07";
		part1TestSolution = 3749;
		part2TestSolution = 11387;
	}

	@Override
	protected long part1() {
		parseInput();

		return calculations.stream()
				.filter(it -> isPossible(it, false))
				.map(c -> c.result)
				.reduce(0L, Long::sum);
	}

	@Override
	protected long part2() {
		parseInput();

		return calculations.stream()
				.filter(it -> isPossible(it, true))
				.map(c -> c.result)
				.reduce(0L, Long::sum);
	}

	private boolean isPossible(Calculation calculation, boolean allowConcatenation) {
		var currentOptions = List.of(calculation.operands.removeFirst());

		for (long operand : calculation.operands) {
			List<Long> nextOptions = new ArrayList<>();

			for (Long option : currentOptions) {
				nextOptions.add(option + operand);
				nextOptions.add(option * operand);
				if (allowConcatenation) {
					nextOptions.add(Long.parseLong("" + option + operand));
				}
			}

			nextOptions.removeIf(it -> it > calculation.result);
			currentOptions = nextOptions;
		}

		return currentOptions.contains(calculation.result);
	}

	private void parseInput() {
		calculations = lines.stream()
				.map(Utils::extractAllUnsignedLongs)
				.map(numbers -> {
					Long result = numbers.removeFirst();
					return new Calculation(numbers, result);
				}).toList();
	}


	private record Calculation(List<Long> operands, Long result) {
	}
}