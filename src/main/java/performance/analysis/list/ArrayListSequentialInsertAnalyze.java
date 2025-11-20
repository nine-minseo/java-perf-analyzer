package performance.analysis.list;

import java.util.ArrayList;
import java.util.List;
import performance.model.BasePerformanceAnalyze;

public class ArrayListSequentialInsertAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "ArrayList 순차 삽입";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(long iterations) {
        List<Long> list = new ArrayList<>();

        for (long i = 0; i < iterations; i++) {
            list.add(i);
        }
    }
}
