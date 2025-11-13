package performance;

public interface PerformanceAnalyze {
    String getAnalyzeName();

    AnalyzeResult runAnalyze(long iterations);
}