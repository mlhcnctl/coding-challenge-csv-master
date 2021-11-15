package exercise;


import exercise.entity.AddressBook;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class Main {


    public static void main(String[] args) {
        // csv path in my local computer
        String fileToParse = "C:\\Users\\mlhcn\\Desktop\\coding-challenge-csv-master\\src\\testdata\\address-book.csv";
        List<AddressBook> listAddressBook = new ArrayList<>();
        csvReader(fileToParse, listAddressBook);

        System.out.println("Number of females inside address book: TODO");
        System.out.println(numberOfFemales(listAddressBook));
        System.out.println("Oldest person inside address book: TODO");
        System.out.println(oldestPersonName(listAddressBook));
    }

    public static void csvReader(String fileToParse, List<AddressBook> listAddressBook) {
        BufferedReader fileReader = null;
        final String splitter = ","; // using ',' to split

        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(fileToParse));
            // getting each line from csv
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(splitter);
                addDatas(tokens, listAddressBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close(); // closing fileReader in finally
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void addDatas(String[] tokens, List<AddressBook> listAddressBook) throws ParseException {

        AddressBook addressBook = new AddressBook();
        addressBook.setName(tokens[0].trim()); // getting name from token[0] each line and setting to AddressBook
        addressBook.setGender(tokens[1].trim()); // getting gender from token[1] each line and setting to AddressBook
        addressBook.setBirthDate(convertStringDateToDate(tokens[2].trim())); // getting date from token[2] each line and setting to AddressBook

        listAddressBook.add(addressBook);
    }
    // Converting String dates to Date format
    public static Date convertStringDateToDate(String strDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(strDate);
        return date;
    }
    // Calculating number of female in list of AddressBook
    public static long numberOfFemales(List<AddressBook> listAddressBook) {
        return listAddressBook.stream()
                .filter(item -> item.getGender().equals("Female"))
                .count();
    }
    // Getting oldest person name in list of AddressBook
    public static String oldestPersonName(List<AddressBook> listAddressBook) {
        return listAddressBook.stream()
                .sorted(Comparator.comparing(AddressBook::getBirthDate))
                .findFirst()
                .get()
                .getName();
    }



}
