package principal;


import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.CarRental;
import model.Veiculo;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Preencha as informações:");

        System.out.print("Modelo do Veiculo: ");
        String carModel = sc.nextLine();
        System.out.print("Horario de entrada (dd/MM/yyyy hh:ss): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Hora de saida (dd/MM/yyyy hh:ss): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start, finish, new Veiculo(carModel));

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("Fatura: ");

        System.out.println("Pagamento Basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));

    

        






    }
}