package microfluidic

import scala.concurrent.duration._
import org.scalatest.{ FlatSpec, ShouldMatchers }

class ExamplesSpec extends FlatSpec with ShouldMatchers {



  "Microfluidic" should "be able to store fluids" in {

    val storedWater = dispense("water", 100, 22).store


    experiment(storedWater) should be { // expected intermediate representation
      Stage(List(Store(Input("water",100,22.0))),List())
    }
  }

  it should "be able to waste fluids" in {

    val water = dispense("water", 100, 22).waste


    experiment(water) should be { // expected intermediate representation
      Stage(List(Waste(Input("water",100,22.0))),List())
    }
  }

  it should "mix fluids" in {

    val water = dispense("water", 60, 22)
    val syrup = dispense("syrup", 40, 22)
    val juice = water.mix(syrup, 5 seconds).store
    // we could do something like:
    //   water mix syrup for 5 seconds


    experiment(juice) should be { // expected intermediate representation
      Stage(List(Store(Mix(Input("water",60,22.0),Input("syrup",40,22.0),"water with syrup"))),List())
    }
  }

  it should "split up fluids" in {

    val (halfOfWater, otherHalf) = dispense("water", 100, 22).split


    experiment(Seq(halfOfWater.store, otherHalf.store)) should be { // expected intermediate representation
      Stage(List(Store(Split(Input("water",100,22.0),2)), Store(Split(Input("water",100,22.0),2))),List())
    }
  }

  it should "heat up fluids" in {

    val sterilizedWater = dispense("water", 100, 22).heat(95, 5 minutes).store


    experiment(sterilizedWater) should be { // expected intermediate representation
      Stage(List(Store(Heat(Input("water",100,22.0),95.0,5 minutes))),List())
    }
  }

  /*
   * Take measurements.
   */

  import microfluidic.sensors._
  it should "support taking measurements" in {
    val hotWater = dispense("water", 100, 22).heat(95, 1 seconds)
    val salt = dispense("salt", 10, 22)
    val saltyWater = hotWater.mix(salt, 5 seconds)

    experiment(saltyWater.store, saltyWater.conductivity) should be {
      Stage(List(Store(Mix(Heat(Input("water",100,22.0),95.0,1 second),Input("salt",10,22.0),"water with salt"))),List(Measurement(Mix(Heat(Input("water",100,22.0),95.0,1 second),Input("salt",10,22.0),"water with salt"),"conductivity")))
    }

  }

}
