package org;
import java.io.*;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class  Bank implements BankingServices
{
    private String name ;
    private String address ;
    private String phone ;
    public int balance ;
    private String  bankName;
    //Connection dbconnection;
    public Scanner scanner = new Scanner(System.in);

    // all operation bank can provide to the customer.
    public  void operations (int option) throws IOException
    {
        switch (option)
        {
            case 1 :
                createAccount();
                break;
            case 2 :
                deleteCustomer();
                break;
            case 3 :
                checkIfCustomerExists();
                break;
            case 4 :
                updateCustomerInformation();
                break;
            case 5 :
                withdraw();
                break;
            case 6 :
                checkBalance();
                break;
            case 7 :
                deposit();
                break;
            case 8 :
                System.exit(0);
                break;
            default:
                System.out.println("Sorry your select is not listed ");
        }
    }
    public void createAccount() throws IOException
    {
        try {
            // String name , String address , String phone,int balance
            System.out.println("Enter customer id   ? ");
            String id = scanner.next();
            System.out.println("Enter customer name  ? ");
            String name = scanner.next();
            System.out.println("Enter customer address  ? ");
            String address = scanner.next();
            System.out.println("Enter customer phone  ? ");
            String phone = scanner.next();
            System.out.println("Enter customer balance  ? ");
            int balance = scanner.nextInt();
            BufferedWriter myWriter = new BufferedWriter(new FileWriter("CreateAccount.txt",true));
            myWriter.write("\n"+id+" "+name+" "+address+" "+phone+" "+balance);
            myWriter.close();

        }catch(InputMismatchException e )
        {
            System.out.println("Sorry input mismatch !");
        }
    }
    @Override
    public void checkBalance() {
        try {
            System.out.println("Enter customer id :");
            String id = scanner.next();
            BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
            String st;
            while ((st = br.readLine()) != null)
            {
                String[] arr = st.split(" ");
                if(arr[0].equals(id))
                {
                    System.out.println("Available Balance is :"+arr[4]);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Sorry Input error ");
        }

    }

    @Override
    public void withdraw()
    {
        try {
            // int id
            System.out.println("Enter customer id : ");
            String id = scanner.nextLine();
            System.out.println("Enter the amount to withdraw: ");
            int amnt= scanner.nextInt();
            File obj1= new File("Withdraw.txt");
            FileWriter myWriter1= new FileWriter(obj1);
            BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
            String st;
            int balance=0;
            String[] arr = new String[5];
            String[] arr1 = new String[5];
            while ((st = br.readLine()) != null)
            {
                arr = st.split(" ");
                if(arr[0].equals(id))
                {
                    arr1=arr;
                    balance = Integer.parseInt(arr[4]);
                    while(amnt>balance)
                    {
                        System.out.println("Enter the amount to withdraw(OOPS!!! It is greater than the balance amount!!!) : ");
                        amnt = scanner.nextInt();
                    }
                    balance=balance-amnt;
                    System.out.println("Amount after withdraw: "+balance);
                }
                else
                {
                    myWriter1.write(st+"\n");
                }
            }
            myWriter1.write(arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+arr1[3]+" "+balance);
            myWriter1.close();
            br.close();
            File obj= new File("CreateAccount.txt");
            FileWriter my = new FileWriter(obj);
            BufferedReader read = new BufferedReader(new FileReader("Withdraw.txt"));
            while((st=read.readLine())!= null)
            {
                my.write(st+"\n");
            }
            my.close();
            read.close();
        } catch (Exception e) {
            System.out.println("Sorry Input error ");
        }
    }

    @Override
    public void checkIfCustomerExists()
    {
        System.out.println("Enter customer id :");
        String id = scanner.nextLine();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
            String st;
            int flag=0;
            while ((st = br.readLine()) != null)
            {
                String[] arr = st.split(" ");
                if(arr[0].equals(id))
                {
                    flag=1;
                }
            }
            if(flag==0)
            {
                System.out.println("CUSTOMER DOES NOT EXIST!!!");
            }else if(flag==1)
            {
                System.out.println("CUSTOMER EXIST!!!");
            }
            br.close();
        }catch (Exception e)
        {
            e.getMessage();
        }
    }

    @Override
    public void deposit()
    {
        try
        {
            String dum= scanner.nextLine();
            System.out.println("Enter customer id : ");
            String id = scanner.nextLine();
            System.out.println("Enter the amount to deposit: ");
            int amnt= scanner.nextInt();
            File obj1= new File("Deposit.txt");
            FileWriter myWriter1= new FileWriter(obj1);
            BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
            String st;
            String[] arr = new String[5];
            String[] arr1 = new String[5];
            while ((st = br.readLine()) != null)
            {
                arr = st.split(" ");
                if(arr[0].equals(id))
                {
                    arr1=arr;
                    int balance = Integer.parseInt(arr[4]);
                    balance=balance+amnt;
                    System.out.println("Amount after deposit: "+balance);
                }
                else
                {
                    myWriter1.write(st+"\n");
                }
            }
            myWriter1.write(arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+arr1[3]+" "+balance+"\n");
            myWriter1.close();
            br.close();
            File obj= new File("CreateAccount.txt");
            FileWriter my = new FileWriter(obj);
            BufferedReader read = new BufferedReader(new FileReader("Deposit.txt"));
            while((st=read.readLine())!= null)
            {
                my.write(st+"\n");
            }
            my.close();
            read.close();
        } catch (Exception e) {
            System.out.println("Sorry Input error ");
        }
    }

    @Override
    public void deleteCustomer()
    {
        try
        {
            String dum=scanner.nextLine();
            System.out.println("Enter customer id to be deleted : ");
            String id = scanner.nextLine();
            File obj1= new File("DeleteCust.txt");
            FileWriter myWriter1= new FileWriter(obj1);
            BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
            String st;
            String[] arr = new String[5];
            while ((st = br.readLine()) != null)
            {
                arr = st.split(" ");
                if(arr[0].equals(id))
                {
                    continue;
                }
                else
                {
                    myWriter1.write(st+"\n");
                }
            }
            myWriter1.close();
            br.close();
            File obj= new File("CreateAccount.txt");
            FileWriter my = new FileWriter(obj);
            BufferedReader read = new BufferedReader(new FileReader("DeleteCust.txt"));
            while((st=read.readLine())!= null)
            {
                my.write(st+"\n");
            }
            my.close();
            read.close();
        } catch (Exception e) {
            System.out.println("Sorry Input error ");
        }

    }

    @Override
    public void updateCustomerInformation()
    {
        System.out.println(" ENTER THE OPTION TO BE UPDATED: ");
        System.out.println(" 1 - Update Customer Name ");
        System.out.println(" 2 - Update Customer Address.");
        System.out.println(" 3 - Update Customer Phone.");
        System.out.println(" 4 - Exit.");
        System.out.println(" Your select  : ");
        int option= scanner.nextInt();
        String dum=scanner.nextLine();
        if(option==1)
        {
            System.out.println("Enter the customer name to be updated: ");
            String name= scanner.nextLine();
            try
            {
                System.out.println("Enter customer id : ");
                String id = scanner.nextLine();
                File obj1= new File("Update.txt");
                FileWriter myWriter1= new FileWriter(obj1);
                BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
                String st;
                String[] arr = new String[5];
                String[] arr1 = new String[5];
                while ((st = br.readLine()) != null)
                {
                    arr = st.split(" ");
                    if(arr[0].equals(id))
                    {
                        arr1=arr;
                        arr1[1]=name;
                    }
                    else
                    {
                        myWriter1.write(st+"\n");
                    }
                }
                myWriter1.write(arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+arr1[3]+" "+arr1[4]+"\n");
                br.close();
                myWriter1.close();
                br.close();
                File obj= new File("CreateAccount.txt");
                FileWriter my = new FileWriter(obj);
                BufferedReader read = new BufferedReader(new FileReader("Update.txt"));
                while((st=read.readLine())!= null)
                {
                    my.write(st+"\n");
                }
                my.close();
                read.close();
            } catch (Exception e) {
                System.out.println("Sorry Input error ");
            }
        }
        else if(option==2)
        {
            System.out.println("Enter the customer Address to be updated: ");
            String address= scanner.nextLine();
            try
            {
                System.out.println("Enter customer id : ");
                String id = scanner.nextLine();
                File obj1= new File("Update.txt");
                FileWriter myWriter1= new FileWriter(obj1);
                BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
                String st;
                String[] arr = new String[5];
                String[] arr1 = new String[5];
                while ((st = br.readLine()) != null)
                {
                    arr = st.split(" ");
                    if(arr[0].equals(id))
                    {
                        arr1=arr;
                        arr1[2]=address;
                    }
                    else
                    {
                        myWriter1.write(st+"\n");
                    }
                }
                myWriter1.write(arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+arr1[3]+" "+arr1[4]+"\n");
                br.close();
                myWriter1.close();
                br.close();
                File obj= new File("CreateAccount.txt");
                FileWriter my = new FileWriter(obj);
                BufferedReader read = new BufferedReader(new FileReader("Update.txt"));
                while((st=read.readLine())!= null)
                {
                    my.write(st+"\n");
                }
                my.close();
                read.close();
            } catch (Exception e) {
                System.out.println("Sorry Input error ");
            }
        }
        else if(option==3)
        {
            System.out.println("Enter the customer phone number to be updated: ");
            String phoneNo= scanner.nextLine();
            try
            {
                System.out.println("Enter customer id : ");
                String id = scanner.nextLine();
                File obj1= new File("Update.txt");
                FileWriter myWriter1= new FileWriter(obj1);
                BufferedReader br = new BufferedReader(new FileReader("CreateAccount.txt"));
                String st;
                String[] arr = new String[5];
                String[] arr1 = new String[5];
                while ((st = br.readLine()) != null)
                {
                    arr = st.split(" ");
                    if(arr[0].equals(id))
                    {
                        arr1=arr;
                        arr1[3]=phoneNo;
                    }
                    else
                    {
                        myWriter1.write(st+"\n");
                    }
                }
                myWriter1.write(arr1[0]+" "+arr1[1]+" "+arr1[2]+" "+arr1[3]+" "+arr1[4]+"\n");
                br.close();
                myWriter1.close();
                br.close();
                File obj= new File("CreateAccount.txt");
                FileWriter my = new FileWriter(obj);
                BufferedReader read = new BufferedReader(new FileReader("Update.txt"));
                while((st=read.readLine())!= null)
                {
                    my.write(st+"\n");
                }
                my.close();
                read.close();
            } catch (Exception e) {
                System.out.println("Sorry Input error ");
            }
        }
        else if(option==4)
        {
            return;
        }
    }
}