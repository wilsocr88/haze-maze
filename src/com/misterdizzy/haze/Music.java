
package com.misterdizzy.haze;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Music implements Runnable {

	public static void play( String filePath ) {
		try {
			File audioFile = new File( filePath );
			AudioInputStream audioStream = AudioSystem.getAudioInputStream( audioFile );
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info( Clip.class, format );
			Clip audioClip = ( Clip ) AudioSystem.getLine( info );
			audioClip.open( audioStream );
			audioClip.start();
			while ( audioClip.getMicrosecondLength() != audioClip.getMicrosecondPosition() ) {
				TimeUnit.SECONDS.sleep( 1 );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		for ( String file : UI.getFiles() ) {
			play( file );
		}
	}

}
