package me.pedrohenriquerls.handlers;

import me.pedrohenriquerls.models.Company;
import java.util.Map;

public class CreateCompanyHandler  extends AbstractRequestHandler {

    @Override
    protected Answer process(Map<String, String> params) {
        Company company = new Company(params);

        Answer answer;
        if (company.save())
            answer = Answer.ok(company, "New company perssited.");
        else
            answer = Answer.error("Invalid company params.");

        return answer;
    }
}