import java.util.*;

// strategy interface
interface SortingStrategy {
    void sort(List<Integer> numbers);
}

// Concrete strategy
class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<Integer> numbers) {
        // Bubble sort logic
    }
}

class QuickSortStrategy implements SortingStrategy {
    @Override
    public void sort(List<Integer> numbers) {
        // Quick sort logic
    }
}

// Context class
class Sorter {

    private SortingStrategy strategy;

    // Constructor to set the strategy
    public Sorter(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortNumbers(List<Integer> numbers) {
        strategy.sort(numbers);
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 3, 2, 8, 1);

        Sorter sorter = new Sorter(new BubbleSortStrategy());
        sorter.sortNumbers(numbers);

        // Switch to a different strategy
        sorter.setStrategy(new QuickSortStrategy());
        sorter.sortNumbers(numbers);
    }
}
