package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Grid<T> {


	public enum Direction4 {
		N,
		S,
		E,
		W;
	}


	public enum Direction8 {
		N,
		S,
		E,
		W,
		NE,
		NW,
		SE,
		SW;
	}

	public List<List<T>> data;

	protected Grid() {
		// For custom constructors
	}

	public Grid(Pair<Integer, Integer> dimensions, T initialValue) {
		this(dimensions.first, dimensions.second, initialValue);
	}

	public Grid(int rows, int columns, T initialValue) {
		var grid = new ArrayList<List<T>>();
		for (int y = 0; y < rows; ++y) {
			grid.add(new ArrayList<>(Collections.nCopies(columns, initialValue)));
		}
		this.data = grid;
	}

	public int rows() {
		return data.size();
	}

	public int columns() {
		return data.getFirst().size();
	}

	public Pair<Integer, Integer> dimensions() {
		return new Pair<>(columns(), rows());
	}

	public T get(GridLocation location) {
		return data.get(location.second).get(location.first);
	}

	public T get(int x, int y) {
		return data.get(y).get(x);
	}

	public void set(GridLocation location, T newValue) {
		data.get(location.second).set(location.first, newValue);
	}

	public Optional<GridLocation> findFirst(T element) {
		for (int y = 0; y < rows(); ++y) {
			for (int x = 0; x < columns(); ++x) {
				if (get(x, y) == element) {
					return Optional.of(new GridLocation(x, y));
				}
			}
		}
		return Optional.empty();
	}

	public Griterator<T> iterator() {
		return new Griterator<T>(new Pair<>(rows(), columns()));
	}
}

