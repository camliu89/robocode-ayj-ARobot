/**
 * This file is part of ARobot.
 * ARobot is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * ARobot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with ARobot.  If not, see <http://www.gnu.org/licenses/>.
 */

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

  /** 
   * To keep track the number of turns. 
   */
  int count = 0;
  
  /** 
   * To turn 10 degrees each time. 
   */
  int turn = 360;
  
  /** 
   * To keep track of the enemy energy for the dodge movement. 
   */
  double enemyEnergy = 100;
  
  /** 
   * change the direction of the robot movement. 
   */
  int direction = 1;
  
  /**
   *  Instantiate an object of the robot utilities class.
   */
  RobotUtils utils = new RobotUtils ();

  /**
   * Runs the robot.
   */
  @Override
  public void run() {
    // Set the colors
    this.pimpMyRobot();
    // infinite loop
    // checks for enemy
    while (true) {
      setAdjustGunForRobotTurn(true);
      turnRadarRight(this.turn);
      this.count++;
      // if in two turns can't find an enemy
      // turn -10 degrees
      if (this.count > 2) {
        this.turn = -10;
      }
      // if in five turns can't find an enemy
      // turn 10 degrees
      if (this.count > 5) {
        this.turn = 10;
      }
      if (this.count > 10) {
        ahead(100);
      }
    }
  }

  /**
   * Overrides event when a robot is scanned. Part of the code taken from :
   * "http://robocoderepository.com/BotFiles/2638/Newbie.java"
   * 
   * @param enemy enemy.
   */
  @Override
  public void onScannedRobot(ScannedRobotEvent enemy) {
    // Set counter to 0
    count = 0;
    // make robots move side to side
    turnRight(enemy.getBearing() + 90);
    // Calculate exact location of the robot
    double absoluteBearing = getHeading() + enemy.getBearing();
    double bearingFromGun = this.utils.normalRelativeAngle(absoluteBearing - getGunHeading());
    out.println(bearingFromGun);
    // If it's close enough, fire!
    if (Math.abs(bearingFromGun) <= 3) {
      turnGunRight(bearingFromGun);
      // We check gun heat here, because calling fire()
      // uses a turn, which could cause us to lose track
      // of the other robot.
      if (getGunHeat() == 0) {
        fire(Math.min(this.utils.getPowerFire(enemy.getDistance()), getEnergy() - .1));
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
    this.dodge(enemy);
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
   * @param enemy The enemy robot.
   */
  public void dodge(ScannedRobotEvent enemy) {
    double changeInEnergy = enemyEnergy - enemy.getEnergy();
    enemyEnergy = enemy.getEnergy();
    if (changeInEnergy > 0 && changeInEnergy <= 3) {
      direction = -direction;
      ahead(100 * direction);
    }
  }
  
  /**
   * Customize my robot.
   * 
   */
  public void pimpMyRobot () {
    //Personalized my robot
    setColors(Color.white, Color.blue, Color.blue);
    setBulletColor(Color.GRAY);
  }
}