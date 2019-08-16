package iprange;

import java.util.Scanner;

final class TerminalManager {

    private StringBuilder firstIpAddress = new StringBuilder();
    private StringBuilder secondIpAddress = new StringBuilder();

    void start() {

        do {
            getAddress(firstIpAddress, "the first");
        } while (firstIpAddress.length() == 0);

        do {
            getAddress(secondIpAddress, "the second");
        } while (secondIpAddress.length() == 0);

        printIpAddresses(firstIpAddress, secondIpAddress);
    }

    private void getAddress(StringBuilder address, final String count) {

        System.out.println("\nTo exit the program enter \"exit\"\n");
        System.out.println("Enter the " + count +  " ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        final var input = scanner.nextLine();

        if (input.equals("exit")) System.exit(0);

        if (IpAddressValidator.validate(input)) {
            address.append(input);
        } else {
            System.out.println("The ip address has a wrong format: " + input);
        }
    }

    private void printIpAddresses(StringBuilder firstIpAddress, StringBuilder secondIpAddress) {
        try {
            IpAddressRanger ipAddressRanger = new IpAddressRanger(firstIpAddress.toString(), secondIpAddress.toString());

            System.out.println("\nAvailable range between " + firstIpAddress + " and " + secondIpAddress + ":\n");

            for (String address : ipAddressRanger) {
                System.out.println(address);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            firstIpAddress.setLength(0);
            secondIpAddress.setLength(0);
            start();
        }
    }
}
