package iprange;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class IpAddressValidatorTest {

    @Test
    public void validateSuccess() {
        var result = IpAddressValidator.validate("255.255.255.255");
        Assert.assertTrue(result);

        result = IpAddressValidator.validate("55.55.55.55");
        Assert.assertTrue(result);

        result = IpAddressValidator.validate("5.5.5.5");
        Assert.assertTrue(result);

        result = IpAddressValidator.validate("0.0.0.0");
        Assert.assertTrue(result);

        result = IpAddressValidator.validate("192.168.0.1");
        Assert.assertTrue(result);

        result = IpAddressValidator.validate("127.0.0.1");
        Assert.assertTrue(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInFirstOctetFail() {
        var result = IpAddressValidator.validate("2x5.255.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInSecondOctetFail() {
        var result = IpAddressValidator.validate("255.2x5.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInThirdOctetFail() {
        var result = IpAddressValidator.validate("255.255.2x5.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressLetterInFourthOctetFail() {
        var result = IpAddressValidator.validate("255.255.255.x55");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressFourthOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.255.255.055");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressThirdOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.255.055.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressSecondOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("255.055.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatAddressFirstOctetStartWithZeroFail() {
        var result = IpAddressValidator.validate("055.255.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatMoreThanFourOctetsAddressFail() {
        var result = IpAddressValidator.validate("10.55.255.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatLessThanFourOctetsAddressFail() {
        var result = IpAddressValidator.validate("255.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatFirstOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("256.255.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatSecondOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.256.255.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatThirdOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.255.256.255");

        Assert.assertFalse(result);
    }

    @Test
    public void validateWrongFormatFourthOctetOutOfRangeFail() {
        var result = IpAddressValidator.validate("255.255.255.256");

        Assert.assertFalse(result);
    }
}
