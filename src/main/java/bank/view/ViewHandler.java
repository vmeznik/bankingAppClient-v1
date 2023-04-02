package bank.view;

import bank.view.viewController.AccountViewController;
import bank.view.viewController.LoginController;
import bank.view.viewController.RegistrationController;
import bank.view.viewController.SendMoneyController;
import bank.view.viewModel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ViewHandler {
    private Stage primaryStage;
    private Scene currentScene;
    private AccountViewController accountViewController;
    private LoginController loginController;
    private RegistrationController registrationController;
    private SendMoneyController sendMoneyController;
    private final ViewModelFactory viewModelFactory;
    private Region root = null;

    public ViewHandler(ViewModelFactory viewModelFactory) {
        this.viewModelFactory = viewModelFactory;
        currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        currentScene = new Scene(new Region());
        openView("login");

    }

    public void openView(String id) {
        switch (id) {
            case "register" -> root = loadRegisterView("/Registration.fxml");
            case "sendMoney" -> root = loadSendMoneyView("/SendMoney.fxml");
            case "accountView" -> root = loadAccountView("/AccountView.fxml");
            default -> root = loadLoginView("/Login.fxml");
        }
        currentScene.setRoot(root);

        String title = "";
        if (root.getUserData() != null) {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();

    }

    private Region loadRegisterView(String fxmlFile) {
        if (registrationController == null) {
            try {
                URL fxmlLocation = RegistrationController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                registrationController = loader.getController();
                registrationController.init(this, viewModelFactory.getRegistrationViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return registrationController.getRoot();
    }

    private Region loadSendMoneyView(String fxmlFile) {
        if (sendMoneyController == null) {
            try {
                URL fxmlLocation = SendMoneyController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                sendMoneyController = loader.getController();
                sendMoneyController.init(this, viewModelFactory.getSendMoneyViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return sendMoneyController.getRoot();
    }

    private Region loadAccountView(String fxmlFile) {
        if (accountViewController == null) {
            try {
                URL fxmlLocation = AccountViewController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                accountViewController = loader.getController();
                accountViewController.init(this, viewModelFactory.getAccountViewViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return accountViewController.getRoot();
    }

    private Region loadLoginView(String fxmlFile) {
        if (loginController == null) {
            try {
                URL fxmlLocation = LoginController.class.getResource(fxmlFile);
                FXMLLoader loader = new FXMLLoader(fxmlLocation);
                root = loader.load();
                loginController = loader.getController();
                loginController.init(this, viewModelFactory.getLoginViewModel(), root);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return loginController.getRoot();
    }
}
