package com.chj.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class JukeboxView {

	final Button playButton = new Button("play");
	final Button stopButton = new Button("stop");
	final Button nextButton = new Button("next");
	final Element info = DOM.getElementById("infoFieldContainer");

	public JukeboxView(){

		playButton.setEnabled(false);
		stopButton.setEnabled(false);
		nextButton.setEnabled(false);
		RootPanel.get("playButtonContainer").add(playButton);
		RootPanel.get("stopButtonContainer").add(stopButton);
		RootPanel.get("nextButtonContainer").add(nextButton);
	}

	public Button getPlayButton() {
		return playButton;
	}

	public Button getStopButton() {
		return stopButton;
	}

	public Button getNextButton() {
		return nextButton;
	}

	public Element getInfo() {
		return info;
	}
	public void setButtonsEnabled(){
		nextButton.setEnabled(true);
		playButton.setEnabled(true);
		stopButton.setEnabled(true);
	}
}