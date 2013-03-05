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