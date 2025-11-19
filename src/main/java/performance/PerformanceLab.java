package performance;

import performance.controller.AnalyzeController;
import performance.view.InputView;
import performance.view.OutputView;
import performance.view.ResultFormatter;

public class PerformanceLab {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        ResultFormatter formatter = new ResultFormatter();

        AnalyzeController controller = new AnalyzeController(inputView, outputView, formatter);

        controller.run();
    }
}
