package io.gmind7.infinispan.hotrod;

import io.gmind7.infinispan.utils.IOUtils;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.Configuration;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Created by gmind on 14. 7. 21.
 */
public class HotRodClientServerModeCache {

	public static void main(String args[]) throws Exception {

        System.setProperty("java.net.preferIPv4Stack", "true");

		ConfigurationBuilder builder = new ConfigurationBuilder();

		builder
		 .addServer().host("127.0.0.1").port(11222) // GMS: address=nodeA/clustered, cluster=clustered, physical address=127.0.0.1:7700
		 .addServer().host("127.0.0.1").port(11322) // GMS: address=nodeB/clustered, cluster=clustered, physical address=127.0.0.1:7700
		 .addServer().host("127.0.0.1").port(11422) // GMS: address=nodeC/clustered, cluster=clustered, physical address=127.0.0.1:7700
		;
		Configuration config = builder.build();

		RemoteCacheManager remoteCacheManager = new RemoteCacheManager(config);

		RemoteCache<String, String> cache = remoteCacheManager.getCache("default");

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
