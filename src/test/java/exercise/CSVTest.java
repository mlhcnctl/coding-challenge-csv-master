package exercise;


import exercise.entity.AddressBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static exercise.Main.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVTest extends TestUtil{

    // your unit tests here
    @Test
    public void compare_csv_and_expected_result() throws IOException {
        //given
        String fileToParse = "C:\\Users\\mlhcn\\Desktop\\coding-challenge-csv-master\\src\\testdata\\address-book.csv";
        List<AddressBook> listAddressBook = new ArrayList<>();
        String row0column1 = "Male"; // first row and second column equal 'Male'
        String row2column0 = "Jessica"; // third row and first column equal 'Jessica'


        //when
        csvReader(fileToParse, listAddressBook);

        //then
        assertThat(listAddressBook.get(0).getGender()).isEqualTo(row0column1); // expect 'Male' from listAddressBook.get(0).getGender()
        assertThat(listAddressBook.get(2).getName()).isEqualTo(row2column0); // expect 'Jessica' from listAddressBook.get(2).getName()
    }

    @Test
    public void get_number_of_female_and_oldest_person_name() throws IOException {
        //given
        String fileToParse = "C:\\Users\\mlhcn\\Desktop\\coding-challenge-csv-master\\src\\testdata\\address-book.csv";
        List<AddressBook> listAddressBook = new ArrayList<>();
        long numberOfFemales = 2;
        String oldestPersonName = "Michael";

        //when
        csvReader(fileToParse, listAddressBook);

        //then
        assertThat(numberOfFemales(listAddressBook)).isEqualTo(numberOfFemales);
        assertThat(oldestPersonName(listAddressBook)).isEqualTo(oldestPersonName);
    }

    @Test
    public void test_add_data_method() throws ParseException {
        //given
        String[] token = {"Jon", "Male", "1985-03-09"}; // first token

        List<AddressBook> listAddressBook = new ArrayList<>();

        //when
        addDatas(token, listAddressBook);

        //then
        assertThat(listAddressBook.get(0).getName()).isEqualTo("Jon");
        assertThat(convertDateToString(listAddressBook.get(0).getBirthDate())).isEqualTo("1985-03-09");
    }

}
