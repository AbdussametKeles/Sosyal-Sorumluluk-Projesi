-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 30 Kas 2017, 20:59:35
-- Sunucu sürümü: 5.7.20-0ubuntu0.16.04.1-log
-- PHP Sürümü: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `sosyal`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `iletisim`
--

CREATE TABLE `iletisim` (
  `iletisim_id` int(11) NOT NULL,
  `adi_soyadi` varchar(40) NOT NULL,
  `eposta` varchar(40) NOT NULL,
  `konu` varchar(12) NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kategori`
--

CREATE TABLE `kategori` (
  `kategori_id` int(11) NOT NULL,
  `kategori_adi` varchar(60) NOT NULL,
  `kategori_atasi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `kategori`
--

INSERT INTO `kategori` (`kategori_id`, `kategori_adi`, `kategori_atasi`) VALUES
(1, 'Beyaz Eşya', 1),
(2, 'Çamaşır Makinesi', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE `kullanici` (
  `kullanici_id` int(11) NOT NULL,
  `resim` varchar(255) DEFAULT NULL,
  `adi_soyadi` varchar(40) NOT NULL,
  `dogum_tarihi` date NOT NULL,
  `yetki_id` int(11) NOT NULL DEFAULT '1',
  `memleket_id` tinyint(255) NOT NULL,
  `sifre` char(12) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `telefon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `kullanici`
--

INSERT INTO `kullanici` (`kullanici_id`, `resim`, `adi_soyadi`, `dogum_tarihi`, `yetki_id`, `memleket_id`, `sifre`, `mail`, `telefon`) VALUES
(1, NULL, 'Tam Ad', '1996-10-01', 1, 1, 'deneme123', 'deneme@deneme.com', '12341232141'),
(2, NULL, 'ad soyad', '1996-02-21', 1, 1, '13456579', 'sametkelessss@deneme.com', '05412365221'),
(3, NULL, 'Tam Ad', '1996-10-01', 1, 1, 'deneme123', 'deneme@asdasd.com', '12341232141'),
(4, NULL, 'samet keles', '1996-02-21', 1, 1, '13456579', 'deneme@rgfv.com', '0541236521'),
(5, NULL, 'Denemee Ali', '1990-10-10', 1, 1, 'sifresifre', 'denemeee@gmail.com', '55555555555'),
(6, NULL, 'Denemee Ali', '1990-10-10', 1, 1, 'sifresifre', 'denemeeeee@gmail.com', '55555555555'),
(7, NULL, 'apo deneme', '1996-02-21', 1, 1, '13456579', 'apooooo@hotmail.com', '0541236521'),
(8, NULL, 'jsjjsjs', '1996-10-10', 1, 1, '7suusus72', 'sametkelesss@hotmail.com', '04084845424'),
(9, NULL, 'samet keles', '2014-10-25', 1, 1, '813jshsm', 'denemeeeeeee@gmail.com', '05649704513'),
(11, NULL, 'deneme murat', '2001-10-25', 1, 1, 'haysbdkw82', 'deneme.murat@hotmail.com', '04563781094'),
(13, NULL, 'sosyal sorumllik', '2017-11-25', 1, 1, 'jsjsjsk2', 'jsjsnsjs@hotmail.com', '05638068491'),
(14, NULL, 'nurullah kocak', '2020-10-25', 1, 2, 'u27shdoq', 'nurullahkocak@hotmail.com', '05346189463'),
(15, NULL, 'samet keles', '2017-10-21', 1, 4, 'dnek123no', 'sametkeles@hotmail.com', '05346107943'),
(16, NULL, 'samet keles', '2017-11-17', 1, 81, '123456789', 'denemede@gmail.com', '05457289193'),
(17, NULL, 'abdussamet keles', '2004-10-30', 1, 81, '123456789', 'sametkeles@deneme.com', '05635704915'),
(18, NULL, 'nurullah', '2004-10-30', 1, 6, '123456789', 'nurullah@deneme.com', '05673480946'),
(19, NULL, 'deneme', '1996-10-30', 1, 6, '123456789', 'deneme@msn.com', '05315976045'),
(20, NULL, 'samett', '2004-10-30', 1, 6, '123456789', 'samet@deneme.com', '05389481053'),
(21, NULL, 'Tam Ad', '1996-10-01', 1, 1, 'deneme123', 'denemesamet@deneme.com', '12341232141'),
(22, NULL, 'samet', '2004-12-27', 1, 6, '123456789', 'deneme@msnn.com', '05328090856'),
(23, NULL, 'abdussamet keles', '1989-10-24', 1, 1, '123456789', 'osmanaltay@deneme.com', '05320690868'),
(24, NULL, 'deneme', '2017-01-01', 1, 1, '123456789', 'dneme@nurullah.com', '05326090876'),
(25, '5a05c28d16fb4.jpg', 'Tam Ad', '1996-10-01', 1, 1, 'deneme123', 'denemede@live.com', '12341232141'),
(26, '5a06defcc4ddd.jpg', 'deneme', '2010-11-11', 1, 14, '123456789', 'denemedsamet@gmail.com', '05360876948'),
(27, '5a06e0e79424b.jpg', 'deneme samet', '2006-11-11', 1, 81, '123456789', 'abdullahsahinceo@gmail.com', '05326970964'),
(28, '5a13457125f07.jpg', 'samet keles', '2006-11-21', 1, 7, '123456789', 'sametkelesdeneme@gmail.com', '05636940976'),
(29, NULL, 'Nurullah', '1990-12-10', 1, 1, '123456789', 'nurullah@gmail.com', '111111111111'),
(30, NULL, 'samet keles', '1990-10-10', 1, 23, '123456789', 'postman@yahoo.com', '05456326521'),
(31, '5a140e2f15ba4.jpg', 'samet keles', '2006-11-21', 1, 15, '123456789', 'sametkeles@gmail.com', '05326909681'),
(32, NULL, 'Tam Ad', '1996-10-01', 1, 1, 'deneme123', 'sametkeles188@hotmail.com', '12341232141'),
(33, '5a1d8399a41ce.jpg', 'deneme', '1988-11-28', 1, 58, '123456789', 'deneme@samet.com', '05313131382'),
(34, '5a1dae914fb1f.jpg', 'samet keles', '1993-11-28', 1, 41, '123456789', 'eren@derdiyok.com', '05315049678'),
(35, '5a1daed6e9fc3.jpg', 'selcuk inan', '1988-11-28', 1, 35, '123456789', 'selcuk@inan.com', '05316840967'),
(36, '5a1daf74649db.jpg', 'samet keles', '1996-10-01', 1, 1, 'deneme123', 'sametkeles123@hotmail.com', '12341232141'),
(37, '5a1db01ec2659.jpg', 'samet keles', '1990-10-10', 1, 23, '123456789', 'samet@yahoo.com', '05456326521'),
(38, '5a1e998dd5d8b.jpg', 'nurullah', '1995-11-29', 1, 6, '123456789', 'mnk0606@gmail.com', '05326909679');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `memleket`
--

CREATE TABLE `memleket` (
  `memleket_id` tinyint(255) NOT NULL,
  `memleket_adi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `memleket`
--

INSERT INTO `memleket` (`memleket_id`, `memleket_adi`) VALUES
(1, 'Adana'),
(2, 'Adıyaman'),
(3, 'Afyon'),
(4, 'Ağrı'),
(5, 'Amasya'),
(6, 'Ankara'),
(7, 'Antalya'),
(8, 'Artvin'),
(9, 'Aydın'),
(10, 'Balıkesir'),
(11, 'Bilecik'),
(12, 'Bingöl'),
(13, 'Bitlis'),
(14, 'Bolu'),
(15, 'Burdur'),
(16, 'Bursa'),
(17, 'Çanakkale'),
(18, 'Çankırı'),
(19, 'Çorum'),
(20, 'Denizli'),
(21, 'Diyarbakır'),
(22, 'Edirne'),
(23, 'Elazığ'),
(24, 'Erzincan'),
(25, 'Erzurum'),
(26, 'Eskişehir'),
(27, 'Gaziantep'),
(28, 'Giresun'),
(29, 'Gümüşhane'),
(30, 'Hakkari'),
(31, 'Hatay'),
(32, 'Isparta'),
(33, 'İçel(Mersin)'),
(34, 'İstanbul'),
(35, 'İzmir'),
(36, 'Kars'),
(37, 'Kastamonu'),
(38, 'Kayseri'),
(39, 'Kırklareli'),
(40, 'Kırşehir'),
(41, 'Kocaeli'),
(42, 'Konya'),
(43, 'Kütahya'),
(44, 'Malatya'),
(45, 'Manisa'),
(46, 'Kahraman Maraş'),
(47, 'Mardin'),
(48, 'Muğla'),
(49, 'Muş'),
(50, 'Nevşehir'),
(51, 'Niğde'),
(52, 'Ordu'),
(53, 'Rize'),
(54, 'Sakarya'),
(55, 'Samsun'),
(56, 'Siirt'),
(57, 'Sinop'),
(58, 'Sivas'),
(59, 'Tekirdağ'),
(60, 'Tokat'),
(61, 'Trabzon'),
(62, 'Tunceli'),
(63, 'Şanlıurfa'),
(64, 'Uşak'),
(65, 'Van'),
(66, 'Yozgat'),
(67, 'Zonguldak'),
(68, 'Aksaray'),
(69, 'Bayburt'),
(70, 'Karaman'),
(71, 'Kırıkkale'),
(72, 'Batman'),
(73, 'Şırnak'),
(74, 'Bartın'),
(75, 'Ardahan'),
(76, 'Iğdır'),
(77, 'Yalova'),
(78, 'Karabük'),
(79, 'Kilis'),
(80, 'Osmaniye'),
(81, 'Düzce');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `oturum`
--

CREATE TABLE `oturum` (
  `token_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `token_string` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `oturum`
--

INSERT INTO `oturum` (`token_id`, `kullanici_id`, `token_string`) VALUES
(1, 1, '59ef2f3fd002c'),
(2, 2, '59ef303bed54f'),
(3, 3, '59ef31dbcc875'),
(4, 4, '59ef322ebd574'),
(5, 5, '59ef39f9a8212'),
(6, 6, '59ef3d2415233'),
(7, 7, '59ef96b1a0c46'),
(8, 8, '59efb198c0a30'),
(9, 9, '59efb212e276a'),
(10, 11, '59efbf90d1444'),
(11, 13, '59efc22d98487'),
(12, 14, '59f0633936e9e'),
(13, 15, '59f087f7b1eb2'),
(14, 16, '59f46cb7ed648'),
(15, 16, '59f476c8573db'),
(16, 16, '59f4773470f6b'),
(17, 16, '59f47dc73e9f1'),
(18, 16, '59f48657ca805'),
(19, 15, '59f486ed0f859'),
(20, 16, '59f487bfe8af4'),
(21, 16, '59f4893cea78f'),
(22, 16, '59f4f882afb3b'),
(23, 16, '59f4f888577a6'),
(24, 16, '59f5072f037be'),
(25, 16, '59f508bf36462'),
(26, 16, '59f50f6ca942f'),
(27, 16, '59f6f641333e5'),
(28, 16, '59f6f6772a9ef'),
(29, 16, '59f6f6c447922'),
(30, 16, '59f6f721cb851'),
(31, 16, '59f6f7b790808'),
(32, 16, '59f6f805b2f52'),
(33, 16, '59f6f81645b31'),
(34, 16, '59f6f85153e9e'),
(35, 16, '59f6f852ea07d'),
(36, 16, '59f6f85400904'),
(37, 16, '59f6f854e8e5d'),
(38, 16, '59f6f855b33b2'),
(39, 16, '59f6f85677e3a'),
(40, 16, '59f6f85732799'),
(41, 16, '59f6f857e07cd'),
(42, 16, '59f6f86a65358'),
(43, 16, '59f6f87433c15'),
(44, 16, '59f6fa2a8355e'),
(45, 16, '59f6fbbcddb7d'),
(46, 16, '59f6fc3b0e9db'),
(47, 16, '59f6fc56eaf42'),
(48, 16, '59f70eb3e326c'),
(49, 16, '59f70eb9c4177'),
(50, 16, '59f712cbc5866'),
(51, 16, '59f712d9c3bbe'),
(52, 16, '59f712dcc38e7'),
(53, 16, '59f712dd9ec0c'),
(54, 16, '59f712dedc141'),
(55, 16, '59f712e64cb37'),
(56, 16, '59f712e7042e2'),
(57, 16, '59f712e75ed91'),
(58, 16, '59f712e7c93f5'),
(59, 16, '59f712e836167'),
(60, 16, '59f712ef21e7b'),
(61, 16, '59f712f3b8bc8'),
(62, 16, '59f712ff58e8b'),
(63, 16, '59f71300ba6fe'),
(64, 16, '59f71301511f4'),
(65, 16, '59f71326001ea'),
(66, 16, '59f71353ba54b'),
(67, 16, '59f713a88b4af'),
(68, 16, '59f714a2ad6d4'),
(69, 16, '59f714b1d52da'),
(70, 16, '59f719fa5682f'),
(71, 17, '59f71a2acedd8'),
(72, 17, '59f71a2b7883f'),
(73, 18, '59f71aa23fc62'),
(74, 18, '59f71aa291be5'),
(75, 18, '59f71b3625cc8'),
(76, 18, '59f71b4386710'),
(77, 18, '59f71b4de018c'),
(78, 18, '59f75acfd61b7'),
(79, 18, '59f75b0749a9c'),
(80, 16, '59f75b1b684d3'),
(81, 16, '59f75b24039ac'),
(82, 19, '59f75b627244b'),
(83, 19, '59f75b62c8ace'),
(84, 19, '59f75bbe374a6'),
(85, 19, '59f75bccbc4d4'),
(86, 19, '59f75bd79f219'),
(87, 19, '59f75bd872c6b'),
(88, 19, '59f75bd907c09'),
(89, 19, '59f75bd97866e'),
(90, 19, '59f75bd9d84b1'),
(91, 16, '59f75bde7b724'),
(92, 16, '59f75bdf5aa3e'),
(93, 16, '59f75bdfc3e68'),
(94, 16, '59f75be05310d'),
(95, 16, '59f75beef0519'),
(96, 16, '59f75bfd7a57f'),
(97, 16, '59f75bff811ab'),
(98, 16, '59f75c0038549'),
(99, 16, '59f75c0950f1f'),
(100, 16, '59f75c0cded42'),
(101, 16, '59f75c539d57e'),
(102, 16, '59f75c5f6a01a'),
(103, 16, '59f75c60586c8'),
(104, 16, '59f75c60c4ea2'),
(105, 16, '59f7620572be2'),
(106, 16, '59f762589dd4d'),
(107, 16, '59f7625b42a14'),
(108, 16, '59f762851fe5e'),
(109, 16, '59f7628e632df'),
(110, 16, '59f7748293c8f'),
(111, 16, '59f77491659bb'),
(112, 16, '59f774925e549'),
(113, 16, '59f774fe4b892'),
(114, 16, '59f77a1805182'),
(115, 16, '59f77a58a2cec'),
(116, 16, '59f77b0cce45e'),
(117, 16, '59f77b6aca4ce'),
(118, 16, '59f77b8e6500e'),
(119, 16, '59f77bd746a81'),
(120, 16, '59f77c4223b47'),
(121, 16, '59f77c665779a'),
(122, 16, '59f77c8ad0c24'),
(123, 16, '59f77dddd950d'),
(124, 20, '59f77e2eca221'),
(125, 20, '59f77e2f1f481'),
(126, 20, '59f84d28c448f'),
(127, 20, '59f84de59c91e'),
(128, 16, '59f84df98e009'),
(129, 16, '59f84dfb7e354'),
(130, 16, '59f8535ff3001'),
(131, 16, '59f857e317d5e'),
(132, 16, '59f85872be5d9'),
(133, 16, '59f8587531bd3'),
(134, 16, '59f858769f45a'),
(135, 16, '59f85877c0eac'),
(136, 16, '59f85878ab017'),
(137, 16, '59f85879b7064'),
(138, 16, '59f8587b379ff'),
(139, 16, '59f8592b7c933'),
(140, 16, '59f85b2b6c43c'),
(141, 16, '59f85b525e1c0'),
(142, 16, '59f85c06c04de'),
(143, 16, '59f973ba9494a'),
(144, 16, '59f9743e6f9cf'),
(145, 16, '59f9748c70946'),
(146, 16, '59f974cc4b300'),
(147, 16, '59f9755ca32d1'),
(148, 16, '59f976112496e'),
(149, 16, '59f9761c08e9e'),
(150, 21, '59f9775a076e0'),
(151, 22, '59f979cc5704b'),
(152, 22, '59f979cc9fee4'),
(153, 23, '59f97a2722156'),
(154, 23, '59f97a2776b0f'),
(155, 23, '59f97a802bfde'),
(156, 24, '59f97b4799ef3'),
(157, 24, '59f97b47df529'),
(158, 24, '59f97b814b6f2'),
(159, 24, '59f9a6eb6e732'),
(160, 16, '59f9b9e8bcaf7'),
(161, 16, '59f9b9fd13c12'),
(162, 16, '59f9ba92934b6'),
(163, 16, '59f9baee48751'),
(164, 16, '59f9bb25bf4be'),
(165, 16, '59f9be1ae20b8'),
(166, 16, '59f9bee41fd61'),
(167, 16, '59f9bf338e7d7'),
(168, 16, '59f9bfa948958'),
(169, 25, '5a05c28d50fb7'),
(170, 26, '5a06defcd87b8'),
(171, 27, '5a06e0e799ff0'),
(172, 27, '5a06e1075dc7e'),
(173, 27, '5a06e5f2ca141'),
(174, 27, '5a06e66667f96'),
(175, 27, '5a0d6feb7b249'),
(176, 16, '5a0d9f9fce043'),
(177, 16, '5a0da1bb8e984'),
(178, 16, '5a0dc8caf1f04'),
(179, 16, '5a0dc93ed9d8a'),
(180, 16, '5a0eb721b2d5c'),
(181, 16, '5a0eba8a58d17'),
(182, 16, '5a0ebb03130ca'),
(183, 16, '5a0ebcc794103'),
(184, 16, '5a0ebd70e7a1e'),
(185, 16, '5a0ebdcc57273'),
(186, 16, '5a0ebe51ce1ce'),
(187, 16, '5a0ebe8be4e6f'),
(188, 16, '5a0ebf6e708ca'),
(189, 16, '5a0ec0c7718f8'),
(190, 16, '5a0ec1735fb85'),
(191, 16, '5a0ec2001b3c8'),
(192, 16, '5a0ec278e8184'),
(193, 16, '5a0ec2ae470d3'),
(194, 16, '5a129548bab11'),
(195, 27, '5a12bd418f06f'),
(196, 27, '5a1324ad24ef2'),
(197, 16, '5a133b3b0f2f2'),
(198, 16, '5a133ba222d74'),
(199, 16, '5a133d1d66769'),
(200, 16, '5a133d81b86c1'),
(201, 16, '5a133da24ac33'),
(202, 16, '5a133dbd0e114'),
(203, 16, '5a133ea875e63'),
(204, 16, '5a133f53103cb'),
(205, 16, '5a133fb791168'),
(206, 16, '5a134030c77a7'),
(207, 16, '5a13405fed827'),
(208, 16, '5a13406d321fa'),
(209, 16, '5a1340b4e0cca'),
(210, 28, '5a1345714386d'),
(211, 16, '5a13471de3041'),
(212, 16, '5a1347325e94b'),
(213, 16, '5a1347aa299dc'),
(214, 16, '5a1347f544bb7'),
(215, 16, '5a134828ae487'),
(216, 16, '5a13485680f54'),
(217, 16, '5a134868c1f85'),
(218, 16, '5a13489a209f3'),
(219, 16, '5a13495ba8a15'),
(220, 16, '5a13f5dfc82f5'),
(221, 16, '5a13f5f38445f'),
(222, 16, '5a13f64287809'),
(223, 16, '5a13f64e17b63'),
(224, 16, '5a13f96254c31'),
(225, 16, '5a13f97948e8c'),
(226, 29, '5a1406aa94550'),
(227, 16, '5a14077487c18'),
(228, 30, '5a140d3409cb3'),
(229, 31, '5a140e2f1f608'),
(230, 31, '5a140e4c1de25'),
(231, 31, '5a1be34b7b2fe'),
(232, 32, '5a1be40415658'),
(233, 31, '5a1beda970767'),
(234, 31, '5a1c337ced729'),
(235, 31, '5a1c491553a2e'),
(236, 31, '5a1c499074bfe'),
(237, 31, '5a1c4a9673805'),
(238, 31, '5a1c4b106dd10'),
(239, 31, '5a1c4bbe52052'),
(240, 31, '5a1c83bc6aba7'),
(241, 31, '5a1c8419a9262'),
(242, 31, '5a1c84f9460f1'),
(243, 31, '5a1c856a5518f'),
(244, 31, '5a1c86712d226'),
(245, 31, '5a1c871c0c732'),
(246, 31, '5a1c87acbff80'),
(247, 31, '5a1c8c6eae490'),
(248, 31, '5a1c8ce5919a7'),
(249, 31, '5a1c8e1b0351d'),
(250, 31, '5a1d08e134cd4'),
(251, 31, '5a1d0a8e0bd8c'),
(252, 31, '5a1d0ae4cbc12'),
(253, 31, '5a1d0ef8c45c6'),
(254, 31, '5a1d104183f66'),
(255, 31, '5a1d112081676'),
(256, 31, '5a1d11672c37d'),
(257, 31, '5a1d1352037f3'),
(258, 31, '5a1d13b4cfdcf'),
(259, 31, '5a1d2fd8bca43'),
(260, 31, '5a1d3013406c6'),
(261, 31, '5a1d304c26f58'),
(262, 31, '5a1d305218aed'),
(263, 31, '5a1d311a01ba3'),
(264, 31, '5a1d31b06f25b'),
(265, 31, '5a1d3208c74e6'),
(266, 31, '5a1d326d18691'),
(267, 31, '5a1d37a55db68'),
(268, 31, '5a1d37f29ed11'),
(269, 31, '5a1d389329dc0'),
(270, 31, '5a1d38e7718c6'),
(271, 31, '5a1d3b36121fa'),
(272, 31, '5a1d3b7933c35'),
(273, 31, '5a1d444f06497'),
(274, 31, '5a1d7c06c6d7c'),
(275, 31, '5a1d7e03eb66f'),
(276, 31, '5a1d8092082fd'),
(277, 31, '5a1d80b93b906'),
(278, 31, '5a1d80e4b458b'),
(279, 31, '5a1d811781a10'),
(280, 31, '5a1d81647fde9'),
(281, 31, '5a1d81a484d10'),
(282, 31, '5a1d820d9ca2c'),
(283, 31, '5a1d833f9b1bc'),
(284, 31, '5a1d83744c829'),
(285, 33, '5a1d8399bea77'),
(286, 33, '5a1d83ac576e6'),
(287, 33, '5a1d84112b6b3'),
(288, 33, '5a1dadca7e3f1'),
(289, 33, '5a1daddfab3cf'),
(290, 33, '5a1dade121c81'),
(291, 33, '5a1dae07e2d58'),
(292, 16, '5a1dae1e0f880'),
(293, 34, '5a1dae91602ec'),
(294, 34, '5a1daea3da550'),
(295, 34, '5a1daea7b6103'),
(296, 34, '5a1daeaaa9e5e'),
(297, 35, '5a1daed704ab6'),
(298, 35, '5a1daee7475e3'),
(299, 33, '5a1daef4f2388'),
(300, 35, '5a1daf1eb1e6f'),
(301, 16, '5a1daf3246d36'),
(302, 36, '5a1daf747f25b'),
(303, 36, '5a1dafa60fe71'),
(304, 16, '5a1dafedec596'),
(305, 37, '5a1db01ede8b7'),
(306, 37, '5a1db05a441f9'),
(307, 33, '5a1db2bca87e0'),
(308, 31, '5a1db2c5bf7fb'),
(309, 37, '5a1dbc73c046e'),
(310, 37, '5a1dc040af14e'),
(311, 37, '5a1dc07967f24'),
(312, 37, '5a1dc085a0cfc'),
(313, 37, '5a1dc120ef831'),
(314, 37, '5a1dc1247292a'),
(315, 37, '5a1dc12feeb11'),
(316, 37, '5a1dc1ce60712'),
(317, 33, '5a1e8e2bd1b19'),
(318, 33, '5a1e8e6aba690'),
(319, 33, '5a1e91771f4e0'),
(320, 33, '5a1e918e8c4bc'),
(321, 25, '5a1e9880f3cfc'),
(322, 33, '5a1e9918d5ca8'),
(323, 33, '5a1e993c2ef7f'),
(324, 38, '5a1e998e04219'),
(325, 38, '5a1e99a80b81c'),
(326, 38, '5a1e99b974161'),
(327, 38, '5a1e99d75c6f7'),
(328, 38, '5a1ea4e6a86d8'),
(329, 38, '5a1ea5217d589'),
(330, 38, '5a1ea86900d86'),
(331, 38, '5a1eafeb5d77d'),
(332, 38, '5a1ee6a451fe8'),
(333, 38, '5a1ee84e22407'),
(334, 38, '5a1ee97e4efd4'),
(335, 38, '5a1eebbf6244b'),
(336, 38, '5a1eebe7ea815'),
(337, 38, '5a1eec91c8d91'),
(338, 38, '5a1eee3d4ff5b'),
(339, 38, '5a1eee9d19174'),
(340, 38, '5a203f577c15a');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `resimler`
--

CREATE TABLE `resimler` (
  `resim_id` int(11) NOT NULL,
  `urun_id` int(11) NOT NULL,
  `resim` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `resimler`
--

INSERT INTO `resimler` (`resim_id`, `urun_id`, `resim`) VALUES
(1, 1, '5a1c7111081d6.jpg'),
(2, 1, '5a1c711143e35.jpg'),
(3, 7, '5a1dc1a5d131e.jpg');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `urun_id` int(11) NOT NULL,
  `kategori_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `bagis_tipi` varchar(20) NOT NULL,
  `urun_adi` varchar(40) NOT NULL,
  `urun_konumu` tinyint(255) NOT NULL,
  `urun_aciklamasi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `urunler`
--

INSERT INTO `urunler` (`urun_id`, `kategori_id`, `kullanici_id`, `bagis_tipi`, `urun_adi`, `urun_konumu`, `urun_aciklamasi`) VALUES
(1, 2, 16, 'Elden Bağış', 'Temiz Çamaşır Makinesi', 1, 'Çamaşır Makinemiz temizdir.'),
(2, 1, 16, 'admin aracılığıyla', 'Araba', 81, 'Araba bağışlanacaktır'),
(3, 1, 16, 'admin aracılığıyla', 'Araba', 81, 'Araba bağışlanacaktır'),
(4, 2, 16, 'el', 'denenr', 20, 'ddvbfcv ffdcc'),
(5, 2, 16, 'el ile', 'baslik', 20, 'icerik kismi bu sekilde olacak'),
(6, 2, 16, 'elle', '?cretsiz Ec E?yalar?', 20, 'Elazig ilinde ogrenciligim bittigi icin e?yalar?n? ihtiyaci olan insanlara\nvermek istiyorum.'),
(7, 2, 7, 'Elden Bağış', 'Az Kullanılmış Çamaşır Makinesi', 1, 'Çamaşır Makinesi temizdir, yaklaşık olarak 3 ay kullanılmıştır. İhtiyaç sahibine bizimle iletişime geçmesi halinde evine kadar getirebiliriz.'),
(8, 2, 16, 'elle', 'anannana', 20, 'babababna');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetki`
--

CREATE TABLE `yetki` (
  `yetki_id` int(11) NOT NULL,
  `yetki_adi` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `yetki`
--

INSERT INTO `yetki` (`yetki_id`, `yetki_adi`) VALUES
(1, 'kullanici'),
(2, 'yonetici');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yorum`
--

CREATE TABLE `yorum` (
  `yorum_id` int(11) NOT NULL,
  `yorum_icerigi` text NOT NULL,
  `urun_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `iletisim`
--
ALTER TABLE `iletisim`
  ADD PRIMARY KEY (`iletisim_id`);

--
-- Tablo için indeksler `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kategori_id`);

--
-- Tablo için indeksler `kullanici`
--
ALTER TABLE `kullanici`
  ADD PRIMARY KEY (`kullanici_id`),
  ADD KEY `memleket_id` (`memleket_id`),
  ADD KEY `kullanici_ibfk_2` (`yetki_id`);

--
-- Tablo için indeksler `memleket`
--
ALTER TABLE `memleket`
  ADD PRIMARY KEY (`memleket_id`);

--
-- Tablo için indeksler `oturum`
--
ALTER TABLE `oturum`
  ADD PRIMARY KEY (`token_id`),
  ADD UNIQUE KEY `token_string` (`token_string`),
  ADD KEY `kullanici_id` (`kullanici_id`);

--
-- Tablo için indeksler `resimler`
--
ALTER TABLE `resimler`
  ADD PRIMARY KEY (`resim_id`),
  ADD KEY `urun_id` (`urun_id`);

--
-- Tablo için indeksler `urunler`
--
ALTER TABLE `urunler`
  ADD PRIMARY KEY (`urun_id`),
  ADD KEY `kategori_id` (`kategori_id`),
  ADD KEY `urun_konumu` (`urun_konumu`),
  ADD KEY `kullanici_id` (`kullanici_id`);

--
-- Tablo için indeksler `yetki`
--
ALTER TABLE `yetki`
  ADD PRIMARY KEY (`yetki_id`);

--
-- Tablo için indeksler `yorum`
--
ALTER TABLE `yorum`
  ADD PRIMARY KEY (`yorum_id`),
  ADD KEY `kullanici_id` (`kullanici_id`),
  ADD KEY `urun_id` (`urun_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `iletisim`
--
ALTER TABLE `iletisim`
  MODIFY `iletisim_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Tablo için AUTO_INCREMENT değeri `kategori`
--
ALTER TABLE `kategori`
  MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `kullanici_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
--
-- Tablo için AUTO_INCREMENT değeri `memleket`
--
ALTER TABLE `memleket`
  MODIFY `memleket_id` tinyint(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=82;
--
-- Tablo için AUTO_INCREMENT değeri `oturum`
--
ALTER TABLE `oturum`
  MODIFY `token_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=341;
--
-- Tablo için AUTO_INCREMENT değeri `resimler`
--
ALTER TABLE `resimler`
  MODIFY `resim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `urun_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Tablo için AUTO_INCREMENT değeri `yetki`
--
ALTER TABLE `yetki`
  MODIFY `yetki_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Tablo için AUTO_INCREMENT değeri `yorum`
--
ALTER TABLE `yorum`
  MODIFY `yorum_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Dökümü yapılmış tablolar için kısıtlamalar
--

--
-- Tablo kısıtlamaları `kullanici`
--
ALTER TABLE `kullanici`
  ADD CONSTRAINT `kullanici_ibfk_1` FOREIGN KEY (`memleket_id`) REFERENCES `memleket` (`memleket_id`),
  ADD CONSTRAINT `kullanici_ibfk_2` FOREIGN KEY (`yetki_id`) REFERENCES `yetki` (`yetki_id`);

--
-- Tablo kısıtlamaları `oturum`
--
ALTER TABLE `oturum`
  ADD CONSTRAINT `oturum_ibfk_1` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`);

--
-- Tablo kısıtlamaları `resimler`
--
ALTER TABLE `resimler`
  ADD CONSTRAINT `resimler_ibfk_1` FOREIGN KEY (`urun_id`) REFERENCES `urunler` (`urun_id`);

--
-- Tablo kısıtlamaları `urunler`
--
ALTER TABLE `urunler`
  ADD CONSTRAINT `urunler_ibfk_1` FOREIGN KEY (`kategori_id`) REFERENCES `kategori` (`kategori_id`),
  ADD CONSTRAINT `urunler_ibfk_2` FOREIGN KEY (`urun_konumu`) REFERENCES `memleket` (`memleket_id`),
  ADD CONSTRAINT `urunler_ibfk_3` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`);

--
-- Tablo kısıtlamaları `yorum`
--
ALTER TABLE `yorum`
  ADD CONSTRAINT `yorum_ibfk_1` FOREIGN KEY (`kullanici_id`) REFERENCES `kullanici` (`kullanici_id`),
  ADD CONSTRAINT `yorum_ibfk_2` FOREIGN KEY (`urun_id`) REFERENCES `urunler` (`urun_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
