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
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class MonsterSpriteIndexer extends AbstractStatIndexer {

	static Map<String, List<String>> indexToInt = new HashMap<String, List<String>>();

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		indexToInt.put("first", new ArrayList<String>(Arrays.asList(new String[]{"00", "01"})));
		indexToInt.put("sea", new ArrayList<String>(Arrays.asList(new String[]{"03", "04"})));
		indexToInt.put("gods", new ArrayList<String>(Arrays.asList(new String[]{"07", "08", "09", "0A"})));
		indexToInt.put("arco 1", new ArrayList<String>(Arrays.asList(new String[]{"0B"})));
		indexToInt.put("arco 2", new ArrayList<String>(Arrays.asList(new String[]{"0F"})));
		indexToInt.put("arco 3", new ArrayList<String>(Arrays.asList(new String[]{"13"})));
		indexToInt.put("erm 1", new ArrayList<String>(Arrays.asList(new String[]{"17"})));
		indexToInt.put("ermor dead", new ArrayList<String>(Arrays.asList(new String[]{"1B"})));
		indexToInt.put("scel 2", new ArrayList<String>(Arrays.asList(new String[]{"1F"})));
		indexToInt.put("sauro 1", new ArrayList<String>(Arrays.asList(new String[]{"23"})));
		indexToInt.put("pyth 2", new ArrayList<String>(Arrays.asList(new String[]{"27"})));
		indexToInt.put("pyth 3", new ArrayList<String>(Arrays.asList(new String[]{"2A", "2B"})));
		indexToInt.put("ulm 1", new ArrayList<String>(Arrays.asList(new String[]{"2E", "2F"})));
		indexToInt.put("ulm 2", new ArrayList<String>(Arrays.asList(new String[]{"32", "33"})));
		indexToInt.put("ulm 3", new ArrayList<String>(Arrays.asList(new String[]{"36", "37"})));
		indexToInt.put("marv 1", new ArrayList<String>(Arrays.asList(new String[]{"3A"})));
		indexToInt.put("mar 2", new ArrayList<String>(Arrays.asList(new String[]{"3E"})));
		indexToInt.put("mar 3", new ArrayList<String>(Arrays.asList(new String[]{"42"})));
		indexToInt.put("macha 1", new ArrayList<String>(Arrays.asList(new String[]{"46"})));
		indexToInt.put("macha 2", new ArrayList<String>(Arrays.asList(new String[]{"4A"})));
		indexToInt.put("tir 1", new ArrayList<String>(Arrays.asList(new String[]{"52"})));
		indexToInt.put("fom 1", new ArrayList<String>(Arrays.asList(new String[]{"55", "56"})));
		indexToInt.put("eriu 2", new ArrayList<String>(Arrays.asList(new String[]{"59", "5A"})));
		indexToInt.put("man 2", new ArrayList<String>(Arrays.asList(new String[]{"5D", "5E"})));
		indexToInt.put("man 3", new ArrayList<String>(Arrays.asList(new String[]{"61"})));
		indexToInt.put("tien 1", new ArrayList<String>(Arrays.asList(new String[]{"65"})));
		indexToInt.put("tien 2", new ArrayList<String>(Arrays.asList(new String[]{"69"})));
		indexToInt.put("tien 3", new ArrayList<String>(Arrays.asList(new String[]{"6D"})));
		indexToInt.put("yomi 1", new ArrayList<String>(Arrays.asList(new String[]{"71"})));
		indexToInt.put("shinu 2", new ArrayList<String>(Arrays.asList(new String[]{"75"})));
		indexToInt.put("jomon 3", new ArrayList<String>(Arrays.asList(new String[]{"79"})));
		indexToInt.put("van 1", new ArrayList<String>(Arrays.asList(new String[]{"7D"})));
		indexToInt.put("hel 1", new ArrayList<String>(Arrays.asList(new String[]{"80", "81"})));
		indexToInt.put("van 2", new ArrayList<String>(Arrays.asList(new String[]{"84", "85"})));
		indexToInt.put("mid 3", new ArrayList<String>(Arrays.asList(new String[]{"88"})));
		indexToInt.put("niefel 1", new ArrayList<String>(Arrays.asList(new String[]{"8C"})));
		indexToInt.put("jotun 2", new ArrayList<String>(Arrays.asList(new String[]{"90"})));
		indexToInt.put("utg 3", new ArrayList<String>(Arrays.asList(new String[]{"94"})));
		indexToInt.put("rus 1", new ArrayList<String>(Arrays.asList(new String[]{"98"})));
		indexToInt.put("rus 2", new ArrayList<String>(Arrays.asList(new String[]{"9C"})));
		indexToInt.put("rus 3", new ArrayList<String>(Arrays.asList(new String[]{"A0"})));
		indexToInt.put("mict 1", new ArrayList<String>(Arrays.asList(new String[]{"A4"})));
		indexToInt.put("mict 2", new ArrayList<String>(Arrays.asList(new String[]{"A7", "A8"})));
		indexToInt.put("mict 3", new ArrayList<String>(Arrays.asList(new String[]{"AB"})));
		indexToInt.put("aby 1", new ArrayList<String>(Arrays.asList(new String[]{"AF", "B0"})));
		indexToInt.put("aby 2", new ArrayList<String>(Arrays.asList(new String[]{"B3"})));
		indexToInt.put("aby 3", new ArrayList<String>(Arrays.asList(new String[]{"B7"})));
		indexToInt.put("ctis 1", new ArrayList<String>(Arrays.asList(new String[]{"BB"})));
		indexToInt.put("ctis 2", new ArrayList<String>(Arrays.asList(new String[]{"BF"})));
		indexToInt.put("ctis 3", new ArrayList<String>(Arrays.asList(new String[]{"C3"})));
		indexToInt.put("pan 1", new ArrayList<String>(Arrays.asList(new String[]{"C7"})));
		indexToInt.put("pan 2", new ArrayList<String>(Arrays.asList(new String[]{"CB"})));
		indexToInt.put("pan 3", new ArrayList<String>(Arrays.asList(new String[]{"CF"})));
		indexToInt.put("cael 1", new ArrayList<String>(Arrays.asList(new String[]{"D2", "D3"})));
		indexToInt.put("cael 2", new ArrayList<String>(Arrays.asList(new String[]{"D6", "D7"})));
		indexToInt.put("cael 3", new ArrayList<String>(Arrays.asList(new String[]{"DA"})));
		indexToInt.put("aga 1", new ArrayList<String>(Arrays.asList(new String[]{"DE", "DF"})));
		indexToInt.put("aga 2", new ArrayList<String>(Arrays.asList(new String[]{"E2"})));
		indexToInt.put("aga 3", new ArrayList<String>(Arrays.asList(new String[]{"E6"})));
		indexToInt.put("kailasa 1", new ArrayList<String>(Arrays.asList(new String[]{"EA"})));
		indexToInt.put("lanka 1", new ArrayList<String>(Arrays.asList(new String[]{"EE"})));
		indexToInt.put("bandar 2", new ArrayList<String>(Arrays.asList(new String[]{"F2"})));
		indexToInt.put("patala 3", new ArrayList<String>(Arrays.asList(new String[]{"F6"})));
		indexToInt.put("hinnom 1", new ArrayList<String>(Arrays.asList(new String[]{"FA"})));
		indexToInt.put("ashdod 2", new ArrayList<String>(Arrays.asList(new String[]{"FD", "FE"})));
		indexToInt.put("gath 3", new ArrayList<String>(Arrays.asList(new String[]{"01", "02"})));
		indexToInt.put("ur", new ArrayList<String>(Arrays.asList(new String[]{"05", "06"})));
		indexToInt.put("ur 2", new ArrayList<String>(Arrays.asList(new String[]{"09"})));
		indexToInt.put("asp 2", new ArrayList<String>(Arrays.asList(new String[]{"11"})));
		indexToInt.put("lem 3", new ArrayList<String>(Arrays.asList(new String[]{"15"})));
		indexToInt.put("berytos", new ArrayList<String>(Arrays.asList(new String[]{"1D"})));
		indexToInt.put("muspel", new ArrayList<String>(Arrays.asList(new String[]{"21"})));
		indexToInt.put("atl 1", new ArrayList<String>(Arrays.asList(new String[]{"28", "29"})));
		indexToInt.put("atl 2", new ArrayList<String>(Arrays.asList(new String[]{"2C", "2D"})));
		indexToInt.put("atl 3", new ArrayList<String>(Arrays.asList(new String[]{"30"})));
		indexToInt.put("rylle 1", new ArrayList<String>(Arrays.asList(new String[]{"34"})));
		indexToInt.put("rylle 2", new ArrayList<String>(Arrays.asList(new String[]{"38"})));
		indexToInt.put("ryll 3", new ArrayList<String>(Arrays.asList(new String[]{"3C"})));
		indexToInt.put("ocean 1", new ArrayList<String>(Arrays.asList(new String[]{"40"})));
		indexToInt.put("ocean 2", new ArrayList<String>(Arrays.asList(new String[]{"44"})));
		indexToInt.put("pelag 1", new ArrayList<String>(Arrays.asList(new String[]{"4C"})));
		indexToInt.put("pelag 2", new ArrayList<String>(Arrays.asList(new String[]{"4F", "50"})));
		indexToInt.put("fire", new ArrayList<String>(Arrays.asList(new String[]{"57"})));
		indexToInt.put("earth", new ArrayList<String>(Arrays.asList(new String[]{"5B", "5C"})));
		indexToInt.put("water", new ArrayList<String>(Arrays.asList(new String[]{"5F"})));
		indexToInt.put("air", new ArrayList<String>(Arrays.asList(new String[]{"63"})));
		indexToInt.put("nature", new ArrayList<String>(Arrays.asList(new String[]{"67"})));
		indexToInt.put("death", new ArrayList<String>(Arrays.asList(new String[]{"6B", "6C"})));
		indexToInt.put("astral", new ArrayList<String>(Arrays.asList(new String[]{"6F"})));
		indexToInt.put("blood", new ArrayList<String>(Arrays.asList(new String[]{"73"})));
		indexToInt.put("misc", new ArrayList<String>(Arrays.asList(new String[]{"77"})));
		indexToInt.put("hob 1", new ArrayList<String>(Arrays.asList(new String[]{"7E"})));
		indexToInt.put("hob 2", new ArrayList<String>(Arrays.asList(new String[]{"7F"})));
		indexToInt.put("hob 3", new ArrayList<String>(Arrays.asList(new String[]{"83"})));
		indexToInt.put("rag 3", new ArrayList<String>(Arrays.asList(new String[]{"86", "87"})));
		indexToInt.put("naz", new ArrayList<String>(Arrays.asList(new String[]{"8A"})));
		indexToInt.put("xib 1", new ArrayList<String>(Arrays.asList(new String[]{"8E"})));
		indexToInt.put("xib 2", new ArrayList<String>(Arrays.asList(new String[]{"92"})));
		indexToInt.put("xib 3", new ArrayList<String>(Arrays.asList(new String[]{"96"})));
		indexToInt.put("ther 1", new ArrayList<String>(Arrays.asList(new String[]{"19"})));
		indexToInt.put("ys", new ArrayList<String>(Arrays.asList(new String[]{"9A"})));
		indexToInt.put("ery 3", new ArrayList<String>(Arrays.asList(new String[]{"53", "54"})));
		indexToInt.put("mek 1", new ArrayList<String>(Arrays.asList(new String[]{"9E"})));
		indexToInt.put("phl 2", new ArrayList<String>(Arrays.asList(new String[]{"A1", "A2"})));
		indexToInt.put("phl 3", new ArrayList<String>(Arrays.asList(new String[]{"A5", "A6"})));
		indexToInt.put("phae", new ArrayList<String>(Arrays.asList(new String[]{"A9", "AA"})));
		indexToInt.put("ind", new ArrayList<String>(Arrays.asList(new String[]{"B1", "B2"})));
		indexToInt.put("ubar", new ArrayList<String>(Arrays.asList(new String[]{"B5"})));
		indexToInt.put("naba", new ArrayList<String>(Arrays.asList(new String[]{"B9"})));
		indexToInt.put("vaetti", new ArrayList<String>(Arrays.asList(new String[]{"BD"})));
		indexToInt.put("mount", new ArrayList<String>(Arrays.asList(new String[]{"C1"})));
		indexToInt.put("Bekryd", new ArrayList<String>(Arrays.asList(new String[]{"C5"})));
		indexToInt.put("Pyr MA", new ArrayList<String>(Arrays.asList(new String[]{"C9"})));
		indexToInt.put("Pyr LA", new ArrayList<String>(Arrays.asList(new String[]{"CC", "CD"})));
		indexToInt.put("Nid", new ArrayList<String>(Arrays.asList(new String[]{"D0", "D1"})));
		indexToInt.put("Plant", new ArrayList<String>(Arrays.asList(new String[]{"D4", "D5"})));
		indexToInt.put("glamour", new ArrayList<String>(Arrays.asList(new String[]{"D8"})));
		indexToInt.put("Fem", new ArrayList<String>(Arrays.asList(new String[]{"DC"})));
		indexToInt.put("Pico", new ArrayList<String>(Arrays.asList(new String[]{"E0"})));
		indexToInt.put("And", new ArrayList<String>(Arrays.asList(new String[]{"E4"})));
		/*
		macha 3: 1975
		empty: 5023
		muspel: 5173
		ocean 3: 5838
		pelag 3: 5968
		hob 2: 6786
		hob 3: 6827
		empty: 7280
		empty: 7286
		oklara: 7292*/

		FileInputStream stream = null;
		try {
			Path monstersPath = Files.createDirectories(Paths.get("monsters", "output"));
			Files.walkFileTree(monstersPath, new DirCleaner());

			stream = new FileInputStream("monster.trs");
			stream.skip(Starts.MONSTER_TRS_INDEX);

			SortedMap<Integer, String> indexes1 = new TreeMap<Integer, String>();
			int index1 = 0;
			while (index1 != -1) {
				byte[] d = new byte[4];
				stream.read(d, 0, 4);
				String high1 = String.format("%02X", d[3]);
				String low1 = String.format("%02X", d[2]);
				String high = String.format("%02X", d[1]);
				String low = String.format("%02X", d[0]);
				index1 = new BigInteger(low + high + low1 + high1, 16).intValue();
				if (index1 == -1) break;

				StringBuffer buffer = new StringBuffer();
				byte[] b = new byte[1];
				while (stream.read(b) != -1) {
					if (b[0] != 0) {
						buffer.append(new String(new byte[] {b[0]}));
					} else {
						break;
					}
				}
				indexes1.put(index1, buffer.toString());
			}
			stream.close();

			indexes1.put(0, "first");
			for (Map.Entry<Integer, String> entry : indexes1.entrySet()) {
				System.out.println(entry.getValue() + ": " + entry.getKey());
			}

			Map<String, List<String>> map = new HashMap<String, List<String>>();
			stream = new FileInputStream("Dominions6.exe");

			byte[] b = new byte[32];
			byte[] c = new byte[2];

			long startIndex = Starts.MONSTER;
			stream.skip(startIndex);
			int id = 1;
			Set<String> indexes = new HashSet<String>();
			while (stream.read(b, 0, 32) != -1) {
				stream.skip(4);
				stream.read(c, 0, 2);

				StringBuffer name = new StringBuffer();
				for (int i = 0; i < 32; i++) {
					if (b[i] != 0) {
						name.append(new String(new byte[] {b[i]}));
					}
				}
				if (name.toString().equals("end")) {
					break;
				}
				String index = String.format("%02X", c[1]);
				String offset = String.format("%02X", c[0]);
				indexes.add(index);
				List<String> list = map.get(index);
				if (list == null) {
					list = new ArrayList<String>();
					map.put(index, list);
				}

				String rider = null;
				List<AttributeValue> attributes = getAttributes(startIndex+ Starts.MONSTER_ATTRIBUTE_OFFSET);
				for (AttributeValue attr : attributes) {
					if ("F903".equals(attr.attribute)) {
						String value = attr.values.get(0);
						int parseInt = Integer.parseInt(value);
						if (parseInt < 1000) {
							// direct index
							rider = " rider: " + parseInt;
						} else {
							// mapped index
							byte[] m = ByteBuffer.allocate(4).putInt(parseInt).array();

							String riderIndex = String.format("%02X", m[2]);
							String riderOffset = String.format("%02X", m[3]);
							rider = " rider: "  + riderOffset + " " + riderIndex;

						}
					}
				}

				list.add(id + ": " + name + ": " + offset + " " + index + (rider != null?rider:""));
				System.out.println(id + ":" + name + ": " + offset + " " + index + (rider != null?rider:""));

				id++;
				stream = new FileInputStream(EXE_NAME);		
				startIndex = startIndex + Starts.MONSTER_SIZE;
				stream.skip(startIndex);
			}
			
			TreeSet<String> sorted = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					return Integer.decode("0X" + o1).compareTo(Integer.decode("0X" + o2));
				}
			});
			
			sorted.addAll(indexes);
			int things = 0;
			Iterator<String> iter = sorted.iterator();
			while (iter.hasNext()) {
				String ind = iter.next();
				System.out.println(ind);
				List<String> list = map.get(ind);
				List<SortedByOffset> sortedSet = new ArrayList<SortedByOffset>();
				for (String myList : list) {
					sortedSet.add(new SortedByOffset(myList));
				}
				Collections.sort(sortedSet);
				for (SortedByOffset thing : sortedSet) {
					System.out.println("  " + thing.value);
					things++;
				}
			}
			System.out.println("------------------------");
			System.out.println("Indexes:" + indexes.size() + " Monsters:" + things);

			Map<String, SortedSetAndOffset> indexGroups = indexGroups(indexes1, map);

			for (Map.Entry<Integer, String> entry : indexes1.entrySet()) {
				List<String> mappings = indexToInt.get(entry.getValue());
				if (mappings != null) {
					for (String group : mappings) {
						indexes.remove(group);

						SortedSetAndOffset sortedSetAndOffset = indexGroups.get(entry.getValue()+":"+group);
						for (SortedByOffset ugh : sortedSetAndOffset.sortedSet) {
							int val = sortedSetAndOffset.offset + ugh.getIntValue();
							System.out.println(ugh.value + ": " + val);
							if (val > 0) {
								StringTokenizer tok = new StringTokenizer(ugh.value);
								String idStr = tok.nextToken();
								idStr = idStr.substring(0, idStr.length()-1);
								String oldFileName1 = "monster_" + String.format("%04d", val) + ".tga";
								String oldFileName2 = "monster_" + String.format("%04d", ++val) + ".tga";
								String newFileName1 = String.format("%04d", Integer.parseInt(idStr)) + "_1.tga";
								String newFileName2 = String.format("%04d", Integer.parseInt(idStr)) + "_2.tga";

								System.out.println(oldFileName1 + "->" + newFileName1);
								System.out.println(oldFileName2 + "->" + newFileName2);

								String oldRiderFileName1 = null;
								String oldRiderFileName2 = null;
								String newRiderFileName1 = null;
								String newRiderFileName2 = null;
								Integer riderValue = ugh.getRiderValue();
								if (riderValue != null) {
									int riderVal = 0;
									if (riderValue == 1) {
										riderVal = val + 1;
									} else if (riderValue < 1000) {
										riderVal = riderValue;
									} else {
										if (ugh.getRiderGroup() != null && ugh.getRiderGroup().equals(group)) {
											//riderVal = groupPositiveOffset - groupNegativeOffset + riderValue+2+tweak;
											riderVal = sortedSetAndOffset.offset + riderValue;
										} else {
											// Rider in other group
											System.out.println("Rider in other group: " + ugh.getRiderGroup());
											SortedSetAndOffset sortedSetAndOffset2 = indexGroups.get(entry.getValue()+":"+ugh.getRiderGroup());
											if (sortedSetAndOffset2 != null) {
												riderVal = sortedSetAndOffset2.offset + riderValue;
											} else {
												String entryValue = null;
												if (ugh.getRiderGroup().equals("B2")) {
													entryValue="ind";
												} else if (ugh.getRiderGroup().equals("C1")) {
													entryValue="mount";
												} else if (ugh.getRiderGroup().equals("6B")) {
													entryValue="death";
												} else if (ugh.getRiderGroup().equals("6D")) {
													entryValue="tien 3";
												} else if (ugh.getRiderGroup().equals("69")) {
													entryValue="tien 2";
												}
												sortedSetAndOffset2 = indexGroups.get(entryValue+":"+ugh.getRiderGroup());
												if (sortedSetAndOffset2 != null) {
													riderVal = sortedSetAndOffset2.offset + riderValue;
												} else {
													System.out.println("Group not found: " + entryValue+":"+ugh.getRiderGroup());
												}
											}
										}
									}
									if (riderVal != 0) {
										oldRiderFileName1 = "monster_" + String.format("%04d", riderVal) + ".tga";
										oldRiderFileName2 = "monster_" + String.format("%04d", ++riderVal) + ".tga";
										newRiderFileName1 = String.format("%04d", Integer.parseInt(idStr)) + "_rider_1.tga";
										newRiderFileName2 = String.format("%04d", Integer.parseInt(idStr)) + "_rider_2.tga";
										System.out.println(oldRiderFileName1 + "->" + newRiderFileName1);
										System.out.println(oldRiderFileName2 + "->" + newRiderFileName2);
									} else {
										riderValue = null;
									}
								}

								Path old1 = Paths.get("monsters", oldFileName1);
								Path new1 = Paths.get("monsters", "output", newFileName1);
								Path old2 = Paths.get("monsters", oldFileName2);
								Path new2 = Paths.get("monsters", "output", newFileName2);
								try {
									Files.copy(old1, new1);
								} catch (NoSuchFileException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									Files.copy(old2, new2);
								} catch (NoSuchFileException e) {
									e.printStackTrace();
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (riderValue != null) {
									Path oldRider1 = Paths.get("monsters", oldRiderFileName1);
									Path newRider1 = Paths.get("monsters", "output", newRiderFileName1);
									Path oldRider2 = Paths.get("monsters", oldRiderFileName2);
									Path newRider2 = Paths.get("monsters", "output", newRiderFileName2);
									try {
										Files.copy(oldRider1, newRider1);
									} catch (NoSuchFileException e) {
										e.printStackTrace();
									} catch (Exception e) {
										e.printStackTrace();
									}
									try {
										Files.copy(oldRider2, newRider2);
									} catch (NoSuchFileException e) {
										e.printStackTrace();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

							} else {
								System.err.println("FAILED");
							}

						}
					}
				}
			}

			for (String output : indexes) {
				System.out.println(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, SortedSetAndOffset> indexGroups(SortedMap<Integer, String> indexes1, Map<String, List<String>> map) {
		Map<String, SortedSetAndOffset> newMap = new HashMap<String, SortedSetAndOffset>();
		for (Map.Entry<Integer, String> entry : indexes1.entrySet()) {
			List<String> mappings = indexToInt.get(entry.getValue());
			if (mappings != null) {
				boolean first = true;
				int groupNegativeOffset = 0;
				int groupPositiveOffset = 0;
				for (String group : mappings) {
					List<String> list = map.get(group);
					List<SortedByOffset> sortedSet = new ArrayList<SortedByOffset>();
					for (String myList : list) {
						if (myList.indexOf("smeg") != -1) continue;
						SortedByOffset sortedByOffset = new SortedByOffset(myList);
						if (entry.getValue().equals("first")) {
							if (sortedByOffset.getIntValue() < 458 ||
								sortedByOffset.getIDValue() == 19 ||
								sortedByOffset.getIDValue() == 3821 ||
								sortedByOffset.getIDValue() == 3822 ||
								sortedByOffset.getIDValue() == 3823 ||
								sortedByOffset.getIDValue() == 3832 ||
								sortedByOffset.getIDValue() == 3893
								) {
								sortedSet.add(new SortedByOffset(myList));
							}
						} else if (entry.getValue().equals("gath 3")) {
							if (sortedByOffset.getIntValue() > 460 &&
								sortedByOffset.getIDValue() != 19 &&
								sortedByOffset.getIDValue() != 3821 &&
								sortedByOffset.getIDValue() != 3822 &&
								sortedByOffset.getIDValue() != 3823 &&
								sortedByOffset.getIDValue() != 3832 &&
								sortedByOffset.getIDValue() != 3893
								) {
								sortedSet.add(new SortedByOffset(myList));
							}
						} else if (entry.getValue().equals("gods")) {
							if ((sortedByOffset.getIDValue() < 2932 ||
									sortedByOffset.getIDValue() > 2954) &&
									sortedByOffset.getIDValue() != 2963 &&
									sortedByOffset.getIDValue() != 2964) {
								sortedSet.add(new SortedByOffset(myList));
							}
						} else if (entry.getValue().equals("ur 2")) {
							if ((sortedByOffset.getIDValue() > 2932 &&
									sortedByOffset.getIDValue() < 2954) ||
									sortedByOffset.getIDValue() == 2963 ||
									sortedByOffset.getIDValue() == 2964) {
								sortedSet.add(new SortedByOffset(myList));
							}
						} else {
							sortedSet.add(new SortedByOffset(myList));
						}
					}
					Collections.sort(sortedSet);
					if (first) {
						if (sortedSet.size() == 0) {
							System.err.println("Empty Set: " + group);
							continue;
						}
						SortedByOffset sortedByOffset = sortedSet.get(0);
						groupNegativeOffset = sortedByOffset.getIntValue();
						groupPositiveOffset = entry.getKey();
						first = false;
					}
					int tweak = 0;
					if (entry.getValue().equals("first")) {
						tweak = -2;
					}
					if (entry.getValue().equals("man 3")) {
						tweak = 2;
					}
					if (entry.getValue().equals("misc")) {
						tweak = 2;
					}
					if (entry.getValue().equals("patala 3")) {
						tweak = 2;
					}
					if (entry.getValue().equals("hob 1")) {
						tweak = 5;
					}
					if (entry.getValue().equals("ur")) {
						tweak = -2;
					}
					if (entry.getValue().equals("van 2")) {
						tweak = -2;
					}
					if (entry.getValue().equals("mid 3")) {
						tweak = -2;
					}
					if (entry.getValue().equals("ocean 2")) {
						tweak = 4;
					}
					if (entry.getValue().equals("aby 2")) {
						tweak = 2;
					}
					if (entry.getValue().equals("ermor dead")) {
						tweak = 2;
					}
					if (entry.getValue().equals("ulm 1")) {
						tweak = 2;
					}
					if (entry.getValue().equals("ulm 2")) {
						tweak = 2;
					}
					if (entry.getValue().equals("utg 3")) {
						tweak = 6;
					}
					if (entry.getValue().equals("hel 1")) {
						tweak = 10;
					}
					if (entry.getValue().equals("macha 2")) {
						tweak = 2;
					}
					if (entry.getValue().equals("asp 2")) {
						tweak = 4;
					}
					if (entry.getValue().equals("mict 2")) {
						tweak = 2;
					}
					//					if (entry.getValue().equals("mict 3")) {
					//						tweak = 2;
					//					}
					if (entry.getValue().equals("pelag 2")) {
						tweak = 4;
					}
					if (entry.getValue().equals("arco 3")) {
						tweak = 2;
					}
					//					if (entry.getValue().equals("rus 3")) {
					//						tweak = 2;
					//					}
					if (entry.getValue().equals("hob 1")) {
						tweak = 6;
					}
					if (entry.getValue().equals("hob 3")) {
						tweak = 72;
					}
					if (entry.getValue().equals("Pyr MA")) {
						tweak = 2;
					}
					if (entry.getValue().equals("Plant")) {
						tweak = 8;
					}

					SortedSetAndOffset sortedSetAndOffset = new SortedSetAndOffset();
					sortedSetAndOffset.offset = groupPositiveOffset - groupNegativeOffset + 2 + tweak;
					sortedSetAndOffset.sortedSet = sortedSet;
					System.out.println("Group: " + entry.getValue()+":"+group + ", offset: " + sortedSetAndOffset.offset);
					newMap.put(entry.getValue()+":"+group, sortedSetAndOffset);
				}
			}
		}
		return newMap;
	}
}

class SortedSetAndOffset {
	List<SortedByOffset> sortedSet;
	int offset;
}

class SortedByOffset implements Comparable<SortedByOffset> {
	public String value;
	public SortedByOffset(String value) {
		this.value = value;
	}
	public Integer getIntValue() {
		Stack<String> stack = new Stack<String>();
		StringTokenizer tok = new StringTokenizer(value);
		while (tok.hasMoreTokens()) {
			String nextToken = tok.nextToken();
			if (nextToken.equals("rider:")) break;
			stack.push(nextToken);
		}
		return Integer.decode("0X" + stack.pop() + stack.pop());
	}
	public Integer getRiderValue() {
		Stack<String> stack = new Stack<String>();
		StringTokenizer tok = new StringTokenizer(value);
		boolean hasRider = false;
		while (tok.hasMoreTokens()) {
			String nextToken = tok.nextToken();
			if (nextToken.equals("rider:")) {
				hasRider = true;
			} else if (hasRider) {
				stack.push(nextToken);
			}
		}
		if (hasRider) {
			if (stack.size() == 1) {
				return Integer.parseInt(stack.pop());
			} else {
				return Integer.decode("0X" + stack.pop() + stack.pop());
			}
		}
		return null;
	}
	public String getRiderGroup() {
		Stack<String> stack = new Stack<String>();
		StringTokenizer tok = new StringTokenizer(value);
		boolean hasRider = false;
		while (tok.hasMoreTokens()) {
			String nextToken = tok.nextToken();
			if (nextToken.equals("rider:")) {
				hasRider = true;
			} else if (hasRider) {
				stack.push(nextToken);
			}
		}
		if (hasRider) {
			if (stack.size() == 2) {
				return stack.pop();
			}
		}
		return null;
	}
	public Integer getIDValue() {
		StringTokenizer tok = new StringTokenizer(value, ":");
		return Integer.parseInt(tok.nextToken());
	}
	@Override
	public int compareTo(SortedByOffset o) {
		return this.getIntValue().compareTo(o.getIntValue());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SortedByOffset other = (SortedByOffset) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}

