package performance.analysis;

import java.util.LinkedList;
import java.util.List;
import performance.BasePerformanceAnalyze;

public class LinkedListMiddleInsertAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "LinkedList 중간 삽입";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(long iterations) {
        List<Long> list = new LinkedList<>();

        for (long i = 0; i < iterations; i++) {
            list.add(list.size() / 2, i);
        }
    }
}
