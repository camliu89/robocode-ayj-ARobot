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

import robocode.Robot;

/**
 * Utilities methods to be used for the robot.
 * 
 * @author Alexander Cam Liu
 *
 */
public class RobotUtils extends Robot {
  
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