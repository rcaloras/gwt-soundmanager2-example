package com.chj.client.sound;

import java.util.ArrayList;

import com.chj.client.JukeboxView;
import com.chj.client.sound.SoundFactory.PlayingCallback;
import com.chj.gwt.client.soundmanager2.Callback;

public class ClientJukebox {
	
	/* For actually playing music and songs */
	private SoundFactory soundFactory;
	
	final JukeboxView view;

	/* The queue of requests */
	private ArrayList<String> jukeboxQueue = new ArrayList<String>();

	private String lastPlayed = null;

	Boolean isPaused = false;
	
	/* These should be injected with something like GIN */
	public ClientJukebox(SoundFactory soundFactory, final JukeboxView view){
		this.soundFactory = soundFactory;
		this.view = view;
		soundFactory.onStartup(new Callback(){

			public void execute() {
				view.setButtonsEnabled();
				nextSong();
			}
			
		});
		soundFactory.onStartError(new Callback(){

			public void execute() {
				view.getInfo().setInnerText("SoundManager Encountered an error while loading");
			}
		});
		
		soundFactory.setPlayingCallback(new PlayingCallback(){

			public void fire(int now, int end) {
				String total = ""+end/60+":"+end%60;
				String current= ""+now/60+":"+now%60;
				view.getInfo().setInnerText(lastPlayed+"  - "+current+" /"+total);
			}
			
		});
	}
	
	
	public void stopPlaying(){
		soundFactory.stopPlaying();
		isPaused = false;
		view.getPlayButton().setText("Play");
		view.getInfo().setInnerText("");
	}
	
	public void pausePlaying(){
		if(isPaused) {
			resumePlaying();
		}
		else {
			soundFactory.pausePlaying();
			isPaused = true;
			view.getPlayButton().setText("Play");
		}
	}
	
	public void resumePlaying(){
		soundFactory.resumePlaying();
		isPaused = false;
		view.getPlayButton().setText("Pause");
	}

	public boolean nextSong() {
		if (jukeboxQueue.isEmpty()) {

			/* Check if we've already stopped playing */
			stopPlaying();
			return false;
		}

		/* Pop the next song */
		String next = jukeboxQueue.remove(0);
		playRequest(next);
		
		/* Creates a circular queue */
		queueRequest(next);
		
		return true;
	}
	
	
	private void playRequest(final String request) {
		lastPlayed = request;

		/* Create a callback */
		soundFactory.playSong(request, new Callback(){

			public void execute() {
				nextSong();
			}
			
		});

		isPaused = false;
		view.getInfo().setInnerHTML(request);
		view.getPlayButton().setText("Pause");
	}
	
	public boolean isSoundPlayin(){
		return soundFactory.isSoundPlaying();
	}

	public boolean queueRequest(String request) {
		
		if(request == null){
			return false;
		}
		return jukeboxQueue.add(request);
	}

	public ArrayList<String> getJukeboxQueue() {
		return jukeboxQueue;
	}

	public void setJukeboxQueue(ArrayList<String> jukeboxQueue) {
		this.jukeboxQueue = jukeboxQueue;
	}

	public String getLastPlayed() {
		return lastPlayed;
	}

	public void setLastPlayed(String lastPlayed) {
		this.lastPlayed = lastPlayed;
	}

	public Boolean getIsPaused() {
		return isPaused;
	}

	public void setIsPaused(Boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	
	

}
