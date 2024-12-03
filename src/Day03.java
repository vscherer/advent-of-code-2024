import utils.Utils;

import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

import static utils.Utils.*;

public class Day03 extends BaseDay {

	public Day03() {
		DAY = "03";
		part1TestSolution = 161;
		part2TestSolution = 48;
	}

	@Override
	protected long part1() {
		return lines.stream()
				.flatMap(this::extractCleanMultiplications)
				.map(Utils::extractAllUnsignedInts)
				.map(this::multiply)
				.reduce(0, Integer::sum);
	}

	@Override
	protected long part2() {
		String fullInput = lines.stream().reduce("", (s1, s2) -> s1 + s2);
		var instructions = extractAllInstructions(fullInput);
		return runProgram(instructions);
	}

	private long runProgram(List<String> instructions) {
		long sum = 0;
		boolean enabled = true;
		for (String instruction : instructions) {
			if (instruction.equals("do()")) {
				enabled = true;
			} else if (instruction.equals("don't()")) {
				enabled = false;
			} else {
				if (enabled) {
					sum += multiply(extractAllUnsignedInts(instruction));
				}
			}
		}
		return sum;
	}

	private List<String> extractAllInstructions(String fullInput) {
		var multiplications = matchCleanMultiplications(fullInput);
		var dos = matchDos(fullInput);
		var donts = matchDonts(fullInput);

		return Stream.concat(multiplications, Stream.concat(dos, donts))
				.sorted(Comparator.comparing(MatchResult::start))
				.map(MatchResult::group)
				.toList();
	}

	private int multiply(List<Integer> numbers) {
		return numbers.stream().reduce(1, (a, b) -> a * b);
	}

	private Stream<String> extractCleanMultiplications(String s) {
		return extractAll(s, "mul\\(\\d{1,3},\\d{1,3}\\)").stream();
	}

	private Stream<MatchResult> matchCleanMultiplications(String s) {
		return matchAll(s, "mul\\(\\d{1,3},\\d{1,3}\\)").stream();
	}

	private Stream<MatchResult> matchDos(String s) {
		return matchAll(s, "do\\(\\)").stream();
	}

	private Stream<MatchResult> matchDonts(String s) {
		return matchAll(s, "don't\\(\\)").stream();
	}
}
