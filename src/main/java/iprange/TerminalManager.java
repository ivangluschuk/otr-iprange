package iprange;

import java.util.Scanner;

final class TerminalManager {

    private String firstIpAddress;
    private String secondIpAddress;
    private IpAddressValidator ipAddressValidator = new IpAddressValidator();

    void start() {
        if (firstIpAddress == null) getFirstIpAddress();
        if (secondIpAddress == null) getSecondIpAddress();
        printIpAddresses();
    }

    private void getFirstIpAddress() {

        System.out.println("To exit the program enter \"exit\"");
        System.out.println("Enter the first ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        if (input.equals("exit")) System.exit(0);

        if (ipAddressValidator.validate(input)) {
            firstIpAddress = input;
        } else {
            System.out.println("The ip address has a wrong format: " + input);
            start();
        }
    }

    private void getSecondIpAddress() {

        System.out.println("To exit the program enter \"exit\"");
        System.out.println("Enter the second ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        if (input.equals("exit")) System.exit(0);

        if (ipAddressValidator.validate(input)) {
            secondIpAddress = input;
        } else {
            System.out.println("The ip address has a wrong format: " + input);
            start();
        }
    }

    private void printIpAddresses() {
        try {
            IpAddressRanger ipAddressRanger = new IpAddressRanger(firstIpAddress, secondIpAddress);
            String[] addresses = ipAddressRanger.getRange();

            for (String address : addresses) {
                System.out.println(address);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            firstIpAddress = null;
            secondIpAddress = null;
            start();
        }
    }
}
