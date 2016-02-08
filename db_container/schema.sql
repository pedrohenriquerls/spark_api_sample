CREATE TABLE companies (
    company_uuid uuid primary key,
    name text not null,
    address text not null,
    city text not null,
    country text not null,
    email text,
    phone_number text
);

CREATE TABLE employees (
    employee_uuid uuid primary key,
    company_uuid uuid references companies(company_uuid),
    name text not null
);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO sample_owner;
