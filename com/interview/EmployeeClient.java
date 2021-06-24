package com.interview;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeClient {

    public static void main(String[] args) {

        List<Employee> list = getEmployees();

        //How many male and female employees are there in the organization?
        final Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(collect);

        //Print the name of all departments in the organization?
        list.stream().map(Employee::getDepartment).distinct().sorted().forEach(dept -> System.out.println(dept));

        //What is the average age of male and female employees?
        final Map<String, Double> avgAgeCollection = list.stream().
                collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgAgeCollection);

        //Get the details of highest paid employee in the organization?
        final Optional<Employee> max = list.stream().max(Comparator.comparing(Employee::getSalary));
        System.out.println(max.get());

        //Get the names of all employees who have joined after 2015?
        list.stream().filter(employee -> employee.getYearOfJoining() > 2015)
                .map(Employee::getName)
                .forEach(System.out::println);

        //Count the number of employees in each department?
        final Map<String, Long> deptEmpCount = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(deptEmpCount);

        //What is the average salary of each department?
        final Map<String, Double> avgSalDept = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalDept);

        //Get the details of youngest male employee in the product development department?
        final Optional<Employee> employee = list.stream()
                .filter(emp -> "Product Development".equals(emp.getDepartment()) && "Male".equals(emp.getGender()))
                .min(Comparator.comparing(Employee::getAge));
        employee.ifPresent(System.out::println);

        //Who has the most working experience in the organization?
        final Optional<Employee> maxOrg = list.stream().min(Comparator.comparing(Employee::getYearOfJoining));
        maxOrg.ifPresent(System.out::println);

        //How many male and female employees are there in the sales and marketing team?
        final Map<String, Long> saleEmpList = list.stream().filter(emp -> "Sales And Marketing".equals(emp.getDepartment()))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(saleEmpList);

        //What is the average salary of male and female employees?
        final Map<String, Double> avgSalList = list.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalList);

        //List down the names of all employees in each department?
        final Map<String, List<Employee>> allDeptNames = list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        allDeptNames.forEach((k, v) -> {
            System.out.println("\n"+"department name "+k);
            v.forEach(emplist -> System.out.println(emplist.getName()));
        });

        //What is the average salary and total salary of the whole organization?
        final OptionalDouble avgSal = list.stream().mapToDouble(Employee::getSalary).average();
        avgSal.ifPresent(System.out::println);

        final double totalSal = list.stream().mapToDouble(Employee::getSalary).sum();
        System.out.println(totalSal);

        //Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        final List<Employee> list25 = list.stream().filter(emp -> emp.getAge() <= 25).collect(Collectors.toList());
        System.out.println(list25);

        //Who is the oldest employee in the organization? What is his age and which department he belongs to?
        list.stream().max(Comparator.comparing(Employee::getAge)).ifPresent(System.out::println);

        //find top three employee details based on highest salary?
        list.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3).forEach(System.out::println);


    }

    static List<Employee> getEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        return employeeList;
    }
}
