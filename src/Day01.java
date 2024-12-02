import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static utils.Utils.extractAllUnsignedInts;
import static utils.Utils.readFile;

public class Day01 extends BaseDay {

	private Queue<Integer> left;
	private Queue<Integer> right;

	public Day01() {
		DAY = "01";
	}

	@Override
	protected long part1(String inputFile) {
		parseInput(inputFile);

		int sum = 0;
		while (!left.isEmpty()) {
			sum += Math.abs(left.remove() - right.remove());
		}

		return sum;
	}

	@Override
	protected long part2(String inputFile) {
		parseInput(inputFile);

		long sum = 0;
		while (!left.isEmpty()) {
			int number = left.peek();
			discardRightSmallerThan(number);

			int countLeft = count(number, left);
			int countRight = count(number, right);

			sum += (long) number * countLeft * countRight;
		}
		return sum;
	}

	private void parseInput(String inputFile) {
		resetQueues();
		fillQueues(inputFile);
		assert left.size() == right.size();
	}

	private void resetQueues() {
		left = new PriorityQueue<>();
		right = new PriorityQueue<>();
	}

	private void fillQueues(String inputFile) {
		List<String> lines = readFile(inputFile);
		lines.forEach((line) -> {
			var numbers = extractAllUnsignedInts(line);
			left.add(numbers.getFirst());
			right.add(numbers.getLast());
		});
	}

	private void discardRightSmallerThan(int number) {
		while (!right.isEmpty() && right.peek() < number) {
			right.remove();
		}
	}

	private int count(int number, Queue<Integer> queue) {
		int count = 0;
		while (!queue.isEmpty() && queue.peek() == number) {
			queue.remove();
			count++;
		}
		return count;
	}
}
