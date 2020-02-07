
package com.misterdizzy.haze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {

	public double			xPos, yPos, xDir, yDir, xPlane, yPlane;
	public boolean			left, right, forward, back, strafeLeft, strafeRight;
	public static boolean	walked;
	public final double		MOVE_SPEED		= .03;
	public final double		ROTATION_SPEED	= .03;
	public final double		NOSE_BUFFER		= -0.2d;
	public static boolean	paused			= false;

	public Camera( double x, double y, double xd, double yd, double xp, double yp ) {
		xPos = x;
		yPos = y;
		xDir = xd;
		yDir = yd;
		xPlane = xp;
		yPlane = yp;
	}

	public void keyPressed( KeyEvent key ) {
		// ARROWS
		if ( ( key.getKeyCode() == KeyEvent.VK_LEFT ) ) {
			left = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_RIGHT ) ) {
			right = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_UP ) ) {
			forward = true;
			walked = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_DOWN ) ) {
			back = true;
		}

		// WASD
		if ( ( key.getKeyCode() == KeyEvent.VK_A ) ) {
			strafeLeft = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_D ) ) {
			strafeRight = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_W ) ) {
			forward = true;
			walked = true;
		}
		if ( ( key.getKeyCode() == KeyEvent.VK_S ) ) {
			back = true;
		}

		// PAUSE
		if ( ( key.getKeyCode() == KeyEvent.VK_ESCAPE ) ) {
			if ( paused ) {
				paused = false;
				return;
			}
			if ( !paused ) {
				paused = true;
				return;
			}
		}
	}

	public void keyReleased( KeyEvent key ) {
		if ( ( key.getKeyCode() == KeyEvent.VK_LEFT ) )
			left = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_RIGHT ) )
			right = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_UP ) )
			forward = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_DOWN ) )
			back = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_A ) )
			strafeLeft = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_D ) )
			strafeRight = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_W ) )
			forward = false;
		if ( ( key.getKeyCode() == KeyEvent.VK_S ) )
			back = false;
	}

	public void update( int[][] map ) {
		if ( paused )
			return;

		if ( forward ) {
			if ( map[( int ) ( xPos + xDir * ( MOVE_SPEED - NOSE_BUFFER ) )][( int ) yPos] == 0 ) {
				xPos += xDir * MOVE_SPEED;
			}
			if ( map[( int ) xPos][( int ) ( yPos + yDir * ( MOVE_SPEED - NOSE_BUFFER ) )] == 0 )
				yPos += yDir * MOVE_SPEED;
		}
		if ( back ) {
			if ( map[( int ) ( xPos - xDir * ( MOVE_SPEED - NOSE_BUFFER ) )][( int ) yPos] == 0 )
				xPos -= xDir * MOVE_SPEED;
			if ( map[( int ) xPos][( int ) ( yPos - yDir * ( MOVE_SPEED - NOSE_BUFFER ) )] == 0 )
				yPos -= yDir * MOVE_SPEED;
		}

		if ( right ) {
			double oldxDir = xDir;
			xDir = xDir * Math.cos( -ROTATION_SPEED ) - yDir * Math.sin( -ROTATION_SPEED );
			yDir = oldxDir * Math.sin( -ROTATION_SPEED ) + yDir * Math.cos( -ROTATION_SPEED );
			double oldxPlane = xPlane;
			xPlane = xPlane * Math.cos( -ROTATION_SPEED ) - yPlane * Math.sin( -ROTATION_SPEED );
			yPlane = oldxPlane * Math.sin( -ROTATION_SPEED ) + yPlane * Math.cos( -ROTATION_SPEED );
		}
		if ( left ) {
			double oldxDir = xDir;
			xDir = xDir * Math.cos( ROTATION_SPEED ) - yDir * Math.sin( ROTATION_SPEED );
			yDir = oldxDir * Math.sin( ROTATION_SPEED ) + yDir * Math.cos( ROTATION_SPEED );
			double oldxPlane = xPlane;
			xPlane = xPlane * Math.cos( ROTATION_SPEED ) - yPlane * Math.sin( ROTATION_SPEED );
			yPlane = oldxPlane * Math.sin( ROTATION_SPEED ) + yPlane * Math.cos( ROTATION_SPEED );
		}

		if ( strafeLeft ) {
			if ( map[( int ) ( xPos - yDir * ( MOVE_SPEED - NOSE_BUFFER ) )][( int ) yPos] == 0 ) {
				xPos -= yDir * MOVE_SPEED;
			}
			if ( map[( int ) xPos][( int ) ( yPos + xDir * ( MOVE_SPEED - NOSE_BUFFER ) )] == 0 )
				yPos += xDir * MOVE_SPEED;
		}
		if ( strafeRight ) {
			if ( map[( int ) ( xPos + yDir * ( MOVE_SPEED - NOSE_BUFFER ) )][( int ) yPos] == 0 ) {
				xPos += yDir * MOVE_SPEED;
			}
			if ( map[( int ) xPos][( int ) ( yPos - xDir * ( MOVE_SPEED - NOSE_BUFFER ) )] == 0 )
				yPos -= xDir * MOVE_SPEED;
		}
	}

	public void keyTyped( KeyEvent key ) {
	}

}