package com.chj.client;

import java.util.ArrayList;

import com.chj.client.sound.ClientJukebox;
import com.chj.client.sound.SMSoundFactory;
import com.chj.gwt.client.soundmanager2.Callback;
import com.chj.gwt.client.soundmanager2.SMSound;
import com.chj.gwt.client.soundmanager2.SoundManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtSoundManager2Example implements EntryPoint {


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		JukeboxView view = new JukeboxView();
		final ClientJukebox clientJukebox = new ClientJukebox(new SMSoundFactory(), view);
		clientJukebox.queueRequest("sweet_dreams.mp3");
		clientJukebox.queueRequest("ask_for_janice.mp3");

		view.getNextButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
			clientJukebox.nextSong();
			}

		});
		
		view.getPlayButton().addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				if(clientJukebox.getIsPaused()){
					clientJukebox.resumePlaying();
				}
				else if(clientJukebox.isSoundPlayin()){
					clientJukebox.pausePlaying();
				}
				else{
					clientJukebox.nextSong();
				}
				
			}
		});
		
		view.getStopButton().addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				clientJukebox.stopPlaying();
			}

		});
	}
	
}
