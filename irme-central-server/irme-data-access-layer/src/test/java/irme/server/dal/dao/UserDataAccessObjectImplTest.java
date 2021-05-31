package irme.server.dal.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class UserDataAccessObjectImplTest {

    @Tag("DEV")
    @DisplayName("testCalcOne_")
    @Test
    void testCalcOne()
    {
        System.out.println("======TEST ONE EXECUTED=======");
        Assertions.assertEquals( 4 , 2+2);
    }

}
