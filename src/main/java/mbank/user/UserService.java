package mbank.user;

import mbank.account.AccountService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class UserService {
    final private Map<Integer,User> userRepositoryInMemory;

    final private AccountService accountService;
    private int idCounter;
    public UserService( @Lazy AccountService accountService) {
        this.userRepositoryInMemory = new HashMap<>();
        this.accountService = accountService;
        idCounter =-1;

    }
    public int createUser(String login){
        idCounter++;
        userRepositoryInMemory.put(idCounter, new User(idCounter,login));
        return idCounter;
    }
    public Optional<User> findUserById(int id){
            return Optional.ofNullable(userRepositoryInMemory.get(id));//.get(id);
    }

    public boolean isFirstAccount(int userId){
        return userRepositoryInMemory.get(userId).getAccountList().isEmpty();
    }

    public List<User> getUserList(){
        return userRepositoryInMemory.values().stream().toList();
    }
    public void addAccountbyUserId(int accountId){

    }
}
