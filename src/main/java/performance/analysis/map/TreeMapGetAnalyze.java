package performance.analysis.map;

import performance.model.BasePerformanceAnalyze;
import java.util.Map;
import java.util.TreeMap;

public class TreeMapGetAnalyze extends BasePerformanceAnalyze {
    private final Map<Integer, Integer> data;
    private static final int PREPARED_DATA_SIZE = 100_000;
    private static final String ANALYZE_NAME = "TreeMap.get";

    public TreeMapGetAnalyze() {
        this.data = new TreeMap<>();
        for (int i = 0; i < PREPARED_DATA_SIZE; i++) {
            data.put(i, i);
        }
    }

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        for (int i = 0; i < iterations; i++) {
            data.get(i % PREPARED_DATA_SIZE);
        }
    }
}