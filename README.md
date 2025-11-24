# Java Performance Analyzer

# 1. 프로젝트 개요

> 프리코스 기간 동안 사용한 String 덧셈, ArrayList 등이 실제로 JVM 위에서 성능이 얼마나 차이나는지 궁금하여 CLI 기반의 성능 비교 도구를 만들게 되었습니다.
>
1. 자바 기능들의 성능 차이를 `System.nanoTime()` 기반의 데이터로 확인한다.
2. CLI 기반으로 결과를 보여준다.
3. 코드가 느린 이유를 (**JVM, GC, 메모리 구조** 기반으로) 분석한다.

# 2. 시작하기 (프로젝트 실행 방법)

이 프로젝트는 **Java 21** 환경에서 개발되었습니다. 터미널(Terminal)을 통해 직접 빌드하고 실행할 수 있습니다.

## 2.1. 프로젝트 빌드

Gradle을 사용하여 소스 코드를 컴파일하고 실행 가능한 JAR 파일을 생성합니다. 터미널에서 아래 명령어를 입력하세요.

- **Mac / Linux:**

    ```bash
    ./gradlew clean jar
    ```

- **Windows (PowerShell / CMD):**

    ```powershell
    .\gradlew clean jar
    ```

- **Note:** 빌드가 성공(`BUILD SUCCESSFUL`)하면 `build/libs` 경로에 `PerformanceLab-1.0-SNAPSHOT.jar` 파일이 생성됩니다.

## 2.2. 프로그램 실행

생성된 JAR 파일을 실행하여 CLI 도구를 시작합니다.

```bash
java -jar build/libs/PerformanceLab-1.0-SNAPSHOT.jar
```

## 2.3. 프로그램 실행 예시

**시스템 정보 확인 → 분석할 항목 설정 → 반복 횟수 설정 → 결과 시각화 → 파일 저장**

```
=========================================================
Java Performance Analyzer
=========================================================
[사용자 환경 정보]
OS    : Windows 11 (amd64)
Java  : 21.0.7 (Java HotSpot(TM) 64-Bit Server VM)
Cores : 22 logical processors
=========================================================
1. String 연결 성능 비교 (String vs StringBuilder)
2. List 중간 삽입 성능 비교 (ArrayList vs LinkedList)
3. List 순차 삽입 성능 비교 (ArrayList vs LinkedList)
4. Stream 반복 성능 비교 (Stream vs For-loop)
5. 요소 포함 확인 성능 비교 (ArrayList vs HashSet)
6. 배열 대량 복사 성능 비교 (For Loop vs System.arraycopy)
7. Map Key 조회 성능 비교 (TreeMap vs HashMap)
8. 문자열 치환 성능 비교 (replaceAll vs replace)
9. List 반복 중 삭제 비교 (Index vs Iterator)
10. 병렬 스트림 성능 비교 (Stream vs Parallel Stream)
0. 종료
=========================================================
분석할 항목의 번호를 입력하세요: 1
[START] 1. String (+) vs. StringBuilder

[가이드] 매우 느린 작업(O(N^2))입니다. 
→ 추천: 10,000 ~ 50,000
분석 반복 횟수를 입력하세요: 50000
[RESULT] String Concatenation: 50,000회 반복, 341.5687 ms 소요
[RESULT] StringBuilder (.append): 50,000회 반복, 4.6305 ms 소요
[RESULT] [분석 결과] [StringBuilder (.append)] 방식이 [String Concatenation] 방식보다 약 73.76배 더 빠릅니다

[성능 시각화]
String Concatenation           | ██████████████████████████████████████████████████ 341.5687 ms
StringBuilder (.append)        | █ 4.6305 ms

=========================================================
Java Performance Analyzer
=========================================================
[사용자 환경 정보]
OS    : Windows 11 (amd64)
Java  : 21.0.7 (Java HotSpot(TM) 64-Bit Server VM)
Cores : 22 logical processors
=========================================================
1. String 연결 성능 비교 (String vs StringBuilder)
2. List 중간 삽입 성능 비교 (ArrayList vs LinkedList)
3. List 순차 삽입 성능 비교 (ArrayList vs LinkedList)
4. Stream 반복 성능 비교 (Stream vs For-loop)
5. 요소 포함 확인 성능 비교 (ArrayList vs HashSet)
6. 배열 대량 복사 성능 비교 (For Loop vs System.arraycopy)
7. Map Key 조회 성능 비교 (TreeMap vs HashMap)
8. 문자열 치환 성능 비교 (replaceAll vs replace)
9. List 반복 중 삭제 비교 (Index vs Iterator)
10. 병렬 스트림 성능 비교 (Stream vs Parallel Stream)
0. 종료
=========================================================
분석할 항목의 번호를 입력하세요: 0

모든 분석이 종료되었습니다.

결과를 파일로 저장하시겠습니까? (y/n): y
전체 분석 결과가 저장되었습니다: ./reports/Session_Report_20251124_190015.md
프로그램을 종료합니다.
```

