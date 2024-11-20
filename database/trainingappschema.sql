-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2024 at 12:03 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `trainingappschema`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_choice`
--

CREATE TABLE `tbl_choice` (
  `id_choice` int(11) NOT NULL,
  `choice` varchar(150) NOT NULL,
  `tbl_question_id_question` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_choice`
--

INSERT INTO `tbl_choice` (`id_choice`, `choice`, `tbl_question_id_question`) VALUES
(1, 'World class events and hospitality with a personal touch.', 1),
(2, 'To bring people together and have a positive impact on our customers, our team, the environment and the community.', 1),
(3, 'Innovating in catering options', 1),
(4, 'We profit from our relationship with you, your events’ success, your happiness and your guests enjoyment, and your repeat and referral business.', 1),
(5, 'Respect', 2),
(6, 'Teamwork', 2),
(7, 'Integrity', 2),
(8, 'Pride', 2),
(9, 'Treat stakeholders as if they are part of the family.', 3),
(10, 'Cooperatively work with members of your team.', 3),
(11, 'Strive to be the best and constantly improve performance by exceeding expectations.', 3),
(12, 'Integrity, Respect, Pride, Excellence and Teamwork', 4),
(13, 'Integrity, Respect, Consistency, Excellence and Teamwork', 4),
(14, 'Integrity, Humility, Teamwork, Excellence and Perserverance', 4),
(15, 'Integrity, Ambition, Humility, Excellence and Teamwork', 4),
(16, 'Cabernet Sauvignon', 5),
(17, 'Merlot', 5),
(18, 'Shiraz', 5),
(19, 'Pinotage', 5),
(20, 'Yes', 6),
(21, 'No', 6);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_course`
--

CREATE TABLE `tbl_course` (
  `id_course` int(11) NOT NULL,
  `course_name` varchar(45) NOT NULL,
  `course_desc` tinytext NOT NULL,
  `course_content` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_course`
--

