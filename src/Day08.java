import utils.CharGrid;
import utils.GridLocation;
import utils.Pair;
import utils.Vector2;

import java.util.*;

public class Day08 extends BaseDay {

	private CharGrid grid;
	private final Map<Character, List<Antenna>> antennas = new HashMap<>();
	private final Set<GridLocation> antinodes = new HashSet<>();

	private boolean allowHarmonics = false;

	public Day08() {
		DAY = "08";
		part1TestSolution = 14;
		part2TestSolution = 34;
	}

	@Override
	protected long part1() {
		allowHarmonics = false;
		parseInput();
		findAllAntinodes();
		return antinodes.size();
	}

	@Override
	protected long part2() {
		allowHarmonics = true;
		parseInput();
		findAllAntinodes();
		return antinodes.size();
	}

	private void findAllAntinodes() {
		for (char frequency : antennas.keySet()) {
			findAntinodes(frequency);
		}
	}

	private void findAntinodes(char frequency) {
		var matchingAntennas = antennas.get(frequency);
		var antennaPairs = createAllPairs(matchingAntennas);

		if (allowHarmonics) {
			findHarmonicAntinodes(antennaPairs);
		} else {
			findSimpleAntinodes(antennaPairs);
		}
	}

	private void findHarmonicAntinodes(List<Pair<GridLocation, GridLocation>> antennaPairs) {
		for (var pair : antennaPairs) {
			Vector2 firstToSecond = pair.first.vectorTo(pair.second);

			GridLocation candidate = pair.first;
			while (grid.contains(candidate)) {
				antinodes.add(candidate);
				candidate = candidate.plus(firstToSecond.inverse());
			}

			candidate = pair.second;
			while (grid.contains(candidate)) {
				antinodes.add(candidate);
				candidate = candidate.plus(firstToSecond);
			}
		}
	}

	private void findSimpleAntinodes(List<Pair<GridLocation, GridLocation>> antennaPairs) {
		for (var pair : antennaPairs) {
			Vector2 firstToSecond = pair.first.vectorTo(pair.second);

			GridLocation candidate1 = pair.first.plus(firstToSecond.inverse());
			GridLocation candidate2 = pair.second.plus(firstToSecond);

			if (grid.contains(candidate1)) {
				antinodes.add(candidate1);
			}
			if (grid.contains(candidate2)) {
				antinodes.add(candidate2);
			}
		}
	}

	private List<Pair<GridLocation, GridLocation>> createAllPairs(List<Antenna> matchingAntennas) {
		List<Pair<GridLocation, GridLocation>> pairs = new ArrayList<>();
		for (int i = 0; i < matchingAntennas.size() - 1; ++i) {
			for (int j = i + 1; j < matchingAntennas.size(); ++j) {
				pairs.add(new Pair<>(matchingAntennas.get(i).location, matchingAntennas.get(j).location));
			}
		}
		return pairs;
	}

	private void parseInput() {
		antinodes.clear();

		grid = new CharGrid(lines);

		antennas.clear();
		for (int row = 0; row < lines.size(); ++row) {
			for (int col = 0; col < lines.getFirst().length(); ++col) {
				char c = lines.get(lines.size() - row - 1).charAt(col);
				if (c != '.') {
					if (!antennas.containsKey(c)) {
						antennas.put(c, new ArrayList<>());
					}
					antennas.get(c).add(new Antenna(new GridLocation(col, row), c));
				}
			}
		}
	}

	private record Antenna(GridLocation location, char frequency) {
	}
}
