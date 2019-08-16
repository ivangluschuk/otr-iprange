package iprange;

import java.util.Scanner;

final class TerminalManager {

    private String firstIpAddress;
    private String secondIpAddress;

    void start() {
        while (firstIpAddress == null) getFirstIpAddress();
        while (secondIpAddress == null) getSecondIpAddress();
        printIpAddresses();
    }

    private void getFirstIpAddress() {

<<<<<<< HEAD
        System.out.println("\nTo exit the program enter \"exit\"\n");
=======
        System.out.println("To exit the program enter \"exit\"");
>>>>>>> c09d69a9619b744cf0bbb5a10fc361a072861241
        System.out.println("Enter the first ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        final var input = scanner.nextLine();

        if (input.equals("exit")) System.exit(0);

        if (IpAddressValidator.validate(input)) {
            firstIpAddress = input;
        } else {
            System.out.println("The ip address has a wrong format: " + input);
        }
    }

    private void getSecondIpAddress() {

<<<<<<< HEAD
        System.out.println("\nTo exit the program enter \"exit\"\n");
=======
        System.out.println("To exit the program enter \"exit\"");
>>>>>>> c09d69a9619b744cf0bbb5a10fc361a072861241
        System.out.println("Enter the second ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        final var input = scanner.nextLine();

        if (input.equals("exit")) System.exit(0);

        if (IpAddressValidator.validate(input)) {
            secondIpAddress = input;
        } else {
            System.out.println("The ip address has a wrong format: " + input);
        }
    }

    private void printIpAddresses() {
        try {
            IpAddressRanger ipAddressRanger = new IpAddressRanger(firstIpAddress, secondIpAddress);

            System.out.println("\nAvailable range between " + firstIpAddress + " and " + secondIpAddress + ":\n");

            for (String address : ipAddressRanger) {
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
