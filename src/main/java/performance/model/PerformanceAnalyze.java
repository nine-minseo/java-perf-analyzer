package performance.model;

public interface PerformanceAnalyze {
    String getAnalyzeName();

    AnalyzeResult runAnalyze(long iterations);
}