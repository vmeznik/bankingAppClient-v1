package bank.view.viewModel;

import bank.model.Transaction;
import bank.service.IBankingService;
import javafx.application.Platform;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class AccountViewViewModel implements PropertyChangeListener {

    private final StringProperty accountNumber;
    private final StringProperty accountBalance;
    private final ListProperty<Transaction> transactions;
    private final IBankingService iBankingService;

    public AccountViewViewModel(IBankingService iBankingService){
        this.iBankingService = iBankingService;
        this.accountNumber = new SimpleStringProperty();
        this.accountBalance = new SimpleStringProperty();
        this.transactions = new SimpleListProperty<>();
        iBankingService.addListener(this);
    }

    public StringProperty accountNumberProperty() {
        return accountNumber;
    }

    public StringProperty accountBalanceProperty() {
        return accountBalance;
    }

    public ListProperty<Transaction> transactionsProperty() {
        return transactions;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("balance")) {
            Platform.runLater(() -> {
                this.accountBalance.set(evt.getNewValue().toString());
            });
        } else if (evt.getPropertyName().equals("accNumber")) {
            Platform.runLater(() -> {
                this.accountNumber.set(evt.getNewValue().toString());
            });
        } else if (evt.getPropertyName().equals("transactions")) {
            Platform.runLater(() -> {
                this.transactions.set(FXCollections.observableList((ArrayList<Transaction>) evt.getNewValue()));
            });

        }
    }
}
