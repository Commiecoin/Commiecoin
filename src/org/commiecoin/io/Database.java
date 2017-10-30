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
package org.commiecoin.io;

import java.io.File;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class Database {

	public static final File DATA_FOLDER = new File("data");
	{
		DATA_FOLDER.mkdirs();
	}
	public static final File DATABASE_FILE = new File(DATA_FOLDER, "database");
	
	private final DB mapDb;
	
	public Database() throws Exception {
		this.mapDb = DBMaker.fileDB(DATABASE_FILE).closeOnJvmShutdown().checksumStoreEnable()
				.fileMmapEnableIfSupported().make();
	}
	
	public void close() {
		this.mapDb.close();
	}
	
	public boolean isClosed() {
		return this.mapDb.isClosed();
	}
	
}
