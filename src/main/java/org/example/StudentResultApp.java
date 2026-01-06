package org.example;

import java.util.Scanner;

public class StudentResultApp {

    public static void main(String[] args)
            throws Exception {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n--- Student Result System ---");
            System.out.println("1. Add Result");
            System.out.println("2. View All");
            System.out.println("3. Search by Roll");
            System.out.println("4. Update Marks");
            System.out.println("5. Delete Result");
            System.out.println("6. Exit");

            System.out.print("Choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    dao.addStudent();
                    break;
                case 2:
                    dao.viewAll();
                    break;
                case 3:
                    System.out.print("Enter Roll: ");
                    dao.searchByRoll(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Roll: ");
                    int r = sc.nextInt();
                    System.out.print("New Marks: ");
                    dao.updateMarks(r, sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter Roll: ");
                    dao.deleteResult(sc.nextInt());
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
}

