package performance.analysis.stream;

import java.util.ArrayList;
import java.util.List;
import performance.model.BasePerformanceAnalyze;

public class EnhancedForLoopAnalyze extends BasePerformanceAnalyze {
    private final List<Integer> data;
    private static final int PREPARED_DATA_SIZE = 1_000_000;
    private static final String ANALYZE_NAME = "Enhanced For Loop";

    public EnhancedForLoopAnalyze() {
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
        List<Integer> targetData = data.subList(0, iterations);

        for (Integer item : targetData) {
            int value = item;
        }
    }
}
