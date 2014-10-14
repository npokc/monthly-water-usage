package test.tripledev.water.usage.dataentry;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import test.tripledev.water.usage.account.*;
import test.tripledev.water.usage.support.web.*;

import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;

@Controller
public class DataEntryController {

    private static final String SIGNUP_VIEW_NAME = "dataentry/dataentry";

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"dataentry", "/"})
	public ModelAndView signup(ModelAndView modelAndView) {
        modelAndView.addObject(new DataEntryForm());
        modelAndView.addObject("months", Arrays.toString(Month.values()).replaceAll("\\[", "").replaceAll("\\]", "").split(", "));
        modelAndView.addObject("years", Calendar.getInstance().get(Calendar.YEAR));
        modelAndView.setViewName(SIGNUP_VIEW_NAME);
        return modelAndView;
	}
	
	@RequestMapping(value = "dataentry", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute DataEntryForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		//Account account = accountService.save(signupForm.createAccount());
		//userService.signin(account);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
