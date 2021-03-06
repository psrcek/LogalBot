/*
 * Copyright (C) 2018 Logan Fick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.logaldeveloper.logalbot.commands;

import com.logaldeveloper.logalbot.utils.DataManager;
import net.dv8tion.jda.core.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PermissionManager {
	private static final Logger logger = LoggerFactory.getLogger(PermissionManager.class);

	public static boolean isWhitelisted(User user){
		if (DataManager.getValue(user, "whitelisted") == null){
			DataManager.setValue(user, "whitelisted", "false");
		}

		return DataManager.getValue(user, "whitelisted").equals("true");
	}

	public static void addToWhitelist(User user){
		DataManager.setValue(user, "whitelisted", "true");
		logger.info("'" + user.getName() + "' was added to the whitelist.");
	}

	public static void removeFromWhitelist(User user){
		DataManager.setValue(user, "whitelisted", "false");
		logger.info("'" + user.getName() + "' was removed from the whitelist.");
	}
}