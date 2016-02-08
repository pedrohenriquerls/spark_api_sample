package me.pedrohenriquerls.handlers;

import me.pedrohenriquerls.models.Company;
import me.pedrohenriquerls.models.Employee;

import java.util.Map;
import java.util.UUID;

public class CreateEmployeeHandler  extends AbstractRequestHandler {

    @Override
    protected Answer process(Map<String, String> params) {
        UUID companyUuid = UUID.fromString(params.get("company_uuid"));
        Company company = Company.getCompanyOn(companyUuid);

        Answer answer;
        if(company != null){
            String name = params.get("name");
            Employee employee = new Employee(name, company);
            if(employee.save())
                answer = Answer.ok(employee, "Employee from "+ companyUuid + " created.");
            else
                answer = Answer.error("Invalid employee params.");
        }else{
            answer = Answer.error("Company "+ companyUuid + " doest exist.");
        }

        return answer;
    }
}