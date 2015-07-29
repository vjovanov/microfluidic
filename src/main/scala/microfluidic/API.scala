package microfluidic
import scala.concurrent.duration._ // We would use our own. This is a proof of concept.

/**
 * A base trait for all fluids
 */
trait Fluid {
  def name: String
  def volume: Int // this can be improved to include units (see package scala.concurrent.duration)
  def t: Double

  def output: Output = Output(this)
  // Note: temperature should be computed based on specific heat capacity + reaction total ...
  def mix(f: Fluid)(name: String = this.name + " with " + f.name): Fluid = Mix(this, f, name)
  def heat(t: Double, duration: FiniteDuration): Fluid = Heat(this, t, duration)
}

// case class Fluid(name: String, volume: Int, t: Double) extends Node
case class Input(name: String, volume: Int, t: Double)
case class Output(f: Fluid) // output is not a Fluid, i.e., can not be used in the flow until executed
case class Measurement(f: Fluid, name: String)
case class Stage(outputs: Seq[Output], measurements: Seq[Measurement]) {
  def inputs: Input =  ???; // Note: not sure how to convert those
  def results: Seq[Double] = ??? // Note: not sure how to convert those
}




