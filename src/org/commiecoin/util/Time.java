/**
 *  Copyright 2017 Commiecoin Team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
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
