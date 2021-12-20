import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IDecoderTest {
    @Test
    void testOfDecodeMethod() {
        assertEquals("78q33ydcgwuh", IDecoder.decode("NzhxMzN5ZGNnd3Vo"));
    }
}