# 3. 기능 목록

## 3.1. 사용자 인터페이스

- [x]  메인 메뉴(분석 항목 1~10, 종료 0)를 출력한다.
- [x]  사용자의 시스템 환경 정보를 출력한다.
    - [x]  OS, 아키텍처, Java 버전을 출력한다.
    - [x]  병렬 처리 성능과 연관된 CPU 코어(Logical Processors) 수를 출력한다.
- [x]  사용자로부터 메뉴 번호를 입력받는다.
    - [x]  사용자 입력이 '0'일 경우, 프로그램을 종료한다.
    - [x]  해당하는 번호의 분석 모듈을 호출한다.
    - [x]  분석 모듈의 실행 결과를 출력한다.
- [x]  사용자로부터 반복 횟수를 입력받는다.
    - [x]  각 분석 항목별로 권장 반복 횟수 가이드를 출력한다.
    - [x]  사용자로부터 반복 횟수를 입력받아, 데이터 크기에 따른 성능 변화를 체감할 수 있게 한다.
- [x]  유효하지 않은 입력을 받을 시, 에러 메시지를 출력하고 메뉴를 재출력한다.
    - [x]  입력값이 없는 경우
    - [x]  숫자 외의 문자를 입력하는 경우
    - [x]  메뉴에 없는 숫자를 입력하는 경우
- [x]  입력 스트림이 종료되는 경우 프로그램을 종료한다.

## 3.2. 성능 비교 항목

모든 분석은 사용자가 입력한 반복 횟수(N)를 기준으로 수행된다.

### 3.2.1. String 연결 성능 비교

