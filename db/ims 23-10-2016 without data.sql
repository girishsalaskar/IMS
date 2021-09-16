SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `ims` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `ims`;

DROP TABLE IF EXISTS `application`;
CREATE TABLE IF NOT EXISTS `application` (
  `propid` smallint(5) unsigned NOT NULL COMMENT 'Properties Unique ID',
  `property` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Name',
  `value` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Value'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application Properties';

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `custname` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Customer''s Full Name',
  `custadd` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Customer''s Address',
  `custcity` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Customer''s City',
  `custphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Customer''s Contact Number',
  `custref` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Referrer of Customer',
  `debtamt` float NOT NULL DEFAULT '0' COMMENT 'Customer''s Current Debit (Pending in)',
  `crate_holds` int(11) NOT NULL DEFAULT '0',
  `routeid` int(10) unsigned DEFAULT NULL COMMENT 'Customer Route ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer''s Details';

DROP TABLE IF EXISTS `custpayment`;
CREATE TABLE IF NOT EXISTS `custpayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Payment ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `paydate` date NOT NULL COMMENT 'Payment Date',
  `prevbal` float unsigned NOT NULL COMMENT 'Previous Balance',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount',
  `remamt` float unsigned NOT NULL COMMENT 'Remaining Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer Payment Details';

DROP TABLE IF EXISTS `empadvance`;
CREATE TABLE IF NOT EXISTS `empadvance` (
  `advid` bigint(20) unsigned NOT NULL COMMENT 'Emp. Advance Payment ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `paiddate` date NOT NULL COMMENT 'Emp. Advance Paid Date',
  `prevbal` int(11) NOT NULL COMMENT 'Emp. Previous Balance',
  `paidamt` int(11) NOT NULL COMMENT 'Amount Paid to Employee',
  `balanceamt` int(11) NOT NULL COMMENT 'Employees Balance Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employees Advance Payment';

DROP TABLE IF EXISTS `empattendance`;
CREATE TABLE IF NOT EXISTS `empattendance` (
  `attendid` bigint(20) unsigned NOT NULL COMMENT 'Attendance Unique ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `attenddate` date NOT NULL COMMENT 'Employee Attendance Date',
  `status` tinyint(4) NOT NULL COMMENT 'Employee Attendance Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Attendance';

DROP TABLE IF EXISTS `empm`;
CREATE TABLE IF NOT EXISTS `empm` (
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `empname` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Employee Full Name',
  `empadd` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Employee Address',
  `empmo` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Employee Mobile No.',
  `joindate` date NOT NULL COMMENT 'Employee Joining Date',
  `desgn` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Employee Designation',
  `empsal` int(11) NOT NULL COMMENT 'Employee Salary',
  `acbal` int(11) NOT NULL COMMENT 'Employees Account Balance',
  `remsalary` int(11) NOT NULL DEFAULT '0' COMMENT 'Employees Remaining Salary',
  `leavedate` date DEFAULT NULL COMMENT 'Employee Leaving Date',
  `empenabled` tinyint(4) NOT NULL COMMENT 'Employee Enabled Status',
  `emppic` varchar(250) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N/A' COMMENT 'Employee Image Name & Ext'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Master (All Employees Details)';

DROP TABLE IF EXISTS `emppayment`;
CREATE TABLE IF NOT EXISTS `emppayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Employee Payment Unique ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `salmonth` tinyint(3) unsigned NOT NULL COMMENT 'Month of the Salary',
  `salyear` smallint(4) NOT NULL COMMENT 'Year of Salary',
  `workingdays` smallint(5) unsigned NOT NULL COMMENT 'Employee Working Days',
  `attenddays` smallint(6) NOT NULL COMMENT 'Employee Attended Days',
  `empsalary` int(11) NOT NULL COMMENT 'Employees Salary',
  `netsalary` int(11) NOT NULL COMMENT 'Employee Total Salary',
  `deduct` int(11) NOT NULL COMMENT 'Deduction from Salary',
  `advdeduct` int(11) NOT NULL COMMENT 'Deduction from Advance',
  `paidamt` int(11) NOT NULL COMMENT 'Amount Paid to Employee',
  `remamt` int(11) NOT NULL COMMENT 'Remaining amount of Payment of Employee',
  `paydate` date NOT NULL COMMENT 'Employee Payment Date'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Salary Payment';

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE IF NOT EXISTS `expenses` (
  `expid` bigint(20) unsigned NOT NULL COMMENT 'Expenses Unique ID',
  `expenses` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Title/Name',
  `expdate` date NOT NULL COMMENT 'Expenses Date',
  `expamt` float unsigned NOT NULL COMMENT 'Expenses Amount',
  `expdesc` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Expenses Details';

DROP TABLE IF EXISTS `firm`;
CREATE TABLE IF NOT EXISTS `firm` (
  `firmid` tinyint(3) unsigned NOT NULL COMMENT 'Firm Details ID',
  `firmname` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Name',
  `licno` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm License Number.',
  `firmaddr` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Address',
  `firmprop` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Proprietor Name',
  `firmphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Contact No.',
  `active` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Active Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Firm Details';

DROP TABLE IF EXISTS `prodcategory`;
CREATE TABLE IF NOT EXISTS `prodcategory` (
  `catid` bigint(20) unsigned NOT NULL COMMENT 'Product Category ID',
  `category` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Category'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodname` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `unitname` varchar(10) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Unit Name',
  `unitdesc` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Unit Description',
  `proddesc` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Description',
  `purchrate` float NOT NULL COMMENT 'Average Purchase Rate',
  `salesrate` float NOT NULL COMMENT 'Current Sales Rate',
  `instock` int(11) NOT NULL COMMENT 'Amount of Product in Stock',
  `catid` bigint(20) unsigned NOT NULL COMMENT 'Product Category ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Product Master';

DROP TABLE IF EXISTS `purchaseorder_m`;
CREATE TABLE IF NOT EXISTS `purchaseorder_m` (
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Order ID',
  `podate` date NOT NULL COMMENT 'Purchase Order Date',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Order (Master)';

DROP TABLE IF EXISTS `purchaseorder_s`;
CREATE TABLE IF NOT EXISTS `purchaseorder_s` (
  `posid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Sub Order ID',
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase M ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sub Purchase Order (Order Details)';

DROP TABLE IF EXISTS `purchasereturn_m`;
CREATE TABLE IF NOT EXISTS `purchasereturn_m` (
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `purchasemid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `returndate` date NOT NULL COMMENT 'Purchases Return Date',
  `total` float NOT NULL COMMENT 'Total Amount of Return'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Return Master';

DROP TABLE IF EXISTS `purchasereturn_s`;
CREATE TABLE IF NOT EXISTS `purchasereturn_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `returnrate` float unsigned NOT NULL COMMENT 'Product Return Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Return Sub';

DROP TABLE IF EXISTS `purchases_m`;
CREATE TABLE IF NOT EXISTS `purchases_m` (
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Mastter ID',
  `pomid` bigint(20) unsigned DEFAULT NULL COMMENT 'Purchases Order Master ID',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `purchasedate` date NOT NULL COMMENT 'Purchases Date',
  `subtotal` float unsigned NOT NULL COMMENT 'Purchases Sub Total',
  `vat` float unsigned NOT NULL COMMENT 'Vat on Purchases',
  `discount` float unsigned NOT NULL COMMENT 'Discount on Purchases',
  `total` float NOT NULL COMMENT 'Total Amount of Purchases',
  `paidamt` float NOT NULL COMMENT 'Amount Paid to Supplier'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `purchases_s`;
CREATE TABLE IF NOT EXISTS `purchases_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `purchrate` float unsigned NOT NULL COMMENT 'Product Purchases Rate',
  `salesrate` float unsigned NOT NULL COMMENT 'Product Sales Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Sub';

DROP TABLE IF EXISTS `runnupcustprods`;
CREATE TABLE IF NOT EXISTS `runnupcustprods` (
  `prodmid` bigint(20) unsigned NOT NULL COMMENT 'Product Master ID',
  `salemid` bigint(20) unsigned NOT NULL COMMENT 'Sales Master ID',
  `prodname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `prodcost` double NOT NULL COMMENT 'Product Price'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `salesorder_m`;
CREATE TABLE IF NOT EXISTS `salesorder_m` (
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `sodate` date NOT NULL COMMENT 'Sales Order Date',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales order Details';

DROP TABLE IF EXISTS `salesorder_s`;
CREATE TABLE IF NOT EXISTS `salesorder_s` (
  `sosid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Sub ID',
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(11) NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Order Sub Details';

DROP TABLE IF EXISTS `salesreturn_m`;
CREATE TABLE IF NOT EXISTS `salesreturn_m` (
  `salereturnmid` bigint(20) unsigned NOT NULL COMMENT 'Sales Return Master ID',
  `salesmid` bigint(20) unsigned NOT NULL COMMENT 'Sales Master ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `salesreturndate` date NOT NULL COMMENT 'Date of Sales Return',
  `total` float unsigned NOT NULL COMMENT 'Total Sales Return Amount',
  `returncharge` double unsigned NOT NULL COMMENT 'Return Charges',
  `prevdr` double NOT NULL COMMENT 'Previous Dr. Amount',
  `returnamount` double NOT NULL COMMENT 'Return Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Return Master Details';

DROP TABLE IF EXISTS `salesreturn_s`;
CREATE TABLE IF NOT EXISTS `salesreturn_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Return Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

DROP TABLE IF EXISTS `sales_m`;
CREATE TABLE IF NOT EXISTS `sales_m` (
  `salemid` bigint(20) unsigned NOT NULL COMMENT 'Sales Master ID',
  `somid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Order Master ID',
  `custid` bigint(20) unsigned DEFAULT NULL COMMENT 'Customer ID',
  `salesdate` date NOT NULL COMMENT 'Date of Sales',
  `subtotal` float unsigned NOT NULL COMMENT 'Sub total amount of Sales',
  `vat` float unsigned NOT NULL COMMENT 'Vat Percentage',
  `discount` float unsigned NOT NULL COMMENT 'Discount Percentage',
  `total` float unsigned NOT NULL COMMENT 'Total Sales Amount',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Master Details';

DROP TABLE IF EXISTS `sales_s`;
CREATE TABLE IF NOT EXISTS `sales_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

DROP TABLE IF EXISTS `strings`;
CREATE TABLE IF NOT EXISTS `strings` (
  `strid` bigint(20) unsigned NOT NULL COMMENT 'String ID',
  `string` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'String',
  `en` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'English',
  `mr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Marathi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Strings for Multilanguage support';

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `supname` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Name',
  `supadd` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Address',
  `supphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Contact No.',
  `company` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Company Details',
  `supcr` float NOT NULL DEFAULT '0' COMMENT 'Supplier Credit Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Details';

DROP TABLE IF EXISTS `supppayment`;
CREATE TABLE IF NOT EXISTS `supppayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Payment ID',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `bankname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Bank Name',
  `bacno` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Bank account Number',
  `prevbal` float unsigned NOT NULL COMMENT 'Previous Balance',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount',
  `remamt` float unsigned NOT NULL COMMENT 'Remaining Amount',
  `slippic` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Slip Picture',
  `paydate` date NOT NULL COMMENT 'Payment Date',
  `billno` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Bill Number'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Payment Details';

DROP TABLE IF EXISTS `test_report`;
CREATE TABLE IF NOT EXISTS `test_report` (
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report ID',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report generated for',
  `address` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Address',
  `contactno` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Contact Number',
  `rpttitle` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Title/Head',
  `rptdate` date NOT NULL,
  `rptsummary` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Summary'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `test_report_prods`;
CREATE TABLE IF NOT EXISTS `test_report_prods` (
  `prodmid` bigint(20) unsigned NOT NULL COMMENT 'Product Master ID',
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report Master ID',
  `prodname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `prodstatus` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `usersid` smallint(5) unsigned NOT NULL COMMENT 'User''s Unique ID',
  `userid` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User ID',
  `pwd` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Password',
  `uautho` tinyint(4) NOT NULL COMMENT 'User Authentication (0 for simple User and 1 for Administrator)',
  `uname` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Name',
  `mob` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User''s Mobile Number'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application''s User Account';


ALTER TABLE `application`
  ADD PRIMARY KEY (`propid`),
  ADD UNIQUE KEY `property` (`property`);

ALTER TABLE `customers`
  ADD PRIMARY KEY (`custid`),
  ADD KEY `idx_routeid_customers` (`routeid`);

ALTER TABLE `custpayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `idx_custid_custpayment` (`custid`);

ALTER TABLE `empadvance`
  ADD PRIMARY KEY (`advid`),
  ADD KEY `empid` (`empid`);

ALTER TABLE `empattendance`
  ADD PRIMARY KEY (`attendid`),
  ADD KEY `empid` (`empid`);

ALTER TABLE `empm`
  ADD PRIMARY KEY (`empid`);

ALTER TABLE `emppayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `empid` (`empid`);

ALTER TABLE `expenses`
  ADD PRIMARY KEY (`expid`);

ALTER TABLE `firm`
  ADD PRIMARY KEY (`firmid`);

ALTER TABLE `prodcategory`
  ADD PRIMARY KEY (`catid`);

ALTER TABLE `products`
  ADD PRIMARY KEY (`prodid`),
  ADD KEY `catid` (`catid`);

ALTER TABLE `purchaseorder_m`
  ADD PRIMARY KEY (`pomid`),
  ADD KEY `supid` (`supid`);

ALTER TABLE `purchaseorder_s`
  ADD PRIMARY KEY (`posid`),
  ADD KEY `pomid` (`pomid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `purchasereturn_m`
  ADD PRIMARY KEY (`purmid`),
  ADD KEY `supid` (`supid`),
  ADD KEY `idx_purchasemid_purchasereturn_m` (`purchasemid`);

ALTER TABLE `purchasereturn_s`
  ADD PRIMARY KEY (`pursid`),
  ADD KEY `purmid` (`purmid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `purchases_m`
  ADD PRIMARY KEY (`purmid`),
  ADD KEY `pomid` (`pomid`),
  ADD KEY `supid` (`supid`);

ALTER TABLE `purchases_s`
  ADD PRIMARY KEY (`pursid`),
  ADD KEY `purmid` (`purmid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `runnupcustprods`
  ADD PRIMARY KEY (`prodmid`),
  ADD KEY `idx_salemid_runnupcustprods` (`salemid`);

ALTER TABLE `salesorder_m`
  ADD PRIMARY KEY (`somid`),
  ADD KEY `custid` (`custid`),
  ADD KEY `custid_2` (`custid`);

ALTER TABLE `salesorder_s`
  ADD PRIMARY KEY (`sosid`),
  ADD KEY `somid` (`somid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `salesreturn_m`
  ADD PRIMARY KEY (`salereturnmid`),
  ADD KEY `custid` (`custid`),
  ADD KEY `idx_salesmid_salesreturn_m` (`salesmid`);

ALTER TABLE `salesreturn_s`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `salemid` (`salemid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `sales_m`
  ADD PRIMARY KEY (`salemid`),
  ADD KEY `somid` (`somid`,`custid`),
  ADD KEY `custid` (`custid`);

ALTER TABLE `sales_s`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `salemid` (`salemid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

ALTER TABLE `strings`
  ADD PRIMARY KEY (`strid`),
  ADD UNIQUE KEY `uk_string_strings` (`string`);

ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supid`);

ALTER TABLE `supppayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `idx_supid_supppayment` (`supid`);

ALTER TABLE `test_report`
  ADD PRIMARY KEY (`rptid`);

ALTER TABLE `test_report_prods`
  ADD PRIMARY KEY (`prodmid`),
  ADD KEY `idx_rptid_test_report_prods` (`rptid`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`usersid`);


ALTER TABLE `application`
  MODIFY `propid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Properties Unique ID';
ALTER TABLE `customers`
  MODIFY `custid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Customer ID';
ALTER TABLE `custpayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID';
ALTER TABLE `empadvance`
  MODIFY `advid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Emp. Advance Payment ID';
ALTER TABLE `empattendance`
  MODIFY `attendid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Attendance Unique ID';
ALTER TABLE `empm`
  MODIFY `empid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Unique ID';
ALTER TABLE `emppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Payment Unique ID';
ALTER TABLE `expenses`
  MODIFY `expid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Expenses Unique ID';
ALTER TABLE `firm`
  MODIFY `firmid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Firm Details ID';
ALTER TABLE `prodcategory`
  MODIFY `catid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Category ID';
ALTER TABLE `products`
  MODIFY `prodid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product ID';
ALTER TABLE `purchaseorder_m`
  MODIFY `pomid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Order ID';
ALTER TABLE `purchaseorder_s`
  MODIFY `posid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Sub Order ID';
ALTER TABLE `purchasereturn_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Master ID';
ALTER TABLE `purchasereturn_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Sub ID';
ALTER TABLE `purchases_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Mastter ID';
ALTER TABLE `purchases_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Sub ID';
ALTER TABLE `runnupcustprods`
  MODIFY `prodmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Master ID';
ALTER TABLE `salesorder_m`
  MODIFY `somid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Master ID';
ALTER TABLE `salesorder_s`
  MODIFY `sosid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Sub ID';
ALTER TABLE `salesreturn_m`
  MODIFY `salereturnmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Master ID';
ALTER TABLE `salesreturn_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Sub ID';
ALTER TABLE `sales_m`
  MODIFY `salemid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Master ID';
ALTER TABLE `sales_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Sub ID';
ALTER TABLE `strings`
  MODIFY `strid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'String ID';
ALTER TABLE `suppliers`
  MODIFY `supid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Supplier ID';
ALTER TABLE `supppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID';
ALTER TABLE `test_report`
  MODIFY `rptid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Report ID';
ALTER TABLE `test_report_prods`
  MODIFY `prodmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Master ID';
ALTER TABLE `users`
  MODIFY `usersid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'User''s Unique ID';

ALTER TABLE `customers`
  ADD CONSTRAINT `fk_routeid_customers` FOREIGN KEY (`routeid`) REFERENCES `route_m` (`routeid`);

ALTER TABLE `custpayment`
  ADD CONSTRAINT `fk_custid_custpayment` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`);

ALTER TABLE `empadvance`
  ADD CONSTRAINT `fk_EmpAdvance_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

ALTER TABLE `empattendance`
  ADD CONSTRAINT `fk_EmpAttendance_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

ALTER TABLE `emppayment`
  ADD CONSTRAINT `fk_EmpPayment_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_catid` FOREIGN KEY (`catid`) REFERENCES `prodcategory` (`catid`);

ALTER TABLE `purchaseorder_m`
  ADD CONSTRAINT `fk_purchaseorder_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

ALTER TABLE `purchaseorder_s`
  ADD CONSTRAINT `fk_purchaseorder_s_pomid` FOREIGN KEY (`pomid`) REFERENCES `purchaseorder_m` (`pomid`),
  ADD CONSTRAINT `fk_purchaseorder_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`);

ALTER TABLE `purchasereturn_m`
  ADD CONSTRAINT `fk_purchasereturn_m_purchasemid` FOREIGN KEY (`purchasemid`) REFERENCES `purchases_m` (`purmid`),
  ADD CONSTRAINT `fk_purchasereturn_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

ALTER TABLE `purchasereturn_s`
  ADD CONSTRAINT `fk_purchasereturn_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_purchasereturn_s_purmid` FOREIGN KEY (`purmid`) REFERENCES `purchasereturn_m` (`purmid`);

ALTER TABLE `purchases_m`
  ADD CONSTRAINT `fk_purchases_m_pomid` FOREIGN KEY (`pomid`) REFERENCES `purchaseorder_m` (`pomid`),
  ADD CONSTRAINT `fk_purchases_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

ALTER TABLE `purchases_s`
  ADD CONSTRAINT `fk_purchases_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_purchases_s_purmid` FOREIGN KEY (`purmid`) REFERENCES `purchases_m` (`purmid`);

ALTER TABLE `runnupcustprods`
  ADD CONSTRAINT `fk_salemid_runnupcustprods` FOREIGN KEY (`salemid`) REFERENCES `sales_m` (`salemid`);

ALTER TABLE `salesorder_m`
  ADD CONSTRAINT `fk_salesorder_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`);

ALTER TABLE `salesorder_s`
  ADD CONSTRAINT `fk_salesorder_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_salesorder_s_somid` FOREIGN KEY (`somid`) REFERENCES `salesorder_m` (`somid`);

ALTER TABLE `salesreturn_m`
  ADD CONSTRAINT `fk_salesreturn_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`),
  ADD CONSTRAINT `fk_salesreturn_m_salesmid` FOREIGN KEY (`salesmid`) REFERENCES `sales_m` (`salemid`);

ALTER TABLE `salesreturn_s`
  ADD CONSTRAINT `fk_salesreturn_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_salesreturn_s_salemid` FOREIGN KEY (`salemid`) REFERENCES `salesreturn_m` (`salereturnmid`);

ALTER TABLE `sales_m`
  ADD CONSTRAINT `fk_sales_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`),
  ADD CONSTRAINT `fk_sales_m_somid` FOREIGN KEY (`somid`) REFERENCES `salesorder_m` (`somid`);

ALTER TABLE `sales_s`
  ADD CONSTRAINT `fk_sales_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_sales_s_salemid` FOREIGN KEY (`salemid`) REFERENCES `sales_m` (`salemid`);

ALTER TABLE `supppayment`
  ADD CONSTRAINT `fk_supid_supppayment` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

ALTER TABLE `test_report_prods`
  ADD CONSTRAINT `fk_rptid_test_report_prods` FOREIGN KEY (`rptid`) REFERENCES `test_report` (`rptid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
