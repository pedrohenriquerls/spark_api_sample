package me.pedrohenriquerls.models;

import lombok.Data;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
public class Company implements Validable{
    private List<Employee> employees = new ArrayList<>();
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

    public void setEmployee(Employee employee){
        this.employees.add(employee);
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
        String city = params.get("city");
        if(city != null && !city.isEmpty())
            this.city = city;

        String name = params.get("name");
        if(name != null && !name.isEmpty())
            this.name = name;

        String email = params.get("email");
        if(email != null)
            this.email = email;

        String address = params.get("address");
        if(address != null && !address.isEmpty())
            this.address = address;

        String country = params.get("country");
        if(country != null && !country.isEmpty())
            this.country = country;

        String phone_number = params.get("phone_number");
        if(phone_number != null)
            this.phone_number = phone_number;
    }
}

