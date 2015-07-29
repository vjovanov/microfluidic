package microfluidic

import scala.concurrent.duration._ // We would use our own. This is a proof of concept.

case class Heat(f: Fluid, t: Double, duration: FiniteDuration) extends Fluid {
  def volume = f.volume
  def name = f.name
}
case class Mix(f1: Fluid, f2: Fluid, name: String) extends Fluid {
  def t: Double = ??? // Note: Do not know how to compute. We need domain knowledge here as it is not the same for all fluids.
  def volume: Int = f1.volume + f2.volume
}

