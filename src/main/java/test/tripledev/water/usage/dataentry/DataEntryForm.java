package test.tripledev.water.usage.dataentry;

import org.hibernate.validator.constraints.NotBlank;

public class DataEntryForm {

	private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private String month;
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private int year;
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private float kitchenHot;
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private float kitchenCold;
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private float bathroomHot;
    @NotBlank(message = DataEntryForm.NOT_BLANK_MESSAGE)
    private float bathroomCold;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
