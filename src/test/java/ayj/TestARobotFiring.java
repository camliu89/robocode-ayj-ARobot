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

import static org.junit.Assert.assertTrue;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * This tests whether the robot should fire or not and with what power.
 *
 * @author Alexander Cam Liu
 */
public class TestARobotFiring extends RobotTestBed {

  /** True if ARobot fires when it needs to.
  * When the getGunHeat is 0.
  * When the bearingFromGun is 0.
  */
  boolean shouldFire = false;
  
  /**
   * Specifies that Fire and ARobot are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override 
  public String getRobotNames() {
    return "sample.Fire,ayj.ARobot";
  }
  
  /**
   * This test runs for 10 rounds.
   * @return The number of rounds. 
   */
  @Override 
  public int getNumRounds() {
    return 10;
  }
  
  /**
   * At the end of each turn, check that ARobot gunHeat is 0. 
   * @param event Info about the current state of the battle.
   */
  @Override 
  public void onTurnEnded (TurnEndedEvent event) {
    //For each turn, verify that gunHeat is 0
    IRobotSnapshot robots = event.getTurnSnapshot().getRobots()[1];
    double gunHeat = robots.getGunHeat();
    shouldFire = gunHeat == 0 ? true : false;
  }
  
  /**
   * After running all matches, determine if ARobot fires as expected.
   * @param event Details about the completed battle.
   */
  @Override 
  public void onBattleCompleted (BattleCompletedEvent event) {
    assertTrue("Robot fires as behaved", shouldFire);
  }
}