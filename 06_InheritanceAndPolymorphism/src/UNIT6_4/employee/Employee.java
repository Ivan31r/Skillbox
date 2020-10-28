package UNIT6_4.employee;

import UNIT6_4.company.Company;

public interface Employee extends Comparable<Employee> {
    public  int getMonthSalary();
    public void setCompany(Company company);
}
