import java.util.List;

import static utils.Utils.print;
import static utils.Utils.readFile;

public abstract class BaseDay {

	protected String DAY;
	protected long part1TestSolution = -1;
	protected long part2TestSolution = -1;

	protected List<String> lines;

	public void run() {
		print("====== DAY " + DAY + " ======\n");

		print("START PART 1 TEST");
		lines = readFile("Day" + DAY + "_t1.txt");
		assert (part1TestSolution == part1());
		print("PART 1 TEST PASSED");

		print("START PART 1 REAL");
		lines = readFile("Day" + DAY + ".txt");
		print("PART 1 RESULT: " + part1() + "\n");

		print("START PART 2 TEST");
		try {
			lines = readFile("Day" + DAY + "_t2.txt");
		} catch (Exception e) {
			// Reuse input from test 1
			lines = readFile("Day" + DAY + "_t1.txt");
		}
		assert (part2TestSolution == part2());
		print("PART 2 TEST PASSED");

		print("START PART 2 REAL");
		lines = readFile("Day" + DAY + ".txt");
		print("PART 2 RESULT: " + part2() + "\n");
	}

	protected abstract long part1();

	protected abstract long part2();
}
