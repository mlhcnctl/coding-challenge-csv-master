package exercise.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AddressBook {
    private String name;
    private String gender;
    private Date birthDate;
}
