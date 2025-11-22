package performance.analysis.collection;

import performance.model.BasePerformanceAnalyze;
import java.util.HashSet;
import java.util.Set;

public class HashSetContainsAnalyze extends BasePerformanceAnalyze {
    private final Set<Integer> data;
    private static final int PREPARED_DATA_SIZE = 10_000;
    private static final String ANALYZE_NAME = "HashSet.contains";

    public HashSetContainsAnalyze() {
        this.data = new HashSet<>(PREPARED_DATA_SIZE);
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