package me.pedrohenriquerls.handlers;

import me.pedrohenriquerls.models.Company;
import java.util.Map;
import java.util.UUID;

public class UpdateCompanyHandler  extends AbstractRequestHandler {

    @Override
    protected Answer process(Map<String, String> params) {
        UUID companyUuid = UUID.fromString(params.get("company_uuid"));
        Company company = Company.getCompanyOn(companyUuid);

        Answer answer;
        if(company != null){
            company.setParams(params);
            if(company.save())
                answer = Answer.ok(company, "Company "+ companyUuid + " updated.");
            else
                answer = Answer.error("Invalid company params.");
        }else{
            answer = Answer.error("Company "+ companyUuid + " doest exist.");
        }

        return answer;
    }
}