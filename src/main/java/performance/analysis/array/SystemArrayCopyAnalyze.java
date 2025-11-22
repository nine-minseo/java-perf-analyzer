package performance.analysis.array;

import performance.model.BasePerformanceAnalyze;

public class SystemArrayCopyAnalyze extends BasePerformanceAnalyze {

    private final int[] source;
    private final int[] target;
    private static final int ARRAY_SIZE = 10_000;
    private static final String ANALYZE_NAME = "System.arraycopy";

    public SystemArrayCopyAnalyze() {
        this.source = new int[ARRAY_SIZE];
        this.target = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            source[i] = i;
        }
    }

    @Override
    public String getAnalyzeName() {
        return ANALYZE_NAME;
    }

    @Override
    protected void execute(int iterations) {
        for (int i = 0; i < iterations; i++) {
            System.arraycopy(source, 0, target, 0, source.length);
        }
    }
}