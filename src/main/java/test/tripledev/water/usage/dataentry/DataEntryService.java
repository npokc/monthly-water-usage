package test.tripledev.water.usage.dataentry;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.Month;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DataEntryService {

    @Inject
    private DataEntryRepository dataEntryRepository;

    public DataEntry findByMonthAndYearAndUsername(int month, int year, String username){
        return dataEntryRepository.findByMonthAndYearAndUsername(month, year, username);
    }

    public void saveDataEntry(DataEntry dataEntry){
        dataEntry.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        dataEntryRepository.save(dataEntry);
    }
}
