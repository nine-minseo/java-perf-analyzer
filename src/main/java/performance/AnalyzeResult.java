package performance;

public class AnalyzeResult {
    private final String analyzeName;
    private final long durationNano;
    private final long iterations;

    public AnalyzeResult(String analyzeName, long durationNano, long iterations) {
        this.analyzeName = analyzeName;
        this.durationNano = durationNano;
        this.iterations = iterations;
    }

    public String getAnalyzeName() {
        return analyzeName;
    }

    public long getDurationNano() {
        return durationNano;
    }

    public long getIterations() {
        return iterations;
    }

    public double getDurationMs() {
        return durationNano / 1_000_000.0;
    }
}