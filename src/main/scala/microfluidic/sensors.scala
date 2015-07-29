package microfluidic
import scala.concurrent.duration._ // We would use our own. This is a proof of concept.

package object sensors {
  // Note: this is an example how one can add sensors.
  implicit class SensorPack(f: Fluid) {
    def conductivity: Measurement = Measurement(f, "conductivity")
  }
}
