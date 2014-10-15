package test.tripledev.water.usage.dataentry;

import org.hibernate.validator.constraints.NotBlank;
import test.tripledev.water.usage.validator.Year;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.Month;

public class DataEntryForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @Enumerated(EnumType.STRING)
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private Month month;

    @Year
    @NotNull
    private Integer year;

    private float kitchenHot;

    private float kitchenCold;

    private float bathroomHot;

    private float bathroomCold;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public float getKitchenHot() {
        return kitchenHot;
    }

    public void setKitchenHot(float kitchenHot) {
        this.kitchenHot = kitchenHot;
    }

    public float getKitchenCold() {
        return kitchenCold;
    }

    public void setKitchenCold(float kitchenCold) {
        this.kitchenCold = kitchenCold;
    }

    public float getBathroomHot() {
        return bathroomHot;
    }

    public void setBathroomHot(float bathroomHot) {
        this.bathroomHot = bathroomHot;
    }

    public float getBathroomCold() {
        return bathroomCold;
    }

    public void setBathroomCold(float bathroomCold) {
        this.bathroomCold = bathroomCold;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }
}
