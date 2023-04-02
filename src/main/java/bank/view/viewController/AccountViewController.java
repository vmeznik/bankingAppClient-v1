package bank.view.viewController;

import bank.model.Transaction;
import bank.view.ViewHandler;
import bank.view.viewModel.AccountViewViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class AccountViewController {

    @FXML
    private Text accNumber;
    @FXML
    private Text balance;
    @FXML
    private ListView<Transaction> transactions;
    private Region root;
    private ViewHandler viewHandler;
    private AccountViewViewModel viewModel;

    public void init(ViewHandler viewHandler, AccountViewViewModel viewModel, Region root) {
        this.root = root;
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.accNumber.textProperty().bind(viewModel.accountNumberProperty());
        this.balance.textProperty().bind(viewModel.accountBalanceProperty());
        this.transactions.itemsProperty().bind(viewModel.transactionsProperty());
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void sendButton(){
        viewHandler.openView("sendMoney");
    }
}
