package bank.view.viewModel;

import bank.service.IBankingService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginViewModel implements PropertyChangeListener {
    private final StringProperty userName;
    private final StringProperty password;
    private final StringProperty error;
    private final IBankingService iBankingService;
    private boolean login;

    public LoginViewModel(IBankingService iBankingService) {
        this.iBankingService = iBankingService;
        this.userName = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        iBankingService.addListener(this);
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty errorProperty() {
        return error;
    }


    public boolean login() {
        if (nullChecker()) {
            this.error.set(null);
            this.login = iBankingService.login(this.userName.getValue(), this.password.getValue());
            this.userName.setValue(null);
            this.password.setValue(null);
        }
        return this.login;
    }

    private boolean nullChecker() {
        if (this.userName.getValue() != null && this.password.getValue() != null) {
            return true;
        }
        login = false;
        this.error.setValue("username or password is null");
        return false;
    }

    public void resetError(){
        this.error.set(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("errorLogin")) {
            Platform.runLater(() -> {
                this.error.set(evt.getNewValue().toString());
            });
        }
    }
}
