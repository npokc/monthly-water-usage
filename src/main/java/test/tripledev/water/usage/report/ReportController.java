package test.tripledev.water.usage.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import test.tripledev.water.usage.dataentry.DataEntryService;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private DataEntryService dataEntryService;

    @RequestMapping(value="/report")
    public ModelAndView getReport(ModelAndView modelAndView){

        modelAndView.addObject("period", new Period());

        List<Integer> years = new ArrayList<Integer>();
        years.add(Calendar.getInstance().get(Calendar.YEAR) - 1);
        years.add(Calendar.getInstance().get(Calendar.YEAR));
        years.add(Calendar.getInstance().get(Calendar.YEAR) + 1);

        modelAndView.addObject("years", years);
        modelAndView.setViewName("report/report");

        return modelAndView;
    }

    @RequestMapping(value="/report", method = RequestMethod.POST)
    public ModelAndView getReport(ModelAndView modelAndView, @ModelAttribute Period period){

        List<Integer> years = new ArrayList<Integer>();
        years.add(Calendar.getInstance().get(Calendar.YEAR) - 1);
        years.add(Calendar.getInstance().get(Calendar.YEAR));
        years.add(Calendar.getInstance().get(Calendar.YEAR) + 1);

        modelAndView.addObject("years", years);
        modelAndView.setViewName("report/report");

        return modelAndView;
    }
}
