package test.tripledev.water.usage.dataentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.tripledev.water.usage.account.Account;
import test.tripledev.water.usage.account.AccountService;

import javax.inject.Inject;
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

    public Map<String, DataEntry> findDataForAllUsersByMonthAndYear(int month, int year){
        Map<String, DataEntry> allUsersData = new TreeMap<String, DataEntry>();

        for(String username : accountService.findAllUsernames()){
            allUsersData.put(username, null);
        }

        for(DataEntry dataEntry : findByMonthAndYear(month, year)){
            allUsersData.put(dataEntry.getUsername(), dataEntry);
        }
        return allUsersData;
    }
}
