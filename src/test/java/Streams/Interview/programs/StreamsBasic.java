package Streams.Interview.programs;

import Streams.Interview.Model.Employee;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsBasic {

    @Test
    public void nani() {
        List<Integer> list = Arrays.asList(10, 15, 8, 49, 25, 98, 32, 15, 8);
        // only even Numbers or Odd numbers
        list.stream().filter(e -> e % 2 == 0).forEach(System.out::println);
        // or
        System.out.println("----------");
        list.stream().filter(e -> e % 2 != 0).forEach(System.out::println);
        System.out.println("----------");

        // if you want to get Both Even odd Numbers as map
        Map<String, List<Integer>> ab = list.stream().collect(Collectors.groupingBy(e -> e % 2 == 0 ? "even" : "odd"));
        System.out.println(ab.toString());
        System.out.println("----------");

        // Starting with ending with
        list.stream().map(e -> e + "").filter(e -> e.startsWith("1")).forEach(System.out::println);

        Set<Integer> set = new HashSet<>();
        System.out.printf("------------");

        list.stream().filter(set::add).forEach(System.out::println);

        // finding the Duplicates without using Set
        // 1 : Create a MAP with the value and The Counting ... then get the values Which Count >1
        List<Integer> b = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey).toList();

    }

    @Test
    public void ArraysStreamTesting() {
        int[] a = {4, 1, 6, 2, 9, 0, 1, 5, 7, 2};
        List<Integer> list = Arrays.asList(10, 15, 8, 49, 25, 98, 32, 15, 8);


        // Sorting Ascending And printing Array and List
        Arrays.stream(a).sorted().forEach(System.out::println);
        list.stream().sorted().forEach(System.out::println);

        // Sorting in Des and printing Array and List
        Arrays.stream(a).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);
        list.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);

        // sorting the Array and retrun  Int[]
        int[] b = Arrays.stream(a).sorted().toArray();
        int[] c = Arrays.stream(a).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

        // sorting an array and return List<Integer>
        List<Integer> l1 = Arrays.stream(a).sorted().boxed().toList();
        List<Integer> l2 = Arrays.stream(a).boxed().sorted(Collections.reverseOrder()).toList();

        // sorting List and return int[].
        int[] d = list.stream().sorted().mapToInt(Integer::intValue).toArray();
        int[] f = list.stream().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue).toArray();

    }


    @Test
    public void inbuilt() {
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);
        int[] numbers = {10, 20, 30, 40, 50};
        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");


        // System.out.println(""+ myList.stream().count());
        //System.out.println(""+ myList.stream().max(Integer::compareTo).get());
        // first non repeated Charecter in String
        String input = "Java articles are Awesome";
        //input = input.toLowerCase().replaceAll(" ","");

        Character a = input.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(e -> e.getValue() == 1l)
                .map(Map.Entry::getKey)
                .skip(1)
                .findFirst().get();

        Map<String, Long> ab = names.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream().filter(e -> e.getValue() > 1L)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // System.out.println(" "+ a);

        // myList.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        // int[] c =  Arrays.stream(numbers).boxed().sorted(Comparator.reverseOrder()).mapToInt(e-> e).toArray();

    }

    @Test
    public void userDefinedClassesStreamFunctions() {
        List<Employee> employeeList = Employee.getEmployeeList();

        // male and female by map
        employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()))
                .entrySet().forEach(System.out::println);

        // get how many departments in company
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        // get the average Age of female and male employees
        employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)))
                .entrySet().forEach(System.out::println);

        // how many employees in each department
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
                .entrySet().forEach(System.out::println);

        // average salary of Each department
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)))
                .entrySet().forEach(System.out::println);

        // max age person
        System.out.println(employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get());

        // max age person in male / female
        Employee a = employeeList.stream().filter(e -> e.getGender().equalsIgnoreCase("female"))
                .max(Comparator.comparingInt(Employee::getAge)).get();

        // max or min Age perso in male and female Employees

        Map<String, Optional<Employee>> mp = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy(Comparator.comparingInt(Employee::getAge))));


        //average and Total Saalry

        DoubleSummaryStatistics ac = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println(ac);

        // getting map<DepartmentName,List<Employees>>
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)).entrySet().forEach(System.out::println);

        // now map<Department,list<names>>
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())))
                .entrySet().forEach(System.out::println);

        // if we want multiple values of employees in List<String>

        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(e -> e.getName() + "-" + e.getAge() + "-" + e.getSalary(), Collectors.toList())))
                .entrySet().forEach(System.out::println);

        // SOrting the eployee Lisr by Age or nay Other itemss.
        employeeList.stream().sorted(Comparator.comparingInt(Employee::getAge)).toList();

        // in reverse Order
        employeeList.stream().sorted(Comparator.comparingInt(Employee::getId).reversed()).toList();
