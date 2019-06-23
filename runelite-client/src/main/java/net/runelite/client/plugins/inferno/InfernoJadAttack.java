/*
 * Copyright (c) 2017, Devin French <https://github.com/devinfrench>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *	list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *	this list of conditions and the following disclaimer in the documentation
 *	and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.plugins.inferno;

	import com.google.common.collect.ImmutableMap;
	import lombok.Getter;
	import net.runelite.api.AnimationID;
	import net.runelite.api.Prayer;
	import net.runelite.api.SpriteID;

@Getter
public enum InfernoJadAttack
{
	MAGIC(AnimationID.JALTOK_JAD_MAGE_ATTACK, Prayer.PROTECT_FROM_MAGIC, SpriteID.PRAYER_PROTECT_FROM_MAGIC),
	RANGE(AnimationID.JALTOK_JAD_RANGE_ATTACK, Prayer.PROTECT_FROM_MISSILES, SpriteID.PRAYER_PROTECT_FROM_MISSILES);

	private final int animation;
	private final Prayer prayer;
	private final int prayerSpriteID;

	InfernoJadAttack(int animation, Prayer prayer, int prayerSpriteID)
	{
		this.animation = animation;
		this.prayer = prayer;
		this.prayerSpriteID = prayerSpriteID;
	}

	private static final ImmutableMap<Integer, InfernoJadAttack> animMap;

	static
	{
		ImmutableMap.Builder<Integer, InfernoJadAttack> builder = ImmutableMap.builder();

		for (InfernoJadAttack value : values())
		{
			builder.put(value.animation, value);
		}

		animMap = builder.build();
	}

	static InfernoJadAttack ofAnimation(int animation)
	{
		return animMap.get(animation);
	}
}
