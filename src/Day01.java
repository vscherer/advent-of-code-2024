import java.util.PriorityQueue;
import java.util.Queue;

import static utils.Utils.extractAllUnsignedInts;

public class Day01 extends BaseDay {

	private Queue<Integer> left;
	private Queue<Integer> right;

	public Day01() {
		DAY = "01";
		part1TestSolution = 11;
		part2TestSolution = 31;
	}

	@Override
	protected long part1() {
		parseInput();

		int sum = 0;
		while (!left.isEmpty()) {
			sum += Math.abs(left.remove() - right.remove());
		}

		return sum;
	}

	@Override
	protected long part2() {
		parseInput();

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

	private void parseInput() {
		resetQueues();
		fillQueues();
		assert left.size() == right.size();
	}

	private void resetQueues() {
		left = new PriorityQueue<>();
		right = new PriorityQueue<>();
	}

	private void fillQueues() {
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
