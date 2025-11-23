package performance.analysis.list;

import performance.model.BasePerformanceAnalyze;
import java.util.ArrayList;
import java.util.List;

public class ArrayListRemoveByIndexAnalyze extends BasePerformanceAnalyze {
    private final List<Integer> templateData;
    private static final String ANALYZE_NAME = "For Loop remove(i)";

    public ArrayListRemoveByIndexAnalyze() {
        this.templateData = new ArrayList<>();
    }

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

        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
            i--;
        }
    }
}