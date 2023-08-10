package aplication;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner scan = new Scanner(System.in);
        List<Employee> list = new ArrayList<Employee>();

        System.out.print("Enter full file path: ");
        String path = scan.nextLine();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

            String line = bf.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = bf.readLine();
            }

            System.out.println("");
            System.out.print("Enter salary: ");
            double salary = scan.nextDouble();

            List<String> emails = list.stream().filter(e -> e.getSalary() > salary).map(e -> e.getEmail()).sorted().collect(Collectors.toList());

            System.out.println("");
            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
            emails.stream().forEach(System.out::println);

            double sum = list.stream().filter(e -> e.getName().toUpperCase().startsWith("M")).map(e -> e.getSalary()).reduce(0.0, (a, b) -> a + b);

            System.out.println("");
            System.out.println("Sum of salary from people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}