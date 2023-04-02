package bank.view.viewController;

import bank.view.ViewHandler;
import bank.view.viewModel.SendMoneyViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class SendMoneyController {
    @FXML
    private TextField receiverAcc;
    @FXML
    private TextField amountOfMoney;
    @FXML
    private Text error;
    private ViewHandler viewHandler;
    private Region root;
    private SendMoneyViewModel viewModel;

    public void init(ViewHandler viewHandler, SendMoneyViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.receiverAcc.textProperty().bindBidirectional(viewModel.receiverAccProperty());
        this.amountOfMoney.textProperty().bindBidirectional(viewModel.amountOfMoneyProperty());
        this.error.textProperty().bind(viewModel.errorProperty());

    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void onEnter() {
        sendButton();
    }

    @FXML
    private void sendButton() {
        if (viewModel.sendMoney()) {
            viewHandler.openView("accountView");
        }
    }

    @FXML
    private void backButton() {
        viewHandler.openView("accountView");
    }
}
