import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
public class Main{
    public static void main(String[] args) {
        ArrayList<BankAcc> bankacc = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("\n1.Create Account");
            System.out.println("2.Admin Login");
            System.out.println("3.User Login");
            System.out.println("4.Exit");
            int choice = sc.nextInt();
            if(choice == 1){
                BankAcc ba = new BankAcc();
                System.out.print("Enter UserId: ");
                ba.setUserId(sc.nextInt());
                sc.nextLine();
                System.out.print("Enter Name: ");
                ba.setUserName(sc.nextLine());
                System.out.print("Enter Phone (10 digits): ");
                String phone = sc.nextLine();
                if(phone.length() != 10){
                    System.out.println("Invalid Phone");
                    continue;
                }
                ba.setUserNumber(phone);
                System.out.print("Enter Aadhar (12 digits): ");
                String ad = sc.next();
                if(ad.length() != 12){
                    System.out.println("Invalid Aadhar");
                    continue;
                }
                ba.setAdharNum(Long.parseLong(ad));
                System.out.print("Create Password: ");
                ba.setPassword(sc.next());
                System.out.println("1.Savings  2.Current");
                int type = sc.nextInt();
                if(type == 1){
                    ba.setAccountType("Savings");
                    ba.setBalance(1000);
                }
                else if(type == 2){
                    ba.setAccountType("Current");
                    ba.setBalance(1000); 
                }
                else{
                    System.out.println("Invalid Type");
                    continue;
                }
                long accNo = (long)(Math.random()*1000000000L);
                ba.setAccountNumber(accNo);
                bankacc.add(ba);
                System.out.println("Account Created!");
                System.out.println("Account Number: " + accNo);
            }
            else if(choice == 2){
                System.out.print("Username: ");
                String u = sc.next();
                System.out.print("Password: ");
                String p = sc.next();
                if(u.equals("admin") && p.equals("1234")){
                    while(true){
                        System.out.println("\nADMIN MENU");
                        System.out.println("1.View All Accounts");
                        System.out.println("2.Exit");
                        int ch = sc.nextInt();
                        if(ch == 1){
                            for(BankAcc b : bankacc){
                                System.out.println("----------------");
                                System.out.println(b);
                            }
                        }
                        else if(ch == 2){
                            break;
                        }
                    }
                }
                else{
                    System.out.println("Invalid Admin Login");
                }
            }
            else if(choice == 3){
                System.out.print("Enter Account Number: ");
                long acc = sc.nextLong();
                System.out.print("Enter Password: ");
                String pass = sc.next();
                System.out.println("1.Savings  2.Current");
                int type = sc.nextInt();
                String accType = (type == 1) ? "Savings" : "Current";
                BankAcc currentUser = null;
                for(BankAcc b : bankacc){
                    if(b.getAccountNumber() == acc &&
                            b.getPassword().equals(pass) &&
                            b.getAccountType().equals(accType)){
                        currentUser = b;
                    }
                }
                if(currentUser != null){
                    System.out.println("Login Successful");
                    while(true){
                        System.out.println("\nUSER MENU");
                        System.out.println("1.View Details");
                        System.out.println("2.Deposit");
                        System.out.println("3.Withdraw");
                        System.out.println("4.Transfer");
                        System.out.println("5.Transaction History");
                        System.out.println("6.Add Monthly Interest (Savings)");
                        System.out.println("7.Logout");
                        int ch = sc.nextInt();
                        if(ch == 1){
                            System.out.println(currentUser);
                        }
                        else if(ch == 2){
                            System.out.print("Enter Amount: ");
                            double amt = sc.nextDouble();
                            currentUser.setBalance(currentUser.getBalance() + amt);
                            System.out.println("Deposit Successful");
                        }
                        else if(ch == 3){
                            System.out.print("Enter Amount: ");
                            double amt = sc.nextDouble();
                            if(currentUser.getAccountType().equals("Current")){
                                if((currentUser.getBalance() - amt) < 1000){
                                    System.out.println("Minimum balance must be 1000");
                                    continue;
                                }
                            }
                            currentUser.setBalance(currentUser.getBalance() - amt);
                            System.out.println("Withdraw Successful");
                        }
                        else if(ch == 4){
                            System.out.print("Enter Receiver Account: ");
                            long rec = sc.nextLong();
                            System.out.print("Enter Amount: ");
                            double amt = sc.nextDouble();
                            BankAcc receiver = null;
                            for(BankAcc b : bankacc){
                                if(b.getAccountNumber() == rec){
                                    receiver = b;
                                }
                            }
                            if(receiver != null){
                                if(currentUser.getAccountType().equals("Current")){
                                    if((currentUser.getBalance() - amt) < 1000){
                                        System.out.println("Minimum balance must be 1000");
                                        continue;
                                    }
                                }
                                currentUser.setBalance(currentUser.getBalance() - amt);
                                receiver.setBalance(receiver.getBalance() + amt);
                                String date = LocalDate.now().toString();
                                transactions.add(new Transaction(acc, rec, (int)amt, date));
                                System.out.println("Transaction Successful");
                            }
                            else{
                                System.out.println("Receiver not found");
                            }
                        }
                        else if(ch == 5){
                            if(transactions.size() == 0){
                                System.out.println("No Transactions");
                            }
                            else{
                                System.out.println("1.Last 10");
                                System.out.println("2.All");
                                int opt = sc.nextInt();
                                if(opt == 1){
                                    int start = Math.max(0, transactions.size()-10);
                                    for(int i = start; i < transactions.size(); i++){
                                        System.out.println(transactions.get(i));
                                    }
                                }
                                else{
                                    for(Transaction t : transactions){
                                        System.out.println(t);
                                    }
                                }
                            }
                        }
                        else if(ch == 6){
                            if(currentUser.getAccountType().equals("Savings")){
                                double interest = currentUser.getBalance() * 0.05;
                                currentUser.setBalance(currentUser.getBalance() + interest);
                                System.out.println("Interest Added: " + interest);
                                System.out.println("New Balance: " + currentUser.getBalance());
                            }
                            else{
                                System.out.println("Only Savings Account gets interest");
                            }
                        }
                        else if(ch == 7){
                            break;
                        }
                    }
                }
                else{
                    System.out.println("Invalid Login");
                }
            }
            else if(choice == 4){
                System.out.println("Thank You");
                break;
            }

            else{
                System.out.println("Invalid Choice");
            }
        }
    }
}