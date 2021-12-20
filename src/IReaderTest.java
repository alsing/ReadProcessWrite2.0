import static org.junit.jupiter.api.Assertions.*;

class IReaderTest {

    @org.junit.jupiter.api.Test
    void testOfReadTxtMethod() {
        assertEquals("123abcабвФЫВ.ъ", IReader.read("TestPack\\txtTest.txt"));
    }

    @org.junit.jupiter.api.Test
    void testOfReadXmlMethod() {
        assertEquals("<?xml version=\"1+91\">", IReader.read("TestPack\\xmlTest.xml"));
    }

    @org.junit.jupiter.api.Test
    void testOfReadJsonMethod() {
        assertEquals("{\n" + "   \"firstName\": \"Joe\"\n" + "}", IReader.read("TestPack\\jsonTest.json"));
    }
}