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
package org.commiecoin;

import java.security.SecureRandom;
import java.util.Random;

import org.commiecoin.io.Database;
import org.commiecoin.net.Network;
import org.commiecoin.util.Time;

public class Main {

	public static final String VERSION = "0.0.1";
	public static final Random RANDOM  = new Random(new SecureRandom().nextLong());
	
	private static Database database;
	private static Network network;
	
	public static void main(String[] args) {
		System.setProperty("java.util.logging.SimpleFormatter.format", 
	            "[%1$tF] [%1$tT] [%4$s] %5$s%6$s%n");
		Time.getTime(); // Update time offset
		
		try {
			database = new Database();
			network = new Network();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				Main.stop();
			}
		}));
	}

	public static void stop() {
		database.close();
	}
	
	public static Database getDatabase() {
		return database;
	}
	
	public static Network getNetwork() {
		return network;
	}
	
}
