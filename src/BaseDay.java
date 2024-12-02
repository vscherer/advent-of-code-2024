import static utils.Utils.*;

public abstract class BaseDay {

	protected int DAY;

	public void run() {
		print("Running day" + DAY + "...\n");
		print("PART 1 TEST: " + part1("Day"+DAY+"_test.txt") + "\n");
		print("PART 1: " + part1("Day"+DAY+".txt") + "\n");
		print("PART 2 TEST: " + part2("Day"+DAY+"_test2.txt") + "\n");
		print("PART 2: " + part2("Day"+DAY+".txt") + "\n");
	}

	protected abstract long part1(String inputFile);

	protected abstract long part2(String inputFile);
}
