package main.java.iprange;

import java.util.Scanner;

final class TerminalProvider {

    private String firstIpAddress;
    private String secondIpAddress;
    private IpAddressValidator ipAddressValidator = new IpAddressValidator();

    void start() {
        if (firstIpAddress == null) getFirstIpAddress();
        if (secondIpAddress == null) getSecondIpAddress();
        printIpAddresses();
    }

    private void getFirstIpAddress() {

        System.out.println("Enter the first ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        try {
            ipAddressValidator.validate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }

        firstIpAddress = input;
    }

    private void getSecondIpAddress() {

        System.out.println("Enter the second ip address:");
        System.out.print(">");

        Scanner scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        try {
            ipAddressValidator.validate(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }

        secondIpAddress = input;
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