INSERT INTO `tbl_course` (`id_course`, `course_name`, `course_desc`, `course_content`) VALUES
(1, 'Core values', 'Details the core values that you should strive to uphold.', 'THE PROLOGUE\n\n Enter Chorus.\n\nCHORUS.\nTwo households, both alike in dignity,\nIn fair Verona, where we lay our scene,\nFrom ancient grudge break to new mutiny,\nWhere civil blood makes civil hands unclean.\nFrom forth the fatal loins of these two foes\nA pair of star-cross’d lovers take their life;\nWhose misadventur’d piteous overthrows\nDoth with their death bury their parents’ strife.\nThe fearful passage of their death-mark’d love,\nAnd the continuance of their parents’ rage,\nWhich, but their children’s end, nought could remove,\nIs now the two hours’ traffic of our stage;\nThe which, if you with patient ears attend,\nWhat here shall miss, our toil shall strive to mend.\n\n [_Exit._]\n\nACT I\n\nSCENE I. A public place.\n\n Enter Sampson and Gregory armed with swords and bucklers.\n\nSAMPSON.\nGregory, on my word, we’ll not carry coals.\n\nGREGORY.\nNo, for then we should be colliers.\n\nSAMPSON.\nI mean, if we be in choler, we’ll draw.\n\nGREGORY.\nAy, while you live, draw your neck out o’ the collar.\n\nSAMPSON.\nI strike quickly, being moved.\n\nGREGORY.\nBut thou art not quickly moved to strike.\n\nSAMPSON.\nA dog of the house of Montague moves me.\n\nGREGORY.\nTo move is to stir; and to be valiant is to stand: therefore, if thou\nart moved, thou runn’st away.\n\nSAMPSON.\nA dog of that house shall move me to stand.\nI will take the wall of any man or maid of Montague’s.\n\nGREGORY.\nThat shows thee a weak slave, for the weakest goes to the wall.'),
(2, 'Wines and liquor', 'A rundown of the alcoholic beverages you will be serving.', 'The preparation of the wines in cask and the bottling take place in the\nlower of the two celliers, a mere lad being enabled, by the aid of the\nmechanism provided, to bottle from six to eight thousand bottles a day.\nA single workman can cork about 4,500 bottles, which a second workman\nsecures with metal agrafes before they are lowered into the cellars. The\nlatter are of two stories, each being divided into three long parallel\ngalleries 20 feet high and 23 feet wide, vaulted with stone and floored\nwith cement. Bordering the endless stacks of bottles are small gutters,\ninto which the wine flows from the exploded bottles. Lofty, well\nventilated, and beautifully cool, the temperature invariably ranging\nfrom 45° to 47° Fahrenheit, these capitally-constructed cellars combine\nall that is required for a champagne establishment of the first class.\nThe breakage has never exceeded 3 per cent., whereas in some old cellars\nwhich the firm formerly occupied in the centre of the city, their\nbreakage on one occasion amounted to ten times this quantity.\n\nAt Fisse, Thirion, and Co.\'s, after the wine has been disgorged and\nliqueured, the corks are secured neither with string nor wire, but a\nspecial metal fastener is employed for the purpose. This consists of a\ntriple-branched agrafe, provided with a kind of hinge. A tiny toy\nneedle-gun suspended to the agrafe is pulled outwards and turned over\nthe top of the bottle, whereupon the fastening becomes instantly\ndisengaged, and anything like trouble, uncleanliness, or annoyance is\nentirely avoided. The operation is so easy that a mere child can open a\nbottle of champagne, secured by this patent fastener, as easily and\nrapidly as a grown-up man.\n\nThe firm of Fisse, Thirion, and Co. succeeded that of Fisse, Fraiquin,\nand Co.--established originally at Reims in 1821--in 1864, when the\nbrand of the house was already well known on the Continent, more\nespecially in Belgium and Holland. Since that time the wines have been\nlargely introduced into England and the United States, and the firm, who\nhave secured medals at many of the recent exhibitions, to-day have\nagents in the English and Dutch Indies and the various European\nsettlements in China. Several descriptions of wine are shipped by the\nhouse, the finest being their dry Cuvée Reservèe and their fragrant\nsoft-tasting Cachet d\'Or.'),
(3, 'Etiquette', 'How to treat guests.', 'Your first step should be, of course, the securing of an introduction.\nIntroductions still play an important part in social intercourse, and\nmany errors are often perpetrated by those ignorant of savoir faire\n(correct form). When introducing a young lady to a stranger for example,\nit is not au fait (correct form) to simply say, Mr. Roe, I want you to\nshake hands with my friend Dorothy. Under the rules of the beau monde\n(correct form) this would probably be done as follows: Dorothy (or Miss\nDoe), shake hands with Mr. Roe. Always give the name of the lady first,\nunless you are introducing some one to the President of the United\nStates, the Archbishop of Canterbury, a member of the nobility above a\nbaron, or a customer. The person who is being introduced then extends\nhis (or her) right ungloved hand and says, Shake. You shake, saying\nat the same time, It\'s warm (cool) for November (May), to which the\nother replies, I\'ll say it is.\n\nThis brings up the interesting question of introducing two people to\neach other, neither of whose names you can remember. This is generally\ndone by saying very quickly to one of the parties, Of course you know\nMiss Unkunkunk. Say the last unk very quickly, so that it sounds like\nany name from Ab to Zinc. You might even sneeze violently. Of course, in\nnine cases out of ten, one of the two people will at once say, I didn\'t\nget the name, at which you laugh, Ha! Ha! Ha! in a carefree manner\nseveral times, saying at the same time, Well, well--so you didn\'t\nget the name--you didn\'t get the name--well, well. If the man still\npersists in wishing to know who it is to whom he is being introduced,\nthe best procedure consists in simply braining him on the spot with a\nclub or convenient slab of paving stone.\n\nThe introduction, in cases where you have no mutual friend to do the\nintroducing, is somewhat more difficult but can generally be arranged as\nfollows:\n\nProcure a few feet of stout manila rope or clothes-line, from any of\nthe better-class hardware stores. Ascertain (from the Social Register,\npreferably) the location of the young lady\'s residence, and go there\non some dark evening about nine o\'clock. Fasten the rope across the\nsidewalk in front of the residence about six inches or a foot from the\nground. Then, with the aid of a match and some kerosene, set fire to\nthe young lady\'s house in several places and retire behind a convenient\ntree. After some time, if she is at home, she will probably be forced to\nrun out of her house to avoid being burned to death. In her excitement\nshe will fail to notice the rope which you have stretched across\nthe sidewalk and will fall. This is your opportunity to obtain an\nintroduction. Stepping up to her and touching your hat politely, you\nsay, in a well modulated voice, I beg your pardon, Miss Doe, but I\ncannot help noticing that you are lying prone on the sidewalk. If she\nis well bred, she will not at first speak to you, as you are a perfect\nstranger. This silence, however, should be your cue to once more tip\nyour hat and remark, I realize, Miss Doe, that I have not had the honor\nof an introduction, but you will admit that you are lying prone on the\nsidewalk. Here is my card--and here is one for Mrs. Doe, your mother.\nAt that you should hand her two plain engraved calling cards, each\ncontaining your name and address. If there are any other ladies in her\nfamily--aunts, grandmothers, et cetera--it is correct to leave cards for\nthem also. Be sure that the cards are clean, as the name on the calling\ncard is generally sufficient for identification purposes without the\naddition of the thumbprint.\n\nWhen she has accepted your cards, she will give you one of hers, after\nwhich it will be perfectly correct for you to assist her to rise from\nthe sidewalk. Do not, however, press your attentions further upon her at\nthis time, but after expressing the proper regret over her misfortune it\nwould be well to bow and retire'),
(4, 'Name of a course', 'Insert description of course here', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(5, 'Course name goes here', 'Insert course description here', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(6, 'Name of a course', 'Description of course', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(7, 'Course with no name', 'Course description goes here', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(8, 'Course name', 'Filler course description', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(9, 'Course name', 'Description', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(10, 'Name', 'Desc', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!'),
(11, 'Title', 'Description of course', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime mollitia,\r\nmolestiae quas vel sint commodi repudiandae consequuntur voluptatum laborum\r\nnumquam blanditiis harum quisquam eius sed odit fugiat iusto fuga praesentium\r\noptio, eaque rerum! Provident similique accusantium nemo autem. Veritatis\r\nobcaecati tenetur iure eius earum ut molestias architecto voluptate aliquam\r\nnihil, eveniet aliquid culpa officia aut! Impedit sit sunt quaerat, odit,\r\ntenetur error, harum nesciunt ipsum debitis quas aliquid. Reprehenderit,\r\nquia. Quo neque error repudiandae fuga? Ipsa laudantium molestias eos \r\nsapiente officiis modi at sunt excepturi expedita sint? Sed quibusdam\r\nrecusandae alias error harum maxime adipisci amet laborum. Perspiciatis \r\nminima nesciunt dolorem! Officiis iure rerum voluptates a cumque velit \r\nquibusdam sed amet tempora. Sit laborum ab, eius fugit doloribus tenetur \r\nfugiat, temporibus enim commodi iusto libero magni deleniti quod quam \r\nconsequuntur! Commodi minima excepturi repudiandae velit hic maxime\r\ndoloremque. Quaerat provident commodi consectetur veniam similique ad \r\nearum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo \r\nfugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores labore \r\nsuscipit quas? Nulla, placeat. Voluptatem quaerat non architecto ab laudantium\r\nmodi minima sunt esse temporibus sint culpa, recusandae aliquam numquam \r\ntotam ratione voluptas quod exercitationem fuga. Possimus quis earum veniam \r\nquasi aliquam eligendi, placeat qui corporis!');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_profile`
--

CREATE TABLE `tbl_profile` (
  `id_profile` int(11) NOT NULL,
  `tests_taken` int(11) DEFAULT NULL,
  `tests_passed` int(11) DEFAULT NULL,
  `tbl_user_id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_profile`
--

INSERT INTO `tbl_profile` (`id_profile`, `tests_taken`, `tests_passed`, `tbl_user_id_user`) VALUES
(1, 3, 2, 1),
(2, 2, 2, 3),
(3, 0, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_question`
--

CREATE TABLE `tbl_question` (
  `id_question` int(10) UNSIGNED NOT NULL,
  `question_asked` tinytext NOT NULL,
  `question_context` text DEFAULT NULL,
  `question_answer` int(11) NOT NULL,
  `tbl_quiz_id_quiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_question`
--

INSERT INTO `tbl_question` (`id_question`, `question_asked`, `question_context`, `question_answer`, `tbl_quiz_id_quiz`) VALUES
(1, 'Which one of these describes our vision?', NULL, 1, 1),
(2, 'Which one is not a part of our core values?', NULL, 8, 1),
(3, 'What does it mean to practise excellence', NULL, 11, 1),
(4, 'Which of these represent our core values.', 'Testing if the context prompt works', 13, 1),
(5, 'Which of these red wine grapes originates in South Africa?', NULL, 19, 2),
(6, 'Is \"sparkling wine\" a synonoum for champagne?', NULL, 21, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_quiz`
--

CREATE TABLE `tbl_quiz` (
  `id_quiz` int(11) NOT NULL,
  `quiz_name` varchar(45) DEFAULT NULL,
  `tbl_course_id_course` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_quiz`
--

INSERT INTO `tbl_quiz` (`id_quiz`, `quiz_name`, `tbl_course_id_course`) VALUES
(1, 'Quiz 1: Core values', 1),
(2, 'Quiz 2: Wines and liquor', 2),
(3, 'Quiz 3: Etiquette', 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_response`
--

CREATE TABLE `tbl_response` (
  `id_response` int(11) NOT NULL,
  `question_response` int(11) DEFAULT NULL,
  `tbl_profile_id_profile` int(11) NOT NULL,
  `tbl_question_id_question` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_response`
--

INSERT INTO `tbl_response` (`id_response`, `question_response`, `tbl_profile_id_profile`, `tbl_question_id_question`) VALUES
(1, 1, 1, 1),
(2, 5, 1, 2),
(3, 11, 1, 3),
(4, 13, 1, 4),
(5, NULL, 1, 5),
(6, NULL, 1, 6),
(7, NULL, 2, 1),
(8, NULL, 2, 2),
(9, NULL, 2, 3),
(10, NULL, 2, 4),
(11, NULL, 2, 5),
(12, NULL, 2, 6);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_score`
--

CREATE TABLE `tbl_score` (
  `id_score` int(11) NOT NULL,
  `quiz_score` int(11) DEFAULT NULL,
  `tbl_profile_id_profile` int(11) NOT NULL,
  `tbl_quiz_id_quiz` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_score`
--

INSERT INTO `tbl_score` (`id_score`, `quiz_score`, `tbl_profile_id_profile`, `tbl_quiz_id_quiz`) VALUES
(1, NULL, 1, 1),
(2, 6, 1, 2),
(3, 6, 1, 3),
(4, 4, 2, 1),
(5, 10, 2, 2),
(6, 14, 2, 3),
(7, 1, 3, 1),
(8, NULL, 3, 2),
(9, 10, 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(24) NOT NULL,
  `password` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `name`, `password`) VALUES
(1, 'John', '123456'),
(2, 'Edward', '123456'),
(3, 'Jacob', '654321');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_choice`
--
ALTER TABLE `tbl_choice`
  ADD PRIMARY KEY (`id_choice`),
  ADD UNIQUE KEY `id_choice_UNIQUE` (`id_choice`),
  ADD KEY `fk_tbl_choice_tbl_question1_idx` (`tbl_question_id_question`);

--
-- Indexes for table `tbl_course`
--
ALTER TABLE `tbl_course`
  ADD PRIMARY KEY (`id_course`),
  ADD UNIQUE KEY `id_course_UNIQUE` (`id_course`);

--
-- Indexes for table `tbl_profile`
--
ALTER TABLE `tbl_profile`
  ADD PRIMARY KEY (`id_profile`),
  ADD UNIQUE KEY `id_profile_UNIQUE` (`id_profile`),
  ADD UNIQUE KEY `tbl_user_id_user_UNIQUE` (`tbl_user_id_user`),
  ADD KEY `fk_tbl_profile_tbl_user1_idx` (`tbl_user_id_user`);

--
-- Indexes for table `tbl_question`
--
ALTER TABLE `tbl_question`
  ADD PRIMARY KEY (`id_question`),
  ADD KEY `fk_tbl_question_tbl_quiz1_idx` (`tbl_quiz_id_quiz`);

--
-- Indexes for table `tbl_quiz`
--
ALTER TABLE `tbl_quiz`
  ADD PRIMARY KEY (`id_quiz`),
  ADD UNIQUE KEY `id_quiz_UNIQUE` (`id_quiz`),
  ADD KEY `fk_tbl_quiz_tbl_course1_idx` (`tbl_course_id_course`);

--
-- Indexes for table `tbl_response`
--
ALTER TABLE `tbl_response`
  ADD PRIMARY KEY (`id_response`),
  ADD UNIQUE KEY `id_response_UNIQUE` (`id_response`),
  ADD KEY `fk_tbl_response_tbl_profile1_idx` (`tbl_profile_id_profile`),
  ADD KEY `fk_tbl_response_tbl_question1_idx` (`tbl_question_id_question`);

--
-- Indexes for table `tbl_score`
--
ALTER TABLE `tbl_score`
  ADD PRIMARY KEY (`id_score`),
  ADD UNIQUE KEY `id_score_UNIQUE` (`id_score`),
  ADD KEY `fk_tbl_score_tbl_profile1_idx` (`tbl_profile_id_profile`),
  ADD KEY `fk_tbl_score_tbl_quiz1_idx` (`tbl_quiz_id_quiz`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `id_user_UNIQUE` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_course`
--
ALTER TABLE `tbl_course`
  MODIFY `id_course` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_choice`
--
ALTER TABLE `tbl_choice`
  ADD CONSTRAINT `fk_tbl_choice_tbl_question1` FOREIGN KEY (`tbl_question_id_question`) REFERENCES `tbl_question` (`id_question`);

--
-- Constraints for table `tbl_profile`
--
ALTER TABLE `tbl_profile`
  ADD CONSTRAINT `fk_tbl_profile_tbl_user1` FOREIGN KEY (`tbl_user_id_user`) REFERENCES `tbl_user` (`id_user`);

--
-- Constraints for table `tbl_question`
--
ALTER TABLE `tbl_question`
  ADD CONSTRAINT `fk_tbl_question_tbl_quiz1` FOREIGN KEY (`tbl_quiz_id_quiz`) REFERENCES `tbl_quiz` (`id_quiz`);

--
-- Constraints for table `tbl_quiz`
--
ALTER TABLE `tbl_quiz`
  ADD CONSTRAINT `fk_tbl_quiz_tbl_course1` FOREIGN KEY (`tbl_course_id_course`) REFERENCES `tbl_course` (`id_course`);

--
-- Constraints for table `tbl_response`
--
ALTER TABLE `tbl_response`
  ADD CONSTRAINT `fk_tbl_response_tbl_profile1` FOREIGN KEY (`tbl_profile_id_profile`) REFERENCES `tbl_profile` (`id_profile`),
  ADD CONSTRAINT `fk_tbl_response_tbl_question1` FOREIGN KEY (`tbl_question_id_question`) REFERENCES `tbl_question` (`id_question`);

--
-- Constraints for table `tbl_score`
--
ALTER TABLE `tbl_score`
  ADD CONSTRAINT `fk_tbl_score_tbl_profile1` FOREIGN KEY (`tbl_profile_id_profile`) REFERENCES `tbl_profile` (`id_profile`),
  ADD CONSTRAINT `fk_tbl_score_tbl_quiz1` FOREIGN KEY (`tbl_quiz_id_quiz`) REFERENCES `tbl_quiz` (`id_quiz`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
