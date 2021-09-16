-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 04, 2016 at 12:12 PM
-- Server version: 5.6.25
-- PHP Version: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ims`
--

-- --------------------------------------------------------

--
-- Table structure for table `application`
--

CREATE TABLE IF NOT EXISTS `application` (
  `propid` smallint(5) unsigned NOT NULL COMMENT 'Properties Unique ID',
  `property` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Name',
  `value` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Property Value'
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Application Properties';

--
-- Dumping data for table `application`
--

INSERT INTO `application` (`propid`, `property`, `value`) VALUES
(1, 'lang', 'en');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer''s Details';

-- --------------------------------------------------------

--
-- Table structure for table `custpayment`
--

CREATE TABLE IF NOT EXISTS `custpayment` (
  `payid` bigint(20) unsigned NOT NULL COMMENT 'Payment ID',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID',
  `paydate` date NOT NULL COMMENT 'Payment Date',
  `prevbal` float unsigned NOT NULL COMMENT 'Previous Balance',
  `paidamt` float unsigned NOT NULL COMMENT 'Paid Amount',
  `remamt` float unsigned NOT NULL COMMENT 'Remaining Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customer Payment Details';

-- --------------------------------------------------------

--
-- Table structure for table `empadvance`
--

CREATE TABLE IF NOT EXISTS `empadvance` (
  `advid` bigint(20) unsigned NOT NULL COMMENT 'Emp. Advance Payment ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `paiddate` date NOT NULL COMMENT 'Emp. Advance Paid Date',
  `prevbal` int(11) NOT NULL COMMENT 'Emp. Previous Balance',
  `paidamt` int(11) NOT NULL COMMENT 'Amount Paid to Employee',
  `balanceamt` int(11) NOT NULL COMMENT 'Employees Balance Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employees Advance Payment';

-- --------------------------------------------------------

--
-- Table structure for table `empattendance`
--

CREATE TABLE IF NOT EXISTS `empattendance` (
  `attendid` bigint(20) unsigned NOT NULL COMMENT 'Attendance Unique ID',
  `empid` bigint(20) unsigned NOT NULL COMMENT 'Employee Unique ID',
  `attenddate` date NOT NULL COMMENT 'Employee Attendance Date',
  `status` tinyint(4) NOT NULL COMMENT 'Employee Attendance Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Attendance';

-- --------------------------------------------------------

--
-- Table structure for table `empm`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Employee Master (All Employees Details)';

-- --------------------------------------------------------

--
-- Table structure for table `emppayment`
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

-- --------------------------------------------------------

--
-- Table structure for table `expenses`
--

CREATE TABLE IF NOT EXISTS `expenses` (
  `expid` bigint(20) unsigned NOT NULL COMMENT 'Expenses Unique ID',
  `expenses` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Title/Name',
  `expdate` date NOT NULL COMMENT 'Expenses Date',
  `expamt` float unsigned NOT NULL COMMENT 'Expenses Amount',
  `expdesc` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Expenses Description'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Expenses Details';

-- --------------------------------------------------------

--
-- Table structure for table `firm`
--

CREATE TABLE IF NOT EXISTS `firm` (
  `firmid` tinyint(3) unsigned NOT NULL COMMENT 'Firm Details ID',
  `firmname` varchar(40) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Name',
  `licno` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm License Number.',
  `firmaddr` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Address',
  `firmprop` varchar(60) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Proprietor Name',
  `firmphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Firm Contact No.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Firm Details';

-- --------------------------------------------------------

--
-- Table structure for table `prodcategory`
--

