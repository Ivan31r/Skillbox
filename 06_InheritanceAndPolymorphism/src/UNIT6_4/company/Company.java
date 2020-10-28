package UNIT6_4.company;

import UNIT6_4.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class Company {

  private List<Employee> staff = new ArrayList<>();
  private int income;

  public Company(int income) {
    this.income = income;
  }

  public int getIncome() {
    return income;
  }

  public List<Employee> getStaff() {
    return staff;
  }

  public List<Employee> getTopSalaryStaff(int count) {
    List<Employee> topSalaryStaff = new ArrayList<>();
    if (count <= staff.size()) {
      for (int i = 0; i < count; i++) {
        topSalaryStaff.add(staff.get(staff.size() - 1 - i));
      }
    } else {
      System.out.println("В компании работает меньше, чем " + count + " сотрудников");
    }
    return topSalaryStaff;
  }

  public List<Employee> getLowestSalaryStaff(int count) {
    List<Employee> lowestSalaryStaff = new ArrayList<>();
    if (count <= staff.size()) {
      for (int i = 0; i < count; i++) {
        lowestSalaryStaff.add(staff.get(i));
      }
    } else {
      System.out.println("В компании работает меньше, чем " + count + " сотрудников");
    }
    return lowestSalaryStaff;
  }

  public void hire(Employee employee) {
    staff.add(employee);
  }

  public void hireAll(List<Employee> employees) {
    staff.addAll(employees);
  }

  public void fire(Employee employee) {
    staff.remove(employee);
  }


}
