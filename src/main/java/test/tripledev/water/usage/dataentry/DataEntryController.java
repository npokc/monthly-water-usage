package test.tripledev.water.usage.dataentry;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import test.tripledev.water.usage.account.*;
import test.tripledev.water.usage.support.web.*;
import test.tripledev.water.usage.validator.DataEntryValidator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class DataEntryController {

    private static final String DATA_ENTRY_VIEW = "dataentry/dataentry";

    @Autowired
    DataEntryService dataEntryService;

    @Autowired
    private DataEntryValidator dataEntryValidator;

    @InitBinder("dataEntry")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(dataEntryValidator);
    }

    @RequestMapping(value = {"/dataentry"})
    public ModelAndView insertData(ModelAndView modelAndView) {
        initModelAndView(new DataEntry(), modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/dataentry", method = RequestMethod.POST)
    public ModelAndView saveData(@Valid @ModelAttribute DataEntry dataEntry, Errors errors, RedirectAttributes ra, ModelAndView modelAndView) {
        initModelAndView(dataEntry, modelAndView);
        if (errors.hasErrors()) {
            return modelAndView;
        }
        dataEntryService.saveDataEntry(dataEntry);
        DataEntry prevMonthData = dataEntryService.findPreviousMonthConsumption(dataEntry.getMonth(), dataEntry.getYear());
        if (prevMonthData == null) {
            errors.reject("error.no.data.for.previous.month");
        } else {
            dataEntry.calculateTotals();
            modelAndView.addObject("previousMonthData", prevMonthData);
        }

        return modelAndView;
    }

    private void initModelAndView(DataEntry dataEntry, ModelAndView modelAndView) {
        modelAndView.addObject("years", getAllowedYears());
        modelAndView.setViewName(DATA_ENTRY_VIEW);
        modelAndView.addObject(dataEntry);
    }

    private List<Integer> getAllowedYears() {
        List<Integer> years = new ArrayList<Integer>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        years.add(currentYear - 1);
        years.add(currentYear);
        years.add(currentYear + 1);
        return years;
    }
}
