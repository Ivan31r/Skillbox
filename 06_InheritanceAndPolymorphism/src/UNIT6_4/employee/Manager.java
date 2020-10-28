package UNIT6_4.employee;

import UNIT6_4.company.Company;

public class Manager extends CompanyStaff{
    private int profitForCompany;

    public Manager(int salary, Company company){
        super(salary,company);
        profitForCompany = (int) (Math.random() * 200000) + 100000;
    }

    @Override
    public int getMonthSalary() {
        return getSalary() + (int) (profitForCompany * 0.05);
    }

    public int getProfitForCompany() {
        return profitForCompany;
    }

    public String toString(){
        return "Зарплата простого менеджера составила " + getMonthSalary();
    }
}
