package performance.view;

public class OutputView {
    public void printMainMenu() {
        System.out.println("\n=========================================================");
        System.out.println("Java Performance Analyzer");
        System.out.println("=========================================================");

        printSystemInfo();

        System.out.println("=========================================================");
        System.out.println("1. String 연결 성능 비교 (String vs StringBuilder)");
        System.out.println("2. List 중간 삽입 성능 비교 (ArrayList vs LinkedList)");
        System.out.println("3. List 순차 삽입 성능 비교 (ArrayList vs LinkedList)");
        System.out.println("4. Stream 반복 성능 비교 (Stream vs For-loop)");
        System.out.println("5. 요소 포함 확인 성능 비교 (ArrayList vs HashSet)");
        System.out.println("6. 배열 대량 복사 성능 비교 (For Loop vs System.arraycopy)");
        System.out.println("7. Map Key 조회 성능 비교 (TreeMap vs HashMap)");
        System.out.println("8. 문자열 치환 성능 비교 (replaceAll vs replace)");
        System.out.println("9. List 반복 중 삭제 비교 (Index vs Iterator)");
        System.out.println("10. 병렬 스트림 성능 비교 (Stream vs Parallel Stream)");
        System.out.println("0. 종료");
        System.out.println("=========================================================");
    }

    private void printSystemInfo() {
        String os = System.getProperty("os.name");
        String arch = System.getProperty("os.arch");
        String javaVer = System.getProperty("java.version");
        String javaVM = System.getProperty("java.vm.name");
        int processors = Runtime.getRuntime().availableProcessors();

        System.out.println("[사용자 환경 정보]");
        System.out.println(String.format("OS    : %s (%s)", os, arch));
        System.out.println(String.format("Java  : %s (%s)", javaVer, javaVM));
        System.out.println(String.format("Cores : %d logical processors", processors));
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
