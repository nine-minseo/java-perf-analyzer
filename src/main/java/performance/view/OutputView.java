package performance.view;

public class OutputView {
    public void printMainMenu() {
        System.out.println("\n========================================");
        System.out.println("Java Performance Analyzer");
        System.out.println("========================================");
        System.out.println("분석할 항목의 번호를 입력하세요.");
        System.out.println("----------------------------------------");
        System.out.println("1. String 연결 성능 비교 (String vs StringBuilder)");
        System.out.println("2. List 중간 삽입 성능 비교 (ArrayList vs LinkedList)");
        System.out.println("3. List 순차 삽입 성능 비교 (ArrayList vs LinkedList)");
        System.out.println("4. Stream 반복 성능 비교 (Stream vs For-loop)");
        System.out.println("5. 요소 포함 확인 성능 비교 (ArrayList vs HashSet)");
        System.out.println("6. 배열 대량 복사 성능 비교 (For Loop vs System.arraycopy)");
        System.out.println("7. Map Key 조회 성능 비교 (TreeMap vs HashMap)");
        System.out.println("8. 문자열 치환 성능 비교 (replaceAll vs replace)");
        System.out.println("9. List 반복 중 삭제 비교 (Index vs Iterator)");
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

    public void printAnalyzeResult(String formattedResult) {
        System.out.println("[RESULT] " + formattedResult);
    }
}
