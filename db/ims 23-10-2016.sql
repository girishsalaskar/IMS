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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application Properties';

TRUNCATE TABLE `application`;
INSERT INTO `application` (`propid`, `property`, `value`) VALUES
(1, 'firmname', 'General Stores'),
(2, 'clientname', 'Girish'),
(3, 'registered', 'M/ZVPYY2s5nnEna5EXKBuAVrcMxbFVpztoX2ED4aHYg=');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer''s Details';

TRUNCATE TABLE `customers`;
INSERT INTO `customers` (`custid`, `custname`, `custadd`, `custcity`, `custphone`, `custref`, `debtamt`, `crate_holds`, `routeid`) VALUES
(1, 'Girish Salaskar', 'Opp. Mahadev Temple, Khardi. Tal. Pandharpur. dist. Solapur.', 'Pandharpur', '9890098900', '-', 0, 0, NULL),
(2, 'Shrikant Ratnaparakhi', 'Pandharpur', 'Pandharpur', '9890098900', '-', 49, 0, NULL);

DROP TABLE IF EXISTS `custpayment`;
CREATE TABLE IF NOT EXISTS `custpayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Payment ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `paydate` date NOT NULL COMMENT 'Payment Date',
  `prevbal` float unsigned NOT NULL COMMENT 'Previous Balance',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount',
  `remamt` float unsigned NOT NULL COMMENT 'Remaining Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer Payment Details';

TRUNCATE TABLE `custpayment`;
DROP TABLE IF EXISTS `empadvance`;
CREATE TABLE IF NOT EXISTS `empadvance` (
  `advid` bigint(20) unsigned NOT NULL COMMENT 'Emp. Advance Payment ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `paiddate` date NOT NULL COMMENT 'Emp. Advance Paid Date',
  `prevbal` int(11) NOT NULL COMMENT 'Emp. Previous Balance',
  `paidamt` int(11) NOT NULL COMMENT 'Amount Paid to Employee',
  `balanceamt` int(11) NOT NULL COMMENT 'Employees Balance Amount'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employees Advance Payment';

TRUNCATE TABLE `empadvance`;
INSERT INTO `empadvance` (`advid`, `empid`, `paiddate`, `prevbal`, `paidamt`, `balanceamt`) VALUES
(1, 1, '2016-09-05', 0, 2000, 2000),
(2, 1, '2016-09-05', 2000, 1000, 3000),
(3, 1, '2016-09-10', 3000, 1200, 4200),
(4, 2, '2016-09-05', 0, 0, 0),
(5, 2, '2016-09-05', 0, 5000, 5000),
(6, 3, '2015-09-12', 0, 100, 100);

DROP TABLE IF EXISTS `empattendance`;
CREATE TABLE IF NOT EXISTS `empattendance` (
  `attendid` bigint(20) unsigned NOT NULL COMMENT 'Attendance Unique ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `attenddate` date NOT NULL COMMENT 'Employee Attendance Date',
  `status` tinyint(4) NOT NULL COMMENT 'Employee Attendance Status'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Attendance';

TRUNCATE TABLE `empattendance`;
INSERT INTO `empattendance` (`attendid`, `empid`, `attenddate`, `status`) VALUES
(1, 1, '2016-09-04', 1),
(2, 2, '2016-09-04', 1),
(3, 1, '2016-09-05', 1),
(4, 2, '2016-09-05', 0),
(5, 1, '2016-09-06', 0),
(6, 2, '2016-09-06', 1),
(7, 1, '2016-09-07', 0),
(8, 2, '2016-09-07', 0),
(9, 1, '2016-09-12', 1),
(10, 3, '2016-09-12', 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Master (All Employees Details)';

TRUNCATE TABLE `empm`;
INSERT INTO `empm` (`empid`, `empname`, `empadd`, `empmo`, `joindate`, `desgn`, `empsal`, `acbal`, `remsalary`, `leavedate`, `empenabled`, `emppic`) VALUES
(1, 'Ramesh Shinde', 'Laxmi White Hostel,\nPune', '9890098900', '2015-09-15', 'Manager', 12000, 1200, 6000, NULL, 1, 'N/A'),
(2, 'Suresh', 'Ganesh Nagar,\nPune.', '9890098900', '2016-09-05', 'Assist Manager', 6000, 0, 0, '2016-09-06', 0, 'jpg'),
(3, 'Suresh Ramesh Cadeboury', 'Pandharpur', '9890098900', '2016-09-12', 'Mgr', 2000, 100, 0, NULL, 1, 'jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Salary Payment';

TRUNCATE TABLE `emppayment`;
INSERT INTO `emppayment` (`payid`, `empid`, `salmonth`, `salyear`, `workingdays`, `attenddays`, `empsalary`, `netsalary`, `deduct`, `advdeduct`, `paidamt`, `remamt`, `paydate`) VALUES
(1, 1, 9, 2016, 4, 2, 12000, 6000, 3000, 3000, 0, 6000, '2016-09-05'),
(2, 2, 9, 2016, 4, 2, 6000, 2000, 3000, 1000, 0, 2000, '2016-09-05');

DROP TABLE IF EXISTS `expenses`;
CREATE TABLE IF NOT EXISTS `expenses` (
  `expid` bigint(20) unsigned NOT NULL COMMENT 'Expenses Unique ID',
  `expenses` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Title/Name',
  `expdate` date NOT NULL COMMENT 'Expenses Date',
  `expamt` float unsigned NOT NULL COMMENT 'Expenses Amount',
  `expdesc` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Description'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Expenses Details';

TRUNCATE TABLE `expenses`;
INSERT INTO `expenses` (`expid`, `expenses`, `expdate`, `expamt`, `expdesc`) VALUES
(1, 'Light Bill', '2016-09-06', 1500, 'Light Bill'),
(2, 'Phone Bill', '2016-09-06', 1000, 'Phone Billl');

DROP TABLE IF EXISTS `firm`;
CREATE TABLE IF NOT EXISTS `firm` (
  `firmid` tinyint(3) unsigned NOT NULL COMMENT 'Firm Details ID',
  `firmname` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Name',
  `licno` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm License Number.',
  `firmaddr` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Address',
  `firmprop` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Proprietor Name',
  `firmphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Contact No.',
  `active` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'Active Status'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Firm Details';

