package performance;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PerformanceLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            printMainMenu();
            try {
                int userChoice = scanner.nextInt();

                if(userChoice == 0){
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }
            } catch (InputMismatchException e) {
            }
        }
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
