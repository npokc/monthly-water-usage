package test.tripledev.water.usage.dataentry;

import javax.persistence.*;
import java.math.BigDecimal;

@SuppressWarnings("serial")
@Entity
@Table(name = "water_entry_data")
public class DataEntry {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private Integer month;
    private Integer year;
    @Column(name="kitchen_hot")
    private BigDecimal kitchenHot;
    @Column(name="kitchen_cold")
    private BigDecimal kitchenCold;
    @Column(name="bathroom_hot")
    private BigDecimal bathroomHot;
    @Column(name="bathroom_cold")
    private BigDecimal bathroomCold;

    public DataEntry() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getKitchenHot() {
        return kitchenHot;
    }

    public void setKitchenHot(BigDecimal kitchenHot) {
        this.kitchenHot = kitchenHot;
    }

    public BigDecimal getKitchenCold() {
        return kitchenCold;
    }

    public void setKitchenCold(BigDecimal kitchenCold) {
        this.kitchenCold = kitchenCold;
    }

    public BigDecimal getBathroomHot() {
        return bathroomHot;
    }

    public void setBathroomHot(BigDecimal bathroomHot) {
        this.bathroomHot = bathroomHot;
    }

    public BigDecimal getBathroomCold() {
        return bathroomCold;
    }

    public void setBathroomCold(BigDecimal bathroomCold) {
        this.bathroomCold = bathroomCold;
    }
}
