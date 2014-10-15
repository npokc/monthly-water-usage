package test.tripledev.water.usage.validator;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import test.tripledev.water.usage.dataentry.DataEntry;
import test.tripledev.water.usage.dataentry.DataEntryService;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataEntryValidator implements Validator {

    @Autowired
    private DataEntryService dataEntryService;

    @Override
    public boolean supports(Class<?> clazz) {
        return DataEntry.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DataEntry form = (DataEntry) o;

        Integer month = form.getMonth();
        if(month == null || month < 1 || month >12){
            errors.rejectValue("month", "error.invalid.month");
            return;
        }
        Integer year = form.getYear();
        int currentYear = LocalDate.now().getYear();
        if(year == null || year < currentYear - 1 || year > currentYear + 1){
            errors.rejectValue("year", "error.invalid.year");
            return;
        }
        validateNumberPrecision(errors, form.getKitchenHot(), "kitchenHot");
        validateNumberPrecision(errors, form.getKitchenCold(), "kitchenCold");
        validateNumberPrecision(errors, form.getBathroomHot(), "bathroomHot");
        validateNumberPrecision(errors, form.getBathroomCold(), "bathroomCold");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        DataEntry currentPeriodEntry = dataEntryService.findByMonthAndYearAndUsername(month, year, username);
        if(currentPeriodEntry != null){
            errors.reject("error.data.already.exists");
            return;
        }
        LocalDate previousMonth = LocalDate.now().withMonthOfYear(month).withYear(year).minusMonths(1);
        DataEntry previousMonthDataEntry = dataEntryService.findByMonthAndYearAndUsername(
                previousMonth.getMonthOfYear(), previousMonth.getYear(), username);
        if(previousMonthDataEntry != null){
            if(form.getKitchenHot().compareTo(previousMonthDataEntry.getKitchenHot()) < 0){
                errors.rejectValue("kitchenHot", "error.data.less.than.previous.period");
            }
            if(form.getKitchenCold().compareTo(previousMonthDataEntry.getKitchenCold()) < 0){
                errors.rejectValue("kitchenCold", "error.data.less.than.previous.period");
            }
            if(form.getBathroomHot().compareTo(previousMonthDataEntry.getBathroomHot()) < 0){
                errors.rejectValue("bathroomHot", "error.data.less.than.previous.period");
            }
            if(form.getBathroomCold().compareTo(previousMonthDataEntry.getBathroomCold()) < 0){
                errors.rejectValue("bathroomCold", "error.data.less.than.previous.period");
            }
        }
    }

    private void validateNumberPrecision(Errors errors, BigDecimal number, String propertyName) {
        if(number == null){
            errors.rejectValue(propertyName, "error.cannot.be.empty" + propertyName);
            return;
        }
        if(number.scale() != 3){
            errors.rejectValue(propertyName, "error.invalid.precision." + propertyName);
        }
    }


}
