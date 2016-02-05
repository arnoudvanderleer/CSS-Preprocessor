package com.tempestasludi.java.p14_cssp.preprocessor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.tempestasludi.java.p14_cssp.pcss.general.Document;
import com.tempestasludi.java.p14_cssp.pcss.properties.Variable;

/**
 *
 * @author Tempestas Ludi
 */
public class Preprocessor {

	/**
	 * Private class constructor.
	 */
	private Preprocessor() {
	}

	public static String preprocess(String filename, String saveTo) throws IOException {
		String processed = preprocess(filename);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new File(saveTo)), "UTF-8");
		writer.write(processed);
		writer.flush();
		writer.close();
		return processed;
	}

	public static String preprocess(String filename) throws IOException {
		String file = readFile(filename);
		Document document = Document.read(file);
		Document preprocessed = (Document) document.preprocess(new ArrayList<Variable>()).get(0);
		return preprocessed.toString();
	}

	private static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, "UTF-8");
	}

}
