package utils;

public class GridLocation extends Pair<Integer, Integer> {
	public GridLocation(Integer first, Integer second) {
		super(first, second);
	}

	public int row() {
		return second;
	}

	public int column() {
		return first;
	}

	public GridLocation plus(Grid.Direction4 dir) {
		return switch (dir) {
			case N -> new GridLocation(column(), row() + 1);
			case S -> new GridLocation(column(), row() - 1);
			case E -> new GridLocation(column() + 1, row());
			case W -> new GridLocation(column() - 1, row());
		};
	}

	@Override
	public String toString() {
		return "(" + first + "," + second + ")";
	}
}
