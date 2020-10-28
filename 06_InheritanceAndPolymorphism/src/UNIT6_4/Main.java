package UNIT6_4;

import UNIT6_4.company.Company;
import UNIT6_4.employee.Developer;
import UNIT6_4.employee.Employee;
import UNIT6_4.employee.Operator;
import UNIT6_4.employee.TopManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company sony = new Company(100_000_000);
        List<Employee> managers = new ArrayList<>();
//        for(int i=0;i<180;i++){
//            managers.add(new Manager(50000,sony));
//        }
        List<Employee> operators = new ArrayList<>();
        for (int i =0;i<80;i++){
            operators.add(new Operator(60000,sony));
        }
        List<Employee> topManagers = new ArrayList<>();
        for (int i=0;i<10;i++){
            topManagers.add(new TopManager(80000,sony));
        }
        sony.hireAll(managers);
        sony.hireAll(operators);
        sony.hireAll(topManagers);

        System.out.println("В компании " + sony.getStaff().size() + " сотрудников");

        Collections.sort(sony.getStaff());


        List<Employee> top = sony.getTopSalaryStaff(15);
        for (Employee employee : top) {
            System.out.println(employee);
        }
        System.out.println("\n");

        System.out.println("Вот тут уже самые маленькие ЗП\n");

        List<Employee> lowest = sony.getLowestSalaryStaff(20);
        for (Employee employee : lowest) {
            System.out.println(employee);
        }

        System.out.println("Теперь тест Developer'ов\n");
        int managersCountMultiply1000 = managers.size()*1000;
        Developer developer = new Developer(managersCountMultiply1000,sony);
        System.out.println(developer.getSalary());
        System.out.println(developer);



    }
}
// у менеджеров фиксированная ставка + 5% от заработанной для компании денег
//у топменеджеров фиксированная + премия (150 % от ЗП), если компания заработала более 10 млн руб
//у операторов фиксированная ставка