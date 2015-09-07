package microfluidic

import scala.concurrent.duration._ // We would use our own duration. We use this one as a proof of concept.

case class Heat(f: Fluid, temperature: Double, duration: FiniteDuration) extends Fluid {
  def volume = f.volume
  def name = f.name
}

case class Mix(f1: Fluid, f2: Fluid, name: String) extends Fluid {

  /*
  * Question: Do not know how to compute the temperature? Seems that for the temperature we
  * need domain knowledge as it depends based on the reaction.
  * Is temperature something we can always predict based on inputs?
  */
  def temperature: Double = ???

  /*
  * Question: Is this always the case? Some fluids might evaporate, change density etc.
  * Is volume something we can always predict based on inputs?
  */
  def volume: Int = f1.volume + f2.volume
}

case class Split(f: Fluid, ratio: Int) extends Fluid {
  def volume = f.volume / ratio
  def name = f.name
  def temperature = f.temperature
}
