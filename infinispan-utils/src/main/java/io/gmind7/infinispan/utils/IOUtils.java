package io.gmind7.infinispan.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by gmind on 14. 7. 30.
 */
public class IOUtils {

    public static String readLine(String s) {
        System.out.print(s);
        String returnval = null;
        try {
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(System.in));
            returnval = bufRead.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnval;
    }

    public static void dumpWelcomeMessage() {
        System.out.println("Infinispan Cache System");
        System.out.println("=====================");
        System.out.println("COMMAND=put,get,remove,size,clear,putWithTime,putIfAbsent");
    }

}
