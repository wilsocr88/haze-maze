
package com.misterdizzy.haze;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class UI {

	private static ArrayList< String >	menu	= new ArrayList< String >();
	private static ArrayList< String >	files	= new ArrayList< String >();

	public static void init() {
		menu.add( "1. Believe" );
		menu.add( "2. Cabal" );
		menu.add( "3. Safe" );
		menu.add( "4. War" );
		menu.add( "5. Aftermath" );
		menu.add( "6. Untold" );
		menu.add( "7. Underclass" );
		menu.add( "8. Hopeless" );
		menu.add( "9. Agoraphobia" );

		files.add( "data/res/music/1 Believe.wav" );
		files.add( "data/res/music/2 Cabal.wav" );
		files.add( "data/res/music/3 Safe.wav" );
		files.add( "data/res/music/4 War.wav" );
		files.add( "data/res/music/5 Aftermath.wav" );
		files.add( "data/res/music/6 Untold.wav" );
		files.add( "data/res/music/7 Underclass.wav" );
		files.add( "data/res/music/8 Hopeless.wav" );
		files.add( "data/res/music/9 Agoraphobia.wav" );
	}

	public static void draw() {
		Game.g.setColor( new Color( 1f, 1f, 1f, 0.3f ) );
		Game.g.fillRect( ThreadLocalRandom.current().nextInt( 0, Game.WIDTH ),
		        ThreadLocalRandom.current().nextInt( 0, Game.HEIGHT ), 1,
		        ThreadLocalRandom.current().nextInt( 50, 500 ) );
		Game.g.setColor( new Color( 1f, 1f, 1f, 0.3f ) );
		Game.g.setColor( new Color( 1f, 1f, 1f, 0.3f ) );
		Game.g.fillRect( ThreadLocalRandom.current().nextInt( 0, Game.WIDTH ),
		        ThreadLocalRandom.current().nextInt( 0, Game.HEIGHT ), 1,
		        ThreadLocalRandom.current().nextInt( 50, 500 ) );
		Game.g.setColor( new Color( 1f, 1f, 1f, 0.3f ) );
		Game.g.fillRoundRect( ThreadLocalRandom.current().nextInt( 0, Game.WIDTH ),
		        ThreadLocalRandom.current().nextInt( 0, Game.HEIGHT ), ThreadLocalRandom.current().nextInt( 2, 15 ),
		        ThreadLocalRandom.current().nextInt( 2, 15 ), 10, 10 );
		Game.g.fillRoundRect( ThreadLocalRandom.current().nextInt( 0, Game.WIDTH ),
		        ThreadLocalRandom.current().nextInt( 0, Game.HEIGHT ), ThreadLocalRandom.current().nextInt( 2, 15 ),
		        ThreadLocalRandom.current().nextInt( 2, 15 ), 10, 10 );

		if ( !Camera.walked ) {
			Game.g.setColor( new Color( 0f, 0f, 0f, 1f ) );
			Game.g.fillRect( Game.WIDTH / 4, Game.HEIGHT / 4, Game.WIDTH / 2, Game.HEIGHT / 2 );

			Game.g.setColor( Color.WHITE );
			Game.g.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 24 ) );
			Game.g.drawString( "Present Haze by Mister Dizzy", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 75 );
			Game.g.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 14 ) );
			Game.g.drawString( "Interactive listening experience", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 100 );
			Game.g.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 20 ) );
			Game.g.drawString( "Wander the maze and feel the haze", ( Game.WIDTH / 4 ) + 50,
			        ( Game.HEIGHT / 4 ) + 125 );

			Game.g.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 14 ) );
			Game.g.drawString( "WASD to move", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 175 );
			Game.g.drawString( "Arrow keys to turn", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 200 );
			Game.g.drawString( "Esc to unhide mouse pointer & show track list", ( Game.WIDTH / 4 ) + 50,
			        ( Game.HEIGHT / 4 ) + 225 );
			Game.g.drawString( "Close this window to exit", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 250 );
			Game.g.drawString( "Walk foward to hide this message", ( Game.WIDTH / 4 ) + 50, ( Game.HEIGHT / 4 ) + 300 );
		}

		if ( Camera.paused ) {
			Game.showCursor();

			Game.g.setColor( new Color( 0f, 0f, 0f, 0.6f ) );
			Game.g.fillRect( 0, 0, Game.WIDTH, Game.HEIGHT );
			Game.g.setColor( Color.LIGHT_GRAY );

			Game.g.setFont( new Font( Font.SANS_SERIF, Font.BOLD, 24 ) );
			Game.g.drawString( "Mister Dizzy - Present Haze", 100, 100 );

			Game.g.setFont( new Font( Font.SANS_SERIF, Font.PLAIN, 14 ) );
			Game.g.drawString( "TRACKS", 100, 140 );

			int y = 170;
			for ( int i = 0; i < menu.size(); i++ ) {
				Game.g.drawString( menu.get( i ), 100, y );
				y = y + 25;
			}
		} else {
			Game.hideCursor();
		}
	}

	public static void add( String menuString, String file ) {
		// add tracks as they are found
		menu.add( menuString );
		files.add( file );
	}

	public static void select( int index ) {
		// when a menu item is selected
		Music.play( files.get( index ) );
	}

	public static ArrayList< String > getFiles() {
		return files;
	}

}
