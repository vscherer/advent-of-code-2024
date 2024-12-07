package utils;

import java.util.Objects;

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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof GridLocation)) {
			return false;
		} else {
			return Objects.equals(this.first, ((GridLocation) obj).first)
					&& Objects.equals(this.second, ((GridLocation) obj).second);
		}
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(this.first + "999" + this.second);
	}
}
