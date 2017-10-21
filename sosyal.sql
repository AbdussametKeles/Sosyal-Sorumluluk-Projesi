-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 21 Eki 2017, 16:59:20
-- Sunucu sürümü: 10.1.28-MariaDB
-- PHP Sürümü: 7.1.10

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
  `kategori_adi` varchar(15) NOT NULL,
  `kategori_atasi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanici`
--

CREATE TABLE `kullanici` (
  `kullanici_id` int(11) NOT NULL,
  `resim` varchar(255) NOT NULL,
  `adi_soyadi` varchar(40) NOT NULL,
  `dogum_tarihi` date NOT NULL,
  `yetki_id` int(11) NOT NULL,
  `memleket_id` tinyint(255) NOT NULL,
  `sifre` char(12) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `telefon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `memleket`
--

CREATE TABLE `memleket` (
  `memleket_id` tinyint(255) NOT NULL,
  `memleket_adi` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `oturum`
--

CREATE TABLE `oturum` (
  `token_id` int(11) NOT NULL,
  `kullanici_id` int(11) NOT NULL,
  `token_string` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `resimler`
--

CREATE TABLE `resimler` (
  `resim_id` int(11) NOT NULL,
  `urun_id` int(11) NOT NULL,
  `resim` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `urunler`
--

CREATE TABLE `urunler` (
  `urun_id` int(11) NOT NULL,
  `kategori_id` int(11) NOT NULL,
  `bagis_tipi` varchar(20) NOT NULL,
  `urun_adi` varchar(40) NOT NULL,
  `urun_konumu` tinyint(255) NOT NULL,
  `urun_aciklamasi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `yetki`
--

CREATE TABLE `yetki` (
  `yetki_id` int(11) NOT NULL,
  `yetki_adi` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD UNIQUE KEY `yetki_id` (`yetki_id`),
  ADD KEY `memleket_id` (`memleket_id`);

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
  ADD KEY `urun_konumu` (`urun_konumu`);

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
  MODIFY `kategori_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `kullanici`
--
ALTER TABLE `kullanici`
  MODIFY `kullanici_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `memleket`
--
ALTER TABLE `memleket`
  MODIFY `memleket_id` tinyint(255) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `oturum`
--
ALTER TABLE `oturum`
  MODIFY `token_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `resimler`
--
ALTER TABLE `resimler`
  MODIFY `resim_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `urunler`
--
ALTER TABLE `urunler`
  MODIFY `urun_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Tablo için AUTO_INCREMENT değeri `yetki`
--
ALTER TABLE `yetki`
  MODIFY `yetki_id` int(11) NOT NULL AUTO_INCREMENT;

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
  ADD CONSTRAINT `urunler_ibfk_2` FOREIGN KEY (`urun_konumu`) REFERENCES `memleket` (`memleket_id`);

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
