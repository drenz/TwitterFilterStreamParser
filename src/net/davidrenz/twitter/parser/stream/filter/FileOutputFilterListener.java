package net.davidrenz.twitter.parser.stream.filter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class FileOutputFilterListener implements StatusListener {
	static final Logger log = Logger.getLogger(FileOutputFilterListener.class);

	private String outputFileName = null;
	
	public FileOutputFilterListener(String outputFileName) throws IOException{
		super();
		
		this.outputFileName = outputFileName;
		
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice arg0) {
		log.info("Request to delete status:" + arg0.getStatusId());

	}

	@Override
	public void onException(Exception arg0) {
		arg0.printStackTrace();
		log.error("Twitter Exception", arg0);
	}

	@Override
	public void onStatus(Status status) {
		
		// write the status to the given file 
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(outputFileName, true));
			out.write(status.getUser().getScreenName() + ": " +status.getText()+"\n");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Status Error", e);
		}

	}

	@Override
	public void onTrackLimitationNotice(int arg0) {
		log.error("Status Limitation: " + arg0 + " status were limited");

	}

}