CREATE TABLE IF NOT EXISTS `prodcategory` (
  `catid` bigint(20) unsigned NOT NULL COMMENT 'Product Category ID',
  `category` varchar(30) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Category'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `products`
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

-- --------------------------------------------------------

--
-- Table structure for table `purchaseorder_m`
--

CREATE TABLE IF NOT EXISTS `purchaseorder_m` (
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Order ID',
  `podate` date NOT NULL COMMENT 'Purchase Order Date',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Order (Master)';

-- --------------------------------------------------------

--
-- Table structure for table `purchaseorder_s`
--

CREATE TABLE IF NOT EXISTS `purchaseorder_s` (
  `posid` bigint(20) unsigned NOT NULL COMMENT 'Purchase Sub Order ID',
  `pomid` bigint(20) unsigned NOT NULL COMMENT 'Purchase M ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sub Purchase Order (Order Details)';

-- --------------------------------------------------------

--
-- Table structure for table `purchasereturn_m`
--

CREATE TABLE IF NOT EXISTS `purchasereturn_m` (
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `purchasemid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `returndate` date NOT NULL COMMENT 'Purchases Return Date',
  `total` float NOT NULL COMMENT 'Total Amount of Return'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Return Master';

-- --------------------------------------------------------

--
-- Table structure for table `purchasereturn_s`
--

CREATE TABLE IF NOT EXISTS `purchasereturn_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `returnrate` float unsigned NOT NULL COMMENT 'Product Return Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchase Return Sub';

-- --------------------------------------------------------

--
-- Table structure for table `purchases_m`
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

-- --------------------------------------------------------

--
-- Table structure for table `purchases_s`
--

CREATE TABLE IF NOT EXISTS `purchases_s` (
  `pursid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Sub ID',
  `purmid` bigint(20) unsigned NOT NULL COMMENT 'Purchases Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `purchrate` float unsigned NOT NULL COMMENT 'Product Purchases Rate',
  `salesrate` float unsigned NOT NULL COMMENT 'Product Sales Rate'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Purchases Sub';

-- --------------------------------------------------------

--
-- Table structure for table `salesorder_m`
--

CREATE TABLE IF NOT EXISTS `salesorder_m` (
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `sodate` date NOT NULL COMMENT 'Sales Order Date',
  `custid` bigint(20) unsigned NOT NULL COMMENT 'Customer ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales order Details';

-- --------------------------------------------------------

--
-- Table structure for table `salesorder_s`
--

CREATE TABLE IF NOT EXISTS `salesorder_s` (
  `sosid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Sub ID',
  `somid` bigint(20) unsigned NOT NULL COMMENT 'Sales Order Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(11) NOT NULL COMMENT 'Product Quantities'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Order Sub Details';

-- --------------------------------------------------------

--
-- Table structure for table `salesreturn_m`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `salesreturn_s`
--

CREATE TABLE IF NOT EXISTS `salesreturn_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Return Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Return Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

-- --------------------------------------------------------

--
-- Table structure for table `sales_m`
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

-- --------------------------------------------------------

--
-- Table structure for table `sales_s`
--

CREATE TABLE IF NOT EXISTS `sales_s` (
  `salesid` bigint(20) unsigned NOT NULL COMMENT 'Sales Sub ID',
  `salemid` bigint(20) unsigned DEFAULT NULL COMMENT 'Sales Master ID',
  `prodid` bigint(20) unsigned NOT NULL COMMENT 'Product ID',
  `prodqty` int(10) unsigned NOT NULL COMMENT 'Product Quantities',
  `prodrate` float unsigned NOT NULL COMMENT 'Product Rate (Per Unit)',
  `totrate` float unsigned NOT NULL COMMENT 'Total amount of Product'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Sales Sub Details';

-- --------------------------------------------------------

--
-- Table structure for table `strings`
--

CREATE TABLE IF NOT EXISTS `strings` (
  `strid` bigint(20) unsigned NOT NULL COMMENT 'String ID',
  `string` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'String',
  `en` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'English',
  `mr` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Marathi'
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Strings for Multilanguage support';

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
(9, 'mniempadvance', 'Advance to Employee', 'कामगारांचा ॲडव्हान्स'),
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
(41, 'mniassets', 'Firm Assets', 'व्यवसायातील संपत्ती'),
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
(189, 'mniempattendancereport', 'Employee Attendance Report', 'कामगार हजेरी अहवाल');

-- --------------------------------------------------------

--
-- Table structure for table `suppliers`
--

CREATE TABLE IF NOT EXISTS `suppliers` (
  `supid` bigint(20) unsigned NOT NULL COMMENT 'Supplier ID',
  `supname` varchar(120) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Name',
  `supadd` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Address',
  `supphone` varchar(17) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Contact No.',
  `company` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Supplier Company Details',
  `supcr` float NOT NULL DEFAULT '0' COMMENT 'Supplier Credit Amount'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Details';

-- --------------------------------------------------------

--
-- Table structure for table `supppayment`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Supplier Payment Details';

-- --------------------------------------------------------

--
-- Table structure for table `test_report`
--

CREATE TABLE IF NOT EXISTS `test_report` (
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report ID',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report generated for',
  `address` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Address',
  `contactno` varchar(15) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Contact Number',
  `rpttitle` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Title/Head',
  `rptdate` date NOT NULL,
  `rptsummary` varchar(250) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Report Summary'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `test_report_prods`
--

CREATE TABLE IF NOT EXISTS `test_report_prods` (
  `prodmid` bigint(20) unsigned NOT NULL COMMENT 'Product Master ID',
  `rptid` bigint(20) unsigned NOT NULL COMMENT 'Report Master ID',
  `prodname` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Name',
  `prodstatus` varchar(200) COLLATE utf8_unicode_ci NOT NULL COMMENT 'Product Status'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
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
-- Dumping data for table `users`
--

INSERT INTO `users` (`usersid`, `userid`, `pwd`, `uautho`, `uname`, `mob`) VALUES
(1, 'admin', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', 1, 'Administrator', '-');

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
-- Indexes for table `purchasereturn_m`
--
ALTER TABLE `purchasereturn_m`
  ADD PRIMARY KEY (`purmid`),
  ADD KEY `supid` (`supid`),
  ADD KEY `idx_purchasemid_purchasereturn_m` (`purchasemid`);

--
-- Indexes for table `purchasereturn_s`
--
ALTER TABLE `purchasereturn_s`
  ADD PRIMARY KEY (`pursid`),
  ADD KEY `purmid` (`purmid`,`prodid`),
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
-- Indexes for table `salesreturn_m`
--
ALTER TABLE `salesreturn_m`
  ADD PRIMARY KEY (`salereturnmid`),
  ADD KEY `custid` (`custid`),
  ADD KEY `idx_salesmid_salesreturn_m` (`salesmid`);

--
-- Indexes for table `salesreturn_s`
--
ALTER TABLE `salesreturn_s`
  ADD PRIMARY KEY (`salesid`),
  ADD KEY `salemid` (`salemid`,`prodid`),
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
-- Indexes for table `test_report`
--
ALTER TABLE `test_report`
  ADD PRIMARY KEY (`rptid`);

--
-- Indexes for table `test_report_prods`
--
ALTER TABLE `test_report_prods`
  ADD PRIMARY KEY (`prodmid`),
  ADD KEY `idx_rptid_test_report_prods` (`rptid`);

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
  MODIFY `propid` smallint(5) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Properties Unique ID',AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `custid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Customer ID';
--
-- AUTO_INCREMENT for table `custpayment`
--
ALTER TABLE `custpayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID';
--
-- AUTO_INCREMENT for table `empadvance`
--
ALTER TABLE `empadvance`
  MODIFY `advid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Emp. Advance Payment ID';
--
-- AUTO_INCREMENT for table `empattendance`
--
ALTER TABLE `empattendance`
  MODIFY `attendid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Attendance Unique ID';
--
-- AUTO_INCREMENT for table `empm`
--
ALTER TABLE `empm`
  MODIFY `empid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Employee Unique ID';
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
  MODIFY `firmid` tinyint(3) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Firm Details ID';
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
-- AUTO_INCREMENT for table `purchasereturn_m`
--
ALTER TABLE `purchasereturn_m`
  MODIFY `purmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Master ID';
--
-- AUTO_INCREMENT for table `purchasereturn_s`
--
ALTER TABLE `purchasereturn_s`
  MODIFY `pursid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Purchases Return Sub ID';
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
-- AUTO_INCREMENT for table `salesreturn_m`
--
ALTER TABLE `salesreturn_m`
  MODIFY `salereturnmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Master ID';
--
-- AUTO_INCREMENT for table `salesreturn_s`
--
ALTER TABLE `salesreturn_s`
  MODIFY `salesid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Sales Return Sub ID';
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
  MODIFY `strid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'String ID',AUTO_INCREMENT=190;
--
-- AUTO_INCREMENT for table `suppliers`
--
ALTER TABLE `suppliers`
  MODIFY `supid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Supplier ID';
--
-- AUTO_INCREMENT for table `supppayment`
--
ALTER TABLE `supppayment`
  MODIFY `payid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Payment ID';
--
-- AUTO_INCREMENT for table `test_report`
--
ALTER TABLE `test_report`
  MODIFY `rptid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Report ID';
--
-- AUTO_INCREMENT for table `test_report_prods`
--
ALTER TABLE `test_report_prods`
  MODIFY `prodmid` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Product Master ID';
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
-- Constraints for table `purchasereturn_m`
--
ALTER TABLE `purchasereturn_m`
  ADD CONSTRAINT `fk_purchasereturn_m_purchasemid` FOREIGN KEY (`purchasemid`) REFERENCES `purchases_m` (`purmid`),
  ADD CONSTRAINT `fk_purchasereturn_m_supid` FOREIGN KEY (`supid`) REFERENCES `suppliers` (`supid`);

--
-- Constraints for table `purchasereturn_s`
--
ALTER TABLE `purchasereturn_s`
  ADD CONSTRAINT `fk_purchasereturn_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_purchasereturn_s_purmid` FOREIGN KEY (`purmid`) REFERENCES `purchasereturn_m` (`purmid`);

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
-- Constraints for table `salesreturn_m`
--
ALTER TABLE `salesreturn_m`
  ADD CONSTRAINT `fk_salesreturn_m_custid` FOREIGN KEY (`custid`) REFERENCES `customers` (`custid`),
  ADD CONSTRAINT `fk_salesreturn_m_salesmid` FOREIGN KEY (`salesmid`) REFERENCES `sales_m` (`salemid`);

--
-- Constraints for table `salesreturn_s`
--
ALTER TABLE `salesreturn_s`
  ADD CONSTRAINT `fk_salesreturn_s_prodid` FOREIGN KEY (`prodid`) REFERENCES `products` (`prodid`),
  ADD CONSTRAINT `fk_salesreturn_s_salemid` FOREIGN KEY (`salemid`) REFERENCES `salesreturn_m` (`salereturnmid`);

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

--
-- Constraints for table `test_report_prods`
--
ALTER TABLE `test_report_prods`
  ADD CONSTRAINT `fk_rptid_test_report_prods` FOREIGN KEY (`rptid`) REFERENCES `test_report` (`rptid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
