import utils.CharGrid;
import utils.Grid;
import utils.GridLocation;
import utils.Griterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Day06 extends BaseDay {

	private CharGrid grid;
	private GridLocation start;
	private Grid<Boolean> walkedOn;
	private List<PathTile> path;

	public Day06() {
		DAY = "06";
		part1TestSolution = 41;
		part2TestSolution = 6;
	}

	@Override
	protected long part1() {
		parseInput();
		markPath();
		return countTrue(walkedOn);
	}

	@Override
	protected long part2() {
		parseInput();
		markPath();

		return path.stream()
				.filter(this::causesLoopIfBlocked)
				.map(tile -> tile.location.plus(tile.direction))
				.distinct()
				.count();
	}

	private void parseInput() {
		grid = new CharGrid(lines);

		start = grid.findFirst('^').get();

		walkedOn = new Grid<>(grid.dimensions(), false);
		walkedOn.set(start, true);

		path = new ArrayList<>();
	}

	private void markPath() {
		var it = grid.iterator(start);
		Grid.Direction4 facing = Grid.Direction4.N;
		walkAndMark(it, facing);
	}

	private void walkAndMark(Griterator<Character> it, Grid.Direction4 facing) {
		while (it.canStep(facing)) {
			GridLocation nextLocation = it.location.plus(facing);
			if (grid.get(nextLocation) == '#') {
				facing = facing.turnClockwise();
			} else {
				path.add(new PathTile(it.location, facing));
				it.step(facing);
				walkedOn.set(it.location, true);
			}
		}
	}

	private boolean causesLoopIfBlocked(PathTile tile) {
		var blockedTile = tile.location.plus(tile.direction);
		if (blockedTile.equals(start)) return false;

		var it = grid.iterator(start);
		Grid.Direction4 facing = Grid.Direction4.N;

		List<PathTile> newPath = new ArrayList<>();
		while (it.canStep(facing)) {
			GridLocation nextLocation = it.location.plus(facing);
			if (grid.get(nextLocation) == '#' || nextLocation.equals(blockedTile)) {

				var currentTile = new PathTile(it.location, facing);
				if (newPath.contains(currentTile)) {
					return true;
				}

				newPath.add(currentTile);
				facing = facing.turnClockwise();
			} else {
				it.step(facing);
			}
		}
		return false;
	}

	private long countTrue(Grid<Boolean> grid) {
		return grid.data.stream()
				.flatMap(Collection::stream)
				.filter(it -> it)
				.count();
	}

	private record PathTile(GridLocation location, Grid.Direction4 direction) {
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof PathTile)) {
				return false;
			} else {
				return this.direction.equals(((PathTile) obj).direction)
						&& this.location.equals(((PathTile) obj).location);
			}
		}

		@Override
		public String toString() {
			return location.toString() + direction.name();
		}
	}
}