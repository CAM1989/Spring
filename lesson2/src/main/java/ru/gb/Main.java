package ru.gb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CartService cartService = context.getBean(CartService.class);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command: ");
            String cmd = sc.nextLine().trim().toUpperCase();
            switch (cmd) {
                case "NEW":
                    cartService = context.getBean(CartService.class);
                    System.out.println("Cart created");
                    break;
                case "ADD":
                    System.out.println("Enter ID: ");
                    long id = sc.nextLong();
                    System.out.println("Enter count: ");
                    int count = sc.nextInt();
                    cartService.addProduct(id, count);
                    break;
                case "DEL":
                    System.out.println("Enter ID: ");
                    id = sc.nextLong();
                    System.out.println("Enter count: ");
                    count = sc.nextInt();
                    cartService.removeProduct(id, count);
                    break;
                case "SHOW":
                    cartService.getAll().forEach(System.out::println);
                    break;
                case "EXIT":
                    return;
            }
        }
    }
}
