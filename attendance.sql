/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.30 : Database - attendance
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`attendance` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `attendance`;

/*Table structure for table `cwa_company` */

DROP TABLE IF EXISTS `cwa_company`;

CREATE TABLE `cwa_company` (
  `c_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '公司id',
  `c_name` varchar(50) DEFAULT NULL COMMENT '公司名',
  `c_address` varchar(255) DEFAULT NULL COMMENT '公司地址',
  `c_phone` varchar(50) DEFAULT NULL COMMENT '公司联系方式',
  `c_map_address` varchar(50) DEFAULT NULL COMMENT '公司地图上的地址',
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_company` */

insert  into `cwa_company`(`c_id`,`c_name`,`c_address`,`c_phone`,`c_map_address`) values (28,'李团外卖','广东省广州市海珠区赤沙西约石伦里横街','17765671003','23.091299,113.356114'),(30,'','上海市浦东新区世纪大道8号','','39.92019271104446,116.46138668060303'),(39,'大哥好','广东省广州市越秀区解放北路863号','3564—3567','23.13667995876736,113.26168185763889'),(44,'阿里巴巴','广东省广州市越秀区府前路1号','14718440887','23.129163,113.264435'),(45,'蔡','上海市嘉定区小糸东路','','31.364864678639254,121.2558126449073');

/*Table structure for table `cwa_department` */

DROP TABLE IF EXISTS `cwa_department`;

CREATE TABLE `cwa_department` (
  `d_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `d_company_id` bigint(20) DEFAULT NULL COMMENT '公司id',
  `d_name` varchar(50) DEFAULT NULL COMMENT '部门名',
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_department` */

insert  into `cwa_department`(`d_id`,`d_company_id`,`d_name`) values (7,44,'技术部'),(8,44,'美术部');

/*Table structure for table `cwa_department_admin` */

DROP TABLE IF EXISTS `cwa_department_admin`;

CREATE TABLE `cwa_department_admin` (
  `da_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门管理员表id',
  `da_department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `da_company_id` bigint(20) DEFAULT NULL COMMENT '公司id',
  `da_admin_id` varchar(50) DEFAULT NULL COMMENT '员工id',
  PRIMARY KEY (`da_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_department_admin` */

insert  into `cwa_department_admin`(`da_id`,`da_department_id`,`da_company_id`,`da_admin_id`) values (5,7,44,'1');

/*Table structure for table `cwa_department_staff` */

DROP TABLE IF EXISTS `cwa_department_staff`;

CREATE TABLE `cwa_department_staff` (
  `ds_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门员工表id',
  `ds_company_id` bigint(20) DEFAULT NULL COMMENT '公司id',
  `ds_department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `ds_staff_id` varchar(50) DEFAULT NULL COMMENT '员工id',
  PRIMARY KEY (`ds_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_department_staff` */

insert  into `cwa_department_staff`(`ds_id`,`ds_company_id`,`ds_department_id`,`ds_staff_id`) values (63,28,NULL,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM'),(65,30,NULL,'oT4JJ5LrjX872URooPS6NQ6LtZaw'),(78,39,NULL,'oT4JJ5L3cOPDhZweW5OfhdikiaHc'),(85,44,NULL,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0'),(86,44,7,'1'),(87,44,7,'2'),(88,44,7,'3'),(89,45,NULL,'oT4JJ5E6m4ffG1MVc775rwWiIQUU');

/*Table structure for table `cwa_record` */

DROP TABLE IF EXISTS `cwa_record`;

CREATE TABLE `cwa_record` (
  `rec_staff_id` varchar(50) NOT NULL COMMENT '员工id',
  `rec_date` date NOT NULL COMMENT '考勤日期',
  `rec_start_time` time DEFAULT NULL COMMENT '上班打卡时间',
  `rec_start_map` varchar(100) DEFAULT NULL COMMENT '上班打卡地址',
  `rec_start_address` varchar(255) DEFAULT NULL COMMENT '上班打卡地址',
  `rec_start_flag` varchar(50) DEFAULT NULL COMMENT '上班打卡状态',
  `rec_end_time` time DEFAULT NULL COMMENT '下班打卡时间',
  `rec_end_map` varchar(100) DEFAULT NULL COMMENT '下班打卡地址',
  `rec_end_address` varchar(255) DEFAULT NULL COMMENT '下班打卡地址',
  `rec_end_flag` varchar(50) DEFAULT NULL COMMENT '下班打卡状态',
  PRIMARY KEY (`rec_staff_id`,`rec_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cwa_record` */

insert  into `cwa_record`(`rec_staff_id`,`rec_date`,`rec_start_time`,`rec_start_map`,`rec_start_address`,`rec_start_flag`,`rec_end_time`,`rec_end_map`,`rec_end_address`,`rec_end_flag`) values ('1','2020-05-09','23:15:14','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n\n','23:15:15','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n\n'),('1','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-15',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-18',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-19','12:22:55','23.124746322631836,113.38126373291016','广东省广州市天河区中山大道西1123号','已打卡\n外出考勤\n','12:22:57','23.124746322631836,113.38126373291016','广东省广州市天河区中山大道西1123号','已打卡\n外出考勤\n'),('1','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-23',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('1','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2','2020-05-09','08:16:10','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n','23:16:11','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n'),('2','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-15',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-18',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-19','14:07:35','23.12470054626465,113.38118743896484','广东省广州市天河区中山大道西1123号','已打卡\n迟到\n','14:07:35','23.12470054626465,113.38118743896484','广东省广州市天河区中山大道西1123号','已打卡\n\n早退\n'),('2','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-23',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('2','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('3','2020-05-09','08:45:04','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n','23:15:06','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n'),('3','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-15',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-18',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-19','14:07:48','23.12474250793457,113.38119506835938','广东省广州市天河区中山大道西1123号','已打卡\n','14:07:49','23.12474250793457,113.38119506835938','广东省广州市天河区中山大道西1123号','已打卡\n'),('3','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-23',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('3','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('oT4JJ5Bbp8j6ws1C7VkAJh0uIyg0','2020-05-08','12:24:05','23.1288,113.2645','广东省广州市越秀区府前路1号','已打卡\n',NULL,NULL,NULL,NULL),('oT4JJ5BWeT1mPdboQb-OWnwAK-rk','2020-05-08','06:58:22','23.1288,113.2645','广东省广州市越秀区府前路1号','已打卡\n',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-07','18:53:40','31.363628387451172,121.25579833984375','上海市嘉定区招贤路','已打卡\n外出考勤\n迟到\n','18:53:44','31.363628387451172,121.25579833984375','上海市嘉定区招贤路','已打卡\n外出考勤\n'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-15',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-18',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-19',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-23',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-07','00:50:41','31.23691,121.50109','上海市浦东新区世纪大道8号','已打卡\n外出考勤\n',NULL,NULL,NULL,'缺卡'),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-17',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-24',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-31',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-06-07',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-05','08:52:53','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-06','08:52:16','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n','22:52:24','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-07','09:49:00','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n迟到\n','21:50:04','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-08','08:50:38','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n','22:50:58','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-09','23:14:13','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n','23:14:24','23.13171,113.26627','广东省广州市越秀区吉祥路','已打卡\n外出考勤\n'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-15','20:08:56','23.124778747558594,113.38124084472656','广东省广州市天河区中山大道西1123号','已打卡\n外出考勤\n迟到\n',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-18','22:57:49','23.12908,113.26436','广东省广州市越秀区府前路1号','已打卡\n外出考勤\n迟到\n','22:57:56','23.12908,113.26436','广东省广州市越秀区府前路1号','已打卡\n外出考勤\n'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-19',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-23','17:02:52','23.12908,113.26436','广东省广州市越秀区府前路1号','已打卡\n外出考勤\n迟到\n',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-13',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-14',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-15',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-16',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-18',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-19',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-20',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-21',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-22',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-23',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-25',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-26',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-27',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-28',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-29',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-30',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-01',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-02',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-03',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-04',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-05',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-06',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-08',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-09',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-10',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-11',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-12',NULL,NULL,NULL,'缺卡',NULL,NULL,NULL,'缺卡'),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-13',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `cwa_regulations` */

DROP TABLE IF EXISTS `cwa_regulations`;

CREATE TABLE `cwa_regulations` (
  `reg_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '考勤规定id',
  `reg_start_time` time DEFAULT '09:00:00' COMMENT '上班时间',
  `reg_end_time` time DEFAULT '18:00:00' COMMENT '下班时间',
  `reg_company_id` bigint(20) DEFAULT NULL COMMENT '公司id',
  `reg_department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `reg_map_address` varchar(100) DEFAULT NULL COMMENT '考勤地图上的地址',
  `reg_scope` int(10) DEFAULT '100' COMMENT '考勤范围',
  `reg_repair_count` int(3) DEFAULT '3' COMMENT '补卡次数',
  `reg_mon` tinyint(1) DEFAULT '1' COMMENT '周一(1：上班，0：放假)',
  `reg_tues` tinyint(1) DEFAULT '1' COMMENT '周二(1：上班，0：放假)',
  `reg_wed` tinyint(1) DEFAULT '1' COMMENT '周三(1：上班，0：放假)',
  `reg_thur` tinyint(1) DEFAULT '1' COMMENT '周四(1：上班，0：放假)',
  `reg_fri` tinyint(1) DEFAULT '1' COMMENT '周五(1：上班，0：放假)',
  `reg_sat` tinyint(1) DEFAULT '1' COMMENT '周六(1：上班，0：放假)',
  `reg_sun` tinyint(1) DEFAULT '0' COMMENT '周日(1：上班，0：放假)',
  PRIMARY KEY (`reg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_regulations` */

insert  into `cwa_regulations`(`reg_id`,`reg_start_time`,`reg_end_time`,`reg_company_id`,`reg_department_id`,`reg_map_address`,`reg_scope`,`reg_repair_count`,`reg_mon`,`reg_tues`,`reg_wed`,`reg_thur`,`reg_fri`,`reg_sat`,`reg_sun`) values (24,'09:00:00','18:00:00',28,NULL,'23.091299,113.356114',100,3,1,1,1,1,1,1,0),(26,'09:00:00','18:00:00',30,NULL,'39.92019271104446,116.46138668060303',100,3,0,0,0,0,0,0,1),(35,'09:00:00','18:00:00',39,NULL,'23.13667995876736,113.26168185763889',100,3,1,1,1,1,1,1,0),(40,'09:00:00','20:00:00',44,NULL,'23.12581727896623,113.38073715871143',100,3,1,1,1,1,1,1,0),(41,'09:00:00','18:00:00',45,NULL,'31.364864678639254,121.2558126449073',100,3,1,1,1,1,1,1,1);

/*Table structure for table `cwa_remind_repair` */

DROP TABLE IF EXISTS `cwa_remind_repair`;

CREATE TABLE `cwa_remind_repair` (
  `rr_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '提醒补卡表id',
  `rr_staff_id` varchar(50) DEFAULT NULL COMMENT '员工id',
  `rr_date` date DEFAULT NULL COMMENT '补卡日期',
  `rr_detail` varchar(50) DEFAULT NULL COMMENT '补卡详细信息',
  `rr_see` int(1) DEFAULT '0' COMMENT '是否查看(1是，0不是)',
  PRIMARY KEY (`rr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_remind_repair` */

insert  into `cwa_remind_repair`(`rr_id`,`rr_staff_id`,`rr_date`,`rr_detail`,`rr_see`) values (2,'1','2020-05-05','',1),(3,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-08','上午迟到 ',1),(4,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-06','上午没打卡 下午没打卡 ',0),(5,'1','2020-05-07','上午迟到 ',1),(6,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-07','下午没打卡 ',0),(7,'oT4JJ5BWeT1mPdboQb-OWnwAK-rk','2020-05-08','下午没打卡 ',0),(8,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-08','上午没打卡 下午没打卡 ',0),(9,'1','2020-05-09','上午迟到 ',1),(10,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-10','上午没打卡 下午没打卡 ',0),(11,'1','2020-05-11','上午没打卡 下午没打卡 ',1),(12,'2','2020-05-11','上午没打卡 下午没打卡 ',1),(13,'3','2020-05-11','上午没打卡 下午没打卡 ',1),(14,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-11','上午没打卡 下午没打卡 ',0),(15,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-11','上午没打卡 下午没打卡 ',1),(16,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-11','上午没打卡 下午没打卡 ',0),(17,'1','2020-05-12','上午没打卡 下午没打卡 ',1),(18,'2','2020-05-12','上午没打卡 下午没打卡 ',1),(19,'3','2020-05-12','上午没打卡 下午没打卡 ',1),(20,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-12','上午没打卡 下午没打卡 ',0),(21,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-12','上午没打卡 下午没打卡 ',1),(22,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-12','上午没打卡 下午没打卡 ',0),(23,'1','2020-05-13','上午没打卡 下午没打卡 ',1),(24,'2','2020-05-13','上午没打卡 下午没打卡 ',1),(25,'3','2020-05-13','上午没打卡 下午没打卡 ',1),(26,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-13','上午没打卡 下午没打卡 ',0),(27,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-13','上午没打卡 下午没打卡 ',1),(28,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-13','上午没打卡 下午没打卡 ',0),(29,'1','2020-05-14','上午没打卡 下午没打卡 ',1),(30,'2','2020-05-14','上午没打卡 下午没打卡 ',1),(31,'3','2020-05-14','上午没打卡 下午没打卡 ',1),(32,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-14','上午没打卡 下午没打卡 ',0),(33,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-14','上午没打卡 下午没打卡 ',1),(34,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-14','上午没打卡 下午没打卡 ',0),(35,'1','2020-05-15','上午没打卡 下午没打卡 ',1),(36,'2','2020-05-15','上午没打卡 下午没打卡 ',1),(37,'3','2020-05-15','上午没打卡 下午没打卡 ',1),(38,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-15','上午没打卡 下午没打卡 ',0),(39,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-15','上午迟到 下午没打卡 ',1),(40,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-15','上午没打卡 下午没打卡 ',0),(41,'1','2020-05-16','上午没打卡 下午没打卡 ',1),(42,'2','2020-05-16','上午没打卡 下午没打卡 ',1),(43,'3','2020-05-16','上午没打卡 下午没打卡 ',1),(44,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-16','上午没打卡 下午没打卡 ',0),(45,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-16','上午没打卡 下午没打卡 ',1),(46,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-16','上午没打卡 下午没打卡 ',0),(47,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-17','上午没打卡 下午没打卡 ',0),(48,'1','2020-05-18','上午没打卡 下午没打卡 ',1),(49,'2','2020-05-18','上午没打卡 下午没打卡 ',1),(50,'3','2020-05-18','上午没打卡 下午没打卡 ',1),(51,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-18','上午没打卡 下午没打卡 ',0),(52,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-18','上午迟到 ',1),(53,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-18','上午没打卡 下午没打卡 ',0),(54,'2','2020-05-19','上午迟到 下午早退 ',0),(55,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-19','上午没打卡 下午没打卡 ',0),(56,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-19','上午没打卡 下午没打卡 ',1),(57,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-19','上午没打卡 下午没打卡 ',0),(58,'1','2020-05-20','上午没打卡 下午没打卡 ',0),(59,'2','2020-05-20','上午没打卡 下午没打卡 ',0),(60,'3','2020-05-20','上午没打卡 下午没打卡 ',0),(61,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-20','上午没打卡 下午没打卡 ',0),(62,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-20','上午没打卡 下午没打卡 ',1),(63,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-20','上午没打卡 下午没打卡 ',0),(64,'1','2020-05-21','上午没打卡 下午没打卡 ',0),(65,'2','2020-05-21','上午没打卡 下午没打卡 ',0),(66,'3','2020-05-21','上午没打卡 下午没打卡 ',0),(67,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-21','上午没打卡 下午没打卡 ',0),(68,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-21','上午没打卡 下午没打卡 ',1),(69,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-21','上午没打卡 下午没打卡 ',0),(70,'1','2020-05-22','上午没打卡 下午没打卡 ',0),(71,'2','2020-05-22','上午没打卡 下午没打卡 ',0),(72,'3','2020-05-22','上午没打卡 下午没打卡 ',0),(73,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-22','上午没打卡 下午没打卡 ',0),(74,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-22','上午没打卡 下午没打卡 ',1),(75,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-22','上午没打卡 下午没打卡 ',0),(76,'1','2020-05-23','上午没打卡 下午没打卡 ',0),(77,'2','2020-05-23','上午没打卡 下午没打卡 ',0),(78,'3','2020-05-23','上午没打卡 下午没打卡 ',0),(79,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-23','上午没打卡 下午没打卡 ',0),(80,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-23','上午迟到 下午没打卡 ',1),(81,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-23','上午没打卡 下午没打卡 ',0),(82,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-24','上午没打卡 下午没打卡 ',0),(83,'1','2020-05-25','上午没打卡 下午没打卡 ',0),(84,'2','2020-05-25','上午没打卡 下午没打卡 ',0),(85,'3','2020-05-25','上午没打卡 下午没打卡 ',0),(86,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-25','上午没打卡 下午没打卡 ',0),(87,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-25','上午没打卡 下午没打卡 ',1),(88,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-25','上午没打卡 下午没打卡 ',0),(89,'1','2020-05-26','上午没打卡 下午没打卡 ',0),(90,'2','2020-05-26','上午没打卡 下午没打卡 ',0),(91,'3','2020-05-26','上午没打卡 下午没打卡 ',0),(92,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-26','上午没打卡 下午没打卡 ',0),(93,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-26','上午没打卡 下午没打卡 ',1),(94,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-26','上午没打卡 下午没打卡 ',0),(95,'1','2020-05-27','上午没打卡 下午没打卡 ',0),(96,'2','2020-05-27','上午没打卡 下午没打卡 ',0),(97,'3','2020-05-27','上午没打卡 下午没打卡 ',0),(98,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-27','上午没打卡 下午没打卡 ',0),(99,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-27','上午没打卡 下午没打卡 ',1),(100,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-27','上午没打卡 下午没打卡 ',0),(101,'1','2020-05-28','上午没打卡 下午没打卡 ',0),(102,'2','2020-05-28','上午没打卡 下午没打卡 ',0),(103,'3','2020-05-28','上午没打卡 下午没打卡 ',0),(104,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-28','上午没打卡 下午没打卡 ',0),(105,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-28','上午没打卡 下午没打卡 ',1),(106,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-28','上午没打卡 下午没打卡 ',0),(107,'1','2020-05-29','上午没打卡 下午没打卡 ',0),(108,'2','2020-05-29','上午没打卡 下午没打卡 ',0),(109,'3','2020-05-29','上午没打卡 下午没打卡 ',0),(110,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-29','上午没打卡 下午没打卡 ',0),(111,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-29','上午没打卡 下午没打卡 ',0),(112,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-29','上午没打卡 下午没打卡 ',0),(113,'1','2020-05-30','上午没打卡 下午没打卡 ',0),(114,'2','2020-05-30','上午没打卡 下午没打卡 ',0),(115,'3','2020-05-30','上午没打卡 下午没打卡 ',0),(116,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-05-30','上午没打卡 下午没打卡 ',0),(117,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-05-30','上午没打卡 下午没打卡 ',0),(118,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-05-30','上午没打卡 下午没打卡 ',0),(119,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-05-31','上午没打卡 下午没打卡 ',0),(120,'1','2020-06-01','上午没打卡 下午没打卡 ',0),(121,'2','2020-06-01','上午没打卡 下午没打卡 ',0),(122,'3','2020-06-01','上午没打卡 下午没打卡 ',0),(123,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-01','上午没打卡 下午没打卡 ',0),(124,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-01','上午没打卡 下午没打卡 ',0),(125,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-01','上午没打卡 下午没打卡 ',0),(126,'1','2020-06-02','上午没打卡 下午没打卡 ',0),(127,'2','2020-06-02','上午没打卡 下午没打卡 ',0),(128,'3','2020-06-02','上午没打卡 下午没打卡 ',0),(129,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-02','上午没打卡 下午没打卡 ',0),(130,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-02','上午没打卡 下午没打卡 ',0),(131,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-02','上午没打卡 下午没打卡 ',0),(132,'1','2020-06-03','上午没打卡 下午没打卡 ',0),(133,'2','2020-06-03','上午没打卡 下午没打卡 ',0),(134,'3','2020-06-03','上午没打卡 下午没打卡 ',0),(135,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-03','上午没打卡 下午没打卡 ',0),(136,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-03','上午没打卡 下午没打卡 ',0),(137,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-03','上午没打卡 下午没打卡 ',0),(138,'1','2020-06-04','上午没打卡 下午没打卡 ',0),(139,'2','2020-06-04','上午没打卡 下午没打卡 ',0),(140,'3','2020-06-04','上午没打卡 下午没打卡 ',0),(141,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-04','上午没打卡 下午没打卡 ',0),(142,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-04','上午没打卡 下午没打卡 ',0),(143,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-04','上午没打卡 下午没打卡 ',0),(144,'1','2020-06-05','上午没打卡 下午没打卡 ',0),(145,'2','2020-06-05','上午没打卡 下午没打卡 ',0),(146,'3','2020-06-05','上午没打卡 下午没打卡 ',0),(147,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-05','上午没打卡 下午没打卡 ',0),(148,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-05','上午没打卡 下午没打卡 ',0),(149,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-05','上午没打卡 下午没打卡 ',0),(150,'1','2020-06-06','上午没打卡 下午没打卡 ',0),(151,'2','2020-06-06','上午没打卡 下午没打卡 ',0),(152,'3','2020-06-06','上午没打卡 下午没打卡 ',0),(153,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-06','上午没打卡 下午没打卡 ',0),(154,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-06','上午没打卡 下午没打卡 ',0),(155,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-06','上午没打卡 下午没打卡 ',0),(156,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-07','上午迟到 ',0),(157,'oT4JJ5LrjX872URooPS6NQ6LtZaw','2020-06-07','上午没打卡 下午没打卡 ',0),(158,'1','2020-06-08','上午没打卡 下午没打卡 ',0),(159,'2','2020-06-08','上午没打卡 下午没打卡 ',0),(160,'3','2020-06-08','上午没打卡 下午没打卡 ',0),(161,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-08','上午没打卡 下午没打卡 ',0),(162,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-08','上午没打卡 下午没打卡 ',0),(163,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-08','上午没打卡 下午没打卡 ',0),(164,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-08','上午没打卡 下午没打卡 ',0),(165,'1','2020-06-09','上午没打卡 下午没打卡 ',0),(166,'2','2020-06-09','上午没打卡 下午没打卡 ',0),(167,'3','2020-06-09','上午没打卡 下午没打卡 ',0),(168,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-09','上午没打卡 下午没打卡 ',0),(169,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-09','上午没打卡 下午没打卡 ',0),(170,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-09','上午没打卡 下午没打卡 ',0),(171,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-09','上午没打卡 下午没打卡 ',0),(172,'1','2020-06-10','上午没打卡 下午没打卡 ',0),(173,'2','2020-06-10','上午没打卡 下午没打卡 ',0),(174,'3','2020-06-10','上午没打卡 下午没打卡 ',0),(175,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-10','上午没打卡 下午没打卡 ',0),(176,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-10','上午没打卡 下午没打卡 ',0),(177,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-10','上午没打卡 下午没打卡 ',0),(178,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-10','上午没打卡 下午没打卡 ',0),(179,'1','2020-06-11','上午没打卡 下午没打卡 ',0),(180,'2','2020-06-11','上午没打卡 下午没打卡 ',0),(181,'3','2020-06-11','上午没打卡 下午没打卡 ',0),(182,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-11','上午没打卡 下午没打卡 ',0),(183,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-11','上午没打卡 下午没打卡 ',0),(184,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-11','上午没打卡 下午没打卡 ',0),(185,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-11','上午没打卡 下午没打卡 ',0),(186,'1','2020-06-12','上午没打卡 下午没打卡 ',0),(187,'2','2020-06-12','上午没打卡 下午没打卡 ',0),(188,'3','2020-06-12','上午没打卡 下午没打卡 ',0),(189,'oT4JJ5E6m4ffG1MVc775rwWiIQUU','2020-06-12','上午没打卡 下午没打卡 ',0),(190,'oT4JJ5L3cOPDhZweW5OfhdikiaHc','2020-06-12','上午没打卡 下午没打卡 ',0),(191,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0','2020-06-12','上午没打卡 下午没打卡 ',0),(192,'oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','2020-06-12','上午没打卡 下午没打卡 ',0);

/*Table structure for table `cwa_repair` */

DROP TABLE IF EXISTS `cwa_repair`;

CREATE TABLE `cwa_repair` (
  `rep_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '补卡id',
  `rep_staff_id` varchar(50) DEFAULT NULL COMMENT '员工id',
  `rep_department_id` bigint(20) DEFAULT NULL COMMENT '部门id',
  `rep_date` date DEFAULT NULL COMMENT '补卡日期',
  `rep_time` time DEFAULT NULL COMMENT '补卡时间',
  `rep_reason` varchar(100) DEFAULT NULL COMMENT '补卡理由',
  `rep_flag` varchar(100) DEFAULT NULL COMMENT '补卡状态',
  `rep_state` varchar(100) DEFAULT NULL COMMENT '上班还是下班补卡',
  `reg_images` text COMMENT '补卡照片',
  `reg_view` varchar(100) DEFAULT NULL COMMENT '领导意见',
  PRIMARY KEY (`rep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_repair` */

insert  into `cwa_repair`(`rep_id`,`rep_staff_id`,`rep_department_id`,`rep_date`,`rep_time`,`rep_reason`,`rep_flag`,`rep_state`,`reg_images`,`reg_view`) values (13,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0',NULL,'2020-05-07','09:00:00','路上堵车了','申请中','上班','https://www.lqlin.xyz/group1/M00/00/00/rBJq3l62xsCAc-k1AABwBs-lBZY610.jpg',NULL),(14,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0',NULL,'2020-05-15','09:00:00','','申请中','上班','https://www.lqlin.xyz/group1/M00/00/00/rBJq3l6_o3OAdoInAAAUXlYu8Mg876.jpg',NULL),(15,'oT4JJ5N5RpUCetRG2NoaPpK3tkG0',NULL,'2020-05-13','20:00:00','','申请中','下班','',NULL);

/*Table structure for table `cwa_role` */

DROP TABLE IF EXISTS `cwa_role`;

CREATE TABLE `cwa_role` (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `r_name` varchar(50) NOT NULL COMMENT '角色名',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `cwa_role` */

insert  into `cwa_role`(`r_id`,`r_name`) values (1,'用户'),(2,'老板'),(3,'管理员'),(4,'员工');

/*Table structure for table `cwa_staff` */

DROP TABLE IF EXISTS `cwa_staff`;

CREATE TABLE `cwa_staff` (
  `s_id` varchar(50) NOT NULL COMMENT '员工id',
  `s_name` varchar(50) DEFAULT NULL COMMENT '员工姓名',
  `s_phone` varchar(50) DEFAULT NULL COMMENT '员工联系方式',
  `s_address` varchar(50) DEFAULT NULL COMMENT '员工地址',
  `s_role_id` bigint(20) DEFAULT '1' COMMENT '员工角色',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cwa_staff` */

insert  into `cwa_staff`(`s_id`,`s_name`,`s_phone`,`s_address`,`s_role_id`) values ('1','李四',NULL,NULL,3),('2','张三',NULL,NULL,4),('3','王五',NULL,NULL,4),('5','张三',NULL,NULL,1),('6','张三',NULL,NULL,1),('fv','张三',NULL,NULL,1),('oT4JJ5Bbp8j6ws1C7VkAJh0uIyg0','王吉泰',NULL,NULL,1),('oT4JJ5BWeT1mPdboQb-OWnwAK-rk','陈碧天',NULL,NULL,1),('oT4JJ5C41blS7y7GQsyecr2jdpHA','林秋平',NULL,NULL,1),('oT4JJ5DaFn-yhNrjAuf1-CR5if9k','2247',NULL,NULL,1),('oT4JJ5DH4BBnEyRSXn_s0Lc2BWB8','妙哉U哉',NULL,NULL,1),('oT4JJ5E6m4ffG1MVc775rwWiIQUU','淡',NULL,NULL,2),('oT4JJ5EFTtmrlbSlirtZBGym8Dy8','戴任发',NULL,NULL,1),('oT4JJ5Fl7u5yh6YNzuWcLZBbU5Sc','王先生',NULL,NULL,1),('oT4JJ5FXfZX550a-0eEWDEPIqD7A','林绍泰',NULL,NULL,1),('oT4JJ5H5r7Ww07Rsf25oEuber8kE','张三','999','广州',1),('oT4JJ5KJ1mh4ne2JWwhC4Nj6Nt-o','王健以',NULL,NULL,1),('oT4JJ5L3cOPDhZweW5OfhdikiaHc','Andy',NULL,NULL,2),('oT4JJ5LMAeYQ4LC_GSJmQ8q0Oev8','京野男爵㍿(ಡωಡ)',NULL,NULL,1),('oT4JJ5LrjX872URooPS6NQ6LtZaw','小倩',NULL,NULL,2),('oT4JJ5N5RpUCetRG2NoaPpK3tkG0','嘿哟','14718840887','广东广州',2),('oT4JJ5NCQm2-Wt5ohVaGQsAvBdxM','呆九',NULL,NULL,2),('oT4JJ5ODNfNC04R6m3GRVSK1KkfQ','张松伶',NULL,NULL,1),('oT4JJ5P-Jr-cFY3xvnXSjpWbkIbA','杜阳吟',NULL,NULL,1),('oT4JJ5PBddqbhzAjI-A0N2dBzTUk','超_越梦想',NULL,NULL,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
