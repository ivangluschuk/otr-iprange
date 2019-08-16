package iprange;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class IpAddressRangerTest {

    private static IpAddressRanger ipAddressRanger;

    @Test
    public void initializeRangeSecondAddressBiggerThanFirstAddressSuccess() {
        ipAddressRanger = new IpAddressRanger("10.10.10.10", "10.12.10.10");
        Assert.assertNotNull(ipAddressRanger);
    }

    @Test
    public void initializeRangeFirstAddressBiggerThanSecondAddressSuccess() {
        ipAddressRanger = new IpAddressRanger("10.11.10.10", "10.10.10.10");
        Assert.assertNotNull(ipAddressRanger);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeSameAddressesFail() {
        ipAddressRanger = new IpAddressRanger("10.11.10.10", "10.11.10.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongRangeAddressesFail() {
        ipAddressRanger = new IpAddressRanger("10.12.10.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongFirstAddressesFormatOctetNumberStartWithZeroFail() {
        ipAddressRanger = new IpAddressRanger("02.12.10.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongSecondAddressesFormatOctetNumberStartWithZeroFail() {
        ipAddressRanger = new IpAddressRanger("2.12.10.10", "10.01.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatLetterSignInFirstOctetFail() {
        ipAddressRanger = new IpAddressRanger("10.12.x0.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatLetterSignInSecondOctetFail() {
        ipAddressRanger = new IpAddressRanger("10.12.10.10", "10.11.j1.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatMoreThanFourOctetsInFirstAddressFail() {
        ipAddressRanger = new IpAddressRanger("10.12.10.10.10", "10.11.21.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatMoreThanFourOctetsInSecondAddressFail() {
        ipAddressRanger = new IpAddressRanger("10.12.10.10", "10.10.11.11.10");
    }

    @Test
    public void validateRangeSuccess() {
        String[] expected = {"1.1.1.2",
                            "1.1.1.3",
                            "1.1.1.4",
                            "1.1.1.5",
                            "1.1.1.6"};

        ipAddressRanger = new IpAddressRanger("1.1.1.1", "1.1.1.7");
        ArrayList<String> result = new ArrayList<>();

        for (String address : ipAddressRanger) {
            result.add(address);
        }

        Assert.assertArrayEquals(result.toArray(), expected);


        expected = new String[]{"1.1.3.1",
                                "1.1.4.1"};

        ipAddressRanger = new IpAddressRanger("1.1.5.1", "1.1.2.1");

        result = new ArrayList<>();

        for (String address : ipAddressRanger) {
            result.add(address);
        }

        Assert.assertArrayEquals(result.toArray(), expected);


        expected = new String[]{"1.1.2.2",
                                "1.1.2.3",
                                "1.1.3.1",
                                "1.1.3.2",
                                "1.1.3.3",
                                "1.1.4.1",
                                "1.1.4.2",
                                "1.1.4.3",
                                "1.1.5.1",
                                "1.1.5.2"};

        ipAddressRanger = new IpAddressRanger("1.1.5.3", "1.1.2.1");

        result = new ArrayList<>();

        for (String address : ipAddressRanger) {
            result.add(address);
        }

        Assert.assertArrayEquals(result.toArray(), expected);


        expected = new String[]{"0.2.2.2",
                                "0.2.2.3",
                                "1.2.2.1",
                                "1.2.2.2"};

        ipAddressRanger = new IpAddressRanger("1.2.2.3", "0.2.2.1");

        result = new ArrayList<>();

        for (String address : ipAddressRanger) {
            result.add(address);
        }

        Assert.assertArrayEquals(result.toArray(), expected);
    }
}
