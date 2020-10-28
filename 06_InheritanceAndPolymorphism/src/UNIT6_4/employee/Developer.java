package UNIT6_4.employee;

import UNIT6_4.company.Company;

public class Developer extends CompanyStaff {

  public Developer(int salary, Company company) {
    super(salary, company);
  }

  @Override
  public int getMonthSalary() {
    return getSalary();
  }

  @Override
  public String toString() {
    return "ЗП Developer'a составляет " + getMonthSalary();
  }
}
