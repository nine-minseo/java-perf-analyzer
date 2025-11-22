package performance.analysis.map;

import performance.model.BasePerformanceAnalyze;
import java.util.HashMap;
import java.util.Map;

public class HashMapGetAnalyze extends BasePerformanceAnalyze {
    private final Map<Integer, Integer> data;
    private static final int PREPARED_DATA_SIZE = 100_000;
    private static final String ANALYZE_NAME = "HashMap.get";

    public HashMapGetAnalyze() {
        this.data = new HashMap<>(PREPARED_DATA_SIZE);
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