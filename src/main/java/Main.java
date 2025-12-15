import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class WrongStudentName extends Exception { }
class WrongDateOfBirth extends Exception { }
class WrongAge extends Exception { }

class Main {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    case 0: return;
                    default:
                        System.out.println("Błędny wybór menu Zakres: 0–3");
                }
            } catch(InputMismatchException e) {
                System.out.println("Błędny format: litera zamiast cyfry!");
                scan.nextLine();
            } catch(IOException e) {

            } catch(WrongStudentName e) {
                System.out.println("Błędne imię studenta!");
            } catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia! Poprawny format: DD-MM-YYYY");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek! Dozwolony zakres: 0–99 lat");
            }
        }
    }

    public static int menu() {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich studentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        return scan.nextInt();
    }

    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        String name = scan.nextLine();

        if(name.contains(" "))
            throw new WrongStudentName();

        return name;
    }

    public static String ReadDateOfBirth() throws WrongDateOfBirth {
        System.out.println("Podaj datę urodzenia DD-MM-YYYY:");
        String date = scan.nextLine();

        if(!date.matches("\\d{2}-\\d{2}-\\d{4}"))
            throw new WrongDateOfBirth();

        return date;
    }

    public static void exercise1()
            throws IOException, WrongStudentName, WrongDateOfBirth, WrongAge {

        var name = ReadName();

        System.out.println("Podaj wiek: ");
        var age = scan.nextInt();
        scan.nextLine();

        if(age < 0 || age > 99)
            throw new WrongAge();

        var date = ReadDateOfBirth();

        (new Service()).addStudent(new Student(name, age, date));
    }

    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }

    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imię: ");
        var name = scan.nextLine();

        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znaleziono...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}
