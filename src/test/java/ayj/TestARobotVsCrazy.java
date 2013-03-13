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

import static org.junit.Assert.assertEquals;
import robocode.control.testing.RobotTestBed;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;

/**
 * Illustrates JUnit testing of Robocode robots.
 * 
 * Also illustrates the overriding of a set of methods from RobotTestBed to show how the testing
 * behavior can be customized and controlled. 
 * 
 * @author Alexander Cam Liu
 */
public class TestARobotVsCrazy extends RobotTestBed {

  /**
   * Specifies that Crazy and ARobot are to be matched up in this test case.
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.Crazy,ayj.ARobot";
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
   * The actual test, which asserts that ARobot has won every round against Crazy.
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    // Sanity check that results[1] is ARobot (not strictly necessary, but illustrative).
    BattleResults ARobotResults = battleResults[0];
    String robotName = ARobotResults.getTeamLeaderName();
    assertEquals("Check that results[0] is ARobot", robotName, "ayj.ARobot*");
    
    // Check to make sure ARobot won every round.
    assertEquals("Check ARobot winner", ARobotResults.getFirsts(), getNumRounds());
  }  
}
