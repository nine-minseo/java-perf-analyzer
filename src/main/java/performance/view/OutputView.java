package performance.view;

import performance.AnalyzeResult;

public class OutputView {
    public void printMainMenu() {
        System.out.println("\n========================================");
        System.out.println("Java Performance Analyzer");
        System.out.println("========================================");
        System.out.println("분석할 항목의 번호를 입력하세요.");
        System.out.println("----------------------------------------");
        System.out.println("1. String: '+' vs. StringBuilder");
        System.out.println("0. 종료");
        System.out.println("----------------------------------------");
        System.out.print("번호 선택: ");
    }

    public void printProgramExit() {
        System.out.println("프로그램을 종료합니다.");
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printAnalyzeStart(String title) {
        System.out.println("[START] " + title);
    }

    public void printAnalyzeResult(AnalyzeResult result) {
        System.out.println("[RESULT] " + result.getResultSummary());
    }
}
