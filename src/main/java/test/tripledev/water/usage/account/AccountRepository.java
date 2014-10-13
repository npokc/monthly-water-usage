package test.tripledev.water.usage.account;

import javax.persistence.*;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private Md5PasswordEncoder passwordEncoder;

    public Account save(Account account) {
        if (findByUsername(account.getUsername()) == null) {
            account.setPassword(passwordEncoder.encodePassword(account.getPassword(), null));
            entityManager.persist(account);
        }
        return account;
    }

    public Account findByUsername(String username) {
        try {
            return entityManager.createNamedQuery(Account.FIND_BY_USERNAME, Account.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

        public List<Account> findAllUsers() {
        try {
            return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
