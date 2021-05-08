
import UtilitiesPackage.IncomeTaxUtilities
import scala.io.StdIn
object IncomeTaxCalculator {

  def main(args: Array[String]): Unit = {

    val util = new IncomeTaxUtilities()

    print("Please enter your fixed income \n(without PF & Gratuity from Employer) : ")
    val income : Int = scala.io.StdIn.readInt()

    println("\n ******** Old Tax Rules (Monthly Stats)********** ")
    util.getInhandSalary(income,util.calculateTaxAsPerOldRules(income))

    println("\n ********** New Tax Rules (Monthly Stats)**********")
    util.getInhandSalary(income,util.calculateTaxAsPerNewRules(income))
  }
}
