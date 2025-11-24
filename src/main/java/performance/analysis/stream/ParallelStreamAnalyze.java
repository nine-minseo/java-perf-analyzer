package performance.analysis.stream;

import performance.model.BasePerformanceAnalyze;
import java.util.ArrayList;
import java.util.List;

public class ParallelStreamAnalyze extends BasePerformanceAnalyze {
    private final List<Integer> data;
    private static final int PREPARED_DATA_SIZE = 1_000_000;
    private static final String ANALYZE_NAME = "Parallel Stream";

    public ParallelStreamAnalyze() {
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
        data.parallelStream()
                .limit(iterations)
                .forEach(item -> {
                    int value = item;
                });
    }
}