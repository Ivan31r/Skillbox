package UNIT6_4.employee;

import UNIT6_4.company.Company;

public class TopManager extends CompanyStaff {

  private static int INCOME_FOR_BONUS = 10_000_000;

  public TopManager(int salary, Company company) {
    super(salary, company);
  }


  @Override
  public int getMonthSalary() {
    if (getCompany().getIncome() > INCOME_FOR_BONUS) {
      return getSalary() + (getSalary() * 3);
    } else {
      return getSalary();
    }
  }

  public String toString() {
    return "Зарплата топМенеджера составила " + getMonthSalary();
  }
}
