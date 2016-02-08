package me.pedrohenriquerls.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

import me.pedrohenriquerls.Config;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import org.junit.Test;

public class EmployeeTest {
    private static Company company;

    @BeforeClass
    public static void setUp() {
        Config.database = "sampletest";
        Sql2oModel.setInstance(new Config());

        company = new Company();

        company.setName("Private Detective");
        company.setAddress("Backer Street");
        company.setCity("London");
        company.setCountry("England");
        company.save();
    }

    @Test
    public void createNewEmployee() {
        Employee employee = new Employee("Astrogildo da silva", company);
        assertTrue(employee.save());
    }

    @Test
    public void failCreateNewEmployee() {
        Employee employee = new Employee();
        employee.setCompany(company);

        assertFalse(employee.save());
    }
}