// **** very Imp ***
        //Given a list of integers, return a map where the key is the integer, and the value is a boolean indicating
        // whether the number is even or odd.{1=false, 2=true, 3=false, 4=true, 5=false, 6=true}
//        Map<Integer, Boolean> result = input.stream()
//                .collect(Collectors.toMap(
//                        num -> num,  // Key: the integer itself
//                        num -> num % 2 == 0  // Value: true if even, false if odd
//                ));


    }

    @Test
    public void userDefinedObjectListApplyingConditionOnGrouppedList() {

        List<Employee> list = Employee.getEmployeeList();

        // I want to group by Geneder, -> and get max age person Employee Object on each.

        Map<String, Employee> emp = list.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Employee::getAge)),
                        Optional::get
                )));

        System.out.println(emp);

        // grouing by Somehing and get and apply logic to groupped elemnet and get any Specific value of that Object.
        // get the Name of the max age person in male and female Employees.

        Map<String, String> emp2 = list.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Employee::getAge)),
                        e -> e.map(Employee::getName).orElse(" ")
                )));
        System.out.println(emp2);


        // we can also Do this...
        Map<String, Optional<Employee>> emp3 = list.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.maxBy(Comparator.comparingInt(Employee::getAge))));
        // you can convert This Optional Employee to Employee
        Map<String, Employee> emp4 = emp3.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e1 -> e1.getValue().get()));
        System.out.println(emp4);

        // groupping by something and mapping required values into a List.
        Map<String, List<String>> ab = list.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(e -> e.getName() + " " + e.getAge(), Collectors.toList())));


        //Given a list of strings, create a map where the key is the first letter of each string (case-insensitive), and the value is a list of all strings that start with that letter.
        List<String> input = Arrays.asList("Apple", "Apricot", "Banana", "Avocado", "Blueberry");

        input.stream().collect(Collectors.groupingBy(e -> e.substring(0, 1).toLowerCase(), LinkedHashMap::new, Collectors.toList()))
                .entrySet().forEach(System.out::println);

        // comparing the list of employeeing by age then compare by name or salary.
        List<Employee> b = list.stream().sorted(Comparator.comparingInt(Employee::getAge)
                .thenComparing(Employee::getName)).toList();

        // flatMap use.
        List<Employee> list1 = Employee.getEmployeeList();
        System.out.println(list1.size());

        List<Employee> list2 = Employee.getEmployeeList();

        List<List<Employee>> listList = Arrays.asList(list1, list2);

        List<Employee> b1 = listList.stream().flatMap(Collection::parallelStream).toList();
        System.out.println(b1.size());


    }


    /**
     * Method references : method references are special type of lamda expressions, which are used to
     * Create a simple lamda expressions by referencing existing methods...
     * <p>
     * There are 4 types of method references
     * <p>
     * 1 : static method references
     * 2 : instance Method of particular object.
     * 3 : instance methods of Arbitary object of particular type references
     * 4 : constructor
     */
    @Test
    public void methodReferences() {
        List<Integer> myList = Arrays.asList(10, 15, 8, 49, 25, 98, 98, 32, 15);

        // converting this integer list to List list by using String class -> valueof method
        // the value of method in string is Static so we can use the  static method referance here.
        List<String> StringList = myList.stream().map(String::valueOf).toList();

        // Instance method of a pArticular Object type...

        List<Employee> em = Employee.getEmployeeList();
        List<String> nameOFAllEMployees = em.stream().map(Employee::getName).toList();
        // em.stream().sorted(Comparator.comparingInt(Employee::getAge).reversed()).forEach(System.out::println);

        // instance method of a Arbitory Object of a partcular type.
        myList.stream().sorted();

        StringList.stream().map(message -> new RuntimeException(message)).forEach(System.out::println);
    }


    @Test
    public void basicJavaQuestions() {
        // check vowles are present or not using matcheers

        String name = "pradeep";
        System.out.println(name.toLowerCase().matches(".*[aeiou].*"));
        for (int i = 0; i < 10; i++) {
            System.out.println(febWithREcurression(i));
        }


    }


    public void febanacci(int count) {
        int a = 0;
        int b = 1;
        int c = 1;

        for (int i = 0; i <= count; i++) {
            System.out.print(a + ",");
            a = b;
            b = c;
            c = a + b;
        }
    }

    public int febWithREcurression(int count) {
        if (count <= 1) {
            return count;
        }
        return febWithREcurression(count - 1) + febWithREcurression(count - 2);
    }

    @Test
    public void factorial() {
        int a = 5;
        int count = 1;
        for (int i = a; i >= 1; i--) {
            count = count * i;
        }

        System.out.println(count);
    }


}

