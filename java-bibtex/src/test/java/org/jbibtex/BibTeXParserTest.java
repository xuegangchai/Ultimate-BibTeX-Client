/*
 * Copyright (c) 2012 University of Tartu
 */
package org.jbibtex;

import java.io.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;

public class BibTeXParserTest {

	@Before
	public void initAckMacros(){
		String[] macros = {"ack-bnb", "ack-bs", "ack-hk", "ack-kl", "ack-kr", "ack-pb", "ack-rfb"};

		for(String macro : macros){
			BibTeXParser.addMacro(macro, macro);
		}
	}

	@Test
	public void parseJava() throws Exception {
		BibTeXDatabase database = parse("/java.bib");

		List<BibTeXObject> objects = database.getObjects();
		assertEquals(4498, objects.size());

		Map<Key, BibTeXString> strings = database.getStrings();
		assertEquals(467, strings.size());

		Map<Key, BibTeXEntry> entries = database.getEntries();
		assertEquals(4030, entries.size());
	}

	@Test
	public void parseUnix() throws Exception {
		BibTeXDatabase database = parse("/unix.bib");

		List<BibTeXObject> objects = database.getObjects();
		assertEquals(2632, objects.size());

		Map<Key, BibTeXString> strings = database.getStrings();
		assertEquals(358, strings.size());

		Map<Key, BibTeXEntry> entries = database.getEntries();
		assertEquals(2273, entries.size());
	}

	static
	private BibTeXDatabase parse(String path) throws IOException, ParseException {
		InputStream is = (BibTeXParserTest.class).getResourceAsStream(path);

		try {
			Reader reader = new InputStreamReader(is, "US-ASCII");

			try {
				BibTeXParser parser = new BibTeXParser();

				return parser.parse(reader);
			} finally {
				reader.close();
			}
		} finally {
			is.close();
		}
	}
}