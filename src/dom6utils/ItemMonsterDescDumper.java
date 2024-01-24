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
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ItemMonsterDescDumper {
	enum State {
		ITEM, NOTHING, UNIT
	};
	static Map<Integer, String> monsters = new HashMap<Integer, String>();
	static String LAST_ITEM = "Sunrise Barding";
	static String FIRST_UNIT = "Minister of Magic";
	static String LAST_UNIT = "Lord of Fertility";
	static Set<Integer> blockedMonsterIds = new HashSet<Integer>();
	
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		State state = State.ITEM;
		
		FileInputStream stream = null;
		try {
			byte[] b34 = new byte[34];
			byte[] c = new byte[2];

			stream = new FileInputStream("Dominions6.exe");
			stream.skip(Starts.MONSTER);
			int id = 1;
			while (stream.read(b34, 0, 34) != -1) {
				stream.skip(4);
				stream.read(c, 0, 2);
				
				StringBuffer name = new StringBuffer();
				for (int i = 0; i < 34; i++) {
					if (b34[i] != 0) {
						name.append(new String(new byte[] {b34[i]}));
					}
				}
				if (name.toString().equals("end")) {
					break;
				}
				monsters.put(id, name.toString().toUpperCase());
				id++;
				stream.skip(824 + 24);
			}
			stream.close();
			
			stream = new FileInputStream("Dominions6.exe");
			stream.skip(Starts.ITEM_AND_MONSTER_DESC_INDEX);

			List<Long> indexes = new ArrayList<Long>();
			long index = -1;
			while (index != 0) {
				byte[] d = new byte[8];
				stream.read(d, 0, 8);
				String high0 = String.format("%02X", d[7]);
				String low0 = String.format("%02X", d[6]);
				String high1 = String.format("%02X", d[5]);
				String low1 = String.format("%02X", d[4]);
				String high2 = String.format("%02X", d[3]);
				String low2 = String.format("%02X", d[2]);
				String high3 = String.format("%02X", d[1]);
				String low3 = String.format("%02X", d[0]);
				
				index = new BigInteger(high0 + low0 + high1 + low1 + high2 + low2 + high3 + low3, 16).longValue();
				
				if (index != 0) {
					indexes.add(index);
				}
			}
			stream.close();
			
			Path itemDescPath = Files.createDirectories(Paths.get("items", "desc"));
			Files.walkFileTree(itemDescPath, new DirCleaner());
			Path descPath = Files.createDirectories(Paths.get("monsters", "desc"));
			Files.walkFileTree(descPath, new DirCleaner());

			byte[] b = new byte[1];
			List<String> names = new ArrayList<String>();
			String desc = null;

			for (Long offset : indexes) {
				StringBuffer buffer = new StringBuffer();
				stream = new FileInputStream("Dominions6.exe");
				stream.skip(offset-Starts.DESC_OFFSET);
				while (stream.read(b) != -1) {
					if (b[0] != 0) {
						buffer.append(new String(new byte[] {b[0]}));
					} else {
						break;
					}
				}
				//System.out.println(buffer.toString());
				stream.close();

				if (buffer.toString().startsWith(":")) {
					if (buffer.toString().substring(1).equals(FIRST_UNIT)) {
						state = State.UNIT;
					}
					names.add(buffer.toString().substring(1));
					desc = null;
				} else {
					desc = buffer.toString();
				}

				if (names.size() > 0 && desc != null) {
					for (String name : names) {
						if (state == State.ITEM) {
							Path path = Paths.get("items", "desc", name.replaceAll("[^a-zA-Z0-9\\-]", "") + ".txt");
							OutputStream os = Files.newOutputStream(path);
							os.write(desc.getBytes());
							os.close();
							if (name.equals(LAST_ITEM)) {
								state = State.NOTHING;
							}
						}
						if (state == State.UNIT) {
							List<Integer> idsInt = new ArrayList<Integer>();
							if (name.toString().startsWith("mon ")) {
								idsInt.add(Integer.valueOf(name.substring(4)));
								blockedMonsterIds.add(Integer.valueOf(name.substring(4)));
							} else if (name.toString().startsWith("mon")) {
								idsInt.add(Integer.valueOf(name.substring(3)));
								blockedMonsterIds.add(Integer.valueOf(name.substring(3)));
							} else {
								Set<Entry<Integer, String>> entrySet = monsters.entrySet();
								for (Map.Entry<Integer, String> entry : entrySet) {
									if (entry.getValue().equals(name.toString().toUpperCase())) {
										if (!blockedMonsterIds.contains(entry.getKey())) {
											idsInt.add(entry.getKey());
										}
									}
								}
							}
							for (Integer idInt : idsInt) {
								Path path = Paths.get("monsters", "desc", String.format("%04d", idInt) + ".txt");
								OutputStream os = Files.newOutputStream(path);
								os.write(desc.getBytes());
								os.close();
							}
							if (name.equals(LAST_UNIT)) {
								state = State.NOTHING;
							}
						}
					}
					names.clear();
					desc = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
