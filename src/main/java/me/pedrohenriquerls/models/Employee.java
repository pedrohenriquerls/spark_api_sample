package me.pedrohenriquerls.models;

import lombok.Data;
import me.pedrohenriquerls.sql2omodel.Sql2oModel;

import java.util.UUID;

@Data
public class Employee implements Validable{
    private UUID employee_uuid;
    private String name;
    private Company company;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean save(){
        if(!isValid())
            return false;

        employee_uuid = Sql2oModel.getInstance().createEmployee(name, company.getCompanyUuid());
        return employee_uuid != null;
    }

    public Employee(String name, Company company){
        this.name = name;
        this.company = company;
    }

    public Employee(){}

    @Override
    public Boolean isValid() {
        return name != null && !name.isEmpty() && company != null;
    }
}
