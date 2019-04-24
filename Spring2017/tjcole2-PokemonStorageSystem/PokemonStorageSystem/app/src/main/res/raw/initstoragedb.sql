/*
 * Copyright (c) 2017 Tyler Cole.
 *
 * This file is part of PokemonStorageSystem.
 *
 * PokemonStorageSystem is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PokemonStorageSystem is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @author   Tyler Cole    mailto:tjcole2@asu.edu.
 * @version April, 2017
 */

DROP TABLE pokedex;
DROP TABLE pc;
DROP TABLE pokemon;
CREATE TABLE pokedex (
  dexNumber INTEGER PRIMARY KEY,
  speciesName TEXT);
CREATE TABLE pc (
  boxname TEXT,
  boxnumber INTEGER PRIMARY KEY);
CREATE TABLE pokemon (
  boxnumber INTEGER,
  boxposition INTEGER,
  objectstring TEXT);
INSERT INTO pc VALUES
   ('Box 1',1);
   INSERT INTO pc VALUES
   ('Box 2',2);
   INSERT INTO pc VALUES
   ('Box 3',3);
   INSERT INTO pc VALUES
   ('Box 4',4);
   INSERT INTO pc VALUES
   ('Box 5',5);
   INSERT INTO pc VALUES
   ('Box 6',6);
   INSERT INTO pc VALUES
   ('Box 7',7);
   INSERT INTO pc VALUES
   ('Box 8',8);
   INSERT INTO pc VALUES
   ('Box 9',9);
   INSERT INTO pc VALUES
   ('Box 10',10);
   INSERT INTO pc VALUES
   ('Box 11',11);
   INSERT INTO pc VALUES
   ('Box 12',12);
   INSERT INTO pc VALUES
   ('Box 13',13);
   INSERT INTO pc VALUES
   ('Box 14',14);
   INSERT INTO pc VALUES
   ('Box 15',15);
   INSERT INTO pc VALUES
   ('Box 16',16);
   INSERT INTO pc VALUES
   ('Box 17',17);
   INSERT INTO pc VALUES
   ('Box 18',18);
   INSERT INTO pc VALUES
   ('Box 19',19);
   INSERT INTO pc VALUES
   ('Box 20',20);
   INSERT INTO pc VALUES
   ('Box 21',21);
   INSERT INTO pc VALUES
   ('Box 22',22);
   INSERT INTO pc VALUES
   ('Box 23',23);
   INSERT INTO pc VALUES
   ('Box 24',24);
   INSERT INTO pc VALUES
   ('Box 25',25);
   INSERT INTO pc VALUES
   ('Box 26',26);
   INSERT INTO pc VALUES
   ('Box 27',27);
   INSERT INTO pc VALUES
   ('Box 28',28);
   INSERT INTO pc VALUES
   ('Box 29',29);
   INSERT INTO pc VALUES
   ('Box 30',30);
   INSERT INTO pc VALUES
   ('Box 31',31);
   INSERT INTO pc VALUES
   ('Box 32',32);
   
