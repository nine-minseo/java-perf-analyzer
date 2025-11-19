package performance.analysis;

import java.util.ArrayList;
import java.util.List;
import performance.BasePerformanceAnalyze;

public class ArrayListMiddleInsertAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "ArrayList 중간 삽입";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(long iterations) {
        List<Long> list = new ArrayList<>();

        for (long i = 0; i < iterations; i++) {
            list.add(list.size() / 2, i);
        }
    }
}
