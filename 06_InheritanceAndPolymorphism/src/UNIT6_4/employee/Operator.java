package UNIT6_4.employee;

import UNIT6_4.company.Company;

public class Operator extends CompanyStaff{

    public Operator(int salary, Company company) {
        super(salary, company);
    }


    @Override
    public int getMonthSalary() {
        return super.getSalary();
    }
    public String toString(){
        return "Зарплата оператора составила " + getMonthSalary();
    }
}
