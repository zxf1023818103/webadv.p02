package webadv.s99201105.p02;

import java.util.*;
import org.apache.commons.codec.digest.DigestUtils;

public class Main {

    private static Hashtable<String, String> usernamePasswordMap;

    static {
        try {
            usernamePasswordMap = new Hashtable<String,String>();
            Scanner scanner = new Scanner(Main.class.getResourceAsStream("/password.txt"));
            boolean isPasswordLine = false;
            String username = null, password;
            while (scanner.hasNextLine()) {
                if (!isPasswordLine) {
                    username = scanner.nextLine();
                }
                else {
                    password = scanner.nextLine();
                    usernamePasswordMap.put(username, password);
                }
                isPasswordLine = !isPasswordLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Login: ");
                String username = System.console().readLine();
                if (username.trim().length() == 0) {
                    throw new Exception();
                }
                System.out.print("Password: ");
                String password = new String(System.console().readPassword());
                String digest = DigestUtils.sha256Hex(password);
                if (usernamePasswordMap.get(username).equals(digest)) {
                    System.out.println("Login success.");
                    break;
                }
                else {
                    Thread.sleep(1000);
                    System.out.println("Invalid username or password.");
                }
            } catch (Exception e) {
                // ignore
            }
        }
    }
}