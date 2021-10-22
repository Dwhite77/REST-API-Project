package com.sparta.dw.northwindrest.utils;

public class CustomerEntityCommand {

    private String contactName;

    private String companyName;

    private String city;

    private String country;

    public String getContactName() {
        return contactName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public static class CustomerEntityCommandBuilder {

        private String contactName;

        private String companyName;

        private String city;

        private String country;

        public CustomerEntityCommandBuilder() {
        }

        public CustomerEntityCommandBuilder contactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public CustomerEntityCommandBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public CustomerEntityCommandBuilder city(String city) {
            this.city = city;
            return this;
        }

        public CustomerEntityCommandBuilder country(String country) {
            this.country = country;
            return this;
        }

        public CustomerEntityCommand build() {
            CustomerEntityCommand account = new CustomerEntityCommand();
            account.contactName = this.contactName;
            account.companyName = this.companyName;
            account.city = this.city;
            account.country = this.country;
            return account;
        }
    }

    public CustomerEntityCommand exampleOfHowToInvokeBuilder() {
        return new CustomerEntityCommandBuilder().city("London")
                .companyName("Sparta")
                .contactName("Dan")
                .country("GBP")
                .build();
    }
}