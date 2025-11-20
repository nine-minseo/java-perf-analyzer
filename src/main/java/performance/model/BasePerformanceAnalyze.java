package performance.model;

public abstract class BasePerformanceAnalyze implements PerformanceAnalyze {
    private static final int WARM_UP_ITERATIONS = 1_000;

    @Override
    public final AnalyzeResult runAnalyze(long iterations) {
        warmUp();
        long durationNano = measure(iterations);
        return new AnalyzeResult(getAnalyzeName(), durationNano, iterations);
    }

    private void warmUp() {
        execute(WARM_UP_ITERATIONS);
    }

    private long measure(long iterations) {
        long startTime = System.nanoTime();
        execute(iterations);
        long endTime = System.nanoTime();

        return endTime - startTime;
    }

    protected abstract void execute(long iterations);
}
