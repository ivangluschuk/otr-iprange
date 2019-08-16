package iprange;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

final class IpAddressRanger implements Iterable<String> {

    private final int[] upRangeIpAddress;
    private final int[] downRangeIpAddress;
    private final int rangeCounter;

    IpAddressRanger(final String firstIpAddress, final String secondIpAddress) {

        final var shouldReverse = IpAddressValidator.validateRange(firstIpAddress, secondIpAddress);

        if (shouldReverse) {
            upRangeIpAddress = Arrays.stream(firstIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
            downRangeIpAddress = Arrays.stream(secondIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
        } else {
            upRangeIpAddress = Arrays.stream(secondIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
            downRangeIpAddress = Arrays.stream(firstIpAddress.split("\\.")).mapToInt(Integer::parseInt).toArray();
        }

        rangeCounter = calculateRange();
    }

    private int calculateRange() {
        int range = 1;

        for (int i = 0; i <= 3; i++) {
            range *= upRangeIpAddress[i] - downRangeIpAddress[i] + 1;
        }

        return range - 2;
    }

    private String formatIpAddresses(final int[] addresses) {
        return Arrays.stream(addresses)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("."));
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {

            private final int[] upRangeIpAddressInner = upRangeIpAddress.clone();
            private final int[] downRangeIpAddressInner = downRangeIpAddress.clone();
            private final int[] initialDownRangeIpAddressInner = downRangeIpAddress.clone();
            private int rangeCounterInner = rangeCounter;

            @Override
            public boolean hasNext() {
                return rangeCounterInner > 0;
            }

            @Override
            public String next() {
                for (int offset = 3; offset >= 0; offset--) {

                    if (offset < 3) {
                        System.arraycopy(initialDownRangeIpAddressInner, offset + 1, downRangeIpAddressInner, offset + 1, 3 - offset);
                    }

                    if (downRangeIpAddressInner[offset] < upRangeIpAddressInner[offset]) {
                        if (initialDownRangeIpAddressInner[offset] < upRangeIpAddressInner[offset]) {
                            downRangeIpAddressInner[offset] = downRangeIpAddressInner[offset] + 1;
                            rangeCounterInner--;

                            return formatIpAddresses(downRangeIpAddressInner.clone());
                        }
                    }
                }

                return "";
            }
        };
    }
}
