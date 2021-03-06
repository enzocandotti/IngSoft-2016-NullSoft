package model;

import java.util.ArrayList;

import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class BeatModel implements BeatModelInterface, MetaEventListener {
	Sequencer sequencer;

	ArrayList beatObservers = new ArrayList();
	ArrayList bpmObservers = new ArrayList();
	ArrayList bulletObservers = new ArrayList();

	int bpm = 90;
	Sequence sequence;
	Track track;

	public void initialize() {
		setUpMidi();
		buildTrackAndStart();
	}

	public void on() {
		sequencer.start();
		// setBPM(90);
		setBPM(getBPM());
	}

	public void off() {
		setBPM(0);
		sequencer.stop();
	}

	public void setBPM(int bpm) {
		this.bpm = bpm;

		// FIX
		sequencer.setMicrosecondPosition(0);
		sequencer.setTickPosition(0);

		sequencer.setTempoInBPM(getBPM());
		notifyBPMObservers();
	}

	public int getBPM() {
		return bpm;
	}

	void beatEvent() {
		notifyBeatObservers();
	}

	public void registerObserver(BeatObserver o) {
		beatObservers.add(o);
	}

	public void notifyBeatObservers() {
		for (int i = 0; i < beatObservers.size(); i++) {
			BeatObserver observer = (BeatObserver) beatObservers.get(i);
			observer.updateBeat();
		}
	}

	public void registerObserver(BPMObserver o) {
		bpmObservers.add(o);
	}

	public void notifyBPMObservers() {
		for (int i = 0; i < bpmObservers.size(); i++) {
			BPMObserver observer = (BPMObserver) bpmObservers.get(i);
			observer.updateBPM();
		}
	}

	public void removeObserver(BeatObserver o) {
		int i = beatObservers.indexOf(o);
		if (i >= 0) {
			beatObservers.remove(i);
		}
	}

	public void removeObserver(BPMObserver o) {
		int i = bpmObservers.indexOf(o);
		if (i >= 0) {
			bpmObservers.remove(i);
		}
	}

	public void meta(MetaMessage message) {
		// if (message.getType() == 47) {
		// beatEvent();
		// sequencer.start();
		// setBPM(getBPM());
		// }

		// FIX
		if (message.getType() == 0x2F) {
			beatEvent();
			sequencer.setMicrosecondPosition(0);
			sequencer.setTickPosition(0);
			sequencer.start();
			setBPM(getBPM());
		}
	}

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.addMetaEventListener(this);

			// Fix
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

			sequence = new Sequence(Sequence.PPQ, 4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(getBPM());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buildTrackAndStart() {
		int[] trackList = { 35, 0, 46, 0 };

		sequence.deleteTrack(null);
		track = sequence.createTrack();

		makeTracks(trackList);
		track.add(makeEvent(192, 9, 1, 0, 4));
		try {
			sequencer.setSequence(sequence);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void makeTracks(int[] list) {

		for (int i = 0; i < list.length; i++) {
			int key = list[i];

			if (key != 0) {
				track.add(makeEvent(144, 9, key, 100, i));
				track.add(makeEvent(128, 9, key, 100, i + 1));
			}
		}
	}

	public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd, chan, one, two);
			event = new MidiEvent(a, tick);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return event;
	}

	public void registerObserver(BulletObserver o) {
		bulletObservers.add(o);
	}

	public void removeObserver(BulletObserver o) {
		int i = bulletObservers.indexOf(o);
		if (i >= 0) {
			bulletObservers.remove(i);
		}
	}

	public void notifyBulletObservers() {
		for (int i = 0; i < bulletObservers.size(); i++) {
			BulletObserver observer = (BulletObserver) bulletObservers.get(i);
			observer.updateBPM();
		}
	}

	public int getPosX() {
		return 0;
	}

	public int getPosY() {
		return 0;
	}

	public int getFormSize() {
		return 0;
	}

	public int getBulletSize() {
		return 0;
	}
}
