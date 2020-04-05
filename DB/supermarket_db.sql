-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 05, 2020 at 07:28 PM
-- Server version: 5.7.29-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-23+ubuntu16.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `supermarket_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `CLIE_Code` int(11) NOT NULL,
  `CLIE_Dni` varchar(9) NOT NULL,
  `CLIE_Names` varchar(100) NOT NULL,
  `CLIE_Address` varchar(255) NOT NULL,
  `CLIE_Flag` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`CLIE_Code`, `CLIE_Dni`, `CLIE_Names`, `CLIE_Address`, `CLIE_Flag`) VALUES
(1, '09078932', 'Mark Zuckerberg', 'Palo Alto 550, California', 'ACTIVE'),
(2, '09654951', 'Jeff Bezos', 'Central Park 850, Nrw York', 'ACTIVE'),
(3, '09362751', 'Elon Musk', 'Atlantic City 750, New Jersey', 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `PROD_Code` int(11) NOT NULL,
  `PROD_Name` varchar(128) NOT NULL,
  `PROD_Price` double NOT NULL,
  `PROD_Stock` int(11) NOT NULL,
  `PROD_Flag` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`PROD_Code`, `PROD_Name`, `PROD_Price`, `PROD_Stock`, `PROD_Flag`) VALUES
(1, 'iMac Pro', 2785.25, 94, 'ACTIVE'),
(2, 'iPad Air', 1875.45, 492, 'ACTIVE'),
(3, 'iPhone X', 985.65, 985, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `SALE_Code` int(11) NOT NULL,
  `CLIE_Code` int(11) NOT NULL,
  `USR_Code` int(11) NOT NULL,
  `SALE_Number` int(11) NOT NULL,
  `SALE_Date` date NOT NULL,
  `SALE_Total` double NOT NULL,
  `SALE_Flag` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`SALE_Code`, `CLIE_Code`, `USR_Code`, `SALE_Number`, `SALE_Date`, `SALE_Total`, `SALE_Flag`) VALUES
(3, 1, 1, 1, '2020-03-05', 14947.75, 'ACTIVE'),
(4, 2, 1, 2, '2020-03-05', 12525.900000000001, 'ACTIVE'),
(5, 3, 1, 3, '2020-03-05', 19026.2, 'ACTIVE');

-- --------------------------------------------------------

--
-- Table structure for table `sale_details`
--

CREATE TABLE `sale_details` (
  `DETA_Code` int(11) NOT NULL,
  `PROD_Code` int(11) NOT NULL,
  `SALE_Code` int(11) NOT NULL,
  `DETA_Qty` int(11) NOT NULL,
  `DETA_Subtotal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale_details`
--

INSERT INTO `sale_details` (`DETA_Code`, `PROD_Code`, `SALE_Code`, `DETA_Qty`, `DETA_Subtotal`) VALUES
(1, 1, 3, 2, 2785.25),
(2, 2, 3, 5, 1875.45),
(3, 2, 4, 3, 1875.45),
(4, 3, 4, 7, 985.65),
(5, 1, 5, 4, 2785.25),
(6, 3, 5, 8, 985.65);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `USR_Code` int(11) NOT NULL,
  `USR_Dni` varchar(8) NOT NULL,
  `USR_Names` varchar(100) NOT NULL,
  `USR_Nick` varchar(100) NOT NULL,
  `USR_Phone` varchar(9) NOT NULL,
  `USR_Flag` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`USR_Code`, `USR_Dni`, `USR_Names`, `USR_Nick`, `USR_Phone`, `USR_Flag`) VALUES
(1, '12345679', 'Caspar lee', 'empl01', '789451257', 'ACTIVE'),
(2, '96548632', 'Zoe Sugg', 'empl02', '993258654', 'ACTIVE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`CLIE_Code`),
  ADD UNIQUE KEY `CLIE_Dni` (`CLIE_Dni`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`PROD_Code`),
  ADD UNIQUE KEY `PROD_Name` (`PROD_Name`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`SALE_Code`),
  ADD KEY `CLIE_Code` (`CLIE_Code`,`USR_Code`),
  ADD KEY `fk_user_sale` (`USR_Code`);

--
-- Indexes for table `sale_details`
--
ALTER TABLE `sale_details`
  ADD PRIMARY KEY (`DETA_Code`),
  ADD KEY `PROD_Code` (`PROD_Code`,`SALE_Code`),
  ADD KEY `fk_sale_sale_detail` (`SALE_Code`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`USR_Code`),
  ADD UNIQUE KEY `USR_Dni` (`USR_Dni`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `CLIE_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `PROD_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `SALE_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `sale_details`
--
ALTER TABLE `sale_details`
  MODIFY `DETA_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `USR_Code` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_client_sale` FOREIGN KEY (`CLIE_Code`) REFERENCES `clients` (`CLIE_Code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_sale` FOREIGN KEY (`USR_Code`) REFERENCES `users` (`USR_Code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sale_details`
--
ALTER TABLE `sale_details`
  ADD CONSTRAINT `fk_product_sale_detail` FOREIGN KEY (`PROD_Code`) REFERENCES `products` (`PROD_Code`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_sale_sale_detail` FOREIGN KEY (`SALE_Code`) REFERENCES `sales` (`SALE_Code`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
