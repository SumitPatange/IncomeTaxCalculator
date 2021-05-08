package UtilitiesPackage

import scala.io.Source

class IncomeTaxUtilities {

  def getTotalTaxExemptions():Double = {
    val file = System.getProperty("user.dir")+"\\tax_exemptions.txt"
    var exemptionMap = scala.collection.mutable.Map("" -> 0)
    var eightyCTotal, otherTotal, interestTotal, total_exemption:Int = 0

    for(line <- Source.fromFile(file).getLines) {
      exemptionMap = exemptionMap.addOne(line.split(":")(0) -> line.split(":")(1).toInt)
    }

    for (item <- exemptionMap.keySet){
      if (item.contains("80C")) eightyCTotal += exemptionMap(item)
      else if (item.contains("Home Loan Interest")) interestTotal += exemptionMap(item)
      else otherTotal += exemptionMap(item)
    }
    total_exemption = Math.min(eightyCTotal, 150000) + Math.min(interestTotal, 200000) + otherTotal
    total_exemption
  }


  def calculateTaxAsPerOldRules(income:Int ):Double = {
    var tax : Double = 0.0;
    var total_tax : Double = 0.0
    var taxable_income : Double = income - getTotalTaxExemptions()

    while (taxable_income > 250000){
      if (taxable_income > 1000000){
        tax = 0.30 * (taxable_income - 1000000)
        taxable_income = taxable_income - (taxable_income - 1000000);
        total_tax += tax
      }
      if (taxable_income > 500000) {
        tax = 0.20 * (taxable_income - 500000)
        taxable_income = taxable_income - (taxable_income - 500000);
        total_tax += tax
      }
      if (taxable_income > 250000) {
        tax = 0.05 * (taxable_income - 250000)
        taxable_income = taxable_income - (taxable_income - 250000);
        total_tax += tax
      }
    }
    return total_tax
  }


  def calculateTaxAsPerNewRules(income:Int ):Double = {
    var tax : Double = 0.0;
    var total_tax : Double = 0.0
    var taxable_income : Int = income;

    while (taxable_income > 250000){
      if (taxable_income > 1500000){
        tax = 0.30 * (taxable_income - 1500000)
        taxable_income = taxable_income - (taxable_income - 1500000);
        total_tax += tax
      }
      if (taxable_income > 1250000){
        tax = 0.25 * (taxable_income - 1250000)
        taxable_income = taxable_income - (taxable_income - 1250000);
        total_tax += tax
      }
      if (taxable_income > 1000000){
        tax = 0.20 * (taxable_income - 1000000)
        taxable_income = taxable_income - (taxable_income - 1000000);
        total_tax += tax
      }
      if (taxable_income > 750000){
        tax = 0.15 * (taxable_income - 750000)
        taxable_income = taxable_income - (taxable_income - 750000);
        total_tax += tax
      }
      if (taxable_income > 500000){
        tax = 0.10 * (taxable_income - 500000)
        taxable_income = taxable_income - (taxable_income - 500000);
        total_tax += tax
      }
      if (taxable_income > 250000) {
        tax = 0.05 * (taxable_income - 250000)
        taxable_income = taxable_income - (taxable_income - 250000);
        total_tax += tax
      }
    }
    return total_tax
  }


  def getInhandSalary(income:Int, tax:Double):Unit = {

    val file = System.getProperty("user.dir")+"\\tax_exemptions.txt"
    var pf_amount, nps_amount, ppf_amount = 0

    for(line <- Source.fromFile(file).getLines) {
      if(line.split(":")(0).contains("PF") && ! line.split(":")(0).contains("PPF")){
        pf_amount= line.split(":")(1).toInt
      }
      if(line.split(":")(0).contains("NPS")){
        nps_amount= line.split(":")(1).toInt
      }
      if(line.split(":")(0).contains("PPF")){
        ppf_amount= line.split(":")(1).toInt
      }
    }

    println("Gross Income : "+(income / 12))
    println("Income Tax : -"+(tax.toInt) / 12)
    println("PF Contribution : -"+(pf_amount / 12))
    println("PPF Contribution : -"+(ppf_amount / 12))
    println("NPS Contribution : -"+(nps_amount / 12))
    println("-----------------------------------")
    println("Final Income : "+(income - tax.toInt - pf_amount - nps_amount - ppf_amount) / 12)
  }

}
