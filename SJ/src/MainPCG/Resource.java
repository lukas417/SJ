package MainPCG;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Resource {

	public static String[][] LoadTable() {
		try {
			List<String> content;
			content = Files.readAllLines(Paths.get("table.txt"));
			
			String[][] matrix = new String[content.size()][];
			int i = 0;
			for(String row : content) {
				matrix[i] = row.split("\\s+");
				++i;
			}
			
			for(int j = 1; j < matrix.length; ++j) {
				for(int k = 0; k < matrix[0].length; ++k) {
					if("-".equals(matrix[j][k])) {
						matrix[j][k] = "";
					}
				}
			}
			
			return matrix;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<String> LoadRules() {
		try {
			return Files.readAllLines(Paths.get("rules.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
