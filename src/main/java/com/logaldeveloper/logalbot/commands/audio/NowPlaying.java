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

package com.logaldeveloper.logalbot.commands.audio;

import com.logaldeveloper.logalbot.Main;
import com.logaldeveloper.logalbot.commands.Command;
import com.logaldeveloper.logalbot.utils.AudioUtil;
import com.logaldeveloper.logalbot.utils.TimeUtil;
import com.logaldeveloper.logalbot.utils.VoiceChannelUtil;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public class NowPlaying implements Command {
	@Override
	public void initialize(){
	}

	@Override
	public String execute(String[] arguments, User executor, TextChannel channel){
		if (!AudioUtil.isAllowedChannelForAudioCommands(channel)){
			return ":no_entry_sign: Sorry " + executor.getAsMention() + ", but audio commands can only be used in text channels named `" + Main.getTextChannelNameForAudioCommands() + "`.";
		}

		if (!AudioUtil.isTrackLoaded()){
			return ":mute: " + executor.getAsMention() + ", there is nothing currently playing.";
		}

		String reply = ":dancer: " + executor.getAsMention() + ", the track currently playing is **" + AudioUtil.getLoadedTrack().getInfo().title + "**.\n";
		reply += ":clock130: " + TimeUtil.formatTime(AudioUtil.getLoadedTrack().getPosition()) + "/" + TimeUtil.formatTime(AudioUtil.getLoadedTrack().getDuration());

		if (AudioUtil.isPlayerPaused()){
			reply += "\n:pause_button: This track is currently paused.";
		} else if (!VoiceChannelUtil.isInCurrentVoiceChannel(executor)){
			reply += "\n:headphones: You can listen to this track by joining voice channel `" + AudioUtil.getCurrentVoiceChannel().getName() + "`.";
		}

		return reply;
	}
}