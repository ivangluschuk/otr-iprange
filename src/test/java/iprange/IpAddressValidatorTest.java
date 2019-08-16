package iprange;

import org.junit.Test;

import static org.junit.Assert.*;

public class IpAddressValidatorTest {

    @Test
    public void validateSuccess() {
        var result = IpAddressValidator.validate("255.255.255.255");
        assertTrue(result);

        result = IpAddressValidator.validate("55.55.55.55");
        assertTrue(result);

        result = IpAddressValidator.validate("5.5.5.5");
        assertTrue(result);

        result = IpAddressValidator.validate("0.0.0.0");
        assertTrue(result);

        result = IpAddressValidator.validate("192.168.0.1");
        assertTrue(result);

        result = IpAddressValidator.validate("127.0.0.1");
        assertTrue(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInFirstOctetFail() {
        var result = IpAddressValidator.validate("2x5.255.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInSecondOctetFail() {
        var result = IpAddressValidator.validate("255.2x5.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInThirdOctetFail() {
        var result = IpAddressValidator.validate("255.255.2x5.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInFourthOctetFail() {
        var result = IpAddressValidator.validate("255.255.255.x55");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressFourthOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.255.255.055");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressThirdOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.255.055.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressSecondOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.055.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressFirstOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("055.255.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatMoreThanFourOctetsAddressFail() {
        var result = IpAddressValidator.validate("10.55.255.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatLessThanFourOctetsAddressFail() {
        var result = IpAddressValidator.validate("255.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatFirstOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("256.255.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatSecondOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.256.255.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatThirdOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.255.256.255");

        assertFalse(result);
    }

    @Test
    public void validateWrongFormatFourthOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.255.255.256");

        assertFalse(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeSameAddressesFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.11.10.10", "10.11.10.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongRangeAddressesFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.12.10.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongFirstAddressesFormatOctetNumberStartWithZeroFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("02.12.10.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongSecondAddressesFormatOctetNumberStartWithZeroFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("2.12.10.10", "10.01.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatLetterSignInFirstOctetFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.12.x0.10", "10.11.11.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatLetterSignInSecondOctetFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.12.10.10", "10.11.j1.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatMoreThanFourOctetsInFirstAddressFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.12.10.10.10", "10.11.21.10");
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateRangeWrongAddressesFormatMoreThanFourOctetsInSecondAddressFail() {
        IpAddressValidator.shouldReverseOrThrowIfInvalid("10.12.10.10", "10.10.11.11.10");
    }
}
