import static utils.Utils.readFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.Utils;

public class Day05 extends BaseDay {

    private Map<Integer, List<Integer>> pagesBefore = new HashMap<>();
    private List<List<Integer>> updates;

    public Day05() {
        DAY = "05";
    }

    @Override
    protected long part1(String inputFile) {
        List<String> lines = readFile(inputFile);

        parseInput(lines);
        return updates.stream()
            .filter(this::hasCorrectOrder)
            .map(this::extractMiddlePage)
            .reduce(0, Integer::sum);
    }

    @Override
    protected long part2(String inputFile) {
        List<String> lines = readFile(inputFile);

        parseInput(lines);
        return updates.stream()
            .filter(update -> !hasCorrectOrder(update))
            .map(this::fixOrder)
            .map(this::extractMiddlePage)
            .reduce(0, Integer::sum);

        // TOO high: 5326
    }

    private boolean hasCorrectOrder(List<Integer> pages) {
        Set<Integer> noLongerPossible = new HashSet<>();

        for (Integer page : pages) {
            if (noLongerPossible.contains(page)) {
                return false;
            } else {
                if (pagesBefore.containsKey(page)) {
                    noLongerPossible.addAll(pagesBefore.get(page));
                }
            }
        }

        return true;
    }

    private List<Integer> fixOrder(List<Integer> pages) {
        Map<Integer, List<Integer>> reducedPagesBefore = new HashMap<>();
        for (Integer page : pages) {
            if (pagesBefore.containsKey(page)) {
                reducedPagesBefore.put(page,
                    pagesBefore.get(page).stream()
                        .filter(pages::contains).toList());
            }
        }

        ArrayList<Integer> orderedPages = new ArrayList<>();
        while (!pages.isEmpty()) {
            for (int page : pages) {
                if (!reducedPagesBefore.containsKey(page) || orderedPages.containsAll(reducedPagesBefore.get(page))) {
                    orderedPages.add(page);
                    pages.remove((Integer) page);
                    break;
                }
            }
        }

        return orderedPages;
    }

    private int extractMiddlePage(List<Integer> pages) {
        assert pages.size() % 2 != 0;
        return pages.get(pages.size() / 2);
    }

    private void parseInput(List<String> lines) {
        pagesBefore.clear();

        lines.stream()
            .filter(s -> s.contains("|"))
            .map(Utils::extractAllUnsignedInts)
            .forEach(this::addRule);

        updates = lines.stream()
            .filter(s -> s.contains(","))
            .map(Utils::extractAllUnsignedInts)
            .toList();
    }

    private void addRule(List<Integer> rule) {
        if (!pagesBefore.containsKey(rule.getLast())) {
            pagesBefore.put(rule.getLast(), new ArrayList<>());
        }

        pagesBefore.get(rule.getLast()).add(rule.getFirst());
    }
}
