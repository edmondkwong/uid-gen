package io.eddk;

import org.junit.Assert;
import org.junit.Test;

public class UiGeneratorTest {

    private UidGenerator uidGenerator = new UidGenerator();

    @Test
    public void willCreateUniqueHashForDifferentInputString() throws Exception {

        String sha1 = "b75b845fa3162d962c51d397fd2edae2afd918b3";
        String line1 = "facebook,initech,jess1234";
        String line2 = "local,initrode,eddk12345";
        String line3 = "LoCal,initrode,eddk12345";
        String line4 = "local,initrode,EDDK12345";

        Assert.assertEquals(sha1, uidGenerator.createUniqueUserId(line1));
        Assert.assertEquals(sha1, HashUtils.sha1(line1));
        Assert.assertNotEquals(sha1, HashUtils.sha1(line2));

        Assert.assertEquals(uidGenerator.createUniqueUserId(line3), uidGenerator.createUniqueUserId(line2));
        Assert.assertNotEquals(uidGenerator.createUniqueUserId(line3), uidGenerator.createUniqueUserId(line4));

    }

}