String의 불변성과 StringBuilder의 가변성의 성능 차이를 확인한다. (권장: 1만 ~ 5만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **String 덧셈:** 빈 문자열에 문자열을 N번 더하며 시간을 측정한다.
- [x]  **StringBuilder:** 객체에 문자열을 N번 append하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.2. List 중간 삽입 성능 비교

배열 기반 List와 연결 기반 List의 구조적 차이로 인한 중간 삽입 성능 차이를 확인한다. (권장: 1만 ~ 5만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **ArrayList 중간 삽입:** 리스트의 중간 인덱스에 요소를 N번 삽입하며 시간을 측정한다.
- [x]  **LinkedList 중간 삽입:** 리스트의 중간 인덱스에 요소를 N번 삽입하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.3. List 순차 등록 성능 비교

Node 객체 생성 오버헤드와 캐시 지역성을 통해 순차 삽입 성능 차이를 확인한다. (권장: 10만 ~ 100만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **LinkedList 순차 삽입:** 리스트의 끝에 요소를 N번 추가하며 시간을 측정한다.
- [x]  **ArrayList 순차 삽입:** 리스트의 끝에 요소를 N번 추가하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.4. Stream 반복 성능 비교

Stream 파이프라인 처리 오버헤드와 단순 반복문의 성능 차이를 확인한다. (권장: 10만 ~ 100만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **Stream.forEach:** `stream()`을 사용하여 리스트의 요소를 N번 순회하며 시간을 측정한다.
- [x]  **Enhanced For Loop:** `for-each` 문을 사용하여 리스트의 요소를 N번 순회하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.5. 컬렉션 요소 포함 확인 성능 비교

자료구조의 탐색 시간 복잡도(O(N) vs O(1)) 차이로 인한 실제 성능 차이를 확인한다. (권장: 5만 ~ 10만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **ArrayList.contains:** `contains()`로 리스트 내 요소를 N번 탐색하며 시간을 측정한다.
- [x]  **HashSet.contains:** `contains()`로 해시 테이블 내 요소를 N번 탐색하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.6. 배열 대량 복사 성능 비교

배열을 복사할 때, 반복문을 사용하는 방식과 JVM Native 기능을 사용하는 방식의 성능 차이를 확인한다. (권장: 50만 ~ 100만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **For Loop Copy:** `for` 루프를 사용하여 배열 요소를 N번 수동 복사하며 시간을 측정한다.
- [x]  **System.arraycopy:** JVM의 Native 메서드를 사용하여 배열을 N번 복사하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.7. 키 기반 Map 조회 성능 비교

데이터 저장 구조(Tree vs Hash Table)의 차이가 조회 성능에 미치는 영향을 확인한다. (권장: 10만 ~ 100만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **TreeMap.get:** 이진 트리 구조를 탐색하여 키를 N번 조회하며 시간을 측정한다.
- [x]  **HashMap.get:** 해싱을 통해 데이터 위치에 직접 접근하여 키를 N번 조회하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.8. 단순 문자열 치환 성능 비교

단순한 문자열을 치환할 때, 정규 표현식 엔진 사용 여부에 따른 성능 차이를 확인한다. (권장: 10만 ~ 50만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **String.replaceAll:** 정규 표현식(Regex) 엔진을 사용하여 문자열을 N번 치환하며 시간을 측정한다.
- [x]  **String.replace:** 단순 문자열 매칭 알고리즘을 사용하여 문자열을 N번 치환하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.9. List 반복 중 안전한 삭제 비교

List를 순회하며 요소를 삭제할 때, 인덱스를 직접 다루는 방식과 Iterator를 사용하는 방식의 차이를 확인한다. (권장: 1만 ~ 2만)

- [x]  사용자로부터 반복 횟수를 입력받는다.
- [x]  **For Loop remove(i):** 인덱스 기반 루프 내에서 요소를 삭제하고 인덱스를 직접 조정하며 시간을 측정한다.
- [x]  **Iterator.remove:** Iterator를 사용하여 요소를 N번 삭제하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

### 3.2.10. 병렬 스트림 성능 비교

단순 반복 작업에서 작업량에 따른 직렬(Sequential)과 병렬(Parallel) 처리의 효율성을 비교한다.

- [x]  사용자로부터 반복 횟수를 입력받는다. (1,000회 vs 1,000,000회 비교 권장)
- [x]  **Stream (Sequential):** 단일 스레드에서 순차적으로 스트림을 N번 처리하며 시간을 측정한다.
- [x]  **Parallel Stream:** 멀티 스레드를 사용하여 작업을 병렬로 N번 처리하며 시간을 측정한다.
- [x]  두 방식의 성능 차이를 시각화하여 출력한다.

## 3.3. 성능 비교 항목 분석 요약

| 번호 | 비교 주제 | Slow (비효율) | Fast (효율) | 분석 키워드 |
| --- | --- | --- | --- | --- |
| **1** | **문자열 연결** | String `+` | StringBuilder | 불변 객체(Immutable), GC 오버헤드 |
| **2** | **List 중간 삽입** | LinkedList | ArrayList | CPU 캐시 지역성(Locality), 메모리 구조 |
| **3** | **List 순차 삽입** | LinkedList | ArrayList | 노드 객체 생성 비용, 메모리 파편화 |
| **4** | **반복(Iteration)** | Stream | Enhanced For-loop | 파이프라인 비용, JIT 컴파일러 최적화 |
| **5** | **요소 탐색** | ArrayList (O(N)) | HashSet (O(1)) | 시간 복잡도, 해싱(Hashing) 알고리즘 |
| **6** | **배열 복사** | For-loop | System.arraycopy | JNI(Java Native Interface), Intrinsic 최적화 |
| **7** | **Map 조회** | TreeMap (O(\log N)) | HashMap (O(1)) | 이진 탐색 트리 vs 해시 테이블 |
| **8** | **문자열 치환** | replaceAll (Regex) | replace (Literal) | 정규표현식(Regex) 엔진 구동 오버헤드 |
| **9** | **List 삭제** | Loop index | Iterator | 인덱스 관리 실수 방지, 안전성(Safety) |
| **10** | **병렬 처리** | Parallel Stream | Stream | ForkJoinPool, 스레드 컨텍스트 스위칭 비용 |

## 3.4. 결과 출력 및 비교

측정된 데이터를 사용자에게 직관적으로 전달하기 위해 결과를 가공하고 비교 분석한다.

- [x]  나노초(ns)로 측정된 시간을 가독성 좋게 1,000ms 미만은 밀리초(ms)로, 1,000ms 이상은 초(s)로 변환하여 출력한다.
- [x]  반복 횟수와 소요 시간 출력 시 천 단위 구분 기호(,)를 적용하여 가독성을 높인다.
- [x]  (느린 시간 / 빠른 시간) 공식을 사용하여 성능 차이 배수를 계산한 뒤, "A가 B보다 약 XX.XX배 빠릅니다" 형식으로 비교 결과를 출력한다.
- [x]  가장 느린 결과(100%)를 기준으로 상대적인 속도 비율을 막대 그래프로 출력한다.
- [x]  ANSI Color Code를 적용하여 빠른 결과(Green)와 느린 결과(Red)를 색으로 구분한다.
- [x]  두 테스트의 반복 횟수가 다를 경우, 비교를 건너뛰고 안내 메시지를 출력한다.
- [x]  사용자가 프로그램 종료 선택 시, 사용자에게 결과 파일 저장 여부(`y`/`n`)를 입력받는다.
    - [x]  파일 저장을 선택할 경우, 모든 분석 결과를 마크다운(.md)형식으로 `reports` 폴더에 저장한다.

# 4. 프로그램 아키텍처 및 모듈 명세

- 유지보수성과 확장성을 고려하여 MVC (Model-View-Controller) 패턴을 기반으로 설계하였습니다.
- 각 기능은 독립적인 모듈로 분리되어 있으며, 공통 로직은 추상화를 통해 중복을 최소화했습니다.

## 4.1. 모듈 구조

```
src/main/java/performance
├── controller          # 애플리케이션의 흐름 제어 및 비즈니스 로직 연결
│   └── AnalyzeController.java
├── model               # 데이터 객체(DTO) 및 핵심 분석 인터페이스
│   ├── AnalyzeResult.java
│   ├── PerformanceAnalyze.java (Interface)
│   └── BasePerformanceAnalyze.java (Abstract Class)
├── view                # 사용자 입출력 및 결과 포맷팅 담당
│   ├── InputView.java
│   ├── OutputView.java
│   └── ResultFormatter.java
├── analysis            # 성능 분석 구현체 (도메인별 패키징)
│   ├── array           # 배열 복사 관련
│   ├── collection      # 컬렉션 탐색 관련
│   ├── list            # List 삽입/삭제 관련
│   ├── map             # Map 조회 관련
│   ├── regex           # 문자열 치환 관련
│   ├── stream          # 스트림/반복문 관련
│   └── string          # 문자열 연결 관련
└── util                # 공통 유틸리티
    └── FileSaver.java  # 결과 파일 저장 (.md)
```

## 4.2. 성능 분석 모듈 설계

성능 분석 모듈은 코드 중복을 제거하기 위해 템플릿 메서드 패턴(Template Method Pattern)을 사용하여 구현하였습니다.

- `PerformanceAnalyze` (Interface): 모든 분석 모듈이 구현해야 하는 공통 인터페이스입니다.
- `BasePerformanceAnalyze` (Abstract Class):
    - Warm-up: JVM의 JIT 컴파일러가 최적화를 수행할 수 있도록 본 측정 전 예열 과정을 수행합니다.
    - 측정 로직: `System.nanoTime()`을 이용한 시간 측정 로직을 캡슐화했습니다.
- Concrete Classes (Implementation): 개발자는 `execute(iterations)` 메서드만 오버라이딩하여 분석 로직만 구현하면 됩니다.

# 5. 성능 요약

*테스트 환경: Java 21 / Iterations: 각 항목별 권장 횟수 적용*

본 프로젝트를 통해 측정한 주요 자바 기능들의 성능 차이입니다. 어떤 방식을 사용하는 것이 성능상 유리한지 비교할 수 있습니다.

| **기능** | **Slow (비효율)** | **Fast (권장)** | **속도 차이** | **핵심 원인** |
| --- | --- | --- | --- | --- |
| **문자열** | String `+` | StringBuilder | **50~150배** | 불변 객체 생성(GC 부하) vs 가변 버퍼 재사용 |
| **List 삽입** | LinkedList (중간) | ArrayList (중간) | **8~15배** | 포인터 탐색(O(N)) vs 연속된 메모리(Cache Hit) |
| **List 삽입** | LinkedList (순차) | ArrayList (순차) | **동일 수준** | 리사이징 비용과 노드 생성 비용의 차이 미미함 |
| **반복문** | Stream | For-loop | **1.1~1.2배** | 파이프라인 구성 및 람다 객체 생성 오버헤드 |
| **병렬 처리** | Parallel Stream | Stream (소량) | **10~40배** | 스레드 풀(ForkJoinPool) 생성 비용이 연산보다 큼 |
| **탐색** | ArrayList | HashSet | **100배+** | 전체 순차 탐색(O(N)) vs 해시 버킷 접근(O(1)) |
| **배열 복사** | For-loop | System.arraycopy | **동일 수준** | *JNI 호출 비용(소량) vs Intrinsic 최적화(대량)* |
| **Map 조회** | TreeMap | HashMap | **4~5배** | 트리 노드 탐색(O(\log N)) vs 해시 인덱싱(O(1)) |
| **문자열 치환** | replaceAll (Regex) | replace (Literal) | **7~10배** | 정규표현식 엔진 구동 비용 vs 단순 문자열 매칭 |
| **List 삭제** | Index Loop | Iterator | **동일 수준** | 성능은 비슷하나 안전성(Safety) 측면에서 권장 |

# 6. 심층 분석

단순한 수치 비교와 함께, 왜 이런 결과가 나왔는지 **JVM, 메모리 구조, 하드웨어 아키텍처** 관점에서 분석했습니다.

## 6.1. String 연산

- **현상:** `String` 덧셈이 `StringBuilder`보다 수십 배에서 수백 배 느립니다.
- **원인 분석:**
    - **불변성 (Immutability):** 자바의 `String`은 불변 객체입니다. `+` 연산을 할 때마다 기존 문자열을 수정하는 것이 아니라, **새로운 문자열 객체를 힙(Heap) 메모리에 생성**합니다.
    - **GC 오버헤드:** 5만 번의 덧셈은 5만 개의 임시 객체(Garbage)를 생성합니다. 이는 GC(Garbage Collection)를 빈번하게 유발하여 CPU 자원을 낭비하고 프로그램 성능을 저하시킵니다.
    - **반면 StringBuilder:** 내부적으로 가변 배열(`char[]`)을 버퍼로 사용합니다. 새로운 객체 생성 없이 배열의 빈 공간에 문자를 채워 넣으므로 메모리 할당과 해제 비용이 거의 없습니다.

## 6.2. List 중간 삽입 (ArrayList vs LinkedList)

- **현상:** 이론상 중간 삽입은 LinkedList(O(1))가 ArrayList(O(N))보다 유리해야 하지만, **실제 테스트 결과는 ArrayList가 약 10~20배 빠릅니다.**
- **원인 분석:**
    - **CPU 캐시 지역성 (Cache Locality):**
        - **ArrayList:** 데이터가 메모리에 **연속적**으로 저장됩니다. CPU가 데이터를 읽을 때 캐시 라인(Cache Line) 단위로 인접한 데이터를 미리 가져오므로(Pre-fetching), 캐시 히트(Cache Hit)율이 매우 높습니다.
        - **LinkedList:** 데이터(Node)가 힙 메모리 이곳저곳에 **흩어져** 있습니다. 다음 노드를 찾을 때마다 메모리 주소를 점프해야 하므로 캐시 미스(Cache Miss)가 빈번하게 발생하여 CPU가 메모리를 기다리는 시간(Latency)이 길어집니다.
    - **System.arraycopy 최적화:** `ArrayList`는 데이터 이동 시 JVM의 Native 메서드인 `System.arraycopy`를 사용하여 메모리 블록을 통째로 이동시킵니다. 이는 반복문으로 노드를 하나씩 연결하는 것보다 훨씬 빠릅니다.

## 6.3. 가독성 vs. 성능

- **현상:** `Stream` API나 `String.replaceAll`(정규식)은 코드의 가독성과 생산성을 높여주지만, 단순 반복문(`for-loop`)이나 리터럴 치환(`replace`)에 비해 실행 속도는 느렸습니다.
- **원인 분석:**
    - **추상화의 비용:** 최신 기능들은 개발자의 편의를 위해 하부 로직을 추상화하고 있으며, 이는 객체 생성이나 엔진 구동과 같은 런타임 오버헤드를 수반합니다.
    - **결론:** Stream이나 Regex는 가독성과 편의성을 주지만, 극한의 성능이 필요한 핫스팟(Hotspot)에서는 전통적인 방식(For-loop, Literal)이 빠릅니다.
    - 코드의 실행 빈도와 중요도에 따라 가독성과 성능 사이에서 균형을 잡아야 합니다.