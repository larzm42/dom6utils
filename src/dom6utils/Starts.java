package dom6utils;
/* This file is part of dom6utils.
 *
 * dom6utils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * dom6utils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with dom6utils.  If not, see <http://www.gnu.org/licenses/>.
 */

public class Starts {
	public static final long ITEM = 0x03183790l;
	public static final long ITEM_SIZE = 528l;
	public static final long ITEM_ATTRIBUTE_OFFSET = 120l;
	public static final long ITEM_BITMAP_START = 504l;
	
	public static final long MONSTER = 0x032e4558l;
	public static final long MONSTER_SIZE = 888l;
	public static final long MONSTER_ATTRIBUTE_OFFSET = 64l;
	public static final long MONSTER_BITMAP_START = 872l;
	
	public static final long MONSTER_MAGIC = 0x0473baa0l;
	
	public static final long MONSTER_TRS_INDEX = 0x0001f734l;
	
	public static final long ITEM_TRS_INDEX = 0x00002d0cl;

	public static final long SITE = 0x043e8108l;
	public static final long SITE_SIZE = 312l;
	public static final long SITE_ATTRIBUTE_OFFSET = 48;
	
	public static final long NAMES = 0x00ff3e0cl;
	public static final long FIXED_NAMES = 0x0113bb68l;
	public static final int NAMES_COUNT = 161;
	
	public static final long SPELL = 0x04518ca0l;
	public static final long SPELL_ATTRIBUTE_OFFSET = 100l;
	public static final long SPELL_ATTRIBUTE_GAP = 60l;
	public static final long SPELL_SIZE = 280l;

	public static final long DESC_OFFSET = 0x140002000l;
	public static final long ITEM_AND_MONSTER_DESC_INDEX = 0x004fe020l;
	public static final long SPELL_DESC_INDEX = 0x004dea80l;

	public static final long EVENT = 0x0062f3c0l;
	public static final long EVENT_REQUIREMENT_OFFSET = 2408l;
	public static final long EVENT_EFFECT_OFFSET = 2600l;
	public static final long EVENT_SIZE = 2920l;
	
	public static final long MERCENARY = 0x032a65a0l;
	public static final long MERCENARY_SIZE = 312l;

	public static final long ARMOR = 0x0037b848l;
	public static final long ARMOR_ATTRIBUTE_OFFSET = 72l;
	public static final long ARMOR_SIZE = 104l;

	public static final long WEAPON = 0x047993f8l;
	public static final long WEAPON_ATTRIBUTE_OFFSET = 96l;
	public static final long WEAPON_SIZE = 152l;
	
	public static final long NATION = 0x02f17bf0l;
	public static final long NATION_ATTRIBUTE_OFFSET = 176l;
	public static final long NATION_ATTRIBUTE_VALUE_OFFSET = 976l;
	public static final long NATION_TROOP_OFFSET = 2576l;
	public static final long NATION_PRETENDER_OFFSET = 2744l;
	public static final long NATION_SIZE = 3000l;
}