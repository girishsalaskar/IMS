SELECT
(select sum(purchases_m.total) from purchases_m where purchases_m.purchasedate>=$P{prmstartdate} and purchases_m.purchasedate<=$P{prmenddate}) AS totpurch,
(select sum(sales_m.total) from sales_m where sales_m.salesdate>=$P{prmstartdate} and sales_m.salesdate<=$P{prmenddate}) AS totsales,
(select sum(customers.debtamt) from customers) AS debtors,
(select sum(suppliers.supcr) from suppliers) AS creditors,
(select sum(emppayment.paidamt) from emppayment where emppayment.paydate>=$P{prmstartdate} and emppayment.paydate<=$P{prmenddate}) as paidsalary,
(select sum(empm.remsalary) from empm) as remsalary,
(select sum(expenses.expamt) from expenses where expenses.expdate>=$P{prmstartdate} and expenses.expdate<=$P{prmenddate}) as totexpenses,
(select sum(empm.acbal) from empm) as advancetoemp,
(select sum(products.instock*products.purchrate) from products) as stock,
(select sum(salesreturn_m.total) from salesreturn_m where salesreturn_m.salesreturndate>=$P{prmstartdate} and salesreturn_m.salesreturndate<=$P{prmenddate}) AS salesreturn,
(select sum(runnupcustprods.grandtotal) from runnupcustprods where runnupcustprods.salesdate>=$P{prmstartdate} and runnupcustprods.salesdate<=$P{prmenddate} group by runnupcustprods.grandtotal) AS runnupcustsales,
firm.firmname, firm.firmprop
from firm
where firm.firmid=$P{prmfirmid}

SELECT
(select sum(purchases_m.total) from purchases_m where purchases_m.purchasedate>='2014-10-10' and purchases_m.purchasedate<='2016-12-12') AS totpurch,
(select sum(sales_m.total) from sales_m where sales_m.salesdate>='2014-10-10' and sales_m.salesdate<='2016-12-12') AS totsales,
(select sum(customers.debtamt) from customers) AS debtors,
(select sum(suppliers.supcr) from suppliers) AS creditors,
(select sum(emppayment.paidamt) from emppayment where emppayment.paydate>='2014-10-10' and emppayment.paydate<='2016-12-12') as paidsalary,
(select sum(empm.remsalary) from empm) as remsalary,
(select sum(expenses.expamt) from expenses where expenses.expdate>='2014-10-10' and expenses.expdate<='2016-12-12') as totexpenses,
(select sum(empm.acbal) from empm) as advancetoemp,
(select sum(products.instock*products.purchrate) from products) as stock,
(select sum(salesreturn_m.total) from salesreturn_m where salesreturn_m.salesreturndate>='2014-10-10' and salesreturn_m.salesreturndate<='2016-12-12') AS salesreturn,
firm.firmname, firm.firmprop
from firm
where firm.firmid=1