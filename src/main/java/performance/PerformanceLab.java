package performance;

public class PerformanceLab {
    public static void main(String[] args) {
        printMainMenu();
    }

    public static void printMainMenu() {
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
}
