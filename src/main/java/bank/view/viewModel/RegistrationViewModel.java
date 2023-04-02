package bank.view.viewModel;

import bank.service.IBankingService;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RegistrationViewModel implements PropertyChangeListener {
    private final StringProperty userName;
    private final StringProperty password;
    private final StringProperty password2;
    private final StringProperty email;
    private final StringProperty error;
    private final IBankingService iBankingService;
    private boolean register;

    public RegistrationViewModel(IBankingService iBankingService) {
        this. iBankingService = iBankingService;
        this.userName = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
        this.password2 = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.error = new SimpleStringProperty();
        iBankingService.addListener(this);
    }

    public StringProperty userNameProperty() {
        return userName;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty password2Property() {
        return password2;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty errorProperty() {
        return error;
    }

    public boolean register() {
        if (nullChecker() && passwordChecker() && emailChecker()) {
            Platform.runLater(() -> {
                this.error.set(null);
            });
            this.register = iBankingService.register(
                    this.userName.getValue(), this.password.getValue(), this.email.getValue());
            this.userName.setValue(null);
            this.password.setValue(null);
            this.password2.setValue(null);
            this.email.setValue(null);
        }
        return this.register;
    }

    private boolean passwordChecker() {
        if (this.password.getValue().equals(this.password2.getValue())) {
            return true;
        }
        this.error.setValue("Passwords are not the same");
        register = false;
        return false;
    }

    private boolean nullChecker() {
        if (this.userName.getValue() != null && this.password.getValue() != null &&
                this.password2.getValue() != null && this.email.getValue() != null) {
            return true;
        }
        this.error.setValue("Some argument is null");
        register = false;
        return false;
    }


    private boolean emailChecker() {
        if (this.email.getValue().contains("@") && this.email.getValue().contains(".")) {
            return true;
        }
        this.error.setValue("wrong format of an email address");
        register = false;
        return false;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("errorRegister")) {
            Platform.runLater(() -> {
                this.error.set(evt.getNewValue().toString());
            });
        }
    }
}
