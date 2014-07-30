package io.gmind7.infinispan;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.gmind7.infinispan.utils.IOUtils;
import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

/**
 *
 * @author Junshik Jeon(service@opennaru.com, nameislocus@gmail.com)
 *
 */
public class XmlConfigLibraryModeCache {

	public static void main(String args[]) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.setProperty("jgroups.bind_addr", "127.0.0.1");
        System.setProperty("jgroups.udp.mcast_addr", "228.2.2.2");
        System.setProperty("jgroups.udp.mcast_port", "46655");

		EmbeddedCacheManager manager = new DefaultCacheManager("infinispan-distribution.xml");
		Cache<Object, Object> cache = manager.getCache();

        String command = ""; // Line read from standard in
        IOUtils.dumpWelcomeMessage();

        while (true) {
            command = IOUtils.readLine(">> ");

            if (command.equals("PUT")) {

                String key = IOUtils.readLine("Enter KEY=");
                String value = IOUtils.readLine("Enter VALUE=");
                cache.put(key, value);

            } else if (command.equals("GET")) {

                String key = IOUtils.readLine("Enter KEY=");
                System.out.println("RETURN VALUE=" + cache.get(key));

            } else if (command.equals("REMOVE")) {

                String key = IOUtils.readLine("Enter KEY=");
                cache.remove(key);

            } else if (command.equals("CLEAR")) {
                cache.clear();

            } else if (command.equals("size")) {

                System.out.println("Cache SIZE=" + cache.size());

            }
        }

	}

}