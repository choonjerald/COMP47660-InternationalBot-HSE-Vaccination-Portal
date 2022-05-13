package org.example.dto;

import org.example.constraint.CheckAge;
import org.example.constraint.ValidPassword;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UserRegistrationDto {
    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Surname is required")
    private String surname;
    @NotEmpty(message = "Date of Birth is required")
    @CheckAge(groups = Extended.class)
    private String DOB;
    private String ageGroup;
    @NotEmpty(message = "Sex is required")
    private String sex;
    @NotBlank(message = "PPS number is required")
    @Pattern(regexp = "^\\d{7}.{1,2}$", message = "A PPS Number is always 7 numbers followed by either one or 2 letters", groups = Extended.class)
    private String PPS;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "Use a valid phone number", groups = Extended.class)
    private String phone;
    @NotBlank(message = "Email is required")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Use a Valid Email Address", groups = Extended.class)
    private String email;
    @NotBlank(message = "Nationality is required")
    private String nationality;
    @NotBlank(message = "Password is required")
    @ValidPassword
    private String password;

    public UserRegistrationDto() {

    }

    public UserRegistrationDto(String firstName, String surname, String DOB, String ageGroup, String sex, String PPS, String address, String phone, String email, String nationality, String password) {
        super();
        this.firstName = firstName;
        this.surname = surname;
        this.DOB = DOB;
        this.ageGroup = ageGroup;
        this.sex = sex;
        this.PPS = PPS;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.nationality = nationality;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getAgeGroup() {
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(DOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);
        if (age.getYears() >= 18 && age.getYears() <= 25) {
            setAgeGroup("18 - 25");
        } else if (age.getYears() >= 26 && age.getYears() <= 35) {
            setAgeGroup("26 - 35");
        } else if (age.getYears() >= 36 && age.getYears() <= 45) {
            setAgeGroup("36 - 45");
        } else if (age.getYears() >= 46 && age.getYears() <= 55) {
            setAgeGroup("46 - 55");
        } else if (age.getYears() >= 56 && age.getYears() <= 65) {
            setAgeGroup("56 - 65");
        } else {
            setAgeGroup("Over 66");
        }

        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPPS() {
        return PPS;
    }

    public void setPPS(String PPS) {
        this.PPS = PPS;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public interface Extended {
    }

    @GroupSequence({Default.class, Extended.class})
    public interface MySequence {
    }
}
