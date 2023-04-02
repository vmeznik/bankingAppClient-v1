package bank;

import bank.service.BankingService;
import bank.service.IBankingService;
import bank.view.ViewHandler;
import bank.view.viewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;


public class MyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            IBankingService iBankingService = new BankingService();
            ViewModelFactory viewModelFactory = new ViewModelFactory(iBankingService);
            ViewHandler view = new ViewHandler(viewModelFactory);
            view.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}