package test.tripledev.water.usage.dataentry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface DataEntryRepository extends JpaRepository<DataEntry, Long> {

    DataEntry findByMonthAndYearAndUsername(int month, int year, String username);

    List<DataEntry> findByMonthAndYear(int month, int year);

}
