package iprange;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class IpAddressValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IP_ADDRESS_PATTERN =
           "^(25[0-5]|2[0-4][0-9]|[0]|[1]?[1-9][0-9]?)\\." +
           "(25[0-5]|2[0-4][0-9]|[0]|[1]?[1-9][0-9]?)\\." +
           "(25[0-5]|2[0-4][0-9]|[0]|[1]?[1-9][0-9]?)\\." +
           "(25[0-5]|2[0-4][0-9]|[0]|[1]?[1-9][0-9]?)$";

    IpAddressValidator() {
        pattern = Pattern.compile(IP_ADDRESS_PATTERN);
    }

    boolean validate(final String address) {
        matcher = pattern.matcher(address.trim());

        return matcher.matches();
    }

    int validateRange(final String firstIpAddress, final String secondIpAddress) {

        if (!validate(firstIpAddress)) {
            throw new IllegalArgumentException("The ip address has a wrong format: " + firstIpAddress);
        }

        if (!validate(secondIpAddress)) {
            throw new IllegalArgumentException("The ip address has a wrong format: " + secondIpAddress);
        }

        if (firstIpAddress.trim().equals(secondIpAddress)) {
            throw new IllegalArgumentException("There is no range between these addresses");
        }

        int[] firstIpAddressOctets =
                Arrays.stream(firstIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();

        int[] secondIpAddressOctets =
                Arrays.stream(secondIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();

        if (firstIpAddressOctets[0] <= secondIpAddressOctets[0] &&
                firstIpAddressOctets[1] <= secondIpAddressOctets[1] &&
                firstIpAddressOctets[2] <= secondIpAddressOctets[2] &&
                firstIpAddressOctets[3] <= secondIpAddressOctets[3]) {

            return -1;
        } else if (firstIpAddressOctets[0] >= secondIpAddressOctets[0] &&
                firstIpAddressOctets[1] >= secondIpAddressOctets[1] &&
                firstIpAddressOctets[2] >= secondIpAddressOctets[2] &&
                firstIpAddressOctets[3] >= secondIpAddressOctets[3]) {


            return 1;
        } else {
            throw new IllegalArgumentException("There is no range between these addresses");
        }
    }
}