INSERT INTO pokedex VALUES(1,'Bulbasaur');
INSERT INTO pokedex VALUES(2,'Ivysaur');
INSERT INTO pokedex VALUES(3,'Venusaur');
INSERT INTO pokedex VALUES(4,'Charmander');
INSERT INTO pokedex VALUES(5,'Charmeleon');
INSERT INTO pokedex VALUES(6,'Charizard');
INSERT INTO pokedex VALUES(7,'Squirtle');
INSERT INTO pokedex VALUES(8,'Wartortle');
INSERT INTO pokedex VALUES(9,'Blastoise');
INSERT INTO pokedex VALUES(10,'Caterpie');
INSERT INTO pokedex VALUES(11,'Metapod');
INSERT INTO pokedex VALUES(12,'Butterfree');
INSERT INTO pokedex VALUES(13,'Weedle');
INSERT INTO pokedex VALUES(14,'Kakuna');
INSERT INTO pokedex VALUES(15,'Beedrill');
INSERT INTO pokedex VALUES(16,'Pidgey');
INSERT INTO pokedex VALUES(17,'Pidgeotto');
INSERT INTO pokedex VALUES(18,'Pidgeot');
INSERT INTO pokedex VALUES(19,'Rattata');
INSERT INTO pokedex VALUES(20,'Raticate');
INSERT INTO pokedex VALUES(21,'Spearow');
INSERT INTO pokedex VALUES(22,'Fearow');
INSERT INTO pokedex VALUES(23,'Ekans');
INSERT INTO pokedex VALUES(24,'Arbok');
INSERT INTO pokedex VALUES(25,'Pikachu');
INSERT INTO pokedex VALUES(26,'Raichu');
INSERT INTO pokedex VALUES(27,'Sandshrew');
INSERT INTO pokedex VALUES(28,'Sandslash');
INSERT INTO pokedex VALUES(29,'Nidoran♀');
INSERT INTO pokedex VALUES(30,'Nidorina');
INSERT INTO pokedex VALUES(31,'Nidoqueen');
INSERT INTO pokedex VALUES(32,'Nidoran♂');
INSERT INTO pokedex VALUES(33,'Nidorino');
INSERT INTO pokedex VALUES(34,'Nidoking');
INSERT INTO pokedex VALUES(35,'Clefairy');
INSERT INTO pokedex VALUES(36,'Clefable');
INSERT INTO pokedex VALUES(37,'Vulpix');
INSERT INTO pokedex VALUES(38,'Ninetales');
INSERT INTO pokedex VALUES(39,'Jigglypuff');
INSERT INTO pokedex VALUES(40,'Wigglytuff');
INSERT INTO pokedex VALUES(41,'Zubat');
INSERT INTO pokedex VALUES(42,'Golbat');
INSERT INTO pokedex VALUES(43,'Oddish');
INSERT INTO pokedex VALUES(44,'Gloom');
INSERT INTO pokedex VALUES(45,'Vileplume');
INSERT INTO pokedex VALUES(46,'Paras');
INSERT INTO pokedex VALUES(47,'Parasect');
INSERT INTO pokedex VALUES(48,'Venonat');
INSERT INTO pokedex VALUES(49,'Venomoth');
INSERT INTO pokedex VALUES(50,'Diglett');
INSERT INTO pokedex VALUES(51,'Dugtrio');
INSERT INTO pokedex VALUES(52,'Meowth');
INSERT INTO pokedex VALUES(53,'Persian');
INSERT INTO pokedex VALUES(54,'Psyduck');
INSERT INTO pokedex VALUES(55,'Golduck');
INSERT INTO pokedex VALUES(56,'Mankey');
INSERT INTO pokedex VALUES(57,'Primeape');
INSERT INTO pokedex VALUES(58,'Growlithe');
INSERT INTO pokedex VALUES(59,'Arcanine');
INSERT INTO pokedex VALUES(60,'Poliwag');
INSERT INTO pokedex VALUES(61,'Poliwhirl');
INSERT INTO pokedex VALUES(62,'Poliwrath');
INSERT INTO pokedex VALUES(63,'Abra');
INSERT INTO pokedex VALUES(64,'Kadabra');
INSERT INTO pokedex VALUES(65,'Alakazam');
INSERT INTO pokedex VALUES(66,'Machop');
INSERT INTO pokedex VALUES(67,'Machoke');
INSERT INTO pokedex VALUES(68,'Machamp');
INSERT INTO pokedex VALUES(69,'Bellsprout');
INSERT INTO pokedex VALUES(70,'Weepinbell');
INSERT INTO pokedex VALUES(71,'Victreebel');
INSERT INTO pokedex VALUES(72,'Tentacool');
INSERT INTO pokedex VALUES(73,'Tentacruel');
INSERT INTO pokedex VALUES(74,'Geodude');
INSERT INTO pokedex VALUES(75,'Graveler');
INSERT INTO pokedex VALUES(76,'Golem');
INSERT INTO pokedex VALUES(77,'Ponyta');
INSERT INTO pokedex VALUES(78,'Rapidash');
INSERT INTO pokedex VALUES(79,'Slowpoke');
INSERT INTO pokedex VALUES(80,'Slowbro');
INSERT INTO pokedex VALUES(81,'Magnemite');
INSERT INTO pokedex VALUES(82,'Magneton');
INSERT INTO pokedex VALUES(83,'Farfetchd');
INSERT INTO pokedex VALUES(84,'Doduo');
INSERT INTO pokedex VALUES(85,'Dodrio');
INSERT INTO pokedex VALUES(86,'Seel');
INSERT INTO pokedex VALUES(87,'Dewgong');
INSERT INTO pokedex VALUES(88,'Grimer');
INSERT INTO pokedex VALUES(89,'Muk');
INSERT INTO pokedex VALUES(90,'Shellder');
INSERT INTO pokedex VALUES(91,'Cloyster');
INSERT INTO pokedex VALUES(92,'Gastly');
INSERT INTO pokedex VALUES(93,'Haunter');
INSERT INTO pokedex VALUES(94,'Gengar');
INSERT INTO pokedex VALUES(95,'Onix');
INSERT INTO pokedex VALUES(96,'Drowzee');
INSERT INTO pokedex VALUES(97,'Hypno');
INSERT INTO pokedex VALUES(98,'Krabby');
INSERT INTO pokedex VALUES(99,'Kingler');
INSERT INTO pokedex VALUES(100,'Voltorb');
INSERT INTO pokedex VALUES(101,'Electrode');
INSERT INTO pokedex VALUES(102,'Exeggcute');
INSERT INTO pokedex VALUES(103,'Exeggutor');
INSERT INTO pokedex VALUES(104,'Cubone');
INSERT INTO pokedex VALUES(105,'Marowak');
INSERT INTO pokedex VALUES(106,'Hitmonlee');
INSERT INTO pokedex VALUES(107,'Hitmonchan');
INSERT INTO pokedex VALUES(108,'Lickitung');
INSERT INTO pokedex VALUES(109,'Koffing');
INSERT INTO pokedex VALUES(110,'Weezing');
INSERT INTO pokedex VALUES(111,'Rhyhorn');
INSERT INTO pokedex VALUES(112,'Rhydon');
INSERT INTO pokedex VALUES(113,'Chansey');
INSERT INTO pokedex VALUES(114,'Tangela');
INSERT INTO pokedex VALUES(115,'Kangaskhan');
INSERT INTO pokedex VALUES(116,'Horsea');
INSERT INTO pokedex VALUES(117,'Seadra');
INSERT INTO pokedex VALUES(118,'Goldeen');
INSERT INTO pokedex VALUES(119,'Seaking');
INSERT INTO pokedex VALUES(120,'Staryu');
INSERT INTO pokedex VALUES(121,'Starmie');
INSERT INTO pokedex VALUES(122,'Mr. Mime');
INSERT INTO pokedex VALUES(123,'Scyther');
INSERT INTO pokedex VALUES(124,'Jynx');
INSERT INTO pokedex VALUES(125,'Electabuzz');
INSERT INTO pokedex VALUES(126,'Magmar');
INSERT INTO pokedex VALUES(127,'Pinsir');
INSERT INTO pokedex VALUES(128,'Tauros');
INSERT INTO pokedex VALUES(129,'Magikarp');
INSERT INTO pokedex VALUES(130,'Gyarados');
INSERT INTO pokedex VALUES(131,'Lapras');
INSERT INTO pokedex VALUES(132,'Ditto');
INSERT INTO pokedex VALUES(133,'Eevee');
INSERT INTO pokedex VALUES(134,'Vaporeon');
INSERT INTO pokedex VALUES(135,'Jolteon');
INSERT INTO pokedex VALUES(136,'Flareon');
INSERT INTO pokedex VALUES(137,'Porygon');
INSERT INTO pokedex VALUES(138,'Omanyte');
INSERT INTO pokedex VALUES(139,'Omastar');
INSERT INTO pokedex VALUES(140,'Kabuto');
INSERT INTO pokedex VALUES(141,'Kabutops');
INSERT INTO pokedex VALUES(142,'Aerodactyl');
INSERT INTO pokedex VALUES(143,'Snorlax');
INSERT INTO pokedex VALUES(144,'Articuno');
INSERT INTO pokedex VALUES(145,'Zapdos');
INSERT INTO pokedex VALUES(146,'Moltres');
INSERT INTO pokedex VALUES(147,'Dratini');
INSERT INTO pokedex VALUES(148,'Dragonair');
INSERT INTO pokedex VALUES(149,'Dragonite');
INSERT INTO pokedex VALUES(150,'Mewtwo');
INSERT INTO pokedex VALUES(151,'Mew');
INSERT INTO pokedex VALUES(152,'Chikorita');
INSERT INTO pokedex VALUES(153,'Bayleef');
INSERT INTO pokedex VALUES(154,'Meganium');
INSERT INTO pokedex VALUES(155,'Cyndaquil');
INSERT INTO pokedex VALUES(156,'Quilava');
INSERT INTO pokedex VALUES(157,'Typhlosion');
INSERT INTO pokedex VALUES(158,'Totodile');
INSERT INTO pokedex VALUES(159,'Croconaw');
INSERT INTO pokedex VALUES(160,'Feraligatr');
INSERT INTO pokedex VALUES(161,'Sentret');
INSERT INTO pokedex VALUES(162,'Furret');
INSERT INTO pokedex VALUES(163,'Hoothoot');
INSERT INTO pokedex VALUES(164,'Noctowl');
INSERT INTO pokedex VALUES(165,'Ledyba');
INSERT INTO pokedex VALUES(166,'Ledian');
INSERT INTO pokedex VALUES(167,'Spinarak');
INSERT INTO pokedex VALUES(168,'Ariados');
INSERT INTO pokedex VALUES(169,'Crobat');
INSERT INTO pokedex VALUES(170,'Chinchou');
INSERT INTO pokedex VALUES(171,'Lanturn');
INSERT INTO pokedex VALUES(172,'Pichu');
INSERT INTO pokedex VALUES(173,'Cleffa');
INSERT INTO pokedex VALUES(174,'Igglybuff');
INSERT INTO pokedex VALUES(175,'Togepi');
INSERT INTO pokedex VALUES(176,'Togetic');
INSERT INTO pokedex VALUES(177,'Natu');
INSERT INTO pokedex VALUES(178,'Xatu');
INSERT INTO pokedex VALUES(179,'Mareep');
INSERT INTO pokedex VALUES(180,'Flaaffy');
INSERT INTO pokedex VALUES(181,'Ampharos');
INSERT INTO pokedex VALUES(182,'Bellossom');
INSERT INTO pokedex VALUES(183,'Marill');
INSERT INTO pokedex VALUES(184,'Azumarill');
INSERT INTO pokedex VALUES(185,'Sudowoodo');
INSERT INTO pokedex VALUES(186,'Politoed');
INSERT INTO pokedex VALUES(187,'Hoppip');
INSERT INTO pokedex VALUES(188,'Skiploom');
INSERT INTO pokedex VALUES(189,'Jumpluff');
INSERT INTO pokedex VALUES(190,'Aipom');
INSERT INTO pokedex VALUES(191,'Sunkern');
INSERT INTO pokedex VALUES(192,'Sunflora');
INSERT INTO pokedex VALUES(193,'Yanma');
INSERT INTO pokedex VALUES(194,'Wooper');
INSERT INTO pokedex VALUES(195,'Quagsire');
INSERT INTO pokedex VALUES(196,'Espeon');
INSERT INTO pokedex VALUES(197,'Umbreon');
INSERT INTO pokedex VALUES(198,'Murkrow');
INSERT INTO pokedex VALUES(199,'Slowking');
INSERT INTO pokedex VALUES(200,'Misdreavus');
INSERT INTO pokedex VALUES(201,'Unown');
INSERT INTO pokedex VALUES(202,'Wobbuffet');
INSERT INTO pokedex VALUES(203,'Girafarig');
INSERT INTO pokedex VALUES(204,'Pineco');
INSERT INTO pokedex VALUES(205,'Forretress');
INSERT INTO pokedex VALUES(206,'Dunsparce');
INSERT INTO pokedex VALUES(207,'Gligar');
INSERT INTO pokedex VALUES(208,'Steelix');
INSERT INTO pokedex VALUES(209,'Snubbull');
INSERT INTO pokedex VALUES(210,'Granbull');
INSERT INTO pokedex VALUES(211,'Qwilfish');
INSERT INTO pokedex VALUES(212,'Scizor');
INSERT INTO pokedex VALUES(213,'Shuckle');
INSERT INTO pokedex VALUES(214,'Heracross');
INSERT INTO pokedex VALUES(215,'Sneasel');
INSERT INTO pokedex VALUES(216,'Teddiursa');
INSERT INTO pokedex VALUES(217,'Ursaring');
INSERT INTO pokedex VALUES(218,'Slugma');
INSERT INTO pokedex VALUES(219,'Magcargo');
INSERT INTO pokedex VALUES(220,'Swinub');
INSERT INTO pokedex VALUES(221,'Piloswine');
INSERT INTO pokedex VALUES(222,'Corsola');
INSERT INTO pokedex VALUES(223,'Remoraid');
INSERT INTO pokedex VALUES(224,'Octillery');
INSERT INTO pokedex VALUES(225,'Delibird');
INSERT INTO pokedex VALUES(226,'Mantine');
INSERT INTO pokedex VALUES(227,'Skarmory');
INSERT INTO pokedex VALUES(228,'Houndour');
INSERT INTO pokedex VALUES(229,'Houndoom');
INSERT INTO pokedex VALUES(230,'Kingdra');
INSERT INTO pokedex VALUES(231,'Phanpy');
INSERT INTO pokedex VALUES(232,'Donphan');
INSERT INTO pokedex VALUES(233,'Porygon2');
INSERT INTO pokedex VALUES(234,'Stantler');
INSERT INTO pokedex VALUES(235,'Smeargle');
INSERT INTO pokedex VALUES(236,'Tyrogue');
INSERT INTO pokedex VALUES(237,'Hitmontop');
INSERT INTO pokedex VALUES(238,'Smoochum');
INSERT INTO pokedex VALUES(239,'Elekid');
INSERT INTO pokedex VALUES(240,'Magby');
INSERT INTO pokedex VALUES(241,'Miltank');
INSERT INTO pokedex VALUES(242,'Blissey');
INSERT INTO pokedex VALUES(243,'Raikou');
INSERT INTO pokedex VALUES(244,'Entei');
INSERT INTO pokedex VALUES(245,'Suicune');
INSERT INTO pokedex VALUES(246,'Larvitar');
INSERT INTO pokedex VALUES(247,'Pupitar');
INSERT INTO pokedex VALUES(248,'Tyranitar');
INSERT INTO pokedex VALUES(249,'Lugia');
INSERT INTO pokedex VALUES(250,'Ho-Oh');
INSERT INTO pokedex VALUES(251,'Celebi');
INSERT INTO pokedex VALUES(252,'Treecko');
INSERT INTO pokedex VALUES(253,'Grovyle');
INSERT INTO pokedex VALUES(254,'Sceptile');
INSERT INTO pokedex VALUES(255,'Torchic');
INSERT INTO pokedex VALUES(256,'Combusken');
INSERT INTO pokedex VALUES(257,'Blaziken');
INSERT INTO pokedex VALUES(258,'Mudkip');
INSERT INTO pokedex VALUES(259,'Marshtomp');
INSERT INTO pokedex VALUES(260,'Swampert');
INSERT INTO pokedex VALUES(261,'Poochyena');
INSERT INTO pokedex VALUES(262,'Mightyena');
INSERT INTO pokedex VALUES(263,'Zigzagoon');
INSERT INTO pokedex VALUES(264,'Linoone');
INSERT INTO pokedex VALUES(265,'Wurmple');
INSERT INTO pokedex VALUES(266,'Silcoon');
INSERT INTO pokedex VALUES(267,'Beautifly');
INSERT INTO pokedex VALUES(268,'Cascoon');
INSERT INTO pokedex VALUES(269,'Dustox');
INSERT INTO pokedex VALUES(270,'Lotad');
INSERT INTO pokedex VALUES(271,'Lombre');
INSERT INTO pokedex VALUES(272,'Ludicolo');
INSERT INTO pokedex VALUES(273,'Seedot');
INSERT INTO pokedex VALUES(274,'Nuzleaf');
INSERT INTO pokedex VALUES(275,'Shiftry');
INSERT INTO pokedex VALUES(276,'Taillow');
INSERT INTO pokedex VALUES(277,'Swellow');
INSERT INTO pokedex VALUES(278,'Wingull');
INSERT INTO pokedex VALUES(279,'Pelipper');
INSERT INTO pokedex VALUES(280,'Ralts');
INSERT INTO pokedex VALUES(281,'Kirlia');
INSERT INTO pokedex VALUES(282,'Gardevoir');
INSERT INTO pokedex VALUES(283,'Surskit');
INSERT INTO pokedex VALUES(284,'Masquerain');
INSERT INTO pokedex VALUES(285,'Shroomish');
INSERT INTO pokedex VALUES(286,'Breloom');
INSERT INTO pokedex VALUES(287,'Slakoth');
INSERT INTO pokedex VALUES(288,'Vigoroth');
INSERT INTO pokedex VALUES(289,'Slaking');
INSERT INTO pokedex VALUES(290,'Nincada');
INSERT INTO pokedex VALUES(291,'Ninjask');
INSERT INTO pokedex VALUES(292,'Shedinja');
INSERT INTO pokedex VALUES(293,'Whismur');
INSERT INTO pokedex VALUES(294,'Loudred');
INSERT INTO pokedex VALUES(295,'Exploud');
INSERT INTO pokedex VALUES(296,'Makuhita');
INSERT INTO pokedex VALUES(297,'Hariyama');
INSERT INTO pokedex VALUES(298,'Azurill');
INSERT INTO pokedex VALUES(299,'Nosepass');
INSERT INTO pokedex VALUES(300,'Skitty');
INSERT INTO pokedex VALUES(301,'Delcatty');
INSERT INTO pokedex VALUES(302,'Sableye');
INSERT INTO pokedex VALUES(303,'Mawile');
INSERT INTO pokedex VALUES(304,'Aron');
INSERT INTO pokedex VALUES(305,'Lairon');
INSERT INTO pokedex VALUES(306,'Aggron');
INSERT INTO pokedex VALUES(307,'Meditite');
INSERT INTO pokedex VALUES(308,'Medicham');
INSERT INTO pokedex VALUES(309,'Electrike');
INSERT INTO pokedex VALUES(310,'Manectric');
INSERT INTO pokedex VALUES(311,'Plusle');
INSERT INTO pokedex VALUES(312,'Minun');
INSERT INTO pokedex VALUES(313,'Volbeat');
INSERT INTO pokedex VALUES(314,'Illumise');
INSERT INTO pokedex VALUES(315,'Roselia');
INSERT INTO pokedex VALUES(316,'Gulpin');
INSERT INTO pokedex VALUES(317,'Swalot');
INSERT INTO pokedex VALUES(318,'Carvanha');
INSERT INTO pokedex VALUES(319,'Sharpedo');
INSERT INTO pokedex VALUES(320,'Wailmer');
INSERT INTO pokedex VALUES(321,'Wailord');
INSERT INTO pokedex VALUES(322,'Numel');
INSERT INTO pokedex VALUES(323,'Camerupt');
INSERT INTO pokedex VALUES(324,'Torkoal');
INSERT INTO pokedex VALUES(325,'Spoink');
INSERT INTO pokedex VALUES(326,'Grumpig');
INSERT INTO pokedex VALUES(327,'Spinda');
INSERT INTO pokedex VALUES(328,'Trapinch');
INSERT INTO pokedex VALUES(329,'Vibrava');
INSERT INTO pokedex VALUES(330,'Flygon');
INSERT INTO pokedex VALUES(331,'Cacnea');
INSERT INTO pokedex VALUES(332,'Cacturne');
INSERT INTO pokedex VALUES(333,'Swablu');
INSERT INTO pokedex VALUES(334,'Altaria');
INSERT INTO pokedex VALUES(335,'Zangoose');
INSERT INTO pokedex VALUES(336,'Seviper');
INSERT INTO pokedex VALUES(337,'Lunatone');
INSERT INTO pokedex VALUES(338,'Solrock');
INSERT INTO pokedex VALUES(339,'Barboach');
INSERT INTO pokedex VALUES(340,'Whiscash');
INSERT INTO pokedex VALUES(341,'Corphish');
INSERT INTO pokedex VALUES(342,'Crawdaunt');
INSERT INTO pokedex VALUES(343,'Baltoy');
INSERT INTO pokedex VALUES(344,'Claydol');
INSERT INTO pokedex VALUES(345,'Lileep');
INSERT INTO pokedex VALUES(346,'Cradily');
INSERT INTO pokedex VALUES(347,'Anorith');
INSERT INTO pokedex VALUES(348,'Armaldo');
INSERT INTO pokedex VALUES(349,'Feebas');
INSERT INTO pokedex VALUES(350,'Milotic');
INSERT INTO pokedex VALUES(351,'Castform');
INSERT INTO pokedex VALUES(352,'Kecleon');
INSERT INTO pokedex VALUES(353,'Shuppet');
INSERT INTO pokedex VALUES(354,'Banette');
INSERT INTO pokedex VALUES(355,'Duskull');
INSERT INTO pokedex VALUES(356,'Dusclops');
INSERT INTO pokedex VALUES(357,'Tropius');
INSERT INTO pokedex VALUES(358,'Chimecho');
INSERT INTO pokedex VALUES(359,'Absol');
INSERT INTO pokedex VALUES(360,'Wynaut');
INSERT INTO pokedex VALUES(361,'Snorunt');
INSERT INTO pokedex VALUES(362,'Glalie');
INSERT INTO pokedex VALUES(363,'Spheal');
INSERT INTO pokedex VALUES(364,'Sealeo');
INSERT INTO pokedex VALUES(365,'Walrein');
INSERT INTO pokedex VALUES(366,'Clamperl');
INSERT INTO pokedex VALUES(367,'Huntail');
INSERT INTO pokedex VALUES(368,'Gorebyss');
INSERT INTO pokedex VALUES(369,'Relicanth');
INSERT INTO pokedex VALUES(370,'Luvdisc');
INSERT INTO pokedex VALUES(371,'Bagon');
INSERT INTO pokedex VALUES(372,'Shelgon');
INSERT INTO pokedex VALUES(373,'Salamence');
INSERT INTO pokedex VALUES(374,'Beldum');
INSERT INTO pokedex VALUES(375,'Metang');
INSERT INTO pokedex VALUES(376,'Metagross');
INSERT INTO pokedex VALUES(377,'Regirock');
INSERT INTO pokedex VALUES(378,'Regice');
INSERT INTO pokedex VALUES(379,'Registeel');
INSERT INTO pokedex VALUES(380,'Latias');
INSERT INTO pokedex VALUES(381,'Latios');
INSERT INTO pokedex VALUES(382,'Kyogre');
INSERT INTO pokedex VALUES(383,'Groudon');
INSERT INTO pokedex VALUES(384,'Rayquaza');
INSERT INTO pokedex VALUES(385,'Jirachi');
INSERT INTO pokedex VALUES(386,'Deoxys');
INSERT INTO pokedex VALUES(387,'Turtwig');
INSERT INTO pokedex VALUES(388,'Grotle');
INSERT INTO pokedex VALUES(389,'Torterra');
INSERT INTO pokedex VALUES(390,'Chimchar');
INSERT INTO pokedex VALUES(391,'Monferno');
INSERT INTO pokedex VALUES(392,'Infernape');
INSERT INTO pokedex VALUES(393,'Piplup');
INSERT INTO pokedex VALUES(394,'Prinplup');
INSERT INTO pokedex VALUES(395,'Empoleon');
INSERT INTO pokedex VALUES(396,'Starly');
INSERT INTO pokedex VALUES(397,'Staravia');
INSERT INTO pokedex VALUES(398,'Staraptor');
INSERT INTO pokedex VALUES(399,'Bidoof');
INSERT INTO pokedex VALUES(400,'Bibarel');
INSERT INTO pokedex VALUES(401,'Kricketot');
INSERT INTO pokedex VALUES(402,'Kricketune');
INSERT INTO pokedex VALUES(403,'Shinx');
INSERT INTO pokedex VALUES(404,'Luxio');
INSERT INTO pokedex VALUES(405,'Luxray');
INSERT INTO pokedex VALUES(406,'Budew');
INSERT INTO pokedex VALUES(407,'Roserade');
INSERT INTO pokedex VALUES(408,'Cranidos');
INSERT INTO pokedex VALUES(409,'Rampardos');
INSERT INTO pokedex VALUES(410,'Shieldon');
INSERT INTO pokedex VALUES(411,'Bastiodon');
INSERT INTO pokedex VALUES(412,'Burmy');
INSERT INTO pokedex VALUES(413,'Wormadam');
INSERT INTO pokedex VALUES(414,'Mothim');
INSERT INTO pokedex VALUES(415,'Combee');
INSERT INTO pokedex VALUES(416,'Vespiquen');
INSERT INTO pokedex VALUES(417,'Pachirisu');
INSERT INTO pokedex VALUES(418,'Buizel');
INSERT INTO pokedex VALUES(419,'Floatzel');
INSERT INTO pokedex VALUES(420,'Cherubi');
INSERT INTO pokedex VALUES(421,'Cherrim');
INSERT INTO pokedex VALUES(422,'Shellos');
INSERT INTO pokedex VALUES(423,'Gastrodon');
INSERT INTO pokedex VALUES(424,'Ambipom');
INSERT INTO pokedex VALUES(425,'Drifloon');
INSERT INTO pokedex VALUES(426,'Drifblim');
INSERT INTO pokedex VALUES(427,'Buneary');
INSERT INTO pokedex VALUES(428,'Lopunny');
INSERT INTO pokedex VALUES(429,'Mismagius');
INSERT INTO pokedex VALUES(430,'Honchkrow');
INSERT INTO pokedex VALUES(431,'Glameow');
INSERT INTO pokedex VALUES(432,'Purugly');
INSERT INTO pokedex VALUES(433,'Chingling');
INSERT INTO pokedex VALUES(434,'Stunky');
INSERT INTO pokedex VALUES(435,'Skuntank');
INSERT INTO pokedex VALUES(436,'Bronzor');
INSERT INTO pokedex VALUES(437,'Bronzong');
INSERT INTO pokedex VALUES(438,'Bonsly');
INSERT INTO pokedex VALUES(439,'Mime Jr.');
INSERT INTO pokedex VALUES(440,'Happiny');
INSERT INTO pokedex VALUES(441,'Chatot');
INSERT INTO pokedex VALUES(442,'Spiritomb');
INSERT INTO pokedex VALUES(443,'Gible');
INSERT INTO pokedex VALUES(444,'Gabite');
INSERT INTO pokedex VALUES(445,'Garchomp');
INSERT INTO pokedex VALUES(446,'Munchlax');
INSERT INTO pokedex VALUES(447,'Riolu');
INSERT INTO pokedex VALUES(448,'Lucario');
INSERT INTO pokedex VALUES(449,'Hippopotas');
INSERT INTO pokedex VALUES(450,'Hippowdon');
INSERT INTO pokedex VALUES(451,'Skorupi');
INSERT INTO pokedex VALUES(452,'Drapion');
INSERT INTO pokedex VALUES(453,'Croagunk');
INSERT INTO pokedex VALUES(454,'Toxicroak');
INSERT INTO pokedex VALUES(455,'Carnivine');
INSERT INTO pokedex VALUES(456,'Finneon');
INSERT INTO pokedex VALUES(457,'Lumineon');
INSERT INTO pokedex VALUES(458,'Mantyke');
INSERT INTO pokedex VALUES(459,'Snover');
INSERT INTO pokedex VALUES(460,'Abomasnow');
INSERT INTO pokedex VALUES(461,'Weavile');
INSERT INTO pokedex VALUES(462,'Magnezone');
INSERT INTO pokedex VALUES(463,'Lickilicky');
INSERT INTO pokedex VALUES(464,'Rhyperior');
INSERT INTO pokedex VALUES(465,'Tangrowth');
INSERT INTO pokedex VALUES(466,'Electivire');
INSERT INTO pokedex VALUES(467,'Magmortar');
INSERT INTO pokedex VALUES(468,'Togekiss');
INSERT INTO pokedex VALUES(469,'Yanmega');
INSERT INTO pokedex VALUES(470,'Leafeon');
INSERT INTO pokedex VALUES(471,'Glaceon');
INSERT INTO pokedex VALUES(472,'Gliscor');
INSERT INTO pokedex VALUES(473,'Mamoswine');
INSERT INTO pokedex VALUES(474,'Porygon-Z');
INSERT INTO pokedex VALUES(475,'Gallade');
INSERT INTO pokedex VALUES(476,'Probopass');
INSERT INTO pokedex VALUES(477,'Dusknoir');
INSERT INTO pokedex VALUES(478,'Froslass');
INSERT INTO pokedex VALUES(479,'Rotom');
INSERT INTO pokedex VALUES(480,'Uxie');
INSERT INTO pokedex VALUES(481,'Mesprit');
INSERT INTO pokedex VALUES(482,'Azelf');
INSERT INTO pokedex VALUES(483,'Dialga');
INSERT INTO pokedex VALUES(484,'Palkia');
INSERT INTO pokedex VALUES(485,'Heatran');
INSERT INTO pokedex VALUES(486,'Regigigas');
INSERT INTO pokedex VALUES(487,'Giratina');
INSERT INTO pokedex VALUES(488,'Cresselia');
INSERT INTO pokedex VALUES(489,'Phione');
INSERT INTO pokedex VALUES(490,'Manaphy');
INSERT INTO pokedex VALUES(491,'Darkrai');
INSERT INTO pokedex VALUES(492,'Shaymin');
INSERT INTO pokedex VALUES(493,'Arceus');
INSERT INTO pokedex VALUES(494,'Victini');
INSERT INTO pokedex VALUES(495,'Snivy');
INSERT INTO pokedex VALUES(496,'Servine');
INSERT INTO pokedex VALUES(497,'Serperior');
INSERT INTO pokedex VALUES(498,'Tepig');
INSERT INTO pokedex VALUES(499,'Pignite');
INSERT INTO pokedex VALUES(500,'Emboar');
INSERT INTO pokedex VALUES(501,'Oshawott');
INSERT INTO pokedex VALUES(502,'Dewott');
INSERT INTO pokedex VALUES(503,'Samurott');
INSERT INTO pokedex VALUES(504,'Patrat');
INSERT INTO pokedex VALUES(505,'Watchog');
INSERT INTO pokedex VALUES(506,'Lillipup');
INSERT INTO pokedex VALUES(507,'Herdier');
INSERT INTO pokedex VALUES(508,'Stoutland');
INSERT INTO pokedex VALUES(509,'Purrloin');
INSERT INTO pokedex VALUES(510,'Liepard');
INSERT INTO pokedex VALUES(511,'Pansage');
INSERT INTO pokedex VALUES(512,'Simisage');
INSERT INTO pokedex VALUES(513,'Pansear');
INSERT INTO pokedex VALUES(514,'Simisear');
INSERT INTO pokedex VALUES(515,'Panpour');
INSERT INTO pokedex VALUES(516,'Simipour');
INSERT INTO pokedex VALUES(517,'Munna');
INSERT INTO pokedex VALUES(518,'Musharna');
INSERT INTO pokedex VALUES(519,'Pidove');
INSERT INTO pokedex VALUES(520,'Tranquill');
INSERT INTO pokedex VALUES(521,'Unfezant');
INSERT INTO pokedex VALUES(522,'Blitzle');
INSERT INTO pokedex VALUES(523,'Zebstrika');
INSERT INTO pokedex VALUES(524,'Roggenrola');
INSERT INTO pokedex VALUES(525,'Boldore');
INSERT INTO pokedex VALUES(526,'Gigalith');
INSERT INTO pokedex VALUES(527,'Woobat');
INSERT INTO pokedex VALUES(528,'Swoobat');
INSERT INTO pokedex VALUES(529,'Drilbur');
INSERT INTO pokedex VALUES(530,'Excadrill');
INSERT INTO pokedex VALUES(531,'Audino');
INSERT INTO pokedex VALUES(532,'Timburr');
INSERT INTO pokedex VALUES(533,'Gurdurr');
INSERT INTO pokedex VALUES(534,'Conkeldurr');
INSERT INTO pokedex VALUES(535,'Tympole');
INSERT INTO pokedex VALUES(536,'Palpitoad');
INSERT INTO pokedex VALUES(537,'Seismitoad');
INSERT INTO pokedex VALUES(538,'Throh');
INSERT INTO pokedex VALUES(539,'Sawk');
INSERT INTO pokedex VALUES(540,'Sewaddle');
INSERT INTO pokedex VALUES(541,'Swadloon');
INSERT INTO pokedex VALUES(542,'Leavanny');
INSERT INTO pokedex VALUES(543,'Venipede');
INSERT INTO pokedex VALUES(544,'Whirlipede');
INSERT INTO pokedex VALUES(545,'Scolipede');
INSERT INTO pokedex VALUES(546,'Cottonee');
INSERT INTO pokedex VALUES(547,'Whimsicott');
INSERT INTO pokedex VALUES(548,'Petilil');
INSERT INTO pokedex VALUES(549,'Lilligant');
INSERT INTO pokedex VALUES(550,'Basculin');
INSERT INTO pokedex VALUES(551,'Sandile');
INSERT INTO pokedex VALUES(552,'Krokorok');
INSERT INTO pokedex VALUES(553,'Krookodile');
INSERT INTO pokedex VALUES(554,'Darumaka');
INSERT INTO pokedex VALUES(555,'Darmanitan');
INSERT INTO pokedex VALUES(556,'Maractus');
INSERT INTO pokedex VALUES(557,'Dwebble');
INSERT INTO pokedex VALUES(558,'Crustle');
INSERT INTO pokedex VALUES(559,'Scraggy');
INSERT INTO pokedex VALUES(560,'Scrafty');
INSERT INTO pokedex VALUES(561,'Sigilyph');
INSERT INTO pokedex VALUES(562,'Yamask');
INSERT INTO pokedex VALUES(563,'Cofagrigus');
INSERT INTO pokedex VALUES(564,'Tirtouga');
INSERT INTO pokedex VALUES(565,'Carracosta');
INSERT INTO pokedex VALUES(566,'Archen');
INSERT INTO pokedex VALUES(567,'Archeops');
INSERT INTO pokedex VALUES(568,'Trubbish');
INSERT INTO pokedex VALUES(569,'Garbodor');
INSERT INTO pokedex VALUES(570,'Zorua');
INSERT INTO pokedex VALUES(571,'Zoroark');
INSERT INTO pokedex VALUES(572,'Minccino');
INSERT INTO pokedex VALUES(573,'Cinccino');
INSERT INTO pokedex VALUES(574,'Gothita');
INSERT INTO pokedex VALUES(575,'Gothorita');
INSERT INTO pokedex VALUES(576,'Gothitelle');
INSERT INTO pokedex VALUES(577,'Solosis');
INSERT INTO pokedex VALUES(578,'Duosion');
INSERT INTO pokedex VALUES(579,'Reuniclus');
INSERT INTO pokedex VALUES(580,'Ducklett');
INSERT INTO pokedex VALUES(581,'Swanna');
INSERT INTO pokedex VALUES(582,'Vanillite');
INSERT INTO pokedex VALUES(583,'Vanillish');
INSERT INTO pokedex VALUES(584,'Vanilluxe');
INSERT INTO pokedex VALUES(585,'Deerling');
INSERT INTO pokedex VALUES(586,'Sawsbuck');
INSERT INTO pokedex VALUES(587,'Emolga');
INSERT INTO pokedex VALUES(588,'Karrablast');
INSERT INTO pokedex VALUES(589,'Escavalier');
INSERT INTO pokedex VALUES(590,'Foongus');
INSERT INTO pokedex VALUES(591,'Amoonguss');
INSERT INTO pokedex VALUES(592,'Frillish');
INSERT INTO pokedex VALUES(593,'Jellicent');
INSERT INTO pokedex VALUES(594,'Alomomola');
INSERT INTO pokedex VALUES(595,'Joltik');
INSERT INTO pokedex VALUES(596,'Galvantula');
INSERT INTO pokedex VALUES(597,'Ferroseed');
INSERT INTO pokedex VALUES(598,'Ferrothorn');
INSERT INTO pokedex VALUES(599,'Klink');
INSERT INTO pokedex VALUES(600,'Klang');
INSERT INTO pokedex VALUES(601,'Klinklang');
INSERT INTO pokedex VALUES(602,'Tynamo');
INSERT INTO pokedex VALUES(603,'Eelektrik');
INSERT INTO pokedex VALUES(604,'Eelektross');
INSERT INTO pokedex VALUES(605,'Elgyem');
INSERT INTO pokedex VALUES(606,'Beheeyem');
INSERT INTO pokedex VALUES(607,'Litwick');
INSERT INTO pokedex VALUES(608,'Lampent');
INSERT INTO pokedex VALUES(609,'Chandelure');
INSERT INTO pokedex VALUES(610,'Axew');
INSERT INTO pokedex VALUES(611,'Fraxure');
INSERT INTO pokedex VALUES(612,'Haxorus');
INSERT INTO pokedex VALUES(613,'Cubchoo');
INSERT INTO pokedex VALUES(614,'Beartic');
INSERT INTO pokedex VALUES(615,'Cryogonal');
INSERT INTO pokedex VALUES(616,'Shelmet');
INSERT INTO pokedex VALUES(617,'Accelgor');
INSERT INTO pokedex VALUES(618,'Stunfisk');
INSERT INTO pokedex VALUES(619,'Mienfoo');
INSERT INTO pokedex VALUES(620,'Mienshao');
INSERT INTO pokedex VALUES(621,'Druddigon');
INSERT INTO pokedex VALUES(622,'Golett');
INSERT INTO pokedex VALUES(623,'Golurk');
INSERT INTO pokedex VALUES(624,'Pawniard');
INSERT INTO pokedex VALUES(625,'Bisharp');
INSERT INTO pokedex VALUES(626,'Bouffalant');
INSERT INTO pokedex VALUES(627,'Rufflet');
INSERT INTO pokedex VALUES(628,'Braviary');
INSERT INTO pokedex VALUES(629,'Vullaby');
INSERT INTO pokedex VALUES(630,'Mandibuzz');
INSERT INTO pokedex VALUES(631,'Heatmor');
INSERT INTO pokedex VALUES(632,'Durant');
INSERT INTO pokedex VALUES(633,'Deino');
INSERT INTO pokedex VALUES(634,'Zweilous');
INSERT INTO pokedex VALUES(635,'Hydreigon');
INSERT INTO pokedex VALUES(636,'Larvesta');
INSERT INTO pokedex VALUES(637,'Volcarona');
INSERT INTO pokedex VALUES(638,'Cobalion');
INSERT INTO pokedex VALUES(639,'Terrakion');
INSERT INTO pokedex VALUES(640,'Virizion');
INSERT INTO pokedex VALUES(641,'Tornadus');
INSERT INTO pokedex VALUES(642,'Thundurus');
INSERT INTO pokedex VALUES(643,'Reshiram');
INSERT INTO pokedex VALUES(644,'Zekrom');
INSERT INTO pokedex VALUES(645,'Landorus');
INSERT INTO pokedex VALUES(646,'Kyurem');
INSERT INTO pokedex VALUES(647,'Keldeo');
INSERT INTO pokedex VALUES(648,'Meloetta');
INSERT INTO pokedex VALUES(649,'Genesect');
INSERT INTO pokedex VALUES(650,'Chespin');
INSERT INTO pokedex VALUES(651,'Quilladin');
INSERT INTO pokedex VALUES(652,'Chesnaught');
INSERT INTO pokedex VALUES(653,'Fennekin');
INSERT INTO pokedex VALUES(654,'Braixen');
INSERT INTO pokedex VALUES(655,'Delphox');
INSERT INTO pokedex VALUES(656,'Froakie');
INSERT INTO pokedex VALUES(657,'Frogadier');
INSERT INTO pokedex VALUES(658,'Greninja');
INSERT INTO pokedex VALUES(659,'Bunnelby');
INSERT INTO pokedex VALUES(660,'Diggersby');
INSERT INTO pokedex VALUES(661,'Fletchling');
INSERT INTO pokedex VALUES(662,'Fletchinder');
INSERT INTO pokedex VALUES(663,'Talonflame');
INSERT INTO pokedex VALUES(664,'Scatterbug');
INSERT INTO pokedex VALUES(665,'Spewpa');
INSERT INTO pokedex VALUES(666,'Vivillon');
INSERT INTO pokedex VALUES(667,'Litleo');
INSERT INTO pokedex VALUES(668,'Pyroar');
INSERT INTO pokedex VALUES(669,'Flabébé');
INSERT INTO pokedex VALUES(670,'Floette');
INSERT INTO pokedex VALUES(671,'Florges');
INSERT INTO pokedex VALUES(672,'Skiddo');
INSERT INTO pokedex VALUES(673,'Gogoat');
INSERT INTO pokedex VALUES(674,'Pancham');
INSERT INTO pokedex VALUES(675,'Pangoro');
INSERT INTO pokedex VALUES(676,'Furfrou');
INSERT INTO pokedex VALUES(677,'Espurr');
INSERT INTO pokedex VALUES(678,'Meowstic');
INSERT INTO pokedex VALUES(679,'Honedge');
INSERT INTO pokedex VALUES(680,'Doublade');
INSERT INTO pokedex VALUES(681,'Aegislash');
INSERT INTO pokedex VALUES(682,'Spritzee');
INSERT INTO pokedex VALUES(683,'Aromatisse');
INSERT INTO pokedex VALUES(684,'Swirlix');
INSERT INTO pokedex VALUES(685,'Slurpuff');
INSERT INTO pokedex VALUES(686,'Inkay');
INSERT INTO pokedex VALUES(687,'Malamar');
INSERT INTO pokedex VALUES(688,'Binacle');
INSERT INTO pokedex VALUES(689,'Barbaracle');
INSERT INTO pokedex VALUES(690,'Skrelp');
INSERT INTO pokedex VALUES(691,'Dragalge');
INSERT INTO pokedex VALUES(692,'Clauncher');
INSERT INTO pokedex VALUES(693,'Clawitzer');
INSERT INTO pokedex VALUES(694,'Helioptile');
INSERT INTO pokedex VALUES(695,'Heliolisk');
INSERT INTO pokedex VALUES(696,'Tyrunt');
INSERT INTO pokedex VALUES(697,'Tyrantrum');
INSERT INTO pokedex VALUES(698,'Amaura');
INSERT INTO pokedex VALUES(699,'Aurorus');
INSERT INTO pokedex VALUES(700,'Sylveon');
INSERT INTO pokedex VALUES(701,'Hawlucha');
INSERT INTO pokedex VALUES(702,'Dedenne');
INSERT INTO pokedex VALUES(703,'Carbink');
INSERT INTO pokedex VALUES(704,'Goomy');
INSERT INTO pokedex VALUES(705,'Sliggoo');
INSERT INTO pokedex VALUES(706,'Goodra');
INSERT INTO pokedex VALUES(707,'Klefki');
INSERT INTO pokedex VALUES(708,'Phantump');
INSERT INTO pokedex VALUES(709,'Trevenant');
INSERT INTO pokedex VALUES(710,'Pumpkaboo');
INSERT INTO pokedex VALUES(711,'Gourgeist');
INSERT INTO pokedex VALUES(712,'Bergmite');
INSERT INTO pokedex VALUES(713,'Avalugg');
INSERT INTO pokedex VALUES(714,'Noibat');
INSERT INTO pokedex VALUES(715,'Noivern');
INSERT INTO pokedex VALUES(716,'Xerneas');
INSERT INTO pokedex VALUES(717,'Yveltal');
INSERT INTO pokedex VALUES(718,'Zygarde');
INSERT INTO pokedex VALUES(719,'Diancie');
INSERT INTO pokedex VALUES(720,'Hoopa');
INSERT INTO pokedex VALUES(721,'Volcanion');
INSERT INTO pokedex VALUES(722,'Rowlet');
INSERT INTO pokedex VALUES(723,'Dartrix');
INSERT INTO pokedex VALUES(724,'Decidueye');
INSERT INTO pokedex VALUES(725,'Litten');
INSERT INTO pokedex VALUES(726,'Torracat');
INSERT INTO pokedex VALUES(727,'Incineroar');
INSERT INTO pokedex VALUES(728,'Popplio');
INSERT INTO pokedex VALUES(729,'Brionne');
INSERT INTO pokedex VALUES(730,'Primarina');
INSERT INTO pokedex VALUES(731,'Pikipek');
INSERT INTO pokedex VALUES(732,'Trumbeak');
INSERT INTO pokedex VALUES(733,'Toucannon');
INSERT INTO pokedex VALUES(734,'Yungoos');
INSERT INTO pokedex VALUES(735,'Gumshoos');
INSERT INTO pokedex VALUES(736,'Grubbin');
INSERT INTO pokedex VALUES(737,'Charjabug');
INSERT INTO pokedex VALUES(738,'Vikavolt');
INSERT INTO pokedex VALUES(739,'Crabrawler');
INSERT INTO pokedex VALUES(740,'Crabominable');
INSERT INTO pokedex VALUES(741,'Oricorio');
INSERT INTO pokedex VALUES(742,'Cutiefly');
INSERT INTO pokedex VALUES(743,'Ribombee');
INSERT INTO pokedex VALUES(744,'Rockruff');
INSERT INTO pokedex VALUES(745,'Lycanroc');
INSERT INTO pokedex VALUES(746,'Wishiwashi');
INSERT INTO pokedex VALUES(747,'Mareanie');
INSERT INTO pokedex VALUES(748,'Toxapex');
INSERT INTO pokedex VALUES(749,'Mudbray');
INSERT INTO pokedex VALUES(750,'Mudsdale');
INSERT INTO pokedex VALUES(751,'Dewpider');
INSERT INTO pokedex VALUES(752,'Araquanid');
INSERT INTO pokedex VALUES(753,'Fomantis');
INSERT INTO pokedex VALUES(754,'Lurantis');
INSERT INTO pokedex VALUES(755,'Morelull');
INSERT INTO pokedex VALUES(756,'Shiinotic');
INSERT INTO pokedex VALUES(757,'Salandit');
INSERT INTO pokedex VALUES(758,'Salazzle');
INSERT INTO pokedex VALUES(759,'Stufful');
INSERT INTO pokedex VALUES(760,'Bewear');
INSERT INTO pokedex VALUES(761,'Bounsweet');
INSERT INTO pokedex VALUES(762,'Steenee');
INSERT INTO pokedex VALUES(763,'Tsareena');
INSERT INTO pokedex VALUES(764,'Comfey');
INSERT INTO pokedex VALUES(765,'Oranguru');
INSERT INTO pokedex VALUES(766,'Passimian');
INSERT INTO pokedex VALUES(767,'Wimpod');
INSERT INTO pokedex VALUES(768,'Golisopod');
INSERT INTO pokedex VALUES(769,'Sandygast');
INSERT INTO pokedex VALUES(770,'Palossand');
INSERT INTO pokedex VALUES(771,'Pyukumuku');
INSERT INTO pokedex VALUES(772,'Type: Null');
INSERT INTO pokedex VALUES(773,'Silvally');
INSERT INTO pokedex VALUES(774,'Minior');
INSERT INTO pokedex VALUES(775,'Komala');
INSERT INTO pokedex VALUES(776,'Turtonator');
INSERT INTO pokedex VALUES(777,'Togedemaru');
INSERT INTO pokedex VALUES(778,'Mimikyu');
INSERT INTO pokedex VALUES(779,'Bruxish');
INSERT INTO pokedex VALUES(780,'Drampa');
INSERT INTO pokedex VALUES(781,'Dhelmise');
INSERT INTO pokedex VALUES(782,'Jangmo-o');
INSERT INTO pokedex VALUES(783,'Hakamo-o');
INSERT INTO pokedex VALUES(784,'Kommo-o');
INSERT INTO pokedex VALUES(785,'Tapu Koko');
INSERT INTO pokedex VALUES(786,'Tapu Lele');
INSERT INTO pokedex VALUES(787,'Tapu Bulu');
INSERT INTO pokedex VALUES(788,'Tapu Fini');
INSERT INTO pokedex VALUES(789,'Cosmog');
INSERT INTO pokedex VALUES(790,'Cosmoem');
INSERT INTO pokedex VALUES(791,'Solgaleo');
INSERT INTO pokedex VALUES(792,'Lunala');
INSERT INTO pokedex VALUES(793,'Nihilego');
INSERT INTO pokedex VALUES(794,'Buzzwole');
INSERT INTO pokedex VALUES(795,'Pheromosa');
INSERT INTO pokedex VALUES(796,'Xurkitree');
INSERT INTO pokedex VALUES(797,'Celesteela');
INSERT INTO pokedex VALUES(798,'Kartana');
INSERT INTO pokedex VALUES(799,'Guzzlord');
INSERT INTO pokedex VALUES(800,'Necrozma');
INSERT INTO pokedex VALUES(801,'Magearna');
INSERT INTO pokedex VALUES(802,'Marshadow');

   
   