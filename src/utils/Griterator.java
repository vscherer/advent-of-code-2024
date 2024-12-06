package utils;

public class Griterator<T> {
	public GridLocation location;
	private final Pair<Integer, Integer> dimensions;

	public Griterator(GridLocation location, Pair<Integer, Integer> dimensions) {
		this.location = location;
		this.dimensions = dimensions;
	}

	public Griterator(Pair<Integer, Integer> dimensions) {
		this.location = new GridLocation(0, 0);
		this.dimensions = dimensions;
	}

	public int row() {
		return location.first;
	}

	public int col() {
		return location.second;
	}

	public void moveTo(int x, int y) {
		moveTo(new GridLocation(x, y));
	}

	public void moveTo(GridLocation newLocation) {
		assert newLocation.first >= 0 && newLocation.second >= 0;
		if (newLocation.first > dimensions.first - 1 || newLocation.second > dimensions.second - 1) {
			throw new RuntimeException("Attempting to move griterator out of bounds! Dimensions: "
					+ dimensions + " Target: " + newLocation);
		}
		this.location = newLocation;
	}

	public void step(Grid.Direction4 dir) {
		moveTo(location.plus(dir));
	}

	public boolean canStep(Grid.Direction4 dir) {
		var target = location.plus(dir);
		return target.first >= 0
				&& target.second >= 0
				&& target.first < dimensions.first
				&& target.second < dimensions.second;
	}
}