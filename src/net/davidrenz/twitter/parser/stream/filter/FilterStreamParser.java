package net.davidrenz.twitter.parser.stream.filter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import net.davidrenz.twitter.parser.util.PropertyUtil;

import org.apache.log4j.Logger;

import twitter4j.FilterQuery;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.http.Authorization;

public class FilterStreamParser {

	private Authorization authorization = null;

	private String username = null;
	private String password = null;

	private StatusListener listener;

	static final Logger log = Logger.getLogger(FilterStreamParser.class);

	public FilterStreamParser(Authorization authorization,
			StatusListener listener) {

		setAuthorization(authorization);
		setListener(listener);

	}

	public FilterStreamParser(String username, String password,
			StatusListener listener) {

		setUsername(username);
		setPassword(password);
		setListener(listener);

	}

	public void filter(ArrayList<String> keywords, ArrayList<Integer> userIds)
			throws TwitterException {

		// copy for internal use
		String[] keys = new String[keywords.size()];
		
		for (int i = 0; i < keywords.size(); i++) {
			keys[i] = keywords.get(i);
		}
		
		int[] users = new int[userIds.size()];
		for (int i = 0; i < userIds.size(); i++) {
			users[i] = userIds.get(i);
		}

		TwitterStream twitterStream = null;

		if (getUsername() != null && getPassword() != null) {
			twitterStream = new TwitterStreamFactory(getListener())
					.getInstance(getUsername(), getPassword());
		} else if (getAuthorization() != null) {
			twitterStream = new TwitterStreamFactory(getListener())
					.getInstance(getAuthorization());
		} else {
			throw new TwitterException("No Twitter Identification given");
		}

		FilterQuery query = new FilterQuery(0, users, keys);

		twitterStream.filter(query);

	}

	private Authorization getAuthorization() {
		return authorization;
	}

	private void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	private String getUsername() {
		return username;
	}

	private void setUsername(String username) {
		this.username = username;
	}

	private String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}

	private StatusListener getListener() {
		return listener;
	}

	private void setListener(StatusListener listener) {
		this.listener = listener;
	}

}
