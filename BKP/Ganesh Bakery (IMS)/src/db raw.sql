-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 02, 2015 at 10:11 AM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `ganesh_ims`
--
CREATE DATABASE IF NOT EXISTS `ganesh_ims` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `ganesh_ims`;

-- --------------------------------------------------------

--
-- Table structure for table `application`
--
-- Creation: Oct 26, 2015 at 01:05 PM
--

CREATE TABLE IF NOT EXISTS `application` (
  `propid` smallint(5) unsigned NOT NULL COMMENT 'Properties Unique ID',
  `property` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Name',
  `value` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Value'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application Properties';

--
-- RELATIONS FOR TABLE `application`:
--

--
-- Truncate table before insert `application`
--

TRUNCATE TABLE `application`;
--
-- Dumping data for table `application`
--

INSERT INTO `application` (`propid`, `property`, `value`) VALUES
(1, 'firmname', 'Laxmi Agencies'),
(2, 'clientname', 'Jyotiram Pore'),
(3, 'registered', 'TC7Sg3AvRBNaTLHoCRBjyWfnzKnWdFjl8HkmChwZHK4='),
(4, 'lang', 'mr');

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `assets` (
  `assetid` int(11) unsigned NOT NULL COMMENT 'Asset ID',
  `astname` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Asset Name',
  `astvalue` varchar(50) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Asset Price or Value'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `assets`:
--

--
-- Truncate table before insert `assets`
--

TRUNCATE TABLE `assets`;
--
-- Dumping data for table `assets`
--

INSERT INTO `assets` (`assetid`, `astname`, `astvalue`) VALUES
(1, 'Crate', '70'),
(2, 'Vehicle', 'MH13-1212');

-- --------------------------------------------------------

--
-- Table structure for table `cratepurch`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `cratepurch` (
  `cratepurchid` bigint(20) unsigned NOT NULL COMMENT 'Crate Purchases ID',
  `purchdate` date NOT NULL COMMENT 'Crate Purchases Date',
  `crateqty` int(11) NOT NULL COMMENT 'Crate Purchase Quantity',
  `rate` int(11) NOT NULL COMMENT 'Rate per Crate',
  `amt` int(11) NOT NULL COMMENT 'Total Amount of Crate Purch.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Crate Purchases Details';

--
-- RELATIONS FOR TABLE `cratepurch`:
--

--
-- Truncate table before insert `cratepurch`
--

TRUNCATE TABLE `cratepurch`;
-- --------------------------------------------------------

--
-- Table structure for table `customers`
--
-- Creation: Oct 14, 2015 at 12:21 AM
--

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer''s Details';

--
-- RELATIONS FOR TABLE `customers`:
--   `routeid`
--       `route_m` -> `routeid`
--

--
-- Truncate table before insert `customers`
--

TRUNCATE TABLE `customers`;
--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`custid`, `custname`, `custadd`, `custcity`, `custphone`, `custref`, `debtamt`, `crate_holds`, `routeid`) VALUES
(1, 'Nandakishor Pukale', 'Khardi', 'Khardi', '9890098900', 'N/A', 600, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `custpayment`
--
-- Creation: Oct 24, 2015 at 08:54 AM
--

CREATE TABLE IF NOT EXISTS `custpayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Payment ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `paydate` date NOT NULL COMMENT 'Payment Date',
  `prevbal` float unsigned NOT NULL COMMENT 'Previous Balance',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount',
  `remamt` float unsigned NOT NULL COMMENT 'Remaining Amount'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer Payment Details';

--
-- RELATIONS FOR TABLE `custpayment`:
--   `custid`
--       `customers` -> `custid`
--

--
-- Truncate table before insert `custpayment`
--

TRUNCATE TABLE `custpayment`;
--
-- Dumping data for table `custpayment`
--

INSERT INTO `custpayment` (`payid`, `custid`, `paydate`, `prevbal`, `paidamt`, `remamt`) VALUES
(1, 1, '2015-10-24', 2000, 500, 1500),
(2, 1, '2015-10-24', 1500, 300, 1200),
(3, 1, '2015-10-24', 1200, 500, 700),
(4, 1, '2015-10-30', 700, 100, 600);

-- --------------------------------------------------------

--
-- Table structure for table `empadvance`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `empadvance` (
  `advid` bigint(20) unsigned NOT NULL COMMENT 'Emp. Advance Payment ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `paiddate` date NOT NULL COMMENT 'Emp. Advance Paid Date',
  `prevbal` int(11) NOT NULL COMMENT 'Emp. Previous Balance',
  `paidamt` int(11) NOT NULL COMMENT 'Amount Paid to Employee',
  `balanceamt` int(11) NOT NULL COMMENT 'Employees Balance Amount'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employees Advance Payment';

--
-- RELATIONS FOR TABLE `empadvance`:
--   `empid`
--       `empm` -> `empid`
--

--
-- Truncate table before insert `empadvance`
--

TRUNCATE TABLE `empadvance`;
--
-- Dumping data for table `empadvance`
--

INSERT INTO `empadvance` (`advid`, `empid`, `paiddate`, `prevbal`, `paidamt`, `balanceamt`) VALUES
(1, 1, '2015-10-19', 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `empattendance`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `empattendance` (
  `attendid` bigint(20) unsigned NOT NULL COMMENT 'Attendance Unique ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `attenddate` date NOT NULL COMMENT 'Employee Attendance Date',
  `status` tinyint(4) NOT NULL COMMENT 'Employee Attendance Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Attendance';

--
-- RELATIONS FOR TABLE `empattendance`:
--   `empid`
--       `empm` -> `empid`
--

--
-- Truncate table before insert `empattendance`
--

TRUNCATE TABLE `empattendance`;
-- --------------------------------------------------------

--
-- Table structure for table `empm`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Master (All Employees Details)';

--
-- RELATIONS FOR TABLE `empm`:
--

--
-- Truncate table before insert `empm`
--

TRUNCATE TABLE `empm`;
--
-- Dumping data for table `empm`
--

INSERT INTO `empm` (`empid`, `empname`, `empadd`, `empmo`, `joindate`, `desgn`, `empsal`, `acbal`, `remsalary`, `leavedate`, `empenabled`, `emppic`) VALUES
(1, 'Kamgar', 'patta', '98989898', '2015-10-20', 'Mahit Nahi', 4000, 0, 0, NULL, 1, 'jpg');

-- --------------------------------------------------------

--
-- Table structure for table `emppayment`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

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

--
-- RELATIONS FOR TABLE `emppayment`:
--   `empid`
--       `empm` -> `empid`
--

--
-- Truncate table before insert `emppayment`
--

TRUNCATE TABLE `emppayment`;
-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--
-- Creation: Oct 05, 2015 at 02:00 PM
--

CREATE TABLE IF NOT EXISTS `expenses` (
  `expid` bigint(20) unsigned NOT NULL COMMENT 'Expenses Unique ID',
  `expenses` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Title/Name',
  `expdate` date NOT NULL COMMENT 'Expenses Date',
  `expamt` float unsigned NOT NULL COMMENT 'Expenses Amount',
  `expdesc` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Expenses Details';

--
-- RELATIONS FOR TABLE `expenses`:
--

--
-- Truncate table before insert `expenses`
--

TRUNCATE TABLE `expenses`;
-- --------------------------------------------------------

--
-- Table structure for table `firm`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `firm` (
  `firmid` tinyint(3) unsigned NOT NULL COMMENT 'Firm Details ID',
  `firmname` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Name',
  `licno` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm License Number.',
  `firmaddr` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Address',
  `firmprop` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Proprietor Name',
  `firmphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Contact No.'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Firm Details';

--
-- RELATIONS FOR TABLE `firm`:
--

--
-- Truncate table before insert `firm`
--

TRUNCATE TABLE `firm`;
--
-- Dumping data for table `firm`
--

INSERT INTO `firm` (`firmid`, `firmname`, `licno`, `firmaddr`, `firmprop`, `firmphone`) VALUES
(1, 'Laxmi Agencies', '-', 'Vakhari', 'Jyotiram Pore', '9890098900');

-- --------------------------------------------------------

--
-- Table structure for table `prodcategory`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `prodcategory` (
  `catid` bigint(20) unsigned NOT NULL COMMENT 'Product Category ID',
  `category` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Category'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `prodcategory`:
--

--
-- Truncate table before insert `prodcategory`
--

TRUNCATE TABLE `prodcategory`;
-- --------------------------------------------------------

--
-- Table structure for table `products`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

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

--
-- RELATIONS FOR TABLE `products`:
--   `catid`
--       `prodcategory` -> `catid`
--

--
-- Truncate table before insert `products`
--

TRUNCATE TABLE `products`;
-- --------------------------------------------------------

--
-- Table structure for table `purchaseorder_m`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `purchaseorder_m` (
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Order ID',
  `podate` date NOT NULL COMMENT 'Purchase Order Date',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Order (Master)';

--
-- RELATIONS FOR TABLE `purchaseorder_m`:
--   `supid`
--       `suppliers` -> `supid`
--

--
-- Truncate table before insert `purchaseorder_m`
--

TRUNCATE TABLE `purchaseorder_m`;
-- --------------------------------------------------------

--
-- Table structure for table `purchaseorder_s`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `purchaseorder_s` (
  `posid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Sub Order ID',
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase M ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sub Purchase Order (Order Details)';

--
-- RELATIONS FOR TABLE `purchaseorder_s`:
--   `pomid`
--       `purchaseorder_m` -> `pomid`
--   `prodid`
--       `products` -> `prodid`
--

--
-- Truncate table before insert `purchaseorder_s`
--

TRUNCATE TABLE `purchaseorder_s`;
-- --------------------------------------------------------

--
-- Table structure for table `purchases_m`
--
-- Creation: Oct 08, 2015 at 09:39 AM
--

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

--
-- RELATIONS FOR TABLE `purchases_m`:
--   `pomid`
--       `purchaseorder_m` -> `pomid`
--   `supid`
--       `suppliers` -> `supid`
--

--
-- Truncate table before insert `purchases_m`
--

TRUNCATE TABLE `purchases_m`;
-- --------------------------------------------------------

--
-- Table structure for table `purchases_s`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `purchases_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `purchrate` float unsigned NOT NULL COMMENT 'Product Purchases Rate',
  `salesrate` float unsigned NOT NULL COMMENT 'Product Sales Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Sub';

--
-- RELATIONS FOR TABLE `purchases_s`:
--   `prodid`
--       `products` -> `prodid`
--   `purmid`
--       `purchases_m` -> `purmid`
--

--
-- Truncate table before insert `purchases_s`
--

TRUNCATE TABLE `purchases_s`;
-- --------------------------------------------------------

--
-- Table structure for table `route_m`
--
-- Creation: Oct 11, 2015 at 01:18 PM
--

CREATE TABLE IF NOT EXISTS `route_m` (
  `routeid` int(11) unsigned NOT NULL,
  `routeno` int(11) NOT NULL,
  `routename` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `locations` varchar(250) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `route_m`:
--

--
-- Truncate table before insert `route_m`
--

TRUNCATE TABLE `route_m`;
--
-- Dumping data for table `route_m`
--

INSERT INTO `route_m` (`routeid`, `routeno`, `routename`, `locations`) VALUES
(1, 1, 'Khardi', 'Khardi, Tavashi, Tanali');

-- --------------------------------------------------------

--
-- Table structure for table `route_trip`
--
-- Creation: Oct 14, 2015 at 10:14 AM
--

CREATE TABLE IF NOT EXISTS `route_trip` (
  `tripid` bigint(20) unsigned NOT NULL COMMENT 'Trip ID',
  `routeid` int(10) unsigned NOT NULL COMMENT 'Route ID',
  `vehicleno` varchar(20) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Vehicle Number',
  `empdriver` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Driver or Employee Name',
  `cratesend` int(11) NOT NULL COMMENT 'Crate Send',
  `cratereceived` int(11) DEFAULT NULL COMMENT 'Crate Receive',
  `tripdate` date NOT NULL COMMENT 'Trip Date'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS FOR TABLE `route_trip`:
--   `routeid`
--       `route_m` -> `routeid`
--

--
-- Truncate table before insert `route_trip`
--

TRUNCATE TABLE `route_trip`;
--
-- Dumping data for table `route_trip`
--

INSERT INTO `route_trip` (`tripid`, `routeid`, `vehicleno`, `empdriver`, `cratesend`, `cratereceived`, `tripdate`) VALUES
(1, 1, 'MH13-1212', 'Kamgar', 30, 30, '2015-10-19');

-- --------------------------------------------------------

--
-- Table structure for table `salesorder_m`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `salesorder_m` (
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `sodate` date NOT NULL COMMENT 'Sales Order Date',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales order Details';

--
-- RELATIONS FOR TABLE `salesorder_m`:
--   `custid`
--       `customers` -> `custid`
--

--
-- Truncate table before insert `salesorder_m`
--

TRUNCATE TABLE `salesorder_m`;
-- --------------------------------------------------------

--
-- Table structure for table `salesorder_s`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `salesorder_s` (
  `sosid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Sub ID',
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(11) NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Order Sub Details';

--
-- RELATIONS FOR TABLE `salesorder_s`:
--   `prodid`
--       `products` -> `prodid`
--   `somid`
--       `salesorder_m` -> `somid`
--

--
-- Truncate table before insert `salesorder_s`
--

TRUNCATE TABLE `salesorder_s`;
-- --------------------------------------------------------

--
-- Table structure for table `sales_m`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `sales_m` (
  `salemid` bigint(20) unsigned NOT NULL COMMENT 'Sales Master ID',
  `somid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Order Master ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `salesdate` date NOT NULL COMMENT 'Date of Sales',
  `subtotal` float unsigned NOT NULL COMMENT 'Sub total amount of Sales',
  `vat` float unsigned NOT NULL COMMENT 'Vat Percentage',
  `discount` float unsigned NOT NULL COMMENT 'Discount Percentage',
  `total` float unsigned NOT NULL COMMENT 'Total Sales Amount',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Master Details';

--
-- RELATIONS FOR TABLE `sales_m`:
--   `custid`
--       `customers` -> `custid`
--   `somid`
--       `salesorder_m` -> `somid`
--

--
-- Truncate table before insert `sales_m`
--

TRUNCATE TABLE `sales_m`;
-- --------------------------------------------------------

--
-- Table structure for table `sales_s`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `sales_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

--
-- RELATIONS FOR TABLE `sales_s`:
--   `prodid`
--       `products` -> `prodid`
--   `salemid`
--       `sales_m` -> `salemid`
--

--
-- Truncate table before insert `sales_s`
--

TRUNCATE TABLE `sales_s`;
-- --------------------------------------------------------

--
-- Table structure for table `strings`
--
-- Creation: Oct 26, 2015 at 12:54 PM
--

CREATE TABLE IF NOT EXISTS `strings` (
  `strid` bigint(20) unsigned NOT NULL COMMENT 'String ID',
  `string` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'String',
  `en` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'English',
  `mr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Marathi'
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Strings for Multilanguage support';

--
-- RELATIONS FOR TABLE `strings`:
--

--
-- Truncate table before insert `strings`
--

TRUNCATE TABLE `strings`;
--
-- Dumping data for table `strings`
--

INSERT INTO `strings` (`strid`, `string`, `en`, `mr`) VALUES
(1, 'mnuemployee', 'Employee', 'कामगार'),
(2, 'mnuproduct', 'Product', 'उत्पादन'),
(3, 'mnutrans', 'Transaction', 'व्यवहार'),
(4, 'mnuroute', 'Route Management', 'फेरी व्यवस्थापन'),
(5, 'mnureport', 'Reports', 'अहवाल'),
(6, 'mnufirm', 'Firm', 'व्यवसय'),
(7, 'mnuuser', 'User Account', 'युजर खाते'),
(8, 'mniempenroll', 'Employee Enrollment', 'नवीन कामगार नोंदणी'),
(9, 'mniempadvance', 'Advance to Employee', 'कामगारांचा ॲडव्हान्स(इसारा)'),
(10, 'mniempattend', 'Employee Attendance', 'कामगार हजेरी'),
(11, 'mnuempsalary', 'Employee Salary', 'कामगारांचा पगार'),
(12, 'mniempsettle', 'Employee Settlement', 'कामगार हिशोब'),
(13, 'mniempsalpayment', 'Employee Salary Payment', 'कामगार पगार देयक'),
(14, 'mniempsalslip', 'Employee Salary Slip', 'पगार पत्रक स्लीप'),
(15, 'mniempsalreport', 'Monthwise Salary Report', 'मासिक पगार पत्रक'),
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
(41, 'mniassets', 'Firm Assets Assets', 'व्यवसायातील संपत्ती'),
(42, 'mninewuser', 'New User', 'नवीन युजर'),
(43, 'mniupdateuser', 'Update User', 'युजर माहिती अद्ययावत'),
(44, 'mnichangepass', 'Change Password', 'पासवर्ड बदला'),
(45, 'lblempid', 'Employee ID', 'कामगार आय.डी.'),
(46, 'lblempname', 'Employee Name', 'कामगार नाव'),
(47, 'lblempadd', 'Employee Address', 'पत्ता'),
(48, 'lblcontactno', 'Contact Number', 'दुरध्वनी क्रमांक'),
(49, 'lblemppic', 'Employee Picture', 'कामगाराचा फोटो'),
(50, 'lbljoindate', 'Joining Date', 'कामावर आल्याची दिनांक'),
(51, 'lbldesgn', 'Designation', 'हुद्दा'),
(52, 'lblempsal', 'Salary', 'पगार'),
(53, 'chkempadvance', 'Advance to Employee', 'ॲडव्हान्स'),
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
(67, 'lblemprempay', 'Remaining Payment', 'उर्वरित पगार'),
(68, 'lblnetsalary', 'Net Salary', 'एकुण पगार'),
(69, 'lblsalyear', 'Salary Year', 'वर्ष'),
(70, 'lblsalmonth', 'Salary Month', 'महिना'),
(71, 'lblworkdays', 'Working Days', 'कामाचे दिवस'),
(72, 'lblattenddays', 'Attended Days', 'एकुण हजर दिवस'),
(73, 'lblprevpendingsal', 'Prev. Pending Salary', 'आधीचा उर्वरित पगार'),
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
(108, 'lblsupremainamt', 'Remaining Payment', 'उर्वरित रक्कम रु.'),
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
(164, 'lblprodcatid', 'Category ID', 'उत्पा. प्रकार आय.डी.');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `suppliers` (
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `supname` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Name',
  `supadd` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Address',
  `supphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Contact No.',
  `company` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Company Details',
  `supcr` float NOT NULL DEFAULT '0' COMMENT 'Supplier Credit Amount'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Details';

--
-- RELATIONS FOR TABLE `suppliers`:
--

--
-- Truncate table before insert `suppliers`
--

TRUNCATE TABLE `suppliers`;
--
-- Dumping data for table `suppliers`
--

INSERT INTO `suppliers` (`supid`, `supname`, `supadd`, `supphone`, `company`, `supcr`) VALUES
(1, 'Ganesh Bakers', 'Nandani', '9898989898', 'Chakote Brothers', 4000);

-- --------------------------------------------------------

--
-- Table structure for table `supppayment`
--
-- Creation: Oct 24, 2015 at 11:30 AM
--

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Payment Details';

--
-- RELATIONS FOR TABLE `supppayment`:
--   `supid`
--       `suppliers` -> `supid`
--

--
-- Truncate table before insert `supppayment`
--

TRUNCATE TABLE `supppayment`;
--
-- Dumping data for table `supppayment`
--

INSERT INTO `supppayment` (`payid`, `supid`, `bankname`, `bacno`, `prevbal`, `paidamt`, `remamt`, `slippic`, `paydate`, `billno`) VALUES
(1, 1, 'icici', 'n/a', 5000, 1000, 4000, 'n/a 24-10-2015.jpg', '2015-10-24', 'n/a'),
(2, 1, 'icici', 'n/a', 5000, 1000, 4000, 'bill 24-10-2015.jpg', '2015-10-24', 'bill');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--
-- Creation: Oct 05, 2015 at 03:36 AM
--

CREATE TABLE IF NOT EXISTS `users` (
  `usersid` smallint(5) unsigned NOT NULL COMMENT 'User''s Unique ID',
  `userid` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User ID',
  `pwd` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Password',
  `uautho` tinyint(4) NOT NULL COMMENT 'User Authentication (0 for simple User and 1 for Administrator)',
  `uname` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User Name',
  `mob` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'User''s Mobile Number'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application''s User Account';

--
-- RELATIONS FOR TABLE `users`:
--

--
-- Truncate table before insert `users`
--

TRUNCATE TABLE `users`;
--
-- Dumping data for table `users`
--

INSERT INTO `users` (`usersid`, `userid`, `pwd`, `uautho`, `uname`, `mob`) VALUES
(1, 'girish', 'e2847a3185b3178e060b703102f6a3547bdeb7b4', 1, 'Girish Salaskar', '9890098900');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `application`
--
ALTER TABLE `application`
  ADD PRIMARY KEY (`propid`),
  ADD UNIQUE KEY `property` (`property`);

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`assetid`);

--
-- Indexes for table `cratepurch`
--
ALTER TABLE `cratepurch`
  ADD PRIMARY KEY (`cratepurchid`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`custid`),
  ADD KEY `idx_routeid_customers` (`routeid`);

--
-- Indexes for table `custpayment`
--
ALTER TABLE `custpayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `idx_custid_custpayment` (`custid`);

--
-- Indexes for table `empadvance`
--
ALTER TABLE `empadvance`
  ADD PRIMARY KEY (`advid`),
  ADD KEY `empid` (`empid`);

--
-- Indexes for table `empattendance`
--
ALTER TABLE `empattendance`
  ADD PRIMARY KEY (`attendid`),
  ADD KEY `empid` (`empid`);

--
-- Indexes for table `empm`
--
ALTER TABLE `empm`
  ADD PRIMARY KEY (`empid`);

--
-- Indexes for table `emppayment`
--
ALTER TABLE `emppayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `empid` (`empid`);

--
-- Indexes for table `expenses`
--
ALTER TABLE `expenses`
  ADD PRIMARY KEY (`expid`);

--
-- Indexes for table `firm`
--
ALTER TABLE `firm`
  ADD PRIMARY KEY (`firmid`);

--
-- Indexes for table `prodcategory`
--
ALTER TABLE `prodcategory`
  ADD PRIMARY KEY (`catid`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`prodid`),
  ADD KEY `catid` (`catid`);

--
-- Indexes for table `purchaseorder_m`
--
ALTER TABLE `purchaseorder_m`
  ADD PRIMARY KEY (`pomid`),
  ADD KEY `supid` (`supid`);

--
-- Indexes for table `purchaseorder_s`
--
ALTER TABLE `purchaseorder_s`
  ADD PRIMARY KEY (`posid`),
  ADD KEY `pomid` (`pomid`),
  ADD KEY `prodid` (`prodid`);

--
-- Indexes for table `purchases_m`
--
ALTER TABLE `purchases_m`
  ADD PRIMARY KEY (`purmid`),
  ADD KEY `pomid` (`pomid`),
  ADD KEY `supid` (`supid`);

--
-- Indexes for table `purchases_s`
--
ALTER TABLE `purchases_s`
  ADD PRIMARY KEY (`pursid`),
  ADD KEY `purmid` (`purmid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

--
-- Indexes for table `route_m`
--
ALTER TABLE `route_m`
  ADD PRIMARY KEY (`routeid`);

--
-- Indexes for table `route_trip`
--
ALTER TABLE `route_trip`
  ADD PRIMARY KEY (`tripid`),
  ADD KEY `idx_routeid_routetrip` (`routeid`);

--
-- Indexes for table `salesorder_m`
--
ALTER TABLE `salesorder_m`
  ADD PRIMARY KEY (`somid`),
  ADD KEY `custid` (`custid`),
  ADD KEY `custid_2` (`custid`);

--
-- Indexes for table `salesorder_s`
--
ALTER TABLE `salesorder_s`
  ADD PRIMARY KEY (`sosid`),
  ADD KEY `somid` (`somid`),
  ADD KEY `prodid` (`prodid`);

--
-- Indexes for table `sales_m`
--
ALTER TABLE `sales_m`
  ADD PRIMARY KEY (`salemid`),
  ADD KEY `somid` (`somid`,`custid`),
  ADD KEY `custid` (`custid`);

--
-- Indexes for table `sales_s`
--
ALTER TABLE `sales_s`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `salemid` (`salemid`,`prodid`),
  ADD KEY `prodid` (`prodid`);

--
-- Indexes for table `strings`
--
ALTER TABLE `strings`
  ADD PRIMARY KEY (`strid`),
  ADD UNIQUE KEY `uk_string_strings` (`string`);

--
-- Indexes for table `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`supid`);

--
-- Indexes for table `supppayment`
--
ALTER TABLE `supppayment`
  ADD PRIMARY KEY (`payid`),
  ADD KEY `idx_supid_supppayment` (`supid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`usersid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `application`
--
ALTER TABLE `application`
  MODIFY `propid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Properties Unique ID',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `assets`
--
ALTER TABLE `assets`
  MODIFY `assetid` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Asset ID',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `cratepurch`
--
ALTER TABLE `cratepurch`
  MODIFY `cratepurchid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Crate Purchases ID';
--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `custid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Customer ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `custpayment`
--
ALTER TABLE `custpayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID',AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `empadvance`
--
ALTER TABLE `empadvance`
  MODIFY `advid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Emp. Advance Payment ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `empattendance`
--
ALTER TABLE `empattendance`
  MODIFY `attendid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Attendance Unique ID';
--
-- AUTO_INCREMENT for table `empm`
--
ALTER TABLE `empm`
  MODIFY `empid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Unique ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `emppayment`
--
ALTER TABLE `emppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Payment Unique ID';
--
-- AUTO_INCREMENT for table `expenses`
--
ALTER TABLE `expenses`
  MODIFY `expid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Expenses Unique ID';
--
-- AUTO_INCREMENT for table `firm`
--
ALTER TABLE `firm`
  MODIFY `firmid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Firm Details ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `prodcategory`
--
ALTER TABLE `prodcategory`
  MODIFY `catid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Category ID';
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `prodid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product ID';
--
-- AUTO_INCREMENT for table `purchaseorder_m`
--
ALTER TABLE `purchaseorder_m`
  MODIFY `pomid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Order ID';
--
-- AUTO_INCREMENT for table `purchaseorder_s`
--
ALTER TABLE `purchaseorder_s`
  MODIFY `posid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchase Sub Order ID';
--
-- AUTO_INCREMENT for table `purchases_m`
--
ALTER TABLE `purchases_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Mastter ID';
--
-- AUTO_INCREMENT for table `purchases_s`
--
ALTER TABLE `purchases_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Sub ID';
--
-- AUTO_INCREMENT for table `route_m`
--
ALTER TABLE `route_m`
  MODIFY `routeid` int(11) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `route_trip`
--
ALTER TABLE `route_trip`
  MODIFY `tripid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Trip ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `salesorder_m`
--
ALTER TABLE `salesorder_m`
  MODIFY `somid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Master ID';
--
-- AUTO_INCREMENT for table `salesorder_s`
--
ALTER TABLE `salesorder_s`
  MODIFY `sosid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Order Sub ID';
--
-- AUTO_INCREMENT for table `sales_m`
--
ALTER TABLE `sales_m`
  MODIFY `salemid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Master ID';
--
-- AUTO_INCREMENT for table `sales_s`
--
ALTER TABLE `sales_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Sub ID';
--
-- AUTO_INCREMENT for table `strings`
--
ALTER TABLE `strings`
  MODIFY `strid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'String ID',AUTO_INCREMENT=165;
--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Supplier ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `supppayment`
--
ALTER TABLE `supppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID',AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `usersid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'User''s Unique ID',AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_routeid_customers` FOREIGN KEY (`routeid`) REFERENCES `route_m` (`routeid`);

--
-- Constraints for table `custpayment`
--
ALTER TABLE `custpayment`
  ADD CONSTRAINT `fk_custid_custpayment` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`);

--
-- Constraints for table `empadvance`
--
ALTER TABLE `empadvance`
  ADD CONSTRAINT `fk_EmpAdvance_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

--
-- Constraints for table `empattendance`
--
ALTER TABLE `empattendance`
  ADD CONSTRAINT `fk_EmpAttendance_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

--
-- Constraints for table `emppayment`
--
ALTER TABLE `emppayment`
  ADD CONSTRAINT `fk_EmpPayment_empid` FOREIGN KEY (`empid`) REFERENCES `empm` (`empid`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `fk_products_catid` FOREIGN KEY (`catid`) REFERENCES `prodcategory` (`catid`);

--
-- Constraints for table `purchaseorder_m`
--
ALTER TABLE `purchaseorder_m`
  ADD CONSTRAINT `fk_purchaseorder_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

--
-- Constraints for table `purchaseorder_s`
--
ALTER TABLE `purchaseorder_s`
  ADD CONSTRAINT `fk_purchaseorder_s_pomid` FOREIGN KEY (`pomid`) REFERENCES `purchaseorder_m` (`pomid`),
  ADD CONSTRAINT `fk_purchaseorder_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`);

--
-- Constraints for table `purchases_m`
--
ALTER TABLE `purchases_m`
  ADD CONSTRAINT `fk_purchases_m_pomid` FOREIGN KEY (`pomid`) REFERENCES `purchaseorder_m` (`pomid`),
  ADD CONSTRAINT `fk_purchases_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

--
-- Constraints for table `purchases_s`
--
ALTER TABLE `purchases_s`
  ADD CONSTRAINT `fk_purchases_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_purchases_s_purmid` FOREIGN KEY (`purmid`) REFERENCES `purchases_m` (`purmid`);

--
-- Constraints for table `route_trip`
--
ALTER TABLE `route_trip`
  ADD CONSTRAINT `fk_routeid_routetrip` FOREIGN KEY (`routeid`) REFERENCES `route_m` (`routeid`);

--
-- Constraints for table `salesorder_m`
--
ALTER TABLE `salesorder_m`
  ADD CONSTRAINT `fk_salesorder_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`);

--
-- Constraints for table `salesorder_s`
--
ALTER TABLE `salesorder_s`
  ADD CONSTRAINT `fk_salesorder_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_salesorder_s_somid` FOREIGN KEY (`somid`) REFERENCES `salesorder_m` (`somid`);

--
-- Constraints for table `sales_m`
--
ALTER TABLE `sales_m`
  ADD CONSTRAINT `fk_sales_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`),
  ADD CONSTRAINT `fk_sales_m_somid` FOREIGN KEY (`somid`) REFERENCES `salesorder_m` (`somid`);

--
-- Constraints for table `sales_s`
--
ALTER TABLE `sales_s`
  ADD CONSTRAINT `fk_sales_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_sales_s_salemid` FOREIGN KEY (`salemid`) REFERENCES `sales_m` (`salemid`);

--
-- Constraints for table `supppayment`
--
ALTER TABLE `supppayment`
  ADD CONSTRAINT `fk_supid_supppayment` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);
