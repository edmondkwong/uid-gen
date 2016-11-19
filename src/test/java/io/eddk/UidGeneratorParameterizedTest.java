package io.eddk;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class UidGeneratorParameterizedTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { "facebook,initech,jess1234", true}, { "local,initech,jess1234", true},
                { "google,initech,jess1234", true}, { "facebook,invalid,xxxx", false}, {",,", false}
        });

    }

    @Parameterized.Parameter
    public String input;
    @Parameterized.Parameter(value = 1)
    public boolean expected;

    private UidGenerator uidGenerator = new UidGenerator();

    @Test
    public void willReturnFalseIfLineDoesNotConfirm() throws Exception {
        Assert.assertEquals(expected, this.uidGenerator.isValidFormat(input));
    }

}