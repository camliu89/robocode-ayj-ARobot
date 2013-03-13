package ayj;

import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import java.awt.Color;

/**
 * A Robot that will attempt to fire when an enemy is spotted. Then dodge any incoming bullets.
 * 
 * @author Alexander Cam Liu
 * 
 */
public class ARobot extends Robot {

  /** to keep track the number of turns. */
  int count = 0;
  /** to turn 10 degrees each time. */
  int turn = 360;
  /** to keep track of the enemy energy for the dodge movement. */
  double enemyEnergy = 100;
  /** change the direction of the robot movement. */
  int direction = 1;

  /**
   * Runs the robot.
   */
  @Override
  public void run() {
    // Set the colors
    setColors(Color.white, Color.blue, Color.blue);
    setBulletColor(Color.GRAY);
    // infinite loop
    // checks for enemy
    while (true) {
      setAdjustGunForRobotTurn(true);
      turnRadarRight(turn);
      count++;
      // if in two turns can't find an enemy
      // turn -10 degrees
      if (count > 2) {
        turn = -10;
      }
      // if in five turns can't find an enemy
      // turn 10 degrees
      if (count > 5) {
        turn = 10;
      }
      if (count > 10) {
        ahead(100);
      }
    }
  }

  /**
   * Overrides event when a robot is scanned. Part of the code taken from :
   * "http://robocoderepository.com/BotFiles/2638/Newbie.java"
   * 
   * @param e enemy.
   */
  @Override
  public void onScannedRobot(ScannedRobotEvent e) {
    // Set counter to 0
    count = 0;
    // make robots move side to side
    turnRight(e.getBearing() + 90);
    // Calculate exact location of the robot
    double absoluteBearing = getHeading() + e.getBearing();
    double bearingFromGun = normalRelativeAngle(absoluteBearing - getGunHeading());
    out.println(bearingFromGun);
    // If it's close enough, fire!
    if (Math.abs(bearingFromGun) <= 3) {
      turnGunRight(bearingFromGun);
      // We check gun heat here, because calling fire()
      // uses a turn, which could cause us to lose track
      // of the other robot.
      if (getGunHeat() == 0) {
        fire(Math.min(getPowerFire(e.getDistance()), getEnergy() - .1));
      }
    }
    // otherwise just set the gun to turn.
    // Note: This will have no effect until we call scan()
    else {
      turnGunRight(bearingFromGun);
    }
    // Generates another scan event if we see a robot.
    // We only need to call this if the gun (and therefore radar)
    // are not turning. Otherwise, scan is called automatically.
    if (bearingFromGun == 0) {
      fire(2);
    }
    // Dodges the bullet of the enemy.
    dodge(e);
  }

  /**
   * Overrides an event when my robot is hit by a bullet. Move back 200 pixels.
   * 
   * @param event event.
   */
  @Override
  public void onHitByBullet(HitByBulletEvent event) {
    back(200);
  }

  /**
   * Dodge the bullets by checking the change of energy of the enemy.
   * 
   * @param e enemy.
   */
  public void dodge(ScannedRobotEvent e) {
    double changeInEnergy = enemyEnergy - e.getEnergy();
    enemyEnergy = e.getEnergy();
    if (changeInEnergy > 0 && changeInEnergy <= 3) {
      direction = -direction;
      ahead(100 * direction);
    }
  }

  /**
   * Gets the power of the bullet proportional to the distance.
   * 
   * @param distance the distance to the enemy.
   * @return the power of the bullet.
   */
  public double getPowerFire(double distance) {
    // change number to compute easier the proportion of the power by the distance
    // if the distance is 0, then change to 1 since 0/(any number) is 0
    if (distance == 0) {
      double changeDistance = 1;
      return changeDistance / 200;
    }
    // Divide distance number by 200
    // because the MAX_BULLET_POWER is 3 so
    // if it is at 1200 distance (the max RADAR_SCAN_RADIUS) then 1200/400 is 3
    // UPDATE: Fire power divided by 400 is to weak. Changed to 200
    // as the distance gets lower, then then bullet power will be weaker
    return distance / 200;
  }

  /**
   * Normalize an angle to a relative angle.
   * 
   * @param angle the angle to be converted.
   * @return the normalized angle.
   */
  public double normalRelativeAngle(double angle) {
    if (angle > -180 && angle <= 180) {
      return angle;
    }
    double fixedAngle = angle;
    // if angle is less or equal than - 180, increased it by 360.
    while (fixedAngle <= -180) {
      fixedAngle += 360;
    }
    // if angle is bigger than 180, decreased it by 360.
    while (fixedAngle > 180) {
      fixedAngle -= 360;
    }
    return fixedAngle;
  }
}