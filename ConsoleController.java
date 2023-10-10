package org;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleController {

    public void showMenu() throws IOException
    {
        int opt;
        Bank bk= new Bank();
        do {
            System.out.println(" ------------------------ ");
            System.out.println("  Welcome to " + " INDIAN " + " Bank" );
            System.out.println(" ------------------------ ");
            System.out.println(" Select from the menu :  ");
            System.out.println(" 1 - Create new account.");
            System.out.println(" 2 - Delete Customer.");
            System.out.println(" 3 - Check If Customer Exists.");
            System.out.println(" 4 - Update Customer Information ");
            System.out.println(" 5 - Withdraw  ");
            System.out.println(" 6 - Check Customer Balance ");
            System.out.println(" 7 - Deposit");
            System.out.println(" 8 - Exit");
            System.out.println(" Your select  : ");
            Scanner sc = new Scanner(System.in);
            opt= sc.nextInt();
            bk.operations(opt);
        }while(opt!=8);

    }
}