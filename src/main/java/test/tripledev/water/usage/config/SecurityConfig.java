package test.tripledev.water.usage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.RequestMatcher;
import test.tripledev.water.usage.account.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

@Configuration
@ImportResource(value = "classpath:spring-security-context.xml")
class SecurityConfig {
	
	@Bean
	public UserService userService() {
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me-key", userService());
	}
	
	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}

  @Profile("test")
  @Bean(name = "csrfMatcher")
  public RequestMatcher testCsrfMatcher() {
    return new RequestMatcher() {

      @Override
      public boolean matches(HttpServletRequest request) {
        return false;
      }
    };
  }

  @Profile("!test")
  @Bean(name = "csrfMatcher")
  public RequestMatcher csrfMatcher() {
    /**
     * Copy of default request matcher from
     * CsrfFilter$DefaultRequiresCsrfMatcher
     */
    return new RequestMatcher() {
      private Pattern allowedMethods = Pattern
        .compile("^(GET|HEAD|TRACE|OPTIONS)$");

      /*
       * (non-Javadoc)
       *
       * @see
       * org.springframework.security.web.util.matcher.RequestMatcher#
       * matches(javax.servlet.http.HttpServletRequest)
       */
      public boolean matches(HttpServletRequest request) {
        return !allowedMethods.matcher(request.getMethod()).matches();
      }
    };
  }
}