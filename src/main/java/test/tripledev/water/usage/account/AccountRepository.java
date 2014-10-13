package test.tripledev.water.usage.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);

    @Query("SELECT username FROM Account")
    List<String> findAllUsernames();
}
