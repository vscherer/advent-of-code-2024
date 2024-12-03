public class AoC24 {

	private static void runSingle() {
		BaseDay today = new Day03();
		today.run();
	}

	private static void runAll() {
		(new Day01()).run();
		(new Day02()).run();
		(new Day03()).run();
	}

	public static void main(String[] args) {
		runSingle();
//		runAll();
	}
}