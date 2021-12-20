import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IEncoderTest {

    @Test
    void testOfEncodeMethod() {
        assertEquals("NzhxMzN5ZGNnd3Vo", IEncoder.encode("78q33ydcgwuh"));

    }
}