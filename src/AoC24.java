import java.util.List;

import static utils.Utils.print;

@SuppressWarnings("unused")
public class AoC24 {

	private static void runSingle() {
		BaseDay today = new Day08();
		today.run();
	}

	private static void runAll() {
		List<BaseDay> days = List.of(
				new Day01(),
				new Day02(),
				new Day03(),
				new Day04(),
				new Day05(),
				new Day06(),
				new Day07(),
				new Day08()
		);

		days.forEach(day -> {
			try {
				day.run();
			} catch (Exception e) {
				print("Missing input files!");
			}
		});


	}

	public static void main(String[] args) {
		runSingle();
//		runAll();
	}
}