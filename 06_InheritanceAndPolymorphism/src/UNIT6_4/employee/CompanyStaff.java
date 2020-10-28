package UNIT6_4.employee;

import UNIT6_4.company.Company;

abstract class CompanyStaff implements Employee {

  private int salary;
  private Company company;


  public CompanyStaff(int salary, Company company) {
    this.salary = salary;
    this.company = company;
  }

  public int getSalary() {
    return salary;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public int compareTo(Employee o) {
    if (getMonthSalary() > o.getMonthSalary()) {
      return 1;
    }
    if (getMonthSalary() < o.getMonthSalary()) {
      return -1;
    }
    return 0;
  }

}

