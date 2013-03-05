package ayj;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * This tests whether the robot should fire or not and with what power.
 *
 * @author Alexander Cam Liu
 */

public class TestARobotMethods {
  
  /** Instance of a ARobot class. */
  ARobot robot = new ARobot();
  
  /**
   * Test the getPowerFire() method.
   */
  @Test
  public void testPowerFire() {
    assertEquals("Power1", 0.005 , robot.getPowerFire(0), 0);
    assertEquals("Power2", 6.0 , robot.getPowerFire(1200), 0);
    assertEquals("Power3", 1.0 , robot.getPowerFire(200), 0);
    assertEquals("Power4", 3.0 , robot.getPowerFire(600), 0);
    assertEquals("Power5", 2.5 , robot.getPowerFire(500), 0);
  }
  
  /**
   * Test the normalRelativeAngle() method.
   */
  @Test
  public void testNormalizedAngle () {
    assertEquals("Angle1", 180.0 , robot.normalRelativeAngle(180), 0);
    assertEquals("Angle2", 180.0 , robot.normalRelativeAngle(-180), 0);
    assertEquals("Angle3", -90.0 , robot.normalRelativeAngle(270), 0);
    assertEquals("Angle4", 90.0 , robot.normalRelativeAngle(-270), 0);
    assertEquals("Angle5", 0.0 , robot.normalRelativeAngle(360), 0);
    assertEquals("Angle6", 0.0 , robot.normalRelativeAngle(-360), 0);
  }
}