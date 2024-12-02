import static utils.Utils.print;

public abstract class BaseDay {

	protected String DAY;

	public void run() {
		print("Running Day" + DAY + "...\n");
		print("PART 1 TEST");
		print("PART 1 TEST RESULT: " + part1("Day" + DAY + "_test.txt") + "\n");
		print("PART 1");
		print("PART 1 RESULT: " + part1("Day" + DAY + ".txt") + "\n");
		print("PART 2 TEST");
		print("PART 2 TEST RESULT: " + part2("Day" + DAY + "_test.txt") + "\n");
		print("PART 2");
		print("PART 2 RESULT: " + part2("Day" + DAY + ".txt") + "\n");
	}

	protected abstract long part1(String inputFile);

	protected abstract long part2(String inputFile);
}
