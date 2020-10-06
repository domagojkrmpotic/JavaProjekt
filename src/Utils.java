import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class Utils {
    Scanner scanner = new Scanner(System.in);
    public void upisStudenta(Student student){


        File file = new File("student.txt");
        Format format = new SimpleDateFormat("dd.MM.yyyy.");
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(file,true));
            bf.write(student.maticniBroj+"|"+student.ime+"|"+student.prezime+"|"+format.format(student.datumRodenja)+"|"+ format.format(student.godinaUpisa)+student.email+"|"+student.brojTelefona);
            bf.newLine();
            bf.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }



    }

    public void ispisStudenta() {

        try {
            File file = new File("student.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine());
            }

            sc.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Nije pronaden file");
        }





    }

    public void izbrisiStudenta(String maticni){
        try {
            Integer.parseInt(maticni);
        }
        catch (Exception ex){
            System.out.println("Niste unijeli broj!");
        }
        int flag=0;
        String line;
        File file = new File("student.txt");
        ArrayList<String> lista = new ArrayList<String>();
        try {
            BufferedReader bufferedReader= new BufferedReader(new FileReader(file));
            while ((line=bufferedReader.readLine())!=null) {
                lista.add(line);
            }

            for(int i = 0; i < lista.size(); i++){
                line=lista.get(i);
                if (line.startsWith(maticni)){
                    lista.remove(i);
                    flag=1;
                }

            }
            if (flag==0)
                System.out.println("Nije pronaden student s unesenim maticnim brojem");
            bufferedReader.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            file.delete();
            File file1 = new File("student.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1,true));
            for(int i = 0; i < lista.size(); i++){
                bufferedWriter.write(lista.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }



    }

    public boolean provjeraNiza(String niz){
        if (niz.matches("^[a-zA-Z]*$")){
            return true;
        }
        else {
            System.out.println("Krivi unos");
            return false;
        }

    }

    public boolean provjeraTelefona(String broj){
        if (broj.matches("^[0-9]*$"))
            return true;
        return false;
    }

    public boolean provjeraMaila(String email){
        if (email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
            return true;
        return false;
    }

    public void menu(){
        Student student = new Student();
        String datRod,datUpisa;
        int odabir,flag=0;

        do {
            System.out.println("1. Unesite studenta");
            System.out.println("2. Ispis studenata");
            System.out.println("3. Brisanje studenta");
            System.out.println("4. Izlaz");
            odabir=scanner.nextInt();


            switch (odabir) {
                case 1:
                    do {

                        System.out.println("Unesite ime: ");
                        student.ime = scanner.next();

                    } while (provjeraNiza(student.ime) == false);
                    do {
                        System.out.println("Unesite prezime: ");
                        student.prezime = scanner.next();

                    } while (provjeraNiza(student.prezime) == false);
                    try {
                        System.out.println("Unesite maticni broj: ");
                        student.maticniBroj = scanner.nextInt();
                    } catch (Exception ex) {
                        System.out.println("Krivi unos");
                    }
                    do {
                        System.out.println("Unesite broj telefona: ");
                        student.brojTelefona = scanner.next();
                    } while (provjeraTelefona(student.brojTelefona) == false);
                    do {
                        System.out.println("Unesite email: ");
                        student.email = scanner.next();
                    } while (provjeraMaila(student.email) == false);
                    System.out.println("Unesite datum rodenja: ");
                    datRod = scanner.next();
                    try {
                        student.datumRodenja = new SimpleDateFormat("dd.MM.yyyy").parse(datRod);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    System.out.println("Unesite datum upisa: ");
                    datUpisa = scanner.next();
                    try {
                        student.godinaUpisa = new SimpleDateFormat("dd.MM.yyyy").parse(datUpisa);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    upisStudenta(student);
                    break;
                case 2:
                    ispisStudenta();
                    break;

                case 3:
                    String obrisi;
                    System.out.println("Unesite maticni broj studenta kojeg zelite obrisati: ");
                    obrisi = scanner.next();
                    izbrisiStudenta(obrisi);
                    break;
                case 4:
                    System.out.println("Kraj!");
                    flag = 1;
                    break;
            }
        }
            while (flag != 1);



    }
}
