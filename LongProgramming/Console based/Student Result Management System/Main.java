import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean IDfound = false;
        Set<Student> students = new HashSet<>();
        students.add(new Student("02-02-2006", "cse", "abi", 1));
        students.add(new Student("02-02-2006", "cse", "barath", 2));
        students.add(new Student("02-02-2006", "cse", "pradeep", 4));
        students.add(new Student("02-02-2006", "cse", "kirubha", 3));
        students.add(new Student("02-02-2007", "cse", "mani", 5));


        while (true) {
            System.out.println("\n1).enter marks\n2).show marks\n3).Exit");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Enter Student ID");
                int studID = sc.nextInt();
                for(Student student : students) {
                if(studID==student.getId()) {
                    IDfound = true;
                    System.out.print("tamil Mark: ");
                    int tamil = sc.nextInt();
                    System.out.print("eng Mark: ");
                    int eng = sc.nextInt();
                    System.out.print("maths Mark: ");
                    int maths = sc.nextInt();
                    System.out.print("social Mark: ");
                    int social = sc.nextInt();
                    System.out.print("science Mark: ");
                    int science = sc.nextInt();
                    StudentPerformance sp = new StudentPerformance(tamil, eng, maths, social, science);
                    student.setSp(sp);

                }
                }
                if(!IDfound) {
                   System.out.println("Invalid ID");
                }
            }
            else if (choice == 2) {
                   System.out.println("Enter Student ID: ");
                   int studID = sc.nextInt();
                for(Student student : students) {
                    if (studID == student.getId()) {
                        student.getSp().showMarks();
                    }
                }

            }else if (choice == 3) {
                System.out.println("Thank you for using this program");
                break;
            }else{
                System.out.println("Invalid choice");
            }
        }
    }
}
