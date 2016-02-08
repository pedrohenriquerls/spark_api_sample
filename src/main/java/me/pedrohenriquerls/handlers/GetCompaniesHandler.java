package me.pedrohenriquerls.handlers;

import me.pedrohenriquerls.models.Company;

import java.util.Map;

public class GetCompaniesHandler  extends AbstractRequestHandler {

    @Override
    protected Answer process(Map<String, String> params) {
        return new Answer(Company.getAll());
    }
}