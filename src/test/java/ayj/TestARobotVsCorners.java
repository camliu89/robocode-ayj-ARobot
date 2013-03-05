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
public class TestARobotVsCorners extends RobotTestBed {

  /**
   * Specifies that Corners and ARobot are to be matched up in this test case.
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.Corners,ayj.ARobot";
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
   * The actual test, which asserts that ARobot has won every round against Corners.
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
