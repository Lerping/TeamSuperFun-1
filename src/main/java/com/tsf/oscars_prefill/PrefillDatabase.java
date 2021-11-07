package com.tsf.oscars_prefill;
// Java program to read JSON from a file

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.tsf.oscars_prefill.*;

// TODO:
// Seperate out file config to constructor


public class PrefillDatabase
{
	public PrefillDatabase()
	{

	}

    public ArrayList<Entry> getEntriesArray() throws Exception {
        // Return object
        ArrayList<Entry> returnObject = new ArrayList<Entry>();

		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(new FileReader(".\\src\\main\\resources\\json_example.json"));
		
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
            Entry newEntry = new Entry(year, category, winner, entity);
            returnObject.add(newEntry);
        }
        return returnObject;
    }
}
