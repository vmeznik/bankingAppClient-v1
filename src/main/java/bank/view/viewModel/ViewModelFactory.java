package bank.view.viewModel;

import bank.service.IBankingService;

public class ViewModelFactory {
    private final AccountViewViewModel accountViewViewModel;
    private final LoginViewModel loginViewModel;
    private final RegistrationViewModel registrationViewModel;
    private final SendMoneyViewModel sendMoneyViewModel;

    public ViewModelFactory(IBankingService iBankingService){
        this.accountViewViewModel = new AccountViewViewModel(iBankingService);
        this.loginViewModel = new LoginViewModel(iBankingService);
        this.registrationViewModel = new RegistrationViewModel(iBankingService);
        this.sendMoneyViewModel = new SendMoneyViewModel(iBankingService);
    }

    public AccountViewViewModel getAccountViewViewModel() {
        return accountViewViewModel;
    }

    public LoginViewModel getLoginViewModel() {
        return loginViewModel;
    }

    public RegistrationViewModel getRegistrationViewModel() {
        return registrationViewModel;
    }

    public SendMoneyViewModel getSendMoneyViewModel() {
        return sendMoneyViewModel;
    }
}
