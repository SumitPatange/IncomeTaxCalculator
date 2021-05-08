import scala.io.Source

object HRAExemptionCalculator {

  def main(args: Array[String]): Unit = {

    var name : String = ""
    var basic_salary, hra_received, rent_paid = 0
    val file = System.getProperty("user.dir")+"\\hra_info.txt"

    for (line <- Source.fromFile(file).getLines()) {
      name = line.split(",")(0)
      basic_salary = line.split(",")(1).toInt
      rent_paid = line.split(",")(2).toInt
      hra_received = line.split(",")(3).toInt

      println("\nCase : "+name)
      println("Basic Salary : "+basic_salary)
      println("Rent Paid : "+rent_paid)
      println("1. HRA received : "+ hra_received)
      println("2. 40% of basic salary : "+ (basic_salary * 0.4).round)
      println("3. Rent - 10% basic salary : "+ (rent_paid - (basic_salary * 0.1)).round)

      var hra_params = List((basic_salary * 0.4), (hra_received), (rent_paid - (basic_salary * 0.1)))
      println("HRA Exempted for TAX (Min of above three) : "+hra_params.min)
      println("---------------------------------------------------")
    }
  }
}
