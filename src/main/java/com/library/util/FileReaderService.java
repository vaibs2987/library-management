package com.library.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileReaderService {

	public List<String> readFile(String path) {
		ClassLoader classLoader = getClass().getClassLoader();
		BufferedReader br = null;
		FileReader reader = null;
		List<String> list = new ArrayList<>();
		try {
			reader = new FileReader(classLoader.getResource(path).getFile());
			br = new BufferedReader(reader);
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				list.add(currentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}
}
