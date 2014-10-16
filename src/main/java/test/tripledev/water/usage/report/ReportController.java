package test.tripledev.water.usage.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import test.tripledev.water.usage.dataentry.DataEntry;
import test.tripledev.water.usage.dataentry.DataEntryService;

import java.time.Month;
import java.util.*;

@Controller
public class ReportController {

    @Autowired
    private DataEntryService dataEntryService;

    @RequestMapping(value="/report")
    public ModelAndView getReport(ModelAndView modelAndView){

        modelAndView.addObject("period", new Period());

        modelAndView.addObject("years", getAllowedYears());
        modelAndView.setViewName("report/report");

        return modelAndView;
    }

    @RequestMapping(value="/report", method = RequestMethod.POST)
    public ModelAndView getReport(ModelAndView modelAndView, @ModelAttribute Period period){

        modelAndView.addObject("years", getAllowedYears());

        Map<String, DataEntry> reportData = dataEntryService.findDataForAllUsersByMonthAndYear(period.getMonth(), period.getYear());
        modelAndView.addObject("reportData", reportData);

        modelAndView.setViewName("report/report");

        return modelAndView;
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
