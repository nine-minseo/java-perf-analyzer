package performance.analysis.list;

import performance.model.BasePerformanceAnalyze;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListRemoveByIteratorAnalyze extends BasePerformanceAnalyze {
    private static final String ANALYZE_NAME = "Iterator.remove";

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        List<Integer> list = new ArrayList<>(iterations);
        for (int i = 0; i < iterations; i++) {
            list.add(i);
        }

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }
}