package io.gmind7.infinispan;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import io.gmind7.infinispan.utils.IOUtils;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;


/**
* Created by gmind on 14. 7. 21.
*/
public class JavaConfigLibraryModeCache {

	private static final long ENTRY_LIFESPAN = 60 * 1000; // 60 seconds

	public static void main(String args[]) throws Exception {
		System.setProperty("java.net.preferIPv4Stack", "true");
		System.setProperty("jgroups.bind_addr", "127.0.0.1");
		System.setProperty("jgroups.udp.mcast_addr", "228.2.2.2");
		System.setProperty("jgroups.udp.mcast_port", "46655");

		EmbeddedCacheManager manager = new DefaultCacheManager();

		manager.defineConfiguration("",
		 new ConfigurationBuilder()
		  .eviction()
		  .strategy(EvictionStrategy.LRU)
		  .maxEntries(10)
		  .build());

		GlobalConfiguration globalConfiguration = new GlobalConfigurationBuilder()
		 .clusteredDefault()
		 .transport()
		 .addProperty("configurationFile", "jgroups-tcp.xml")
		 .globalJmxStatistics()
		 .allowDuplicateDomains(true).enable()
		 .build();

		Configuration configuration = new ConfigurationBuilder()
		 .jmxStatistics()
		 .enable()
		 .clustering()
		 .cacheMode(CacheMode.DIST_SYNC)
		 .hash()
		 .numOwners(2)
		 .expiration()
		 .lifespan(ENTRY_LIFESPAN)
		 .build();

		manager = new DefaultCacheManager(globalConfiguration, configuration, true);
		Cache<Object, Object> cache = manager.getCache("testCache");

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
