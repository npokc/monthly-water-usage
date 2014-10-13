package test.tripledev.water.usage.account;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountService {

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private Md5PasswordEncoder passwordEncoder;

    public Account save(Account account) {
        if (findByUsername(account.getUsername()) == null) {
            account.setPassword(passwordEncoder.encodePassword(account.getPassword(), null));
            accountRepository.save(account);
        }
        return account;
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public List<String> findAllUsernames(){
        return  accountRepository.findAllUsernames();
    }

}
