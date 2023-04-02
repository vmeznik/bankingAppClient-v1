package bank.service;

import bank.utility.UnnamedPropertyChangeSubject;

public interface IBankingService extends UnnamedPropertyChangeSubject {

    boolean sendMoney(int receiverAccNumber , double amountOfMoney);

    boolean login(String userName, String password);

    boolean register(String userName, String password, String email);

}
