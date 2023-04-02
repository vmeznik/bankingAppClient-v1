package bank.view.viewController;

import bank.view.ViewHandler;
import bank.view.viewModel.LoginViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private Text error;
    private ViewHandler viewHandler;
    private LoginViewModel viewModel;
    private Region root;

    public void init(ViewHandler viewHandler, LoginViewModel viewModel, Region root) {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.root = root;
        this.userName.textProperty().bindBidirectional(viewModel.userNameProperty());
        this.password.textProperty().bindBidirectional(viewModel.passwordProperty());
        this.error.textProperty().bind(viewModel.errorProperty());
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void registerNewUser() {
        viewModel.resetError();
        viewHandler.openView("register");
    }

    @FXML
    private void loginButton() {
        if (viewModel.login()) {
            viewHandler.openView("accountView");
        }
    }

    @FXML
    private void onEnter() {
        loginButton();
    }
}
