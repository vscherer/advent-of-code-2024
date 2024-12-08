package utils;

public class Vector2 extends Pair<Integer, Integer> {
	public Vector2(Integer x, Integer y) {
		super(x, y);
	}

	public int x() {
		return first;
	}

	public int y() {
		return second;
	}

	public Vector2 inverse() {
		return new Vector2(-x(), -y());
	}
}
