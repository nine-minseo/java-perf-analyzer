package performance;

import performance.controller.AnalyzeController;
import performance.view.InputView;
import performance.view.OutputView;

public class PerformanceLab {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        AnalyzeController controller = new AnalyzeController(inputView, outputView);

        controller.run();
    }
}
