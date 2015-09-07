package microfluidic
import scala.concurrent.duration._ // We would use our own. This is a proof of concept.

/**
 * A base trait for all fluids.
 */
trait Fluid {

  /**
   * Name of the fluid.
   */
  def name: String

  /**
   * Volume of the fluid.
   */
  def volume: Int

  def temperature: Double

  /**
   * Stores the fluid on the chip for the next iteration of the experiment.
   */
  def store: Output = Store(this)

  /**
   * Moves the fluid out of the chip.
   * Note: we probably need a container for outputs as well.
   */
  def waste: Output = Waste(this)

  /**
   * Mixes two fluids on the chip.
   */
  def mix(f: Fluid, duration: FiniteDuration): Fluid = Mix(this, f, this.name + " with " + f.name)

  /**
   * Heats the fluid for a finite duration of time.
   */
  def heat(t: Double, duration: FiniteDuration): Fluid = Heat(this, t, duration)

  /**
   * Splits the fluid in two fluids with same properties and half of the volume.
   */
  def split: (Fluid, Fluid) = (Split(this, 2), Split(this, 2))
}

case class Input(name: String, volume: Int, temperature: Double) extends Fluid
trait Output { def fluid: Fluid } // Output is not a Fluid, i.e., can not be used in the flow until executed.
case class Store(fluid: Fluid) extends Output
case class Waste(fluid: Fluid) extends Output

case class Measurement(f: Fluid, name: String)

case class Stage(outputs: Seq[Output], measurements: Seq[Measurement])
