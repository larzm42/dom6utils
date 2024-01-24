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
import java.util.List;

public class SpellDescDumper {
	
	public static void main(String[] args) {
		run();
	}
	
	public static void run() {		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream("Dominions6.exe");
			stream.skip(Starts.SPELL_DESC_INDEX);

			List<Long> indexes = new ArrayList<Long>();
			long index = -1;
			while (index != 0) {
				byte[] c = new byte[8];
				stream.read(c, 0, 8);
				String high0 = String.format("%02X", c[7]);
				String low0 = String.format("%02X", c[6]);
				String high1 = String.format("%02X", c[5]);
				String low1 = String.format("%02X", c[4]);
				String high2 = String.format("%02X", c[3]);
				String low2 = String.format("%02X", c[2]);
				String high3 = String.format("%02X", c[1]);
				String low3 = String.format("%02X", c[0]);
				
				index = new BigInteger(high0 + low0 + high1 + low1 + high2 + low2 + high3 + low3, 16).longValue();
				
				if (index != 0) {
					indexes.add(index);
				}
			}
			stream.close();
			
			Path spellsDescPath = Files.createDirectories(Paths.get("spells"));
			Files.walkFileTree(spellsDescPath, new DirCleaner());
			
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
					String name = buffer.toString().substring(1);
					if ("CalltheWormthatWalks".equals(name)) {
						name = "CalltheWormThatWalks";
					}
					names.add(name);
					desc = null;
				} else {
					desc = buffer.toString();
				}

				if (names.size() > 0 && desc != null) {
					for (String name : names) {
						Path path = Paths.get("spells", name.replaceAll("[^a-zA-Z0-9\\-]", "") + ".txt");
						OutputStream os = Files.newOutputStream(path);
						os.write(desc.getBytes());
						os.close();
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
