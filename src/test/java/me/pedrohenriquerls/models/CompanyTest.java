package me.pedrohenriquerls.models;

import org.junit.BeforeClass;

import me.pedrohenriquerls.Config;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    @BeforeClass
    public static void setUp() {
        Config.database = "sampletest";
        Sql2oModel.setInstance(new Config());
    }

    @Test
    public void createNewCompany() {
        Company company = createCompany();
        company.setName("Private Detective");
        assertTrue(company.save());
    }

    @Test
    public void udpateCompany() {
        Company company = createCompany();
        company.setName("Private Detective");

        assertTrue(company.save());
        assertNotNull(company.getCompanyUuid());

        company.setName("Blastoise");
        assertTrue(company.save());

        Company persistedCompany = Company.getCompanyOn(company.getCompanyUuid());
        assertEquals(persistedCompany.getName(), "Blastoise");
    }

    @Test
    public void getCompany(){
        Company company = createCompany();
        company.setName("Private Detective");
        assertTrue(company.save());

        Company persistedCompany = Company.getCompanyOn(company.getCompanyUuid());
        assertNotNull(persistedCompany);

        assertEquals(persistedCompany.getName(), company.getName());
        assertEquals(persistedCompany.getAddress(), company.getAddress());
        assertEquals(persistedCompany.getCity(), company.getCity());
        assertEquals(persistedCompany.getCountry(), company.getCountry());
    }

    @Test
    public void failCreateNewCompany() {
        Company company = createCompany();
        assertFalse(company.save());
    }

    private Company createCompany(){
        Company company = new Company();

        company.setAddress("Backer Street");
        company.setCity("London");
        company.setCountry("England");
        return company;
    }
}