TRUNCATE TABLE `firm`;
INSERT INTO `firm` (`firmid`, `firmname`, `licno`, `firmaddr`, `firmprop`, `firmphone`, `active`) VALUES
(1, 'Sabale Automobiles', '-', 'Address', 'Sumit Sabale', '98900980900', 1),
(2, 'Sabale Workshop', '-', 'addr', 'sumit', '989898989898', 0),
(3, 'Sabale Systems', '-', 'skldfj', 'sdfkj', 'lsdfj', 0),
(4, 'systems', 'lsdfkj', 'lskdfj', 'lsdkfj', 'slfkj', 0);

DROP TABLE IF EXISTS `prodcategory`;
CREATE TABLE IF NOT EXISTS `prodcategory` (
  `catid` bigint(20) unsigned NOT NULL COMMENT 'Product Category ID',
  `category` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Category'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

TRUNCATE TABLE `prodcategory`;
INSERT INTO `prodcategory` (`catid`, `category`) VALUES
(1, 'Cat1'),
(2, 'Cat2');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Product Master';

TRUNCATE TABLE `products`;
INSERT INTO `products` (`prodid`, `prodname`, `unitname`, `unitdesc`, `proddesc`, `purchrate`, `salesrate`, `instock`, `catid`) VALUES
(1, 'Product1', 'kg', 'Kilo gram', '-', 10, 13, -20, 1),
(2, 'Product2', 'kg', 'kg', 'desc', 12, 15, 5, 2),
(3, 'Product3', 'nm', 'nm', 'nm', 18, 22, 8, 1),
(4, 'Product4', 'nm', 'nm', 'nm', 12, 15, 20, 2),
(5, 'Product3', 'mm', 'mlmt', 'desc', 0, 0, 0, 2);

DROP TABLE IF EXISTS `purchaseorder_m`;
CREATE TABLE IF NOT EXISTS `purchaseorder_m` (
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Order ID',
  `podate` date NOT NULL COMMENT 'Purchase Order Date',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Order (Master)';

TRUNCATE TABLE `purchaseorder_m`;
INSERT INTO `purchaseorder_m` (`pomid`, `podate`, `supid`) VALUES
(1, '2016-09-06', 2);

DROP TABLE IF EXISTS `purchaseorder_s`;
CREATE TABLE IF NOT EXISTS `purchaseorder_s` (
  `posid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Sub Order ID',
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase M ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sub Purchase Order (Order Details)';

TRUNCATE TABLE `purchaseorder_s`;
INSERT INTO `purchaseorder_s` (`posid`, `pomid`, `prodid`, `prodqty`) VALUES
(1, 1, 1, 15),
(2, 1, 2, 10),
(3, 1, 3, 20);

DROP TABLE IF EXISTS `purchasereturn_m`;
CREATE TABLE IF NOT EXISTS `purchasereturn_m` (
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `purchasemid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `returndate` date NOT NULL COMMENT 'Purchases Return Date',
  `total` float NOT NULL COMMENT 'Total Amount of Return'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Return Master';

TRUNCATE TABLE `purchasereturn_m`;
DROP TABLE IF EXISTS `purchasereturn_s`;
CREATE TABLE IF NOT EXISTS `purchasereturn_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `returnrate` float unsigned NOT NULL COMMENT 'Product Return Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Return Sub';

TRUNCATE TABLE `purchasereturn_s`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

TRUNCATE TABLE `purchases_m`;
INSERT INTO `purchases_m` (`purmid`, `pomid`, `supid`, `purchasedate`, `subtotal`, `vat`, `discount`, `total`, `paidamt`) VALUES
(1, 1, 2, '2016-09-06', 724, 20, 4, 740, 500),
(2, 1, 1, '2016-09-06', 440, 20, 0, 460, 500);

DROP TABLE IF EXISTS `purchases_s`;
CREATE TABLE IF NOT EXISTS `purchases_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `purchrate` float unsigned NOT NULL COMMENT 'Product Purchases Rate',
  `salesrate` float unsigned NOT NULL COMMENT 'Product Sales Rate'
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Sub';

TRUNCATE TABLE `purchases_s`;
INSERT INTO `purchases_s` (`pursid`, `purmid`, `prodid`, `prodqty`, `purchrate`, `salesrate`) VALUES
(1, 1, 1, 10, 10, 12),
(2, 1, 2, 10, 12, 15),
(3, 1, 3, 20, 18, 22),
(4, 1, 4, 12, 12, 15),
(5, 2, 4, 20, 12, 15),
(6, 2, 1, 20, 10, 12);

DROP TABLE IF EXISTS `runnupcustprods`;
CREATE TABLE IF NOT EXISTS `runnupcustprods` (
  `prodmid` bigint(20) unsigned NOT NULL COMMENT 'Product Master ID',
  `salemid` bigint(20) unsigned NOT NULL COMMENT 'Sales Master ID',
  `prodname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `prodcost` double NOT NULL COMMENT 'Product Price'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

TRUNCATE TABLE `runnupcustprods`;
INSERT INTO `runnupcustprods` (`prodmid`, `salemid`, `prodname`, `prodcost`) VALUES
(1, 6, 'NAME', 10000),
(2, 6, 'addr', 20),
(3, 6, '23w', 23),
(4, 7, 'Drilling', 10),
(5, 7, 'Skrew Fitting', 20),
(6, 7, 'lksdjf', 324),
(7, 7, 'dkfj', 39),
(8, 8, 'Drilling', 20),
(9, 8, 'Skrew Fitting', 30),
(10, 8, 'Cutting', 10);

DROP TABLE IF EXISTS `salesorder_m`;
CREATE TABLE IF NOT EXISTS `salesorder_m` (
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `sodate` date NOT NULL COMMENT 'Sales Order Date',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales order Details';

TRUNCATE TABLE `salesorder_m`;
INSERT INTO `salesorder_m` (`somid`, `sodate`, `custid`) VALUES
(1, '2016-09-06', 2),
(2, '2016-09-07', 1),
(3, '2016-10-23', 2);

DROP TABLE IF EXISTS `salesorder_s`;
CREATE TABLE IF NOT EXISTS `salesorder_s` (
  `sosid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Sub ID',
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(11) NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Order Sub Details';

TRUNCATE TABLE `salesorder_s`;
INSERT INTO `salesorder_s` (`sosid`, `somid`, `prodid`, `prodqty`) VALUES
(1, 1, 1, 12),
(2, 1, 2, 5),
(3, 1, 4, 5),
(4, 1, 3, 10),
(5, 2, 1, 10),
(6, 2, 3, 20),
(7, 3, 1, 10),
(8, 3, 3, 10);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Return Master Details';

TRUNCATE TABLE `salesreturn_m`;
INSERT INTO `salesreturn_m` (`salereturnmid`, `salesmid`, `custid`, `salesreturndate`, `total`, `returncharge`, `prevdr`, `returnamount`) VALUES
(1, 1, 2, '2016-09-06', 740, 40, 500, 700),
(2, 4, 1, '2016-09-14', 700, 50, 200, 650);

DROP TABLE IF EXISTS `salesreturn_s`;
CREATE TABLE IF NOT EXISTS `salesreturn_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Return Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

TRUNCATE TABLE `salesreturn_s`;
INSERT INTO `salesreturn_s` (`salesid`, `salemid`, `prodid`, `prodqty`, `prodrate`, `totrate`) VALUES
(1, 1, 1, 40, 13, 520),
(2, 1, 3, 10, 22, 220),
(3, 2, 3, 10, 22, 220),
(4, 2, 4, 27, 15, 405),
(5, 2, 2, 5, 15, 75);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Master Details';

TRUNCATE TABLE `sales_m`;
INSERT INTO `sales_m` (`salemid`, `somid`, `custid`, `salesdate`, `subtotal`, `vat`, `discount`, `total`, `paidamt`) VALUES
(1, 1, 2, '2016-09-06', 1020, 0, 20, 1000, 500),
(4, NULL, 1, '2016-09-06', 700, 0, 0, 700, 500),
(5, NULL, NULL, '2016-09-15', 60, 0, 0, 60, 60),
(6, NULL, NULL, '2016-09-15', 10043, 0, 0, 10043, 10043),
(7, NULL, NULL, '2016-09-15', 393, 0, 0, 393, 393),
(8, NULL, NULL, '2016-09-16', 50, 0, 0, 50, 50),
(9, NULL, 2, '2016-10-07', 149, 0, 0, 149, 100);

DROP TABLE IF EXISTS `sales_s`;
CREATE TABLE IF NOT EXISTS `sales_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

TRUNCATE TABLE `sales_s`;
INSERT INTO `sales_s` (`salesid`, `salemid`, `prodid`, `prodqty`, `prodrate`, `totrate`) VALUES
(1, 1, 1, 50, 13, 650),
(2, 1, 2, 5, 15, 75),
(3, 1, 4, 5, 15, 75),
(4, 1, 3, 10, 22, 220),
(5, 4, 3, 10, 22, 220),
(6, 4, 4, 27, 15, 405),
(7, 4, 2, 5, 15, 75),
(8, 9, 3, 2, 22, 44),
(9, 9, 4, 7, 15, 105);

DROP TABLE IF EXISTS `strings`;
CREATE TABLE IF NOT EXISTS `strings` (
  `strid` bigint(20) unsigned NOT NULL COMMENT 'String ID',
  `string` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'String',
  `en` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'English',
  `mr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Marathi'
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Strings for Multilanguage support';

TRUNCATE TABLE `strings`;
INSERT INTO `strings` (`strid`, `string`, `en`, `mr`) VALUES
(1, 'mnuemployee', 'Workers', 'कामगार'),
(2, 'mnuproduct', 'Product', 'उत्पादन'),
(3, 'mnutrans', 'Transaction', 'व्यवहार'),
(4, 'mnuroute', 'Route Management', 'फेरी व्यवस्थापन'),
(5, 'mnureport', 'Reports', 'अहवाल'),
(6, 'mnufirm', 'Firm', 'व्यवसय'),
(7, 'mnuuser', 'User Account', 'युजर खाते'),
(8, 'mniempenroll', 'Worker Enrollment', 'नवीन कामगार नोंदणी'),
(9, 'mniempadvance', 'Advance to Worker', 'कामगारांचा ॲडव्हान्स'),
(10, 'mniempattend', 'Worker Attendance', 'कामगार हजेरी'),
(11, 'mnuempsalary', 'Worker Wages', 'कामगारांचा पगार'),
(12, 'mniempsettle', 'Worker Settlement', 'कामगार हिशोब'),
(13, 'mniempsalpayment', 'Worker Wages Payment', 'कामगार पगार देयक'),
(14, 'mniempsalslip', 'Worker Wages Slip', 'पगार पत्रक स्लीप'),
(15, 'mniempsalreport', 'Monthwise Wages Report', 'मासिक पगार पत्रक'),
(16, 'mniprodcat', 'Product Category', 'उत्पादन प्रकार'),
(17, 'mniproduct', 'Products', 'उत्पादन माहिती'),
(18, 'mnucust', 'Customers', 'ग्राहक'),
(19, 'mnusupplier', 'Suppliers', 'विक्रेते'),
(20, 'mnupurch', 'Purchases', 'खरेदीविषयक'),
(21, 'mnusales', 'Sales', 'विक्रीविषयक'),
(22, 'mniexpenses', 'Expenses/Losses', 'खर्च/तोटे'),
(23, 'mnicustdetails', 'Customer Details', 'ग्राहकांची माहिती'),
(24, 'mnicustpayment', 'Customer Payment', 'उधारी वसुली'),
(25, 'mnisuppdetails', 'Supplier Details', 'विक्रेत्यांची माहिती'),
(26, 'mnisuppayment', 'Supplier Payment', 'उधारी परतफेड'),
(27, 'mnisuppaymentrpt', 'Supplier Payment Report', 'उधारी परतफेड अहवाल'),
(28, 'mnipurchorder', 'Purchases Order', 'खरेदीपुर्व ऑर्डर'),
(29, 'mnipurchases', 'Purchases', 'खरेदी'),
(30, 'mnisalesorder', 'Sales Order', 'विक्रीपुर्व ऑर्डर'),
(31, 'mnisales', 'Sales', 'विक्री'),
(32, 'mnicustroutes', 'Customer Routes', 'ग्राहकांचे रस्ते'),
(33, 'mniroutemaster', 'Route Master ', 'फेरी मस्टर'),
(34, 'mniroutetrip', 'Trip', 'फेरी'),
(35, 'mnipurchrpt', 'Purchases Report', 'खरेदी अहवाल'),
(36, 'mnicurstock', 'Current Stock Status', 'उत्पादन सद्य स्थिती'),
(37, 'mnisalesrpt', 'Sales Report', 'विक्री अहवाल'),
(38, 'mnishopstat', 'Business Status', 'व्यवसाय सद्य स्थिती'),
(39, 'mnicustrpt', 'Customer Reports', 'ग्राहकांचे अहवाल'),
(40, 'mnifirmprof', 'Firm Profile', 'व्यवसाय माहिती'),
(41, 'mniassets', 'Firm Assets', 'व्यवसायातील संपत्ती'),
(42, 'mninewuser', 'New User', 'नवीन युजर'),
(43, 'mniupdateuser', 'Update User', 'युजर माहिती अद्ययावत'),
(44, 'mnichangepass', 'Change Password', 'पासवर्ड बदला'),
(45, 'lblempid', 'Worker ID', 'कामगार आय.डी.'),
(46, 'lblempname', 'Worker Name', 'कामगार नाव'),
(47, 'lblempadd', 'Worker Address', 'पत्ता'),
(48, 'lblcontactno', 'Contact Number', 'दुरध्वनी क्रमांक'),
(49, 'lblemppic', 'Worker Picture', 'कामगाराचा फोटो'),
(50, 'lbljoindate', 'Joining Date', 'कामावर आल्याची दिनांक'),
(51, 'lbldesgn', 'Designation', 'हुद्दा'),
(52, 'lblempsal', 'Wages', 'पगार'),
(53, 'chkempadvance', 'Advance to Worker', 'ॲडव्हान्स'),
(54, 'btnpicbrowse', 'Select Picture', 'फोटो निवडा'),
(55, 'btnaddnew', 'New', 'नवीन'),
(56, 'btnupdate', 'Update', 'जतन'),
(57, 'btnreset', 'Reset', 'पुर्ववत'),
(58, 'lblempprevbal', 'Previous Advance', 'आधीचा ॲडव्हान्स'),
(59, 'lblempadvpaydate', 'Payment Date', 'ॲड. दिल्याची दिनांक'),
(60, 'lblempadvpaidamt', 'Amount Paid', 'ॲडव्हान्स रक्कम रु.'),
(61, 'lblempadvnewbal', 'New Balance', 'एकुण ॲडव्हान्स'),
(62, 'lblemppaynsave', 'Pay & Save', 'जतन'),
(63, 'lblempattenddate', 'Attendance Date', 'हजेरी दिनांक'),
(64, 'btnempsaveattend', 'Save attendance', 'हजेरी जतन'),
(65, 'mnulang', 'Language', 'भाषा'),
(66, 'chkempdeductfromadv', 'Deduct from Adv.', 'ॲडव्हान्समधुन कपात'),
(67, 'lblemprempay', 'Rem. Payment', 'उर्वरित पगार'),
(68, 'lblnetsalary', 'Net Wages', 'एकुण पगार'),
(69, 'lblsalyear', 'Wages Year', 'वर्ष'),
(70, 'lblsalmonth', 'Wages Month', 'महिना'),
(71, 'lblworkdays', 'Working Days', 'कामाचे दिवस'),
(72, 'lblattenddays', 'Attended Days', 'एकुण हजर दिवस'),
(73, 'lblprevpendingsal', 'Prev. Pending Wages', 'आधीचा उर्वरित पगार'),
(74, 'lbldeduction', 'Deduction', 'पगारातुन कपात'),
(75, 'lblpaidamt', 'Paid Amount', 'दिलेला पगार'),
(76, 'lbltotaladvamt', 'Total Advance Amt.', 'एकुण ॲडव्हान्स'),
(77, 'btncalculate', 'Calculate', 'हिशोब करा'),
(78, 'btnpaynsave', 'Pay and Save', 'जतन करा'),
(79, 'btngenerate', 'Generate', 'तयार करा'),
(80, 'btnshow', 'Show', 'पहा'),
(81, 'lblleavedate', 'Leave Date', 'सोडल्याची दिनांक'),
(82, 'lblemppayorreceive', 'Pay/Receive Rs.', 'रु. येणे/देणे'),
(83, 'lblassetlist', 'Name', 'नाव'),
(84, 'lblassetname', 'Other', 'इतर'),
(85, 'lblassetvalue', 'Value', 'किंमत'),
(86, 'lblcustid', 'Customer ID', 'ग्राहक आय.डी.'),
(87, 'lblcustname', 'Customer Name', 'ग्राहकाचे नाव'),
(88, 'lblcustadd', 'Customer Address', 'ग्राहकाचा पत्ता'),
(89, 'lblcity', 'City', 'गाव/शहर'),
(90, 'lblcustrefdet', 'Reference Details', 'ग्राहक संदर्भ माहिती'),
(91, 'lblcustdebitamt', 'Debit Amount', 'रक्कम येणे रु.'),
(92, 'lblcustpaidamt', 'Paid Amount', 'दिलेली रक्कम रु.'),
(93, 'lblcustpaydate', 'Paid Date', 'जमा दिनांक'),
(94, 'lblcustremainamt', 'Remaining Amount', 'उर्वरित येणे रु.'),
(95, 'lblcustpaydetails', 'Payment Details', 'इतर माहिती/टिप्पणी'),
(96, 'btncustreceivesave', 'Receive & Save', 'जमा'),
(97, 'lblroutename', 'Route Name', 'रस्त्याचे नाव'),
(98, 'lblrouteno', 'Route No.', 'रस्ता क्र.'),
(99, 'lblsupid', 'Supplier ID', 'विक्रेता आय.डी.'),
(100, 'lblsupname', 'Supplier Name', 'विक्रेत्याचे नाव'),
(101, 'lblsupaddress', 'Address', 'विक्रेत्याचा पत्ता'),
(102, 'lblsupcompany', 'Company', 'कंपनी'),
(103, 'lblsuptotalpending', 'Total Pending', 'एकुण देणे रु.'),
(104, 'lblbankname', 'Bank Name', 'बॅंकेचे नाव'),
(105, 'lblaccountno', 'Account No.', 'खाते क्रमांक'),
(106, 'lblsuppaydate', 'Payment Date', 'पैसे दिल्याची दिनांक'),
(107, 'lblsuppaidamt', 'Paid Amount', 'दिलेली रक्कम रु.'),
(108, 'lblsupremainamt', 'Rem. Payment', 'उर्वरित रक्कम रु.'),
(109, 'lblbillno', 'Bill No.', 'बिल क्रमांक'),
(110, 'lblsuppayslip', 'Payment Slip', 'पैसे दिल्याची पावती'),
(111, 'btnbrowse', 'Browse', 'ब्राऊज'),
(112, 'lblsuppayid', 'Payment ID', 'देयक आय.डी.'),
(113, 'lblorderdate', 'Order Date', 'ऑर्डर दिनांक'),
(114, 'lblordernote', 'Order Note', 'ऑर्डर टिप्पणी'),
(115, 'lblprodid', 'Product ID', 'उत्पा. आय.डी.'),
(116, 'lblprodcategory', 'Product Category', 'उत्पादन प्रकार'),
(117, 'lblprodname', 'Product Name', 'उत्पादन नाव'),
(118, 'lblprodqty', 'Quantities', 'नग'),
(119, 'btnadd', 'Add', 'भरा'),
(120, 'btnremoveall', 'Remove All', 'सर्व काढा'),
(121, 'btncreateorder', 'Create Order', 'ऑर्डर तयार करा'),
(122, 'lblorderid', 'Order ID', 'ऑर्डर आय.डी.'),
(123, 'lblpurchdate', 'Purchase Date', 'खरेदी दिनांक'),
(124, 'lblpurchnote', 'Purchase Note', 'खरेदी टिप्पणी'),
(125, 'lblpurchrateperunit', 'Purch.Rate/Unit', 'खरेदी दर/नग'),
(126, 'lblsalesrateperunit', 'Sales Rate/Unit', 'विक्री दर/नग'),
(127, 'btnaddtopurchlist', 'Add to Purchase List', 'यादीत टाका'),
(128, 'lbltotal', 'Grand Total', 'निव्वळ एकुण'),
(129, 'lbldiscount', 'Discount', 'सुट'),
(130, 'lblvat', 'Vat', 'व्हॅट'),
(131, 'lblsubtotal', 'Total Amount', 'एकुण रक्कम'),
(132, 'lblprevpaycr', 'Prev. Payable Cr.', 'मागील बाकी'),
(133, 'lblpayamount', 'Paid Amount', 'दिलेली रक्कम'),
(134, 'lblremamt', 'Remaining Amt.', 'राहिलेली रक्कम'),
(135, 'lblnewpaycr', 'New Payable Cr.', 'एकुण देणे रु.'),
(136, 'btnaddpurchases', 'Add Purchase Entry', 'खरेदी नोंद'),
(137, 'lblsalesdate', 'Sales Date', 'विक्री दिनांक'),
(138, 'lblsalesnote', 'Sales Note', 'विक्री टिप्पणी'),
(139, 'btnaddtosaleslist', 'Add to Sales List', 'यादीत टाका'),
(140, 'lblprevrecdr', 'Prev. Receivable Dr.', 'मागील रक्कम येणे'),
(141, 'lblnewrecdr', 'New Receivable Dr.', 'एकुण रक्कम येणे'),
(142, 'btnaddsales', 'Add Sales Entry', 'विक्री नोंद'),
(143, 'lblinstock', 'In Stock', 'शिल्लक'),
(144, 'lblexpenses', 'Expenses Title', 'खर्च नावे'),
(145, 'lblexpamt', 'Expenses Amount', 'खर्च रु.'),
(146, 'lblexpdate', 'Expenses Date', 'खर्च दिनांक'),
(147, 'lblexpdesc', 'Expenses Description', 'इतर माहिती'),
(148, 'lblcities', 'Cities/Villages', 'गावे/शहरे'),
(149, 'lblvehicle', 'Vehicle', 'वाहन'),
(150, 'lblsentcrate', 'Crate Sent', 'क्रेट पाठवले'),
(151, 'lbltripdate', 'Trip Date', 'ट्रिप दिनांक'),
(152, 'lblcraterec', 'Crate Received', 'क्रेट घेतले'),
(153, 'lblpurchid', 'Purchase ID', 'खरेदी आय.डी.'),
(154, 'lblstartdate', 'Start Date', 'सुरुवात दिनांक'),
(155, 'lblenddate', 'End Date', 'शेवट दिनांक'),
(156, 'lblsalesid', 'Sales ID', 'विक्री आय.डी.'),
(157, 'lblfirmname', 'Firm Name', 'दुकानचे नाव'),
(158, 'lbllicenseno', 'License No.', 'लायसेन्स नं.'),
(159, 'lblownername', 'Owner Name', 'दुकानदाराचे नाव'),
(160, 'lblfirmaddr', 'Firm Address', 'दुकान पत्ता'),
(161, 'lblprodunitname', 'Unit Name', 'युनिट नाव'),
(162, 'lblprodunitdesc', 'Unit Desc.', 'युनिट माहिती'),
(163, 'lblproddesc', 'Description', 'इतर माहिती'),
(164, 'lblprodcatid', 'Category ID', 'उत्पा. प्रकार आय.डी.'),
(165, 'btnresign', 'Resign & Save', 'जतन करा'),
(166, 'lbldatewise', 'Datewise', 'तारखेनुसार'),
(167, 'lblcustomerwise', 'Customerwise', 'ग्राहकानुसार'),
(168, 'lbloldpassword', 'Old Password', 'जुना पासवर्ड'),
(169, 'lbluserid', 'User ID', 'युजर आय.डी.'),
(170, 'lblpassword', 'Password', 'पासवर्ड'),
(171, 'lblconfirmpassword', 'Confirm Password', 'पासवर्डची खात्री'),
(172, 'lblusertype', 'User Type', 'युजर प्रकार'),
(173, 'lblusername', 'User Name', 'युजरचे नाव'),
(174, 'btnsave', 'Save', 'जतन'),
(175, 'lblnewpassword', 'New Password', 'नवीन पासवर्ड'),
(176, 'mnitestreport', 'Testing Report', 'तपासणी रिपोर्ट'),
(177, 'lblreportdate', 'Report Date', 'रिपोर्ट दिनांक'),
(178, 'lblreporttitle', 'Report Title', 'रिपोर्ट टायटल'),
(179, 'lblproductstatus', 'Product Status', 'उत्पादन स्थिती'),
(180, 'lblreportsummary', 'Report Summary', 'रिपोर्ट सारांश'),
(181, 'lblreportid', 'Report ID', 'अहवाल आय.डी.'),
(182, 'mnisalesreturn', 'Sales Return', 'विक्री परत'),
(183, 'btnaddreturn', 'Add Return Entry', 'परताव्याची नोंद करा'),
(184, 'lblchargeonreturn', 'Return Charges', 'परतावा कर'),
(185, 'lblreturnamtrs', 'Return Amount', 'परतावा रक्कम रु.'),
(186, 'mnisalesreturnrpt', 'Sales Return Report', 'विक्री परत अहवाल'),
(187, 'mnicustdebtreport', 'Customer Debit Report', 'ग्राहक येणे बाकी अहवाल'),
(188, 'mnisupcrreport', 'Supplier Credit Report', 'विक्रेता देणे बाकी अहवाल'),
(189, 'mniempattendancereport', 'Worker Attendance Report', 'कामगार हजेरी अहवाल'),
(190, 'mniquicksales', 'Quick Sales', 'तात्काळ विक्री');

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `supname` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Name',
  `supadd` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Address',
  `supphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Contact No.',
  `company` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Company Details',
  `supcr` float NOT NULL DEFAULT '0' COMMENT 'Supplier Credit Amount'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Details';

TRUNCATE TABLE `suppliers`;
INSERT INTO `suppliers` (`supid`, `supname`, `supadd`, `supphone`, `company`, `supcr`) VALUES
(1, 'Sagar Suresh Khole', 'Pandharpur', '9890098900', 'Laxmi Motors', -40),
(2, 'Ninganna Kamalapure', 'Mangalwedha', '9890098900', 'Tata motors', 140);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Payment Details';

TRUNCATE TABLE `supppayment`;
INSERT INTO `supppayment` (`payid`, `supid`, `bankname`, `bacno`, `prevbal`, `paidamt`, `remamt`, `slippic`, `paydate`, `billno`) VALUES
(1, 2, 'BOI', '-', 0, 0, 0, '- 06-09-2016.jpg', '2016-09-06', '-'),
(2, 2, 'BOI', '-', 0, 0, 0, '- 06-09-2016.jpg', '2016-09-06', '-'),
(3, 2, 'BOI', '04121', 240, 100, 140, 'b001 10-09-2016.jpg', '2016-09-10', 'b001');

DROP TABLE IF EXISTS `test_report`;
CREATE TABLE IF NOT EXISTS `test_report` (
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report ID',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report generated for',
  `address` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Address',
  `contactno` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Contact Number',
  `rpttitle` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Title/Head',
  `rptdate` date NOT NULL,
  `rptsummary` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Summary'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

TRUNCATE TABLE `test_report`;
INSERT INTO `test_report` (`rptid`, `name`, `address`, `contactno`, `rpttitle`, `rptdate`, `rptsummary`) VALUES
(1, 'Sagar Khole', 'Pandharpur', '9898989', 'jhgjhgjhgjhghjg`', '2016-09-20', 'jghj'),
(2, 'sdf', 'sdf', 'sdf', 'sdf', '2016-09-20', 'sdf'),
(3, 'CUST', 'ADDR', '563442', 'MY REP', '2016-10-23', 'smr'),
(4, 'ssss', 'sdasd`	asdad	', '876876', 'nghgfhf', '2016-10-23', 'jhgjgjgh');

DROP TABLE IF EXISTS `test_report_prods`;
CREATE TABLE IF NOT EXISTS `test_report_prods` (
  `prodmid` bigint(20) unsigned NOT NULL COMMENT 'Product Master ID',
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report Master ID',
  `prodname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `prodstatus` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Status'
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

TRUNCATE TABLE `test_report_prods`;
INSERT INTO `test_report_prods` (`prodmid`, `rptid`, `prodname`, `prodstatus`) VALUES
(1, 1, 'hfhf', 'gfdgf'),
(2, 1, 'gfdgd', 'hgfhfg '),
(3, 1, 'gfd', 'GFD '),
(4, 2, 'dsf', 'sdf'),
(5, 2, 'sdf', 'sdf '),
(6, 2, 'sdf', 'sdf'),
(7, 3, 'p2', 's2'),
(8, 3, 'p3', 's3'),
(9, 3, 'p01', 's01'),
(10, 3, 'p4', 's4'),
(11, 3, 'p5', 's8'),
(12, 3, 'p7', 's8'),
(13, 4, 'hgdfg', '43qhjg'),
(14, 4, 'dsds', 'fds');

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `usersid` smallint(5) unsigned NOT NULL COMMENT 'User''s Unique ID',
  `userid` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User ID',
  `pwd` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Password',
  `uautho` tinyint(4) NOT NULL COMMENT 'User Authentication (0 for simple User and 1 for Administrator)',
  `uname` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Name',
  `mob` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User''s Mobile Number'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application''s User Account';

TRUNCATE TABLE `users`;
INSERT INTO `users` (`usersid`, `userid`, `pwd`, `uautho`, `uname`, `mob`) VALUES
(1, 'admin', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 1, 'Administrator', '-');


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
  MODIFY `propid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Properties Unique ID',AUTO_INCREMENT=4;
ALTER TABLE `customers`
  MODIFY `custid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Customer ID',AUTO_INCREMENT=3;
ALTER TABLE `custpayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID';
ALTER TABLE `empadvance`
  MODIFY `advid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Emp. Advance Payment ID',AUTO_INCREMENT=7;
ALTER TABLE `empattendance`
  MODIFY `attendid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Attendance Unique ID',AUTO_INCREMENT=11;
ALTER TABLE `empm`
  MODIFY `empid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Unique ID',AUTO_INCREMENT=4;
ALTER TABLE `emppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Payment Unique ID',AUTO_INCREMENT=3;
ALTER TABLE `expenses`
  MODIFY `expid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Expenses Unique ID',AUTO_INCREMENT=3;
ALTER TABLE `firm`
  MODIFY `firmid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Firm Details ID',AUTO_INCREMENT=5;
ALTER TABLE `prodcategory`
  MODIFY `catid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Category ID',AUTO_INCREMENT=3;
ALTER TABLE `products`
  MODIFY `prodid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product ID',AUTO_INCREMENT=6;
ALTER TABLE `purchaseorder_m`
  MODIFY `pomid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Order ID',AUTO_INCREMENT=2;
ALTER TABLE `purchaseorder_s`
  MODIFY `posid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Sub Order ID',AUTO_INCREMENT=4;
ALTER TABLE `purchasereturn_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Master ID';
ALTER TABLE `purchasereturn_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Sub ID';
ALTER TABLE `purchases_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Mastter ID',AUTO_INCREMENT=3;
ALTER TABLE `purchases_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Sub ID',AUTO_INCREMENT=7;
ALTER TABLE `runnupcustprods`
  MODIFY `prodmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Master ID',AUTO_INCREMENT=11;
ALTER TABLE `salesorder_m`
  MODIFY `somid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Master ID',AUTO_INCREMENT=4;
ALTER TABLE `salesorder_s`
  MODIFY `sosid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Sub ID',AUTO_INCREMENT=9;
ALTER TABLE `salesreturn_m`
  MODIFY `salereturnmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Master ID',AUTO_INCREMENT=3;
ALTER TABLE `salesreturn_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Sub ID',AUTO_INCREMENT=6;
ALTER TABLE `sales_m`
  MODIFY `salemid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Master ID',AUTO_INCREMENT=10;
ALTER TABLE `sales_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Sub ID',AUTO_INCREMENT=10;
ALTER TABLE `strings`
  MODIFY `strid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'String ID',AUTO_INCREMENT=191;
ALTER TABLE `suppliers`
  MODIFY `supid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Supplier ID',AUTO_INCREMENT=3;
ALTER TABLE `supppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID',AUTO_INCREMENT=4;
ALTER TABLE `test_report`
  MODIFY `rptid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Report ID',AUTO_INCREMENT=5;
ALTER TABLE `test_report_prods`
  MODIFY `prodmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Master ID',AUTO_INCREMENT=15;
ALTER TABLE `users`
  MODIFY `usersid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'User''s Unique ID',AUTO_INCREMENT=2;

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
