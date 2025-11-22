package performance.analysis.collection;

import java.util.ArrayList;
import java.util.List;
import performance.model.BasePerformanceAnalyze;

public class ArrayListContainsAnalyze extends BasePerformanceAnalyze {
    private final List<Integer> data;
    private static final int PREPARED_DATA_SIZE = 10_000;
    private static final String ANALYZE_NAME = "ArrayList.contains";

    public ArrayListContainsAnalyze() {
        this.data = new ArrayList<>(PREPARED_DATA_SIZE);
        for (int i = 0; i < PREPARED_DATA_SIZE; i++) {
            data.add(i);
        }
    }

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        for (int i = 0; i < iterations; i++) {
            data.contains(i);
        }
    }
}
