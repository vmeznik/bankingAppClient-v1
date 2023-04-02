package bank.service;

import bank.model.RequestConfirmation;
import bank.model.Transaction;
import bank.model.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class BankingService implements IBankingService {
    private final PropertyChangeSupport property;
    private final ApiClient apiClient;
    private static   int myAccNumber;
    private  String name;
    private  String password;

    public BankingService(){
        this.property = new PropertyChangeSupport(this);
        this.apiClient = new ApiClient();
        this.myAccNumber = 0;
    }


    @Override
    public boolean sendMoney(int receiverAccNumber , double amountOfMoney) {
        RequestConfirmation requestConfirmation = apiClient
                .sendMoneyRequest(new Transaction(myAccNumber,receiverAccNumber,amountOfMoney));
        if (requestConfirmation.isSuccess()){
            viewAccountInfo(this.name,this.password);
            return true;
        }
        property.firePropertyChange("error",null,requestConfirmation.getError());
        return false;
    }

    @Override
    public boolean login(String userName, String password) {
        RequestConfirmation requestConfirmation = apiClient.
                loginRequest(new User(userName,password,null,-1,-1));
        if (requestConfirmation.isSuccess()){
            this.name = userName;
            this.password = password;
            viewAccountInfo(userName,password);
            return true;
        }
        property.firePropertyChange("errorLogin",null,requestConfirmation.getError());
        return false;
    }

    @Override
    public boolean register(String userName, String password, String email) {
        RequestConfirmation requestConfirmation = apiClient.
                registerRequest(new User(userName,password,email,-1,-1));
        if (requestConfirmation.isSuccess()){
            return true;
        }
        property.firePropertyChange("errorRegister",null,requestConfirmation.getError());
        return false;
    }

    private void viewAccountInfo(String name , String password){
            User user = apiClient
                    .accountInfoRequest(new User(name, password, null, -1, -1));
            if (myAccNumber == 0) {
                myAccNumber = user.getAccNumber();
                property.firePropertyChange("accNumber", null, myAccNumber);
            }
        ArrayList<Transaction> transactions = apiClient.getTransactions(new User(
                null, null, null, this.myAccNumber, -1));
            property.firePropertyChange("balance", null, user.getBalance());
            property.firePropertyChange("transactions", null, transactions);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }

    public static int getMyAccNumber(){
        return myAccNumber;
    }
}
