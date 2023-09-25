package com.systechafrica.pos;

import java.util.Scanner;

import com.systechafrica.loginhelper.LoginHelper;

public class PosReviewed {
    private Scanner posScanner = new Scanner(System.in);
    String suppliedPwd;
    boolean isLoggedIn;

    Item itemObj = new Item();

    private int userItemCode[] = new int[itemObj.mem];
    private int userItemPrice[] = new int[itemObj.mem];
    private int userItemQuantity[] = new int[itemObj.mem];

    boolean login() {
        LoginHelper login = new LoginHelper();
        int attepmts = 3;
        while (attepmts != 0) {
            System.out.println("Enter Password: ");
            suppliedPwd = posScanner.nextLine();
            if (login.checkUserCredentials(1, suppliedPwd)) {
                System.out.println("Login succesfull!!");
                isLoggedIn = true;
                attepmts = 0;
            } else {
                attepmts--;
                System.out.println("INVALID CREDENTIALS ,PLEASE TRY AGAIN!!! ");
                System.out.println(attepmts + " attempts remaining!!\n");
                isLoggedIn = false;
                continue;
            }
        }
        return isLoggedIn;
    }

    void displayMenu() {

        System.out.println("------------------------------------------");
        System.out.println("         Systech POS System");
        System.out.println("------------------------------------------");
        System.out.println("1.ADD ITEM\n");
        System.out.println("2.MAKE PAYMENT\n");
        System.out.println("3.DISPLAY RECEIPT\n");
        System.out.println("4.Quit\n");
        System.out.println("------------------------------------------");
        System.out.print("choose your option : ");

        int choice = posScanner.nextInt();
        switch (choice) {
            case 1:
                addItem();
                break;
            case 2:
                makePayment();
                break;
            case 3:
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid option!!,please try again!!");
                displayMenu();
        }
    }

    private void addItem() {
        for (int i = 0; i < itemObj.mem; i++) {
            System.out.print("Enter item code: ");
            userItemCode[i] = posScanner.nextInt();

            System.out.print("Enter item price: ");
            userItemPrice[i] = posScanner.nextInt();

            System.out.print("Enter item quantity: ");
            userItemQuantity[i] = posScanner.nextInt();
            posScanner.nextLine();
            System.out.println("Press 'A' to add more items 'N' to g back to main Menu");
            String choice = posScanner.nextLine().toUpperCase();
            switch (choice) {
                case "A":
                    break;
                case "N":
                    i = itemObj.mem;
                    displayMenu();
                    break;
                default:
                    System.out.println("Invalid option !!");
                    i = itemObj.mem;
                    displayMenu();
                    break;
            }
        }
    }

    private void makePayment() {
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Item code", "Quantity", "Unit Price", "Total Value");
        double finalTotals=0;
        double totalPrice;
        double customerAmount;

        for (int i = 0; i < itemObj.mem; i++) {
            if(userItemCode[i]!=0){
                totalPrice = userItemQuantity[i] * userItemPrice[i];
            System.out.printf("%-15s%-15s%-15s%-15s\n", userItemCode[i], userItemQuantity[i], userItemPrice[i], totalPrice);

            finalTotals += totalPrice;
            }
            

        }
        System.out.println("*****************************************************");
        System.out.println("TOTAL   - " + finalTotals + " ksh");
        System.out.println("******************************************");

        System.out.print("Enter amount by customer: ");
        customerAmount = posScanner.nextInt();

        if (customerAmount >= finalTotals) {
            System.out.println("Change  - " + (customerAmount - finalTotals) + " ksh");
            System.out.println("******************************************");
            System.out.println("THANK YOU FOR SHOPPING WITH US");
            System.out.println("******************************************");
        } else {
            System.out.println("cannot process payment insuficient funds!!!");
        }
    }

    public static void main(String[] args) {
        PosReviewed pos = new PosReviewed();
        pos.login();
        if (pos.isLoggedIn) {
            pos.displayMenu();
        } else {
            System.out.println("Fatal error!!");
        }

    }
}