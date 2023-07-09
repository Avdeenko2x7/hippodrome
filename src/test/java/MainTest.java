import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;
@Disabled
class MainTest {

    @Test
    @Timeout(value = 22)
    public void testMainExecutionTime() throws Exception {
        Main.main(new String[0]);
    }

}