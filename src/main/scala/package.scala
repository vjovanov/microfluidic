package object microfluidic {
  /**
   * For now we treat input the same as reservoirs.
   */
  def input(name: String, volume: Int, t: Double): Input = Input(name, volume, t)


  /**
   * Stage makes an implicit stage between two flows. Measurements become values, and outputs become inputs.
   * Note: if each measurement has a specific type the result type can be a heterogeneous list of types.
   */
  def stage(outputs: Seq[Output], measurements: Seq[Measurement]): Stage = Stage(outputs, measurements)
}
