package net.davidrenz.twitter.parser.stream.filter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.davidrenz.twitter.parser.util.PropertyUtil;

import org.apache.log4j.Logger;

import twitter4j.TwitterException;

public class FilterStreamParserDriver {
	static final Logger log = Logger.getLogger(FilterStreamParserDriver.class);
	
	public static void main(String[] args)throws TwitterException, IOException {
		
		ArrayList<String> keywords = new ArrayList<String>();
		ArrayList<Integer> users = new ArrayList<Integer>();
		
		
		FileOutputFilterListener listener = new FileOutputFilterListener(PropertyUtil.getProperyFile().getProperty("file.textoutput"));
		
		// Create a FilterStreamParser object 
		FilterStreamParser parser = new FilterStreamParser(PropertyUtil.getProperyFile().getProperty("twitter.screenname"),PropertyUtil.getProperyFile().getProperty("twitter.password"), listener);
		
		
		// Acquire keywords for input to the filter stream
		// read in each keyword from a file
		keywords.add("twitter");
		
		// Acquire users for input to the filter stream
		// read each user id from the user ids file
		users.add(0);
		
		// Kick off parser
		try{
			parser.filter(keywords, users);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
}
