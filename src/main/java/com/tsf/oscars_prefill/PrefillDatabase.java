package com.tsf.oscars_prefill;
// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.tsf.oscars_prefill.*;

// TODO:
// Seperate out file config to constructor


public class PrefillDatabase
{
    private static final Logger log = LoggerFactory.getLogger(PrefillDatabase.class);
    private String JsonFilePath;

    public PrefillDatabase(String filePath) throws FileNotFoundException {
        File f = new File(filePath);

        if (f.exists()) {
            this.JsonFilePath = filePath;
        } else {
            throw new FileNotFoundException("Please specify a different filepath to PrefillDatabase constructor");
        }
    }

    public ArrayList<OscarNominationEntry> getEntriesArray() throws Exception {
        // Return object
        ArrayList<OscarNominationEntry> returnObject = new ArrayList<OscarNominationEntry>();

		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(new FileReader(this.JsonFilePath));
		
		// typecasting obj to JSONObject
		JSONArray jo = (JSONArray) obj;
        Iterator<JSONObject> iterator = jo.iterator();
        
        // Return list from JSON file

        while (iterator.hasNext()) {
            JSONObject entryJson = iterator.next();
            Long year = (Long) entryJson.get("year");
            String category = (String) entryJson.get("category");
            Boolean winner = (Boolean) entryJson.get("winner");
            String entity = (String) entryJson.get("entity");
            OscarNominationEntry newEntry = new OscarNominationEntry(year, category, winner, entity);
            returnObject.add(newEntry);
        }
        return returnObject;
    }
}
