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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dom6utils.CSVWriter.Delimiter;
import dom6utils.CSVWriter.SSType;

public class SiteStatIndexer extends AbstractStatIndexer {
	public static String[] site_columns = {"id", "name", "rarity", "loc", "level", "path", "F", "A", "W", "E", "S", "D", "N", "G", "B", "gold", "res", 
			"sup", "unr", "exp", "lab", "fort", "scale1", "scale2", "domspread", "turmoil", "sloth", "cold", "death", "misfortune", "drain", 
			"fireres", "coldres", "shockres", "poisonres", "str", "prec", "mor", "undying", "att", "darkvision", "aawe", "rit", "ritrng", 
			"hmon1", "hmon2", "hmon3", "hmon4", "hmon5", "voidgate", "sum1", "n_sum1", "sum2", "n_sum2", "sum3", "n_sum3", "conj", "alter", "evo", 
			"const", "ench", "thau", "blood", "heal", "disease", "curse", "horror", "holyfire", "holypow", "scry", "adventure", "other", "sum4", "n_sum4", 
			"hcom1", "hcom2", "hcom3", "hcom4", "hcom5", "mon1", "mon2", "mon3", "mon4", "mon5", "com1", "com2", "com3", "com4", "com5", "reveal", 
			"provdef1", "provdef2", "def", "F2", "A2", "W2", "E2", "S2", "D2", "N2", "B2", "awe", "reinvigoration", "airshield", "provdefcom", 
			"domconflict", "sprite", "nationalrecruits", "natmon","natcom", "throneclustering","wilddefenders", "domconflict", "rituallevelmodifier",
			"callgodbonus", "magicresistancebonus", "bringgold", "scorch", "evil", "scryrange", "addtolimitedrecruitment", "ageratereduction", "dragonlord", "corpselord", "ivylord", "maximizeorder",
			"pdconscript", "mr", "bringres", "provinc", "recpoints", "recpointpercent", "recpointpercentcmd", "agingpercent", "unaging", "popgrowth", "end"};																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									

	private static String[][] KNOWN_SITE_ATTRS = {
			{"2602", "summon1"},
			{"2702", "summon2"},
			{"2802", "summon3"},
			{"2902", "summon4"},
			{"2A02", "summon5"},
			{"2B02", "summon6"},
			{"2C02", "summon7"},
			{"2D02", "summon8"},
			{"2E02", "summon9"},
			{"2F02", "summon10"},
			
			{"1A00", "recpoints"},
			{"6D01", "pdconscript"},
			{"EB03", "mr"},
			{"8001", "bringres"},
			{"0D00", "provinc"},
			{"C702", "recpointpercent"},
			{"C802", "recpointpercentcmd"},
			{"C902", "agingpercent"},
			{"FC03", "unaging"},
			{"1B00", "popgrowth"},
			
			{"0100", "F"},
			{"0200", "A"},
			{"0300", "W"},
			{"0400", "E"},
			{"0500", "S"},
			{"0600", "D"},
			{"0700", "N"},
			{"0800", "G"},
			{"0900", "B"},
			{"9302", "gold"},
			{"0E00", "res"},
			{"1400", "sup"},
			{"1300", "unr"},
			{"1600", "exp"},
			{"0F00", "lab"},
			{"1100", "fort"},
			{"1E00", "hcom#"},
			{"1D00", "hmon#"},
			{"0C00", "com#"},
			{"0B00", "mon#"},
			{"3C00", "conj"},
			{"3D00", "alter"},
			{"3E00", "evo"},
			{"3F00", "const"},
			{"4000", "ench"},
			{"4100", "thau"},
			{"4200", "blood"},
			{"4600", "heal"},
			{"1500", "disease"},
			{"4700", "curse"},
			{"1800", "horror"},
			{"4400", "holyfire"},
			{"4300", "holypow"},
			{"4800", "scry"},
			{"C000", "adventure"},
			{"3900", "voidgate"},
			{"1501", "domspread"},
			{"1901", "turmoil"},
			{"1A01", "sloth"},
			{"1B01", "cold"},
			{"1C01", "death"},
			{"1D01", "misfortune"},
			{"1E01", "drain"},
			{"EF03", "fireres"},
			{"F003", "coldres"},
			{"EE03", "str"},
			{"0402", "prec"},
			{"E803", "mor"},
			{"F103", "shockres"},
			{"EC03", "undying"},
			{"E903", "att"},
			{"F203", "poisonres"},
			{"F703", "darkvision"},
			{"F503", "aawe"},
			{"1401", "throne"},
			{"0A01", "fortparts"},
			{"0601", "reveal"},
			{"E000", "provdef#"},
			{"EA03", "def"},
			{"0202", "awe"},
			{"F303", "reinvigoration"},
			{"0002", "airshield"},
			{"4A00", "provdefcom"},
			{"7501", "nationalrecruits"},
			{"7601", "natmon"},
			{"7701", "natcom"},
			{"1601", "throneclustering"},
			{"2401", "wilddefenders"},
			{"2A00", "domconflict"},
			{"6A01", "rituallevelmodifier"},
			{"7101", "callgodbonus"},
			{"F701", "magicresistancebonus"},
			{"FA00", "firerange"},
			{"FB00", "airrange"},
			{"FC00", "waterrange"},
			{"FD00", "earthrange"},
			{"FE00", "astralrange"},
			{"FF00", "deathrange"},
			{"0001", "naturerange"},
			{"0101", "glamourrange"},
			{"0201", "bloodrange"},
			{"0301", "elementrange"},
			{"0401", "sorceryrange"},
			{"0501", "allrange"},
			
			{"6400", "incscale1"},
			{"6600", "incscale2"},			
			{"6800", "incscale3"},
			{"6A00", "incscale4"},
			{"6700", "deccscale2"},

			{"7F01", "bringgold"},
			{"7602", "scorch"},
			
			{"2301", "evil"},
			{"4900", "scryrange"},
			{"7901", "addtolimitedrecruitment"},
			{"7E01", "ageratereduction"},
			{"8F01", "dragonlord"},
			{"8E01", "corpselord"},
			{"8D01", "ivylord"},
			{"9101", "maximizeorder"}
			
// 7902 is used only in code for the unused site effect 0x165. It isn't clear what this does, but it checks for End of Culture...
// E100 horses vale only, has something to do with the PD altering effects
// E700 spirit pact only, may be setting pd commander?

	};
	


