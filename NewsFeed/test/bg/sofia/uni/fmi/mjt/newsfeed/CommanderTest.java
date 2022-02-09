package bg.sofia.uni.fmi.mjt.newsfeed;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class CommanderTest {
    private Commander cmd;
    @BeforeEach
    void setUp() {
        cmd = Commander.getInstance();
    }

    @Test
    void checkSingleton() {
        assertEquals(cmd, Commander.getInstance());
    }

    @Test
    void run() {
        try (InputStream input = new FileInputStream("testInput.txt")){
            assertDoesNotThrow(() -> cmd.run(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void stallRun() {
        try (InputStream input = new FileInputStream("invalid.txt")){
            assertThrows(IllegalArgumentException.class, () -> cmd.run(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
