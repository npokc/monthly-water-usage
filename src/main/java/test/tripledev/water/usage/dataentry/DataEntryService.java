package test.tripledev.water.usage.dataentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.tripledev.water.usage.account.Account;
import test.tripledev.water.usage.account.AccountService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Transactional(readOnly = true)
public class DataEntryService {

    @Inject
    private DataEntryRepository dataEntryRepository;

    @Inject
    AccountService accountService;

    public DataEntry findByMonthAndYearAndUsername(int month, int year, String username){
        return dataEntryRepository.findByMonthAndYearAndUsername(month, year, username);
    }

    public List<DataEntry> findByMonthAndYear(int month, int year){
        return dataEntryRepository.findByMonthAndYear(month, year);
    }

    public void saveDataEntry(DataEntry dataEntry){
        dataEntry.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        dataEntryRepository.save(dataEntry);
    }

    public Map<String, DataEntry> findDataForAllUsersAndCalculateConsumption(int month, int year){
        Map<String, DataEntry> allUsersData = new TreeMap<String, DataEntry>();

        List<String> allUsernames = accountService.findAllUsernames();
        for(String username : allUsernames){
            allUsersData.put(username, null);
        }

        List<DataEntry> usersByMonthAndYear = findByMonthAndYear(month, year);
        BigDecimal totalHot = new BigDecimal(0).setScale(3);
        BigDecimal totalCold = new BigDecimal(0).setScale(3);
        for(DataEntry dataEntry : usersByMonthAndYear){
            dataEntry.calculateTotals();
            totalHot = totalHot.add(dataEntry.getTotalHot());
            totalCold = totalCold.add(dataEntry.getTotalCold());
            allUsersData.put(dataEntry.getUsername(), dataEntry);
        }
        allUsersData.put("total", createTotalDataEntry(totalHot, totalCold));
        return allUsersData;
    }

    private DataEntry createTotalDataEntry(BigDecimal totalHot, BigDecimal totalCold) {
        DataEntry total = new DataEntry();
        total.setTotalHot(totalHot);
        total.setTotalCold(totalCold);
        total.setTotal(totalHot.add(totalCold));
        return total;
    }
}
