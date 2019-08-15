package main.java.iprange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final class IpAddressRanger {

    private int[] upRangeIpAddress;
    private int[] downRangeIpAddress;

    IpAddressRanger(final String firstIpAddress, final String secondIpAddress) {

        var result = new IpAddressValidator().validateRange(firstIpAddress, secondIpAddress);

        if (result == 1) {
            upRangeIpAddress = Arrays.stream(firstIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
            downRangeIpAddress = Arrays.stream(secondIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
        } else {
            upRangeIpAddress = Arrays.stream(secondIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
            downRangeIpAddress = Arrays.stream(firstIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
        }
    }

    String[] getRange() {

        var ipAddressRange = getIpAddressesRange(downRangeIpAddress, upRangeIpAddress);

        return formatIpAddresses(ipAddressRange);
    }

    private ArrayList<int[]> getIpAddressesRange(final int[] downRangeIpAddress, final int[] upRangeIpAddress) {

        var initialDownRangeIpAddress = downRangeIpAddress.clone();
        var ipAddressRange = new ArrayList<int[]>();

        for (int offset = 3; offset >= 0; offset--) {

            if (offset < 3) {
               System.arraycopy(initialDownRangeIpAddress, offset + 1, downRangeIpAddress, offset + 1, 3 - offset);
            }

            if (downRangeIpAddress[offset] < upRangeIpAddress[offset]) {
                if (initialDownRangeIpAddress[offset] < upRangeIpAddress[offset]) {
                    downRangeIpAddress[offset] = downRangeIpAddress[offset] + 1;
                    ipAddressRange.add(downRangeIpAddress.clone());
                }

                offset = 4;
            }
        }

        ipAddressRange.remove(ipAddressRange.size() - 1);
        return ipAddressRange;
    }

    private String[] formatIpAddresses(List<int[]> addresses) {

        var formattedAddresses = new ArrayList<String>();

        for (int[] address : addresses) {
            String formattedAddress = Arrays.stream(address)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining("."));

            formattedAddresses.add(formattedAddress);
        }

        return formattedAddresses.toArray(new String[0]);
    }
}
