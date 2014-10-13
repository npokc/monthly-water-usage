package test.tripledev.water.usage.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.tripledev.water.usage.account.AccountRepository;

@Controller
public class SigninController {

    @Autowired
    AccountRepository accountRepository;

	@RequestMapping(value = "signin")
	public String signin(Model model) {
        //FIXME: How about passwords?
        model.addAttribute("usernames", accountRepository.findAllUsers());
        return "signin/signin";
    }
}
