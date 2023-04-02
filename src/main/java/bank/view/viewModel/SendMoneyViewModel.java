package bank.view.viewModel;

import bank.service.IBankingService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SendMoneyViewModel implements PropertyChangeListener {

    private final StringProperty receiverAcc;
    private final StringProperty amountOfMoney;
    private final StringProperty error;
    private final IBankingService iBankingService;
    private double amount;
    private int receiverAccNumber;

    public SendMoneyViewModel(IBankingService iBankingService){
        this.iBankingService = iBankingService;
        this.receiverAcc = new SimpleStringProperty();
        this.amountOfMoney = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        this.iBankingService.addListener(this);
    }

    public StringProperty receiverAccProperty() {
        return receiverAcc;
    }

    public StringProperty amountOfMoneyProperty() {
        return amountOfMoney;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public boolean sendMoney(){
        if (amountFormat() && accNumberFormat()) {
            Platform.runLater(() -> {
                this.error.set(null);
            });
            return iBankingService.sendMoney(receiverAccNumber, amount);
        }
        return false;
    }

    private boolean amountFormat(){
        try {
            this.amount = Double.parseDouble(amountOfMoney.get());
        }catch (NumberFormatException e){
            Platform.runLater(()->{
                this.error.set("Wrong format of money ,must be double");
            });
            return false;
        }
        return true;
    }

    private boolean accNumberFormat(){
        try {
            this.receiverAccNumber = Integer.parseInt(receiverAcc.get());
        }catch (NumberFormatException e){
            Platform.runLater(()->{
                this.error.set("Wrong format of account number ,must contains only numbers");
            });
            return false;
        }
        return true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("error")){
            Platform.runLater(()->{
                this.error.set(evt.getNewValue().toString());
            });
        }
    }
}
