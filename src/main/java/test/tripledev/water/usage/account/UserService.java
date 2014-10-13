package test.tripledev.water.usage.account;

import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private AccountService accountService;
	
	@PostConstruct	
	protected void initialize() {
		accountService.save(new Account("apartment01", "apartment01", "ROLE_USER"));
		accountService.save(new Account("apartment02", "apartment02", "ROLE_USER"));
		accountService.save(new Account("apartment03", "apartment03", "ROLE_USER"));
		accountService.save(new Account("apartment04", "apartment04", "ROLE_USER"));
		accountService.save(new Account("apartment05", "apartment05", "ROLE_USER"));
		accountService.save(new Account("apartment06", "apartment06", "ROLE_USER"));
		accountService.save(new Account("apartment07", "apartment07", "ROLE_USER"));
		accountService.save(new Account("apartment08", "apartment08", "ROLE_USER"));
		accountService.save(new Account("apartment09", "apartment09", "ROLE_USER"));
		accountService.save(new Account("apartment10", "apartment10", "ROLE_USER"));
		accountService.save(new Account("apartment11", "apartment11", "ROLE_USER"));
		accountService.save(new Account("apartment12", "apartment12", "ROLE_USER"));
		accountService.save(new Account("apartment13", "apartment13", "ROLE_USER"));
		accountService.save(new Account("apartment14", "apartment14", "ROLE_USER"));
		accountService.save(new Account("apartment15", "apartment15", "ROLE_USER"));
		accountService.save(new Account("apartment16", "apartment16", "ROLE_USER"));
		accountService.save(new Account("apartment17", "apartment17", "ROLE_USER"));
		accountService.save(new Account("apartment18", "apartment18", "ROLE_USER"));
		accountService.save(new Account("apartment19", "apartment19", "ROLE_USER"));
		accountService.save(new Account("apartment20", "apartment20", "ROLE_USER"));
		accountService.save(new Account("apartment21", "apartment21", "ROLE_USER"));
		accountService.save(new Account("apartment22", "apartment22", "ROLE_USER"));
		accountService.save(new Account("apartment23", "apartment23", "ROLE_USER"));
		accountService.save(new Account("apartment24", "apartment24", "ROLE_USER"));
		accountService.save(new Account("apartment25", "apartment25", "ROLE_USER"));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findByUsername(username);
		if(account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(authenticate(account));
	}
	
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));		
	}
	
	private User createUser(Account account) {
		return new User(account.getUsername(), account.getPassword(), Collections.singleton(createAuthority(account)));
	}

	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getRole());
	}

}
