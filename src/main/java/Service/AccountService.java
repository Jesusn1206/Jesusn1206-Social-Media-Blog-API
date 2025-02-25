package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        if(accountDAO.getAccountbyUser(account.getUsername()) == null && account.getPassword().length() >= 4 && !account.getUsername().equals("")){
            return accountDAO.insertAccount(account);
        }
        return null;
    }

    public Account loginAccount(Account account){
        Account checker = accountDAO.getAccountbyUser(account.getUsername());
        if(checker != null && checker.getPassword().equals(account.getPassword())){
            return checker;
        }
        return null;
    }
    
}
