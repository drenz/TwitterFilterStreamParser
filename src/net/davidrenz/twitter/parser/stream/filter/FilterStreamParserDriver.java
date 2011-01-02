package net.davidrenz.twitter.parser.stream.filter;

import java.io.BufferedReader;
import java.io.FileReader;
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
		

		System.out.println(PropertyUtil.getProperyFile().getProperty("file.keywords"));
		
		// Acquire keywords for input to the filter stream
		// read in each keyword from a file
		BufferedReader br = new BufferedReader(new FileReader(PropertyUtil.getProperyFile().getProperty("file.keywords")));
		String line = null;
		
		while ((line = br.readLine()) != null){
			keywords.add(line.trim());
		}
		
		br.close();
		
		
		br = new BufferedReader(new FileReader(PropertyUtil.getProperyFile().getProperty("file.users")));
		

		// Acquire users for input to the filter stream
		// read each user id from the user ids file
		while ((line = br.readLine()) != null){
			users.add(new Integer(line.trim()));
		}
		br.close();

		// Kick off parser
		try{
			parser.filter(keywords, users);
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	

	
	
}
