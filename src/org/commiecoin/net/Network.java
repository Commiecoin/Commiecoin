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
package org.commiecoin.net;

import java.net.ServerSocket;

import org.commiecoin.Main;
import org.commiecoin.util.exceptions.PortUsedException;

public class Network {

	/*
	 * Network "rules"
	 */
	public static final String NETWORK_ID               = "Commiecoin|main"; // Network identifier (default is Commiecoin|main, format coin_name|chain_name)
	public static final int    PORT                     = 1922;              // Network default port (default is 1922)
	public static final int    SUBSIDY_HALVING_INTERVAL = 525600;            // Amount of blocks at which the block reward halves (default is 525600, ~2 years after genesis)
	public static final int    TARGET_BLOCK_TIME        = 1 * 60 * 2;        // Amount of seconds a block should take to mine (default is 2 minutes)
	public static final int    PEER_BAN_TIME            = 1 * 60 * 60 * 72;  // Amount of seconds a peer is banned for when misbehaving (default is 72 hours)
	
	private final byte[] selfIdentifier = new byte[128]; // ID useful for preventing self-connections later on
	
	public Network() throws Exception {
		Main.RANDOM.nextBytes(this.selfIdentifier);
		try {
			new ServerSocket(PORT).close();
		} catch (Exception e) {
			throw new PortUsedException(PORT);
		}
	}
	
	public byte[] getSelfIdentifier() {
		return this.selfIdentifier;
	}
	
}
