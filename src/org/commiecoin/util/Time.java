package org.commiecoin.util;

import java.net.InetAddress;
import java.util.logging.Logger;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class Time {

	public static final long TIME_UNTIL_UPDATE = 1000 * 60 * 5;
	private static long lastUpdate = 0, offset = 0;

	public static long getTime() {
		if (System.currentTimeMillis() > lastUpdate + TIME_UNTIL_UPDATE) {
			updateTimeOffset();
			Logger.getGlobal().info("Adjusted time by " + offset + "ms");
			lastUpdate = System.currentTimeMillis();
		}
		return System.currentTimeMillis() + offset;
	}

	private static void updateTimeOffset() {
		NTPUDPClient client = new NTPUDPClient();
		client.setDefaultTimeout(5000);
		try {
			client.open();
			TimeInfo info = client.getTime(InetAddress.getByName("pool.ntp.org"));
			info.computeDetails();
			if (info.getOffset() != null) {
				offset = info.getOffset();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
	}

}
