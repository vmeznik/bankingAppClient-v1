package bank.service;

import bank.model.RequestConfirmation;
import bank.model.Transaction;
import bank.model.User;
import bank.utility.Logger;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class ApiClient {
    private final Client client;
    private String baseErrorMsg = "confirmation was not successful";

    public ApiClient() {
        this.client = ClientBuilder.newClient();
    }

    public RequestConfirmation registerRequest(User user) {
        WebTarget resource = client.target("http://localhost:8080/bankingApp/register");

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        Response response;
        RequestConfirmation requestConfirmation = new RequestConfirmation(false,
                baseErrorMsg);
        try {
            response = request.post(Entity.entity(user, MediaType.APPLICATION_JSON));
            requestConfirmation = response.readEntity(RequestConfirmation.class);
        } catch (Exception e) {
            Logger.getInstance().log("Error while registering");
            e.printStackTrace();
        }
        return requestConfirmation;
    }

    public RequestConfirmation loginRequest(User user) {
        WebTarget resource = client.target("http://localhost:8080/bankingApp/login");

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        Response response;
        RequestConfirmation requestConfirmation = new RequestConfirmation(false,
                "We are sorry server is off ");
        try {
            response = request.post(Entity.entity(user, MediaType.APPLICATION_JSON));
            requestConfirmation = response.readEntity(RequestConfirmation.class);
        } catch (Exception e) {
            Logger.getInstance().log("Error while logging in");
            e.printStackTrace();
        }
        return requestConfirmation;
    }

    public User accountInfoRequest(User user) {
        WebTarget resource = client.target("http://localhost:8080/bankingApp/accountInfo");

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        Response response;
        User userInfo = null;
        try {
            response = request.post(Entity.entity(user, MediaType.APPLICATION_JSON));
            userInfo = response.readEntity(User.class);
        } catch (Exception e) {
            Logger.getInstance().log("Error while account info request");
            e.printStackTrace();
        }
        return userInfo;
    }

    public RequestConfirmation sendMoneyRequest(Transaction transaction) {
        WebTarget resource = client.target("http://localhost:8080/bankingApp/sendMoney");

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        Response response;
        RequestConfirmation requestConfirmation = new RequestConfirmation(false,
                baseErrorMsg);
        try {
            response = request.post(Entity.entity(transaction, MediaType.APPLICATION_JSON));
            requestConfirmation = response.readEntity(RequestConfirmation.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return requestConfirmation;
    }

    public ArrayList<Transaction> getTransactions(User user) {
        WebTarget resource = client.target("http://localhost:8080/bankingApp/getTransactions");

        Invocation.Builder request = resource.request(MediaType.APPLICATION_JSON);
        request.accept(MediaType.APPLICATION_JSON);
        Response response;
        ArrayList<Transaction> transactions = null;
        try {
            response = request.post(Entity.entity(user, MediaType.APPLICATION_JSON));
            transactions = response.readEntity(new GenericType<ArrayList<Transaction>>(){});
        } catch (Exception e) {
            Logger.getInstance().log("Error while getting transactions");
            e.printStackTrace();
        }
        return transactions;
    }



}