	public static void main(String[] args) {
		run();
	}
	
	public static void run() {
		FileInputStream stream = null;
        List<Site> siteList = new ArrayList<Site>();

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("sites.txt"));
			BufferedWriter writerUnknown = new BufferedWriter(new FileWriter("sitesUnknown.txt"));
	        long startIndex = Starts.SITE;
	        int ch;
			stream = new FileInputStream(EXE_NAME);			
			stream.skip(Starts.SITE);
			List<String> unknown = new ArrayList<String>();

			// Name
			InputStreamReader isr = new InputStreamReader(stream, "ISO-8859-1");
	        Reader in = new BufferedReader(isr);
	        int rowNumber = 1;
			while ((ch = in.read()) > -1) {
				StringBuffer name = new StringBuffer();
				while (ch != 0) {
					name.append((char)ch);
					ch = in.read();
				}
				if (name.length() == 0) {
					continue;
				}
				if (name.toString().equals("end")) {
					break;
				}
				in.close();

				Site site = new Site();
				site.parameters = new TreeMap<String, Object>();
				site.parameters.put("id", rowNumber);
				site.parameters.put("name", name.toString());
				short rarity = getBytes1(startIndex + 42);
				site.parameters.put("rarity", rarity == -1 ? "0" : rarity);
				site.parameters.put("loc", getBytes4(startIndex + 304));
				site.parameters.put("level", getBytes2(startIndex + 40));
				String[] paths = {"Fire", "Air", "Water", "Earth", "Astral", "Death", "Nature", "Glamour", "Blood", "Holy"};
				int[] spriteOffset = {1, 10, 19, 29, 41, 48, 57, 68, 82, 91};
				short path = getBytes1(startIndex + 38);
				short sprite = getBytes1(startIndex + 36);
				site.parameters.put("path", path == -1 ? "" : paths[path]);
				site.parameters.put("sprite", path == -1 ? "" : spriteOffset[path] + sprite);
				
				List<AttributeValue> attributes = getAttributes(startIndex + Starts.SITE_ATTRIBUTE_OFFSET);
				for (AttributeValue attr : attributes) {
					boolean found = false;
					for (int x = 0; x < KNOWN_SITE_ATTRS.length; x++) {
						if (KNOWN_SITE_ATTRS[x][0].equals(attr.attribute)) {
							found = true;
							if (KNOWN_SITE_ATTRS[x][1].endsWith("#")) {
								int i = 1;
								for (String value : attr.values) {
									site.parameters.put(KNOWN_SITE_ATTRS[x][1].replace("#", i+""), Integer.parseInt(value));
									i++;
								}
							} else {
								switch (KNOWN_SITE_ATTRS[x][1]) {
								case ("conj"):
								case ("alter"):
								case ("evo"):
								case ("const"):
								case ("ench"):
								case ("thau"):
								case ("blood"):
								case ("heal"):
								case ("disease"):
								case ("curse"):
								case ("horror"):
								case ("holyfire"):
								case ("holypow"):
								case ("voidgate"):
									site.parameters.put(KNOWN_SITE_ATTRS[x][1], attr.values.get(0)+"%");
									break;
								case ("lab"):
									site.parameters.put(KNOWN_SITE_ATTRS[x][1], "lab");
									break;
								case ("unr"):
									site.parameters.put(KNOWN_SITE_ATTRS[x][1], -Integer.parseInt(attr.values.get(0)));
									break;
								default:
									site.parameters.put(KNOWN_SITE_ATTRS[x][1], attr.values.get(0));
								}

							}
						}
					}
					if (!found) {
						site.parameters.put("\tUnknown Attribute<" + attr.attribute + ">", attr.values.get(0));							
						unknown.add(attr.attribute);
					}
				}
				
				// scales
				String[] scales = {"Turmoil", "Sloth", "Cold", "Death", "Misfortune", "Drain"};
				String[] opposite = {"Order", "Productivity", "Heat", "Growth", "Luck", "Magic"};
				String scalesValue[] = {"", ""};
				int index = 0;
				
				if (attributes.contains(new AttributeValue("6400"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("6400")));
					for (String vals : attributeValue.values) {
						scalesValue[index++] = opposite[Integer.parseInt(vals)] + "(max 1)";
					}
				}
				if (attributes.contains(new AttributeValue("6600"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("6600")));
					for (String vals : attributeValue.values) {
						scalesValue[index++] = opposite[Integer.parseInt(vals)] + "(max 2)";
					}
				}
				if (attributes.contains(new AttributeValue("6800"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("6800")));
					for (String vals : attributeValue.values) {
						scalesValue[index++] = opposite[Integer.parseInt(vals)] + "(max 3)";
					}
				}
				if (attributes.contains(new AttributeValue("6A00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("6A00")));
					for (String vals : attributeValue.values) {
						scalesValue[index++] = opposite[Integer.parseInt(vals)] + "(max 4)";
					}
				}
				if (attributes.contains(new AttributeValue("6700"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("6700")));
					for (String vals : attributeValue.values) {
						scalesValue[index++] = scales[Integer.parseInt(vals)] + "(max 2)";
					}
				}
				site.parameters.put("scale1", scalesValue[0]);
				site.parameters.put("scale2", scalesValue[1]);
				
				// rit/ritrng
				boolean[] boolPaths = {false, false, false, false, false, false, false, false, false};
				String[] boolPathsStr = {"F", "A", "W", "E", "S", "D", "N", "G", "B"};
				String value = "";
				if (attributes.contains(new AttributeValue("FA00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FA00")));
					value = attributeValue.values.get(0);
					boolPaths[0] = true;
				}
				if (attributes.contains(new AttributeValue("FB00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FB00")));
					value = attributeValue.values.get(0);
					boolPaths[1] = true;
				}
				if (attributes.contains(new AttributeValue("FC00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FC00")));
					value = attributeValue.values.get(0);
					boolPaths[2] = true;
				}
				if (attributes.contains(new AttributeValue("FD00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FD00")));
					value = attributeValue.values.get(0);
					boolPaths[3] = true;
				}
				if (attributes.contains(new AttributeValue("FE00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FE00")));
					value = attributeValue.values.get(0);
					boolPaths[4] = true;
				}
				if (attributes.contains(new AttributeValue("FF00"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("FF00")));
					value = attributeValue.values.get(0);
					boolPaths[5] = true;
				}
				if (attributes.contains(new AttributeValue("0001"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0001")));
					value = attributeValue.values.get(0);
					boolPaths[6] = true;
				}
				if (attributes.contains(new AttributeValue("0101"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0101")));
					value = attributeValue.values.get(0);
					boolPaths[7] = true;
				}
				if (attributes.contains(new AttributeValue("0201"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0201")));
					value = attributeValue.values.get(0);
					boolPaths[8] = true;
				}
				if (attributes.contains(new AttributeValue("0301"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0301")));
					value = attributeValue.values.get(0);
					boolPaths = new boolean[]{true, true, true, true, false, false, false, false, false};
				}
				if (attributes.contains(new AttributeValue("0401"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0401")));
					value = attributeValue.values.get(0);
					boolPaths = new boolean[]{false, false, false, false, true, true, true, true, true};
				}
				if (attributes.contains(new AttributeValue("0501"))) {
					AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue("0501")));
					value = attributeValue.values.get(0);
					boolPaths = new boolean[]{true, true, true, true, true, true, true, true, true};
				}
				StringBuffer rit = new StringBuffer();
				for (int x = 0; x < boolPaths.length; x++) {
					if (boolPaths[x]) {
						rit.append(boolPathsStr[x]);
					}
				}
				site.parameters.put("rit", rit.toString());
				site.parameters.put("ritrng", value);
				
				// summoning
				Summon summon = new Summon();
				updateSummon(attributes, "2602", 1, summon);
				updateSummon(attributes, "2702", 2, summon);
				updateSummon(attributes, "2802", 3, summon);
				updateSummon(attributes, "2902", 4, summon);
				updateSummon(attributes, "2A02", 5, summon);
				updateSummon(attributes, "2B02", 6, summon);
				updateSummon(attributes, "2C02", 7, summon);
				updateSummon(attributes, "2D02", 8, summon);
				updateSummon(attributes, "2E02", 9, summon);
				updateSummon(attributes, "2F02", 10, summon);
								
				if (summon.sum1 != null) {
					site.parameters.put("sum1", summon.sum1);
					site.parameters.put("n_sum1", summon.sum1count);
				}
				if (summon.sum2 != null) {
					site.parameters.put("sum2", summon.sum2);
					site.parameters.put("n_sum2", summon.sum2count);
				}
				if (summon.sum3 != null) {
					site.parameters.put("sum3", summon.sum3);
					site.parameters.put("n_sum3", summon.sum3count);
				}
				if (summon.sum4 != null) {
					site.parameters.put("sum4", summon.sum4);
					site.parameters.put("n_sum4", summon.sum4count);
				}

				siteList.add(site);

				stream = new FileInputStream(EXE_NAME);		
				startIndex = startIndex + Starts.SITE_SIZE;
				stream.skip(startIndex);
				isr = new InputStreamReader(stream, "ISO-8859-1");
		        in = new BufferedReader(isr);

				rowNumber++;
			}
			in.close();
			stream.close();
			
			//make sure there's a place to put csv files
			CSVWriter.createCSVOutputDirectory();

			XSSFWorkbook wb = new XSSFWorkbook();
			FileOutputStream fos = CSVWriter.getFOS("MagicSites", SSType.XLSX);
			BufferedWriter   csv = CSVWriter.getBFW("MagicSites", SSType.CSV);
			XSSFSheet sheet = wb.createSheet();

			int rowNum = 0;
			for (Site site : siteList) {
				if (rowNum == 0) {
					XSSFRow row = sheet.createRow(rowNum);
					for (int i = 0; i < site_columns.length; i++) {
						row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(site_columns[i]);
					}
					rowNum++;
				}
				XSSFRow row = sheet.createRow(rowNum);
				for (int i = 0; i < site_columns.length; i++) {
					Object object = site.parameters.get(site_columns[i]);
					if (object != null) {
						row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).setCellValue(object.toString());
					}
				}
				
				dumpTextFile(site, writer);
				rowNum++;
			}
			
			dumpUnknown(unknown, writerUnknown);

			writer.close();
			writerUnknown.close();
			
			wb.write(fos);
			fos.close();
			CSVWriter.writeSimpleCSV(sheet, csv, Delimiter.TAB);
			csv.close();
			wb.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void updateSummon(List<AttributeValue> attributes, String id, int count, Summon summon) {
		if (attributes.contains(new AttributeValue(id))) {
			AttributeValue attributeValue = attributes.get(attributes.indexOf(new AttributeValue(id)));
			for (String val : attributeValue.values) {
				if (summon.sum1 == null) {
					summon.sum1 = val;
					summon.sum1count = count;
				} else if (summon.sum2 == null) {
					summon.sum2 = val;
					summon.sum2count = count;
				} else if (summon.sum3 == null) {
					summon.sum3 = val;
					summon.sum3count = count;
				} else if (summon.sum4 == null) {
					summon.sum4 = val;
					summon.sum4count = count;
				}
			}
		}

	}
	
	private static void dumpTextFile(Site site, BufferedWriter writer) throws IOException {
		Object name = site.parameters.get("name");
		Object id = site.parameters.get("id");
		writer.write(name.toString() + "(" + id + ")");
		writer.newLine();
		for (Map.Entry<String, Object> entry : site.parameters.entrySet()) {
			if (!entry.getKey().equals("name") && !entry.getKey().equals("id") && entry.getValue() != null && !entry.getValue().equals("")) {
				writer.write("\t" + entry.getKey() + ": " + entry.getValue());
				writer.newLine();
			}
		}
		writer.newLine();
	}

	private static class Site {
		Map<String, Object> parameters;
	}
	
	private static class Summon {
		String sum1 = null;
		int sum1count = 0;
		String sum2 = null;
		int sum2count = 0;
		String sum3 = null;
		int sum3count = 0;
		String sum4 = null;
		int sum4count = 0;
	}

}
