package me.pedrohenriquerls.handlers;

import me.pedrohenriquerls.models.Company;

import java.util.Map;
import java.util.UUID;

public class GetCompanyDetailHandler  extends AbstractRequestHandler {

    @Override
    protected Answer process(Map<String, String> params) {
        UUID companyUuid = UUID.fromString(params.get(":uuid"));
        Company company = Company.getCompanyOn(companyUuid);

        return new Answer(company);
    }
}
