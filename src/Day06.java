import utils.CharGrid;
import utils.Grid;
import utils.GridLocation;

import java.util.Collection;

import static utils.Utils.print;

public class Day06 extends BaseDay {

	private CharGrid grid;

	public Day06() {
		DAY = "06";
		part1TestSolution = 41;
		part2TestSolution = -1;
	}

	@Override
	protected long part1() {
		grid = new CharGrid(lines);

		GridLocation start = grid.findFirst('^').get();
		print("Start " + start);

		Grid<Boolean> walkedOn = new Grid<>(grid.dimensions(), false);
		walkedOn.set(start, true);

		walkUntilOut(start, walkedOn);


		return countTrue(walkedOn);
	}

	private long countTrue(Grid<Boolean> grid) {
		return grid.data.stream()
				.flatMap(Collection::stream)
				.filter(it -> it)
				.count();
	}

	private void walkUntilOut(GridLocation start, Grid<Boolean> walkedOn) {
		var it = grid.iterator();
		it.moveTo(start);
		Grid.Direction4 facing = Grid.Direction4.N;

		while (it.canStep(facing)) {
			if (grid.get(it.location.plus(facing)) == '#') {
//				print("Object at " + it.location.plus(facing));
				switch (facing) {
					case N -> facing = Grid.Direction4.E;
					case S -> facing = Grid.Direction4.W;
					case E -> facing = Grid.Direction4.S;
					case W -> facing = Grid.Direction4.N;
				}
			} else {
//				print("Free to move to " + it.location.plus(facing));
			}

			it.step(facing);
			walkedOn.set(it.location, true);
//			print("Moved to " + it.location);
		}
	}

	@Override
	protected long part2() {
		return 0;
	}
}