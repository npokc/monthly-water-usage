package test.tripledev.water.usage.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.tripledev.water.usage.account.AccountService;

@Controller
public class SigninController {

    @Autowired
    AccountService accountService;

	@RequestMapping(value = "signin")
	public String signin(Model model) {
        model.addAttribute("usernames", accountService.findAllUsernames());
        return "signin/signin";
    }
}
