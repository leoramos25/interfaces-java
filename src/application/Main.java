package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carModel = scanner.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime startDate = LocalDateTime.parse(scanner.nextLine(), dateFormatter);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finishDate = LocalDateTime.parse(scanner.nextLine(), dateFormatter);
        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = scanner.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = scanner.nextDouble();

        CarRental carRental = new CarRental(startDate, finishDate, new Vehicle(carModel));
        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(carRental);

        System.out.println("FATURA: ");
        System.out.println("Pagamento básico: " + String.format("R$ %.2f", carRental.getInvoice().getBasicPayment()));
        System.out.println("Imposto: " + String.format("R$ %.2f", carRental.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("R$ %.2f", carRental.getInvoice().getTotalPayment()));

        scanner.close();
    }
}
