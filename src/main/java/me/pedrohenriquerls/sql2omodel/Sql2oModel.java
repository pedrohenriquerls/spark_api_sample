package me.pedrohenriquerls.sql2omodel;

import me.pedrohenriquerls.Config;
import me.pedrohenriquerls.models.Company;
import me.pedrohenriquerls.models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import java.util.List;
import java.util.UUID;

public class Sql2oModel {

    private static Sql2oModel instance;
    private Sql2o sql2o;
    private Boolean debug;

    public static void setInstance(Config options){
        if(instance == null)
            instance = new Sql2oModel(options);
    }

    public static Sql2oModel getInstance(){
        return instance;
    }

    private Sql2oModel(Config options) {
        debug = options.debug;

        PostgresQuirks postgresQuirks = new PostgresQuirks() {
            {converters.put(UUID.class, new UUIDConverter());}
        };

        this.sql2o = new Sql2o("jdbc:postgresql://" + options.dbHost + ":" + options.dbPort + "/" + options.database,
                options.dbUsername, options.dbPassword, postgresQuirks);
    }

    private UUID getRandomUUID(){
        return UUID.randomUUID();
    }

    public UUID createCompany(String name, String address, String city, String country, String email, String phoneNumber) {
        try (Connection conn = sql2o.beginTransaction()) {
            UUID companyUuid = getRandomUUID();
            conn.createQuery("insert into companies(company_uuid, name, address, city, country, email, phone_number) " +
                    "VALUES (:company_uuid, :name, :address, :city, :country, :email, :phone_number)")
                    .addParameter("company_uuid", companyUuid )
                    .addParameter("name", name )
                    .addParameter("address", address )
                    .addParameter("city", city )
                    .addParameter("country", country )
                    .addParameter("email", email )
                    .addParameter("phone_number", phoneNumber )
                    .executeUpdate();
            conn.commit();
            return companyUuid;
        }catch (Exception e){
            if(debug)
                e.printStackTrace();
            return null;
        }
    }

    public Boolean updateCompany(UUID companyUuid, String name, String address, String city, String country, String email, String phoneNumber) {
        try (Connection conn = sql2o.beginTransaction()) {
            conn.createQuery("UPDATE companies SET name=:name, address=:address, city=:city, country=:country, email=:email, phone_number=:phone_number " +
                    "WHERE company_uuid=:company_uuid")
                    .addParameter("company_uuid", companyUuid )
                    .addParameter("name", name )
                    .addParameter("address", address )
                    .addParameter("city", city )
                    .addParameter("country", country )
                    .addParameter("email", email )
                    .addParameter("phone_number", phoneNumber)
                    .executeUpdate();
            conn.commit();
            return true;
        }catch (Exception e){
//            if(debug)
                e.printStackTrace();
            return false;
        }
    }

    public UUID createEmployee(String name, UUID companyUuid){
        try (Connection conn = sql2o.beginTransaction()){
            UUID employeeUuid = getRandomUUID();
            conn.createQuery("insert into employees(employee_uuid, name, company_uuid)" +
                    "VALUES (:employee_uuid, :name, :company_uuid)")
                    .addParameter("employee_uuid", employeeUuid)
                    .addParameter("company_uuid", companyUuid)
                    .addParameter("name", name)
                    .executeUpdate();
            conn.commit();
            return employeeUuid;
        }catch(Exception e){
            if(debug)
                e.printStackTrace();
            return null;
        }
    }

    public Company getCompanyOn(UUID companyUuid){
        try (Connection conn = sql2o.beginTransaction()) {
            Company company = conn.createQuery("SELECT * FROM companies WHERE company_uuid = :company_uuid LIMIT 1")
                    .addParameter("company_uuid", companyUuid)
                    .executeAndFetch(Company.class).get(0);

            List<Employee> employees = conn.createQuery("SELECT name FROM employees WHERE company_uuid = :company_uuid")
                    .addParameter("company_uuid", companyUuid)
                    .executeAndFetch(Employee.class);

            company.setEmployees(employees);
            return company;
        }catch(Exception e){
            if(debug)
                e.printStackTrace();
            return null;
        }
    }

    public List<Company> getCompanies(){
        try (Connection conn = sql2o.beginTransaction()) {
            return conn.createQuery("SELECT company_uuid, name FROM companies")
                    .executeAndFetch(Company.class);
        }
    }
}