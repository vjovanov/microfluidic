package object microfluidic {

  /**
   * For now we treat input the same as reservoirs.
   */
  def dispense(name: String, volume: Int, t: Double): Input = Input(name, volume, t)

  /**
   * Stage makes an implicit stage between two flows. Measurements become values, and outputs become inputs.
   * Note: if each Measurement has a specific type the result type can be a heterogeneous list of types.
   * Note: overloading resolution allows us to use both sequences and single outputs.
   */
  def stage(outputs: Seq[Output], measurements: Seq[Measurement]): Stage = Stage(outputs, measurements)
  def stage(output: Output, measurements: Seq[Measurement]): Stage = Stage(Seq(output), measurements)
  def stage(outputs: Seq[Output]): Stage = Stage(outputs, Nil)
  def stage(output: Output): Stage = Stage(Seq(output), Nil)
  def stage(outputs: Seq[Output], measurement: Measurement): Stage = Stage(outputs, Seq(measurement))
  def stage(output: Output, measurement: Measurement): Stage = Stage(Seq(output), Seq(measurement))

  /**
   * Executes the multi-stage experiment.
   */
  def experiment(stage: Stage): Stage = stage // here we should call the domain-specific compiler and return Unit.

  //
  // Avoids experiment(stage(...)) for short experiments.
  //
  def experiment(outputs: Seq[Output], measurements: Seq[Measurement]): Stage =
    experiment(Stage(outputs, measurements))
  def experiment(output: Output, measurements: Seq[Measurement]): Stage =
    experiment(Stage(Seq(output), measurements))
  def experiment(outputs: Seq[Output]): Stage =
    experiment(Stage(outputs, Nil))
  def experiment(output: Output): Stage =
    experiment(Stage(Seq(output), Nil))
  def experiment(outputs: Seq[Output], measurement: Measurement): Stage =
    experiment(Stage(outputs, Seq(measurement)))
  def experiment(output: Output, measurement: Measurement): Stage =
    experiment(Stage(Seq(output), Seq(measurement)))
}
