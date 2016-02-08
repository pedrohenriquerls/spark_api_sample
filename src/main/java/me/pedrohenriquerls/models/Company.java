package me.pedrohenriquerls.models;

import lombok.Data;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Company implements Validable{
    private List<Employee> employees;
    private UUID company_uuid;
    private String city;
    private String name;
    private String email;
    private String address;
    private String country;
    private String phone_number;

    public UUID getCompanyUuid() {
        return company_uuid;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Boolean save(){
        if(!isValid())
            return false;

        if(company_uuid == null){
            company_uuid = Sql2oModel.getInstance().createCompany(name, address, city, country, email, phone_number);
            return company_uuid != null;
        }

        return Sql2oModel.getInstance().updateCompany(company_uuid, name, address, city, country, email, phone_number);
    }

    public static Company getCompanyOn(UUID companyUuid){
        return Sql2oModel.getInstance().getCompanyOn(companyUuid);
    }

    public static List<Company> getAll(){
        return Sql2oModel.getInstance().getCompanies();
    }

    @Override
    public Boolean isValid() {
        return city != null && !city.isEmpty() && country != null && !country.isEmpty() && name != null && !name.isEmpty()
                && address != null && !address.isEmpty();
    }

    public Company(Map <String, String> params){
        setParams(params);
    }

    public Company(){}

    public void setParams(Map<String, String> params){
        city = params.get("city");
        name = params.get("name");
        email = params.get("email");
        address = params.get("address");
        country = params.get("country");
        phone_number = params.get("phone_number");
    }
}

