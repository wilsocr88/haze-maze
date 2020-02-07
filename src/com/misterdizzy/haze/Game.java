
package com.misterdizzy.haze;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

	static Game					self;

	public static final int		WIDTH				= Toolkit.getDefaultToolkit().getScreenSize().width - 100;
	public static final int		HEIGHT				= Toolkit.getDefaultToolkit().getScreenSize().height - 100;

	private static final long	serialVersionUID	= 1L;
	public static int			mapWidth			= 15;
	public int					mapHeight			= 15;
	private Thread				musicThread;
	private Thread				thread;
	private boolean				running;
	private BufferedImage		image;
	public int[]				pixels;
	public ArrayList< Texture >	textures;
	public Camera				camera;
	public Screen				screen;
	public static char[][]		map					= Maze.getMaze();
	public static int[][]		intM;

	public static Graphics		g;
	static BufferedImage		cursorImg			= new BufferedImage( 16, 16, BufferedImage.TYPE_INT_ARGB );
	static Cursor				blankCursor			= Toolkit.getDefaultToolkit().createCustomCursor( cursorImg,
	        new Point( 0, 0 ), "blank cursor" );

	public Game() {
		intM = new int[map.length][map[0].length];
		for ( int l = 0; l < map[0].length; l++ ) {
			for ( int k = 0; k < map.length; k++ ) {
				intM[k][l] = Integer.parseInt( String.valueOf( map[k][l] ) );
			}
		}

		Music music = new Music();
		musicThread = new Thread( music );
		musicThread.run();

		UI.init();

		thread = new Thread( this );
		image = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB );
		pixels = ( ( DataBufferInt ) image.getRaster().getDataBuffer() ).getData();
		textures = new ArrayList< Texture >();
		textures.add( Texture.wood );
		textures.add( Texture.brick );
		textures.add( Texture.bluestone );
		textures.add( Texture.stone );
		textures.add( Texture.end );
		camera = new Camera( 1.5, 1.5, 1, 0, 0, -.66 );
		screen = new Screen( intM, mapWidth, mapHeight, textures, WIDTH, HEIGHT );
		ImageIcon img = new ImageIcon( "data/res/tex/end.jpg" );
		setIconImage( img.getImage() );
		addKeyListener( camera );
		setSize( WIDTH, HEIGHT );
		// setExtendedState( JFrame.MAXIMIZED_BOTH );
		setResizable( false );
		// setUndecorated( true );
		setTitle( "Mister Dizzy - Present Haze" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBackground( Color.black );
		setLocationRelativeTo( null );
		setVisible( true );
		this.getContentPane().setCursor( blankCursor );
		start();
	}

	public static void showCursor() {
		self.getContentPane().setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
	}

	public static void hideCursor() {
		self.getContentPane().setCursor( blankCursor );
	}

	private synchronized void start() {
		running = true;
		thread.start();
		musicThread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
			musicThread.join();
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if ( bs == null ) {
			createBufferStrategy( 3 );
			return;
		}
		g = bs.getDrawGraphics();
		g.drawImage( image, 0, 0, image.getWidth(), image.getHeight(), null );
		UI.draw();
		bs.show();
	}

	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		requestFocus();
		while ( running ) {
			long now = System.nanoTime();
			delta = delta + ( ( now - lastTime ) / ns );
			lastTime = now;
			while ( delta >= 1 ) {
				screen.update( camera, pixels );
				camera.update( intM );
				delta--;
			}
			render();
		}
	}

	public static void main( String[] args ) {
		self = new Game();
